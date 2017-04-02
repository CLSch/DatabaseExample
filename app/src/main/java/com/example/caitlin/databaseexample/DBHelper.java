package com.example.caitlin.databaseexample;

/**
 * Created by Caitlin on 02-04-17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ContactDB.db";
    private static final int DATABASE_VERSION = 1;
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_NUMBER = "number";
    public static final String TABLE = "contactTable";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DB = "CREATE TABLE " + TABLE + "("
                + KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME
                + " TEXT NOT NULL," + KEY_NUMBER + " TEXT NOT NULL)";

        db.execSQL(CREATE_DB);
    }

    /** upgrade database when helper object is made and there is one already */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    // CRUD METHODS

    public void create(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        onUpgrade(db, 1, 1);
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_NUMBER, contact.getNumber());
        db.insert(TABLE, null, values);
        db.close();
    }

    public ArrayList<Contact> read() {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<Contact> contacts = new ArrayList<>();

        String query = "SELECT " + KEY_ID + ", " + KEY_NAME + ", " + KEY_NUMBER + " FROM " + TABLE;
        Cursor cursor = db.rawQuery(query, null);

        // set cursor to the beginning of the database
        if (cursor.moveToFirst()){
            do {
                // add id, done-status and to-do from current row to Todolist
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(KEY_NUMBER));
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                Contact contact = new Contact(name, number, id);
                contacts.add(contact);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contacts;
    }

    // returned of het gelukt is?
    public int update(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_NUMBER, contact.getNumber());

        return db.update(TABLE, values, KEY_ID + " = ? ",
                new String[] { String.valueOf(contact.getID())});
    }

    public void delete(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, " " + KEY_ID + " = ? ", new String[] {String.valueOf(contact.getID())});
        db.close();
    }
}