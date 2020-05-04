package com.example.itprototypezero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void homePage(View view){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
        Toast.makeText(MainActivity.this,"Successfully logged in!",Toast.LENGTH_SHORT).show();
    }
}
