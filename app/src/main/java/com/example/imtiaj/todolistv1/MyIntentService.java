package com.example.imtiaj.todolistv1;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;


import java.util.ArrayList;
import java.util.Calendar;

import static android.R.attr.name;

public class MyIntentService extends IntentService {

    EventListManager eventListManager;
    ArrayList<EventModel>eventModels;




    Handler handler;


    public MyIntentService() {
        super("MyIntentService");
        handler=new Handler(Looper.getMainLooper());
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        handler.post(new Runnable() {
            @Override
            public void run() {

                eventListManager =new EventListManager(MyIntentService.this);
                eventModels=eventListManager.getAllEvents();
                for (EventModel avar:eventModels)
                {
                    String name=avar.getEventName();
                    String date=avar.getEventTargetDate();
                    String time=avar.getEventTargetTime();
                    String amPm=avar.getEventTargetAmPm();
                    String alarm=avar.getEventAlarmed();
                    String alarmOption=avar.geteventAlarmOption();


                    if(alarm.equals("notify"))
                    {
                        Calendar cal=Calendar.getInstance();
                        String year=String.valueOf(cal.YEAR);
                        String month=String.valueOf(cal.MONTH);
                        String day=String.valueOf(cal.DAY_OF_MONTH);
                        String nowDate=day+"/"+month+"/"+year;



                        String nowAmPm=String.valueOf(cal.AM_PM);
                        String hour;
                        if(nowAmPm.equalsIgnoreCase("pm"))
                        {
                            hour=String.valueOf(cal.HOUR-12);
                        }
                        else
                        {
                            hour=String.valueOf(cal.HOUR);
                        }

                        String minute=String.valueOf(cal.MINUTE);
                        String nowTime=hour+":"+minute;

                        if(nowDate.equals(date))
                        {
                            if(nowTime.equals(time) && nowAmPm.equals(amPm))
                            {

                                NotificationCompat.Builder builder =
                                        new NotificationCompat.Builder(MyIntentService.this)
                                                .setSmallIcon(R.mipmap.ic_launcher)

                                                .setContentTitle("Event: "+name)
                                                .setContentText(name +"event is due on "+time +" "+amPm);

                                Intent notificationIntent = new Intent(MyIntentService.this, FirstActivity.class);
                                PendingIntent contentIntent = PendingIntent.getActivity(MyIntentService.this, 0, notificationIntent,
                                        PendingIntent.FLAG_UPDATE_CURRENT);
                                builder.setContentIntent(contentIntent);

                                // Add as notification
                                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                manager.notify(0, builder.build());

                            }
                        }

                    }

                }

            }
        });

    }
}