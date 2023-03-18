package com.example.projetapplicationmobilemarkus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ImageDatabaseHelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "image_database";
        public static final int DATABASE_VERSION = 1;
        public static final String TABLE_NAME = "images";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_IMAGE = "image";

        public ImageDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTable = "CREATE TABLE " + TABLE_NAME + " ( " +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_IMAGE + " BLOB" +
                    ")";
            db.execSQL(createTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

