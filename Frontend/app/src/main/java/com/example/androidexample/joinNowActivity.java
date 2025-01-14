package com.example.androidexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class joinNowActivity extends AppCompatActivity {

    private EditText nameInput;
    private EditText emailInput;
    private EditText usernameInput;
    private EditText passwordInput;
    private EditText reenterPasswordInput;
    private Button joinNow;
    private ImageButton showPassword;
    private ImageButton showReenterPassword;

    boolean isPasswordVisible = false;
    boolean isPassword2Visible = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joinnow);

        nameInput = findViewById(R.id.nameInputJoinNow);
        emailInput = findViewById(R.id.emailInputJoinNow);
        usernameInput = findViewById(R.id.usernameInputJoinNow);
        passwordInput = findViewById(R.id.enterPasswordJoinNow);
        reenterPasswordInput = findViewById(R.id.reenterPasswordJoinNow);
        joinNow = findViewById(R.id.joinButtonJoinNow);
        showPassword = findViewById(R.id.showPassword1JoinNow);
        showReenterPassword = findViewById(R.id.showPassword2JoinNow);


        //Toolbar setup
        Toolbar toolbar = findViewById(R.id.toolBarJoinNow);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Account");


        // Set initial input types for password fields to hidden
        passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        reenterPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        //Check to see if password is hidden or not and can click to change to view password
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPasswordVisible) {
                    passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    showPassword.setImageResource(R.drawable.eyehide);
                } else {
                    passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    showPassword.setImageResource(R.drawable.eyeshow);
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });

        //Check to see if password is hidden or not and can click to change to view password
        showReenterPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPassword2Visible) {
                    reenterPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    showPassword.setImageResource(R.drawable.eyehide);
                } else {
                    reenterPasswordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
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
            Intent intent = new Intent(joinNowActivity.this, loginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Check if the full name is valid
    private boolean isValidFullName(String nameFilled) {
        // Check if full name is not empty and contains at least one space (indicating two words)
        return !TextUtils.isEmpty(nameFilled) && nameFilled.contains(" ") && nameFilled.trim().split("\\s+").length >= 2;
    }

    //Check if email is valid
    private boolean isValidEmail(String emailFilled) {
        // Check if email matches legit email addresses
        return !TextUtils.isEmpty(emailFilled) && Patterns.EMAIL_ADDRESS.matcher(emailFilled).matches();
    }

    //Helper method to autofill username from given name
    private void fillUsername(EditText name, EditText username){
        String fullName = name.getText().toString().trim();
        //Split name by space
        String[] firstLast = fullName.split("\\s+");

        //Check first and last is in name field
        if(firstLast.length >= 2){
            //first initial
            String firstInitial = firstLast[0].substring(0, 1).toLowerCase();
            //last name
            String lastName = firstLast[firstLast.length - 1].toLowerCase();

            String usernameFinal = firstInitial + lastName;
            usernameInput.setText(usernameFinal);
        }
    }
}

