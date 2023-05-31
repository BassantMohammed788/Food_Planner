package com.example.foodplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class StartScreen extends AppCompatActivity {

    private MaterialButton login_btn;
    private MaterialButton signup_btn;
    private MaterialButton skip_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        login_btn = findViewById(R.id.signinBtn);
        signup_btn = findViewById(R.id.signupBtn);
        skip_btn = findViewById(R.id.SkipBtn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartScreen.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartScreen.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });

    }}