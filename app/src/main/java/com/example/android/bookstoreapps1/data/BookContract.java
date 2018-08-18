package com.example.android.bookstoreapps1.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class BookContract {

    private BookContract() {}

        public static final String CONTENT_AUTHORITY = "com.example.android.bookstoreapps1";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
        public static final String PATH_BOOKS = "books";
    /**
     * Inner class that defines constant values for the books database table.
     * Each entry in the table represents a single book entry.
     */
    public static final class BookEntry implements BaseColumns {
        /** The content URI to access the book data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOKS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of books.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single book.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;


        public static final String TABLE_NAME = "books";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_BOOK_NAME = "Book_Name";
        public static final String COLUMN_BOOK_PRICE = "Price";
        public static final String COLUMN_BOOK_QUANTITY = "Quantity";
        public static final String COLUMN_BOOK_SUPPLIER_NAME = "Supplier_Name";
        public static final String COLUMN_BOOK_SUPPLIER_PHONE = "Supplier_Phone_Number";


    }
}

