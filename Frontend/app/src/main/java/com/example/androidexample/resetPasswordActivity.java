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
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

/**
 * This page will appear if foound email in database
 * This page will allow the user to change their password and save it
 *
 * This will not work properly until get API working
 * Once API complete just need to add JSON requests
 */

public class resetPasswordActivity extends AppCompatActivity {

    private EditText newPassword;
    private EditText verifyNewPassword;
    private Button changePassword;
    private ImageButton showPassword;
    private ImageButton showReEnterPassword;

    boolean isPasswordVisible = false;
    boolean isPassword2Visible = false;

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


        // Set initial input types for password fields to hidden
        newPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        verifyNewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

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


        /**
         * on change password button, we will check if password match and if do then send back to login page being successful
         */
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassword1 = newPassword.getText().toString().trim();
                String newPassword2 = verifyNewPassword.getText().toString().trim();

                if(!newPassword1.isEmpty() && !newPassword2.isEmpty()){
                    if(newPassword1.equals(newPassword2)){
                        Log.d("Reset Password", "Password match and changed successfully! Password(" + newPassword1 + ")");
                        Toast.makeText(getApplicationContext(), "Password changed successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(resetPasswordActivity.this, loginActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Log.d("Reset Password", "Passwords do not match! Password1(" + newPassword1 + ")  Password2(" + newPassword2 + ")");
                        Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }

                else{
                    Log.d("Reset Password", "Password fields are not filled");
                    Toast.makeText(getApplicationContext(), "Password fields are not filled", Toast.LENGTH_SHORT).show();
                }

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


