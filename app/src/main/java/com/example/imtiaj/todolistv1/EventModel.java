    package com.example.imtiaj.todolistv1;

/**
 * Created by imtiaj on 4/8/17.
 */

public class EventModel {
    private int eventid;
    private  String eventName;
    private  String eventTargetDate;
    private  String eventTargetTime;
    private String eventTargetAmPm;
    private String eventRating;
    private  String eventCategory;
    private String eventAlarmed;
    private String eventAlarmOption;

    public String geteventAlarmOption() {
        return eventAlarmOption;
    }

    public void seteventAlarmOption(String alarmOption) {
        this.eventAlarmOption= alarmOption;
    }



    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventTargetDate() {
        return eventTargetDate;
    }

    public void setEventTargetDate(String eventTargetDate) {
        this.eventTargetDate = eventTargetDate;
    }

    public String getEventTargetTime() {
        return eventTargetTime;
    }

    public void setEventTargetTime(String eventTargetTime) {
        this.eventTargetTime = eventTargetTime;
    }

    public String getEventTargetAmPm() {
        return eventTargetAmPm;
    }

    public void setEventTargetAmPm(String eventTargetAmPm) {
        this.eventTargetAmPm = eventTargetAmPm;
    }

    public String getEventRating() {
        return eventRating;
    }

    public void setEventRating(String eventRating) {
        this.eventRating = eventRating;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventAlarmed() {
        return eventAlarmed;
    }

    public void setEventAlarmed(String eventAlarmed) {
        this.eventAlarmed = eventAlarmed;
    }


    public EventModel(int eventid, String eventName, String eventTargetDate, String eventTargetTime, String eventTargetAmPm, String eventRating, String eventCategory, String eventAlarmed ,String eventAlarmOption) {
        this.eventid = eventid;
        this.eventName = eventName;
        this.eventTargetDate = eventTargetDate;
        this.eventTargetTime = eventTargetTime;
        this.eventTargetAmPm = eventTargetAmPm;
        this.eventRating = eventRating;
        this.eventCategory = eventCategory;
        this.eventAlarmed = eventAlarmed;
        this.eventAlarmOption=eventAlarmOption;
    }

    public EventModel(String eventName, String eventTargetDate, String eventTargetTime, String eventTargetAmPm, String eventRating, String eventCategory, String eventAlarmed, String eventAlarmOption) {
        this.eventName = eventName;
        this.eventTargetDate = eventTargetDate;
        this.eventTargetTime = eventTargetTime;
        this.eventTargetAmPm = eventTargetAmPm;
        this.eventRating = eventRating;
        this.eventCategory = eventCategory;
        this.eventAlarmed = eventAlarmed;
        this.eventAlarmOption = eventAlarmOption;
    }
}
