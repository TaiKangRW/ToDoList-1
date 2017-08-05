package com.example.imtiaj.todolistv1;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imtiaj on 4/9/17.
 */

public class EventListAdapter extends ArrayAdapter<EventModel> {
    private ArrayList<EventModel>eventModels;
    private Context context;


    public EventListAdapter(Context context, ArrayList<EventModel> objects) {
        super(context,R.layout.single_event_layout, objects);
        this.context=context;
        this.eventModels=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        EventModel eventModel=eventModels.get(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.single_event_layout,parent,false);
        TextView  eventName= (TextView) convertView.findViewById(R.id.eventNameId);
        TextView  eventRating= (TextView) convertView.findViewById(R.id.eventRatingId);
        TextView  eventDate= (TextView) convertView.findViewById(R.id.eventDateId);
        TextView  eventTime= (TextView) convertView.findViewById(R.id.eventTimeAmPmId);
        TextView  eventAlarmed= (TextView) convertView.findViewById(R.id.eventAlarmedId);
        eventName.setText(eventModel.getEventName());
        eventRating.setText(eventModel.getEventRating());
        eventDate.setText(eventModel.getEventTargetDate());
        eventTime.setText(eventModel.getEventTargetTime() + " " + eventModel.getEventTargetAmPm());
        eventAlarmed.setText(eventModel.getEventAlarmed());

        return convertView;
    }
}



/*


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Employee employee=employees.get(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.employee_row,parent,false);
        TextView nameText= (TextView) convertView.findViewById(R.id.nameText);
        TextView designationText= (TextView) convertView.findViewById(R.id.designationText);
        nameText.setText(employee.getEmpName());
        designationText.setText(employee.getEmpDesignation());
        return convertView;
    }
}*/