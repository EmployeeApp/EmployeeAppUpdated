package com.example.itprototypezero;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.util.ServiceConfigurationError;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ((ThisApplication)getApplication()).setCurrentContext(this);

        SharedPreferences shrdPref = getSharedPreferences("NimbleFixAppData",MODE_PRIVATE);
        String ID = shrdPref.getString("ID",null);
        if(ID==null)
            startActivity(new Intent(this,MainActivity.class));
        else{
            checkToken(shrdPref.getString("ID",null),shrdPref.getString("TOKEN",null));
        }

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
            }
        },2000);*/
    }

    private void checkToken(String id, String token) {
        ((ThisApplication)getApplication()).connectToServer(id, token);
    }

    public void goToStartScan(){
        startActivity(new Intent(SplashScreen.this, Menu.class));
    }

    public void goToSignUp(){
        startActivity(new Intent(this,SignUp.class));
    }
}

