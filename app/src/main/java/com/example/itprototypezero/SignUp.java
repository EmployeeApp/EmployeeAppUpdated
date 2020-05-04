package com.example.itprototypezero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    Button signup;
    EditText organisationID;
    EditText employeeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = findViewById(R.id.next);
        organisationID = findViewById(R.id.organisationID);
        employeeID = findViewById(R.id.employeeID);

        ((ThisApplication)getApplication()).setCurrentContext(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

    }

    private void signup() {
        ((ThisApplication)getApplication()).connectToServer(organisationID.getText().toString());
        ((ThisApplication)getApplication()).connectToServer(employeeID.getText().toString());
    }

    public void showOTPScreen(){
        Intent intent = new Intent(SignUp.this, otp.class);
        startActivity(intent);
    }

}
