package com.example.androidexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


/**
 * This page is for user to enter their email to verify there is an email in database then get sent to screen to reset password
 *
 * This will not work properly until get API working
 * Once API/database is done, just need to add JSON requests
 */

public class forgotpasswordActivity extends AppCompatActivity {

    private EditText emailInput;
    private Button checkEmail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);

        emailInput = findViewById(R.id.emailInputForgotPassword);
        checkEmail = findViewById(R.id.checkEmailForgotPassword);

        //Toolbar setup
        Toolbar toolbar = findViewById(R.id.toolBarForgotPassword);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Check Email");


        /**
         * Check Email button will check if user has valid email
         * If valid then will send to reset password page
         */
        checkEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString().trim();

                if(email.isEmpty()){
                    Log.d("Email Submit Button", "Email field is empty");
                    Toast.makeText(getApplicationContext(),"Please fill out email field", Toast.LENGTH_SHORT).show();
                }

                if(!email.isEmpty()) {
                    if (validateCredentials(email)) {
                        Log.d("Email Submit Button", "Email validated: email(" + email + ")");
                        Intent intent = new Intent(forgotpasswordActivity.this, resetPasswordActivity.class);
                        startActivity(intent);
                    } else {
                        Log.d("Email Submit Button", "Email not validated: email(" + email + ")");
                        Toast.makeText(getApplicationContext(), "Email incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    //Setup for toolbar back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Log.d("Check Email Back Button", "Back button from check email to login page");
            Intent intent = new Intent(forgotpasswordActivity.this, loginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Test user email to be able to test reset password until we get API set up
    private boolean validateCredentials(String email) {
        return email.equals("testuser@testing.com");
    }
}