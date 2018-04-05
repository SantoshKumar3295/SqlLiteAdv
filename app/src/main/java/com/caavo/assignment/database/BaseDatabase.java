package com.caavo.assignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.UUID;

/**
 * Created by santo on 1/4/18.
 */

public class BaseDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Caavo.db";
    private static final int DATABASE_VERSION = 2;

    public static final String PERSON_TABLE_NAME = "Users";
    public static final String STORY_TABLE_NAME = "StoryList";

    public static final String PERSON_COLUMN_UUID = "_id";
    public static final String PERSON_COLUMN_NAME = "name";
    public static final String PERSON_COLUMN_PASSWORD = "password";
    public static final String STORY_CONTENT_COLUMN = "story";
    public static final String STORY_ID_COLUMN = "s_id";
    public static final String STORY_TITLE_COLUMN = "title";


    public BaseDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + PERSON_TABLE_NAME +
                        "(" + PERSON_COLUMN_UUID + " TEXT PRIMARY KEY, " +
                        PERSON_COLUMN_NAME + " TEXT, " +
                        PERSON_COLUMN_PASSWORD + " TEXT) "
        );

        db.execSQL(
                "CREATE TABLE " + STORY_TABLE_NAME +
                        "(" + PERSON_COLUMN_UUID + " TEXT, " +
                        STORY_CONTENT_COLUMN + " TEXT, " + STORY_ID_COLUMN + " TEXT, "
                        + STORY_TITLE_COLUMN + " TEXT, "
                        + " TEXT) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PERSON_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + STORY_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertPerson(String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PERSON_COLUMN_NAME, name);
        contentValues.put(PERSON_COLUMN_PASSWORD, password);
        contentValues.put(PERSON_COLUMN_UUID, UUID.randomUUID().toString());

        db.insert(PERSON_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insertStory(String id, String uuid, String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PERSON_COLUMN_UUID, uuid);
        contentValues.put(STORY_CONTENT_COLUMN, content);
        contentValues.put(STORY_ID_COLUMN, id);
        contentValues.put(STORY_TITLE_COLUMN, title);

        long i = db.insert(STORY_TABLE_NAME, null, contentValues);

        return true;
    }

    public boolean updateStory(String id, String uuid, String content, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STORY_CONTENT_COLUMN, content);

        long i = db.update(STORY_TABLE_NAME, contentValues, STORY_ID_COLUMN+" = ? AND "+PERSON_COLUMN_UUID+" = ? ", new String[] {id, uuid});
        if(i==0){
            insertStory(id, uuid, title, content);
        }
        return true;
    }

    public Cursor getAllStory() {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("SELECT * FROM " + STORY_TABLE_NAME, null);
            return res;
        } catch (Exception e) {
            return null;
        }
    }

    public Cursor getPerson(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + PERSON_TABLE_NAME + " WHERE " +
                PERSON_COLUMN_NAME + " = ? AND "+PERSON_COLUMN_PASSWORD+" = ? ", new String[] {name, password});
        return res;
    }

}
