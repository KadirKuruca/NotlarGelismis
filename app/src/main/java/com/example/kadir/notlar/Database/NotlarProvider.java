package com.example.kadir.notlar.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class NotlarProvider extends ContentProvider {


    //Database ve Tablolar ile ilgili kısım

    SQLiteDatabase db;

    private final static String DATABASE_NAME = "notlar.db";
    private final static int DATABASE_VERSION = 1;
    private final static String NOTLAR_TABLE_NAME = "notlar";

    //Tablo oluşturma sql kodu
    private final static String CREATE_NOTLAR_TABLE = " CREATE TABLE " + NOTLAR_TABLE_NAME+
            " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            " notIcerik TEXT NOT NULL, "+
            " notTarih TEXT, "+
            " tamamlandi INTEGER DEFAULT 0);";

    //Database ve Tablolar ile ilgili kısım

    //ContentProvider

    static final String CONTENT_AUTHORITY = "com.example.kadir.notlar.Database.notlarprovider";
    static final String PATH_NOTLAR = "notlar";

    static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_NOTLAR);

    static final UriMatcher matcher;

    static{
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(CONTENT_AUTHORITY,PATH_NOTLAR,1);
    }

    //ContentProvider

    @Override
    public boolean onCreate() {
        DatabaseHelper helper = new DatabaseHelper(getContext());
        db = helper.getWritableDatabase();

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor = null;
        switch (matcher.match(uri)){

            case 1:
                cursor = db.query(NOTLAR_TABLE_NAME,projection,selection,selectionArgs,sortOrder,null,null);
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        switch (matcher.match(uri)){

            case 1:
                long eklenenSatirId = db.insert(NOTLAR_TABLE_NAME,null,values);
                if(eklenenSatirId > 0)
                {
                    Uri _uri = ContentUris.withAppendedId(CONTENT_URI,eklenenSatirId);
                    return _uri;
                }
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        switch (matcher.match(uri)){

            case 1:

        }

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        return 0;
    }

    //DatabaseHelper
    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_NOTLAR_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + NOTLAR_TABLE_NAME);
            onCreate(db);
        }
    }
    //DatabaseHelper
}
