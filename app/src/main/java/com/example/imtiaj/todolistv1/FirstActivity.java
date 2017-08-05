package com.example.imtiaj.todolistv1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {
    private ListView eventListView;
    private EventListAdapter eventListAdapter;
    private EventListManager eventListManager;
    ArrayList<EventModel>eventModels;

    private AlertDialog.Builder build;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        eventListView= (ListView) findViewById(R.id.eventListViewId);
        eventListManager=new EventListManager(this);

            eventModels=eventListManager.getAllEvents(); //err
            eventListAdapter=new EventListAdapter(this,eventModels);
            eventListView.setAdapter(eventListAdapter);//



        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override//for update + see
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(FirstActivity.this,AddEventActivity.class);

                intent.putExtra("eventId",eventModels.get(i).getEventid());

                startActivity(intent);

                /*Intent ir = new Intent(EventListActivity.this,AddEventActivity.class);
                                ir.putExtra("eventId", tourEvents.get(position).getEventId());
                                startActivity(ir);*/

            }
        });


        eventListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                build = new AlertDialog.Builder(FirstActivity.this);
                build.setTitle("Apply Change " + eventModels.get(position).getEventName().toString());
                build.setMessage("Do you want to delete the record?");





                //user select DELETE
                build.setPositiveButton("DELETE",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,int which) {

                                eventListView= (ListView) findViewById(R.id.eventListViewId);
                                eventListManager=new EventListManager(FirstActivity.this);
                                eventModels=eventListManager.getAllEvents();
                                eventListAdapter=new EventListAdapter(FirstActivity.this,eventModels);
                                int id=eventModels.get(position).getEventid();
                                eventListManager.deleteEvent(eventModels.get(position).getEventid());


                           /*     dataBase.delete(
                                        DBHelper.TABLE_NAME,
                                        DBHelper.STAFID + "="
                                                + stafid.get(arg2), null);*/
                                Toast.makeText(
                                        getApplicationContext(),
                                        eventModels.get(position).getEventName().toString()+
                                                " is deleted.", Toast.LENGTH_SHORT).show();

                                // eventListView.setAdapter(eventListAdapter);
                                finish();
                                startActivity(getIntent());
                                dialog.cancel();
                            }
                        });//end DELETE

                build.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog,
                                                int which) {
                                //Update record selected
                                //Intent ir = new Intent(EventListActivity.this,AddEventActivity.class);
                                //ir.putExtra("eventId", tourEvents.get(position).getEventId());
                                //startActivity(ir);
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = build.create();
                alert.show();

                return true;
            }
        });








    }

    public void createEventFunction(View view) {
        Intent intent =new Intent(FirstActivity.this,AddEventActivity.class);
        startActivity(intent);

    }
}
