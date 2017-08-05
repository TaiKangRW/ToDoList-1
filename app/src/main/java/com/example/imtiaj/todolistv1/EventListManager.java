package com.example.imtiaj.todolistv1;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by imtiaj on 4/9/17.
 */

public class EventListManager {

    private DatabaseHelper databaseHelper;
    private Context context;



    private EventModel eventModel;
    //private SQLiteDatabase sqLiteDatabase;
    //private EventModel eventModel;

    public EventListManager(Context context) {
        //this.databaseHelper = databaseHelper;
        databaseHelper=new DatabaseHelper(context);

        this.context = context;
    }

    public long addEvent(EventModel eventModel){

        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_EVENT_NAME,eventModel.getEventName());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_DATE,eventModel.getEventTargetDate());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_TIME,eventModel.getEventTargetTime());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_AMPM,eventModel.getEventTargetAmPm());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_ALARM_OPTION,eventModel.geteventAlarmOption());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_CATEGORY,eventModel.getEventCategory());

        contentValues.put(DatabaseHelper.COLUMN_EVENT_RATING,eventModel.getEventRating());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_ALARMED,eventModel.getEventAlarmed());

        long insertedRow=sqLiteDatabase.insert(DatabaseHelper.EVENT_TABLE,null,contentValues);
        sqLiteDatabase.close();
        return insertedRow;
    }






    public ArrayList<EventModel>getAllEvents(){

        ArrayList<EventModel> eventModels=new ArrayList<>();

        SQLiteDatabase sqLiteDatabase=databaseHelper.getReadableDatabase(); //err
        String selectQuery="select * from "+DatabaseHelper.EVENT_TABLE;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                int eventId=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_ID));
                String eventName=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_NAME));
                String eventDate=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_DATE));
                String eventTime=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_TIME));
                String eventAmPm=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_AMPM));


                String eventRating=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_RATING));

                String eventCategory=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_CATEGORY));
                String eventAlarmed=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_ALARMED));
                String eventAlarmOption=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_ALARM_OPTION));

                EventModel eventModel=new EventModel(eventId,eventName,eventDate,eventTime,eventAmPm,eventRating,eventCategory,eventAlarmed,eventAlarmOption);
                eventModels.add(eventModel);
            }while(cursor.moveToNext());

        }
        return eventModels;

    }


    public void deleteEvent(int id){
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        sqLiteDatabase.delete(DatabaseHelper.EVENT_TABLE,DatabaseHelper.COLUMN_EVENT_ID+" =?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();

    }



    public long updateEvent(EventModel eventModel,int id) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.COLUMN_EVENT_NAME,eventModel.getEventName());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_DATE,eventModel.getEventTargetDate());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_TIME,eventModel.getEventTargetTime());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_AMPM,eventModel.getEventTargetAmPm());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_ALARM_OPTION,eventModel.geteventAlarmOption());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_CATEGORY,eventModel.getEventCategory());

        contentValues.put(DatabaseHelper.COLUMN_EVENT_RATING,eventModel.getEventRating());
        contentValues.put(DatabaseHelper.COLUMN_EVENT_ALARMED,eventModel.getEventAlarmed());

        long insertedRow = sqLiteDatabase.update(DatabaseHelper.EVENT_TABLE, contentValues, DatabaseHelper.COLUMN_EVENT_ID + " =? ", new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return insertedRow;

    }



    public EventModel getEventsById(int id) {


        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        String selectQuery = "select * from " + DatabaseHelper.EVENT_TABLE + " where " + DatabaseHelper.COLUMN_EVENT_ID + " = " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {

            String eventName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_NAME));
            String eventDate= cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_DATE));
            String eventTime= cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_TIME));
            String eventAmPm= cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_AMPM));
            String eventAlarmedOrNot= cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_ALARMED));
            String eventAlarmOption= cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_ALARM_OPTION));
            String eventCategory= cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_CATEGORY));
            String eventRating= cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EVENT_RATING));
            eventModel = new EventModel(eventName, eventDate, eventTime, eventAmPm, eventRating, eventCategory, eventAlarmedOrNot, eventAlarmOption);



        }


        return eventModel;

    }



}
/*public class EmployeeManager {
    DatabaseHelper databaseHelper;
    Context context;
    SQLiteDatabase sqLiteDatabase;
    Employee employee;

    public EmployeeManager(Context context) {
        this.context = context;
        databaseHelper=new DatabaseHelper(context);
    }

    public long addEmployee(Employee employee){
        sqLiteDatabase=databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_EMPLOYEE_NAME,employee.getEmpName());
        contentValues.put(DatabaseHelper.COLUMN_EMPLOYEE_DESIGNATION,employee.getEmpDesignation());

        long insertedRow=sqLiteDatabase.insert(DatabaseHelper.EMPLOYEE_TABLE,null,contentValues);
        sqLiteDatabase.close();
        return insertedRow;
    }

    public ArrayList<Employee>getAllEmployee(){

        ArrayList<Employee>employees=new ArrayList<>();
        sqLiteDatabase=databaseHelper.getReadableDatabase();
        String selectQuery="select * from "+DatabaseHelper.EMPLOYEE_TABLE;
        Cursor cursor=sqLiteDatabase.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMPLOYEE_ID));
                String name=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMPLOYEE_NAME));
                String designation=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_EMPLOYEE_DESIGNATION));
                Employee employee=new Employee(id,name,designation);
                employees.add(employee);
            }while(cursor.moveToNext());

        }
        return employees;

    }

*/