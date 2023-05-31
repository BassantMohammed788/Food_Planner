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
import com.google.firebase.auth.SignInMethodQueryResult;

public class SignUp extends AppCompatActivity {
    private EditText password_et;
    private EditText confirmPassword_et;
    private ImageButton password_ib;
    private ImageButton confirmPassword_ib;
    private EditText email_et;
    private EditText userName_et;
    private MaterialButton signupBtn;
    FirebaseAuth firebaseAuth;
    String username, email, password, confirmpassword;
    ProgressBar progressBar;
    TextView login_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        password_et = findViewById(R.id.signupconfirmPassword);
        password_ib = findViewById(R.id.confirmpasswordIcond);
        confirmPassword_et = findViewById(R.id.loginPassword);
        confirmPassword_ib = findViewById(R.id.passwordIcon);
        userName_et = findViewById(R.id.signupUserName);
        email_et = findViewById(R.id.signupEmail);
        signupBtn = findViewById(R.id.createAccountBtn);
        progressBar = findViewById(R.id.progressBar);
        login_tv = findViewById(R.id.signUpifNotHaveaccount);


        login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
                finish();

            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = String.valueOf(userName_et.getText());
                email = String.valueOf(email_et.getText());
                password = String.valueOf(password_et.getText());
                confirmpassword = String.valueOf(confirmPassword_et.getText());
                if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
                    Toast.makeText(SignUp.this, "Please Fill All Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(confirmpassword)) {
                    Toast.makeText(SignUp.this, "Passwords Not Matching", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(SignUp.this, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                createUserByEmail(email, password);
            }
        });
        password_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVisibilityIcon(password_et, password_ib);
            }
        });
        confirmPassword_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addVisibilityIcon(confirmPassword_et, confirmPassword_ib);
            }
        });

    }

    private void createUserByEmail(String email, String password) {
        firebaseAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                if (task.isSuccessful()) {
                    SignInMethodQueryResult result = task.getResult();
                    if (result != null && result.getSignInMethods() != null && result.getSignInMethods().size() > 0) {
                        // The email already exists in the Firebase Authentication database
                        Toast.makeText(SignUp.this, "You are already registered", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    } else {
                        // The email does not exist in the Firebase Authentication database
                        firebaseAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(SignUp.this, HomeActivity.class);
                                            startActivity(intent);
                                            finish();
                                            Toast.makeText(SignUp.this, "you are registered successfully", Toast.LENGTH_SHORT).show();
                                        } else {
                                            String errorMsg = task.getException().getLocalizedMessage();
                                            Toast.makeText(SignUp.this, "Error:" + errorMsg, Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    String errorMsg = task.getException().getLocalizedMessage();
                    Toast.makeText(SignUp.this, "Error:" + errorMsg, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void addVisibilityIcon(EditText editTextPassword, ImageButton btnShowPassword) {
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