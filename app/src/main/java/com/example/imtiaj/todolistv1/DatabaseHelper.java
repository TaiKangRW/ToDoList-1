package com.example.imtiaj.todolistv1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by imtiaj on 4/9/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="toDoList_database";

    public static final int DATABASE_VERSION=1;

    public static final String EVENT_TABLE="eventlist";
    public static final String COLUMN_EVENT_ID="eventId";
    public static final String COLUMN_EVENT_NAME="eventName";
    public static final String COLUMN_EVENT_DATE="eventtargetDate";
    public static final String COLUMN_EVENT_TIME="evenTargetTime";
    public static final String COLUMN_EVENT_AMPM="eventTargetAmPm";

    public static final String COLUMN_EVENT_RATING="eventRating";
    public static final String COLUMN_EVENT_CATEGORY="eventCategory";
    public static final String COLUMN_EVENT_ALARMED="eventAlarmed";
    public static final String COLUMN_EVENT_ALARM_OPTION="eventAlarmOption";




    private final String CREATE_EVENT_TABLE="create table "+EVENT_TABLE+"("+COLUMN_EVENT_ID+" integer primary key autoincrement,"+COLUMN_EVENT_NAME+" text,"+COLUMN_EVENT_DATE+" text, "+COLUMN_EVENT_TIME+" text, "+COLUMN_EVENT_AMPM+" text, "+COLUMN_EVENT_RATING+" text, "+COLUMN_EVENT_CATEGORY+" text ,"+COLUMN_EVENT_ALARMED+" text , "+COLUMN_EVENT_ALARM_OPTION+" text);";
// private final String CREATE_EVENT_TABLE="create table "+EVENT_TABLE+"("+COLUMN_EVENT_ID+" integer primary key autoincrement,"+COLUMN__EVENT_DESTINATION+" text,"+COLUMN_EVENT_FROMTIME+" date,"+COLUMN_EVENT_TOTIME+" date,"+COLUMN_EVENT_BUDGET+" double);";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+EVENT_TABLE+" if exists");
        onCreate(db);

    }
}
