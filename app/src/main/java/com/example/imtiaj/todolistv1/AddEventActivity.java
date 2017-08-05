package com.example.imtiaj.todolistv1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddEventActivity extends AppCompatActivity {//implements AdapterView.OnItemSelectedListener

    private EditText eventNameET;
    private EditText eventDateET;
    private EditText eventTimeET;
    private Spinner eventCategoryET;
    private CheckBox eventAlarmed;
    private Spinner eventaAlarmOptionET;
    private Spinner eventaRatingET;
    //private Spinner eventAmPmET;

    private Button saveButton;


    private EventListManager eventListManager;
    int intentedEventId;


    private String eventCategory;

    private String eventAlarmOption;

    private String eventaRating;

    private String alarmedOrNot;

    private String eventDate;
    private String eventTime;
    private String eventAmPm;
    private String eventName;


    private ArrayAdapter<String> dataAdapter, dataAdapter2, dataAdapter3;

    @Override
    protected void onStop() {
        super.onStop();

         eventNameET.setText(null);
        eventDateET.setText(null);
        eventTimeET.setText(null);
        eventCategoryET.setAdapter(dataAdapter);
        eventAlarmed.setText(null);
        eventaAlarmOptionET.setAdapter(dataAdapter2);
        eventaRatingET.setAdapter(dataAdapter3);

        //super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);




        eventNameET = (EditText) findViewById(R.id.eventNameEdit);
        eventDateET = (EditText) findViewById(R.id.eventDateEdit);
        eventTimeET = (EditText) findViewById(R.id.eventTimeEdit);



        eventCategoryET = (Spinner) findViewById(R.id.eventCategoryEdit);
        //eventCategoryET.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        List<String> eventCategories = new ArrayList<String>();
        eventCategories.add("Common");
        eventCategories.add("Business");
        eventCategories.add("Office");
        eventCategories.add("Education");
        eventCategories.add("Personal");
        eventCategories.add("Travel");
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, eventCategories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        eventCategoryET.setAdapter(dataAdapter);
//        eventCategoryET.setOnItemSelectedListener(this);


        eventaAlarmOptionET = (Spinner) findViewById(R.id.eventAlarmTimeOptionEdit);
        //eventaAlarmOptionET.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        List<String> eventAlarmOptions = new ArrayList<String>();
        eventAlarmOptions.add("On Time");
        eventAlarmOptions.add("10 minutes before");
        eventAlarmOptions.add("15 minutes before");
        eventAlarmOptions.add("30 minutes before");
        eventAlarmOptions.add("1 hour before");
        eventAlarmOptions.add("1 day before");


        dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, eventAlarmOptions);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        eventaAlarmOptionET.setAdapter(dataAdapter2);

        eventaAlarmOptionET.setEnabled(false);
        eventaAlarmOptionET.setClickable(false);

        eventAlarmed = (CheckBox) findViewById(R.id.eventIsAlarmedEdit);


        eventaRatingET = (Spinner) findViewById(R.id.eventPriorityOptionEdit);
        //eventaRatingET.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        List<String> ratingOptions = new ArrayList<String>();
        ratingOptions.add("*");
        ratingOptions.add("* *");
        ratingOptions.add("* * *");
        ratingOptions.add("* * * *");
        ratingOptions.add("* * * * *");
        dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ratingOptions);

        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        eventaRatingET.setAdapter(dataAdapter3);



        saveButton =(Button) findViewById(R.id.saveButton);

        intentedEventId =getIntent().getIntExtra("eventId",0);

        eventListManager=new EventListManager(this);
        if(intentedEventId >0)

        {
            EventModel eventModel = eventListManager.getEventsById(intentedEventId);

            saveButton.setText("Update");
            eventNameET.setText(eventModel.getEventName());
            eventDateET.setText(eventModel.getEventTargetDate());
            eventTimeET.setText(eventModel.getEventTargetTime() + " " + eventModel.getEventTargetAmPm());

            if (eventModel.getEventAlarmed().equals("notify")) {
                eventAlarmed.setChecked(true);

                eventaAlarmOptionET.setEnabled(true);
                eventaAlarmOptionET.setClickable(true);

                int alarmIndex = 0;
                for (String opt:eventAlarmOptions) {
                    if(!opt.equals(eventModel.geteventAlarmOption()))
                    {
                        alarmIndex++;
                    }
                    else {
                        //alarmIndex=+1;
                        break;
                    }
                }

                eventaAlarmOptionET.setSelection(alarmIndex);

            }
            else {
                eventAlarmed.setChecked(false);
            }

            int categoryIndex = 0;
            for (String opt:eventCategories) {
                if(!opt.equals(eventModel.getEventCategory()))
                {
                    categoryIndex++;
                }
                else {
                    //categoryIndex=+1;
                    break;
                }
            }

            eventCategoryET.setSelection(categoryIndex);


            int priorityIndex = 0;
            for (String opt:ratingOptions) {
                if(!opt.equals(eventModel.getEventRating()))
                {
                    priorityIndex++;
                }
                else {
                    //priorityIndex=priorityIndex+1;
                    break;
                }
            }
            eventaRatingET.setSelection(priorityIndex);


        }
    }







    public void saveEventFunction(View view) {

        if (eventAlarmed.isChecked()) {
            alarmedOrNot = "notify";

        } else {
            alarmedOrNot = "not notify";
        }
        eventName = eventNameET.getText().toString();



        eventCategory=eventCategoryET.getSelectedItem().toString();

        eventAlarmOption=eventaAlarmOptionET.getSelectedItem().toString();

        eventaRating=eventaRatingET.getSelectedItem().toString();

        EventModel eventModel=new EventModel(eventName,eventDate,eventTime,eventAmPm,eventaRating,eventCategory,alarmedOrNot,eventAlarmOption);


        eventListManager=new EventListManager(this);

        if(intentedEventId>0)
        {
            long insertResult=eventListManager.updateEvent(eventModel,intentedEventId);
            if(insertResult>0){

                Toast.makeText(this, "Updatesuccess"+insertResult, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AddEventActivity.this,FirstActivity.class);
                startActivity(intent);
            }

        }
        else
        {
            long insertResult=eventListManager.addEvent(eventModel);

            if(insertResult>0){
                Toast.makeText(this, "AddSuccess"+insertResult, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AddEventActivity.this,FirstActivity.class);
                startActivity(intent);
            }


        }


        startService(new Intent(this,MyIntentService.class));

    }


    public void cancelEventAddingFunction(View view) {


        Intent intent=new Intent(AddEventActivity.this,FirstActivity.class);
        startActivity(intent);
    }



    public void alarmNotificationOnFunction(View view) {

        if(eventAlarmed.isChecked())
        {


            eventaAlarmOptionET.setEnabled(true);
            eventaAlarmOptionET.setClickable(true);

        }
        else
        {

            eventaAlarmOptionET.setEnabled(false);
            eventaAlarmOptionET.setClickable(false);
            //yourSpinner.setAdapter(typeAdapter);



        }

    }

    public void setTimeFunction(View view) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int ampm = calendar.get(Calendar.AM_PM);
        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                //eventTime = hourOfDay + ":" + minute;

                if (hourOfDay < 12) {
                    eventAmPm = "AM";
                } else {

                    eventAmPm = "PM";
                    hourOfDay=hourOfDay-12;
                }
                eventTime = String.valueOf(hourOfDay )+ ":" + String.valueOf(minute);
                eventTimeET.setText(eventTime + " " + eventAmPm);

            }
        }, hour, minute, false);
        timePickerDialog.show();
    }

    public void setDateFuntion(View view) {




        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog;
        datePickerDialog = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                eventDate = dayOfMonth + "/" + month + "/" + year;
                eventDateET.setText(eventDate);

                //take date
            }


        }, year, month,day );
        datePickerDialog.show();
    }

}

