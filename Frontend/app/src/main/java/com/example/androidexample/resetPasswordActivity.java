package com.example.androidexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import androidx.appcompat.widget.Toolbar;

/**
 * This page will appear if foound email in database
 * This page will allow the user to change their password and save it
 */

public class resetPasswordActivity extends AppCompatActivity {

    private EditText newPassword;
    private EditText verifyNewPassword;
    private Button changePassword;
    private ImageButton showPassword;
    private ImageButton showReEnterPassword;

    private boolean isPasswordVisible = false;
    private boolean isPassword2Visible = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpassword);

        newPassword = findViewById(R.id.newPasswordInput);
        verifyNewPassword = findViewById(R.id.verifyNewPasswordInput);
        changePassword = findViewById(R.id.submitButtonResetPassword);
        showPassword = findViewById(R.id.showPasswordResetPassword);
        showReEnterPassword = findViewById(R.id.showVerifyPasswordResetPassword);


        //Toolbar setup
        Toolbar toolbar = findViewById(R.id.toolBarResetPassword);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Reset Password");


        //Check to see if password is hidden or not and can click to change to view password
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPasswordVisible) {
                    newPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    showPassword.setImageResource(R.drawable.eyehide);
                } else {
                    newPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    showPassword.setImageResource(R.drawable.eyeshow);
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });

        //Check to see if password is hidden or not and can click to change to view password
        showReEnterPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPassword2Visible) {
                    verifyNewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    showPassword.setImageResource(R.drawable.eyehide);
                } else {
                    verifyNewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    showPassword.setImageResource(R.drawable.eyeshow);
                }
                isPassword2Visible = !isPassword2Visible;
            }
        });

    }


    //Setup for toolbar back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Log.d("Check Email Back Button", "Back button from reset password to login page");
            Intent intent = new Intent(resetPasswordActivity.this, loginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


