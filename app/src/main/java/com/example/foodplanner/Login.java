package com.example.foodplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText password_et;
    private ImageButton btnShowPassword2;
    private EditText email_et;
    TextView signup_tv;
    ProgressBar progressBar;
    MaterialButton loginBtn;
    String email, password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        password_et = findViewById(R.id.loginPassword);
        btnShowPassword2 = findViewById(R.id.btnShowPasswordlogin);
        email_et = findViewById(R.id.signinEmail);
        progressBar = findViewById(R.id.progressBarlogin);
        signup_tv = findViewById(R.id.signUpifNotHaveaccount);
        loginBtn = findViewById(R.id.loginbtn);

        signup_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = String.valueOf(email_et.getText());
                password = String.valueOf(password_et.getText());
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Please Fill All Data", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    return;

                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(Login.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(Login.this, "Login successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    String errorMsg = task.getException().getLocalizedMessage();
                                    Toast.makeText(Login.this, "Error:" + errorMsg, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });


        btnShowPassword2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVisibilityIcon(password_et, btnShowPassword2);
            }
        });

    }

    public void addVisibilityIcon(EditText editTextPassword, ImageButton btnShowPassword) {
        int inputType = editTextPassword.getInputType();
        if (inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            inputType = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
            btnShowPassword.setImageResource(R.drawable.ic_baseline_visibility_off_24);
        } else {
            inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
            btnShowPassword.setImageResource(R.drawable.ic_baseline_visibility_24);
        }
        editTextPassword.setInputType(inputType);
        editTextPassword.setSelection(editTextPassword.getText().length());
    }
}