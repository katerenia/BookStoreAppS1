package com.example.android.bookstoreapps1;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bookstoreapps1.data.BookContract;
import com.example.android.bookstoreapps1.data.BookContract.BookEntry;

public class BookCursorAdapter extends CursorAdapter {

    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_items, parent, false);
    }

    /**
     * This method binds the book data (in the current row pointed to by cursor) to the given
     * list item layout.
     */
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView supplierTextView = (TextView) view.findViewById(R.id.supplier);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        Button buyButton = (Button)view.findViewById(R.id.buy_button);
        LinearLayout listItem = (LinearLayout)view.findViewById((R.id.single_item));

        // Find the columns of book attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_NAME);
        int supplierColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_SUPPLIER_NAME);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_QUANTITY);
        int idColumnIndex = cursor.getColumnIndex(BookContract.BookEntry._ID);

        // Read the book attributes from the Cursor for the current book
        String bookName = cursor.getString(nameColumnIndex);
        String bookSupplier = cursor.getString(supplierColumnIndex);
        Double bookPrice = cursor.getDouble(priceColumnIndex);
        final int bookQuantity = cursor.getInt(quantityColumnIndex);
        final int bookId = cursor.getInt(idColumnIndex);



        // Update the TextViews with the attributes for the current book
        listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditorActivity.class);
                Uri currentInventoryUri = ContentUris.withAppendedId(BookEntry.CONTENT_URI, bookId);
                intent.setData(currentInventoryUri);
                context.startActivity(intent);
            }
        });
        nameTextView.setText(bookName);
        supplierTextView.setText(bookSupplier);
        priceTextView.setText(String.valueOf(bookPrice));
        if (bookQuantity == 0) {
            buyButton.setEnabled(false);
            quantityTextView.setText(R.string.out_of_stock);
        } else {
            buyButton.setEnabled(true);
            quantityTextView.setText(String.valueOf(bookQuantity));
        }
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Stock = bookQuantity - 1;
                if (Stock < 0) {
                    Toast.makeText(context, context.getString(R.string.out_of_stock), Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put(BookEntry.COLUMN_BOOK_QUANTITY, Stock);
                    Uri currentBook = ContentUris.withAppendedId(BookContract.BookEntry.CONTENT_URI, bookId);
                    context.getContentResolver().update(currentBook, values, null, null);
                }
            }
        });
    }
}