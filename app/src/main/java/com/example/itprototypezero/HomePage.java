package com.example.itprototypezero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void AssignedTask(View view){
        Intent intent = new Intent(this, ActivityMainAssignedTask.class);
        startActivity(intent);
    }
}
