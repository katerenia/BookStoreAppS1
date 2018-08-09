package com.example.android.bookstoreapps1.data;

import android.provider.BaseColumns;

public final class BookContract {
    public static abstract class BookEntry implements BaseColumns {

        public static final String TABLE_NAME = "books";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_BOOK_NAME = "Product Name";
        public static final String COLUMN_BOOK_PRICE = "Price";
        public static final String COLUMN_BOOK_QUANTITY = "Quantity";
        public static final String COLUMN_BOOK_SUPPLIER_NAME = "Supplier Name";
        public static final String COLUMN_BOOK_SUPPLIER_PHONE = "Supplier Phone Number";


    }
}
