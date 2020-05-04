package com.example.itprototypezero;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.itprototypezero.adapter.TaskContentAdapter;

public class TaskCardDetails extends AppCompatActivity implements Refusal.RefusalDialogListener {
    public static final String EXTRA_TASK_ASSIGNED = "extra_task_assigned";
    private static int seconds =0;
    private static boolean running;

    //views
    private TextView organisationIdTv, inventoryNameTv, inventoryIdTv, locationTv, remarksTv, addedCostTv;
    private Button refuseButton;
    private ActionBar actionBar;
    String reason,logTime;

    ///Make a database helper class
    //private MyDbHelper dbHelper;

    private String taskID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_card_details);

        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
        //setting up actionbar with title and back button
        actionBar = getSupportActionBar();
        actionBar.setTitle("Task Details");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //get task id from adapter through intent
        Intent intent = getIntent();
        taskID = intent.getStringExtra("TASK_ID");

        //init database helper class
//        dbHelper = new MyDbHelper(this);
        //init views
        organisationIdTv = findViewById(R.id.organistationIdTv);
        inventoryNameTv = findViewById(R.id.inventoryNameTv);
        inventoryIdTv = findViewById(R.id.inventoryIdTv);
        locationTv = findViewById(R.id.locationTv);
        remarksTv = findViewById(R.id.remarksTv);
        addedCostTv = findViewById(R.id.addedCostTv);
        refuseButton = findViewById(R.id.refuse_button);
        showTaskDetails();

        refuseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


    }

    public void openDialog() {
        Refusal refusal = new Refusal();
        refusal.show(getSupportFragmentManager(),"Refusal Reason");
    }
    /*This is for reference ,for connecting to SQLite database*/

    private void showTaskDetails() {
        //get task details

        //query to select task based on task id
//        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME+ " WHERE " + Constants.C_ID + " =\"" + taskID+"\"";
//
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        //keep checking in whole db for that task
//        if(cursor.moveToFirst()){
//            do {
//                //get data
//                String taskID = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_ID));
//                String organistaionID = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_OrgansiationID));
//                String inventoryName = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_InventoryName));
//                String inventoryID = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_InventoryID));
//                String location = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_Location));
//                String remarks = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_Remarks));
//                long addedCost = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_AddedCost));
//
//                organisationIdTv.setText(organistaionID);
//                inventoryNameTv.setText(inventoryName);
//                inventoryIdTv.setText(inventoryID);
//                locationTv.setText(location);
//                remarksTv.setText(remarks);
//                addedCostTv.setText(String.valueOf(addedCost));
//
//            }while (cursor.moveToNext());
//        }
//
//        //close db connection
//        db.close();
    }


    public boolean onSupportNavigationUp(){
        onBackPressed();//goto previous activity
        return super.onSupportNavigateUp();
    }

    private void runTimer(){
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d",hours, minutes, secs);
                timeView.setText(time);

                //Store the logTime in database
                logTime = time;
                if(running)
                    seconds++;
                handler.postDelayed(this,1000);
            }
        });
    }

    public void onClickReset(View view) {
        running=false;
        seconds=0;
    }

    public void onClickStop(View view) {
        running=false;
    }

    public void onClickStart(View view) {
        running=true;
    }

    @Override
    public void applyTexts(String reason) {
        //Add the reason to the database
            reason = reason;
    }
}
