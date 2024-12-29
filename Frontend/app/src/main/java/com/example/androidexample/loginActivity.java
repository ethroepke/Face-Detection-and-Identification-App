package com.example.androidexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * The login page allows the user to login with given credentials or select forgot password or create account.
 */

public class loginActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private Button signIn;
    private Button forgotPassword;
    private Button createAccount;
    private ImageButton showPassword;

    boolean isPasswordVisible = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usernameInput = findViewById(R.id.loginUsername);
        passwordInput = findViewById(R.id.loginPassword);
        signIn = findViewById(R.id.signInLogin);
        forgotPassword = findViewById(R.id.forgotLoginButton);
        createAccount = findViewById(R.id.createAccountLogin);
        showPassword = findViewById(R.id.showPasswordLogin);

        //Sign in button will allow user to move to next page if credentials are valid
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Store username/password
                String username = usernameInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                // Check if both fields are filled
                if (!username.isEmpty() && !password.isEmpty()) {
                    //Test user to validate until get API set up
                    if (validateCredentials(username, password)) {
                        Intent intent = new Intent(loginActivity.this, homeActivity.class);
                        startActivity(intent);
                        Log.d("Sign In Button", "Valid credentials: username(" + username + ") password(" + password + ")");
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                        Log.d("Sign In Button", "Invalid credentials: username(" + username + ") password(" + password + ")");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter both fields", Toast.LENGTH_SHORT).show();
                    Log.d("Sign In Button", "Empty fields");
                }
            }
        });


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


        //When forgot password button clicked, send to another page for verification
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, forgotpasswordActivity.class);
                startActivity(intent);
                Log.d("Forgot Password Button", "Forgot password button pressed");
            }
        });


        //If new user and press create account button, send user to new page to create account
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, joinNowActivity.class);
                startActivity(intent);
            }
        });
    }

    //Test user to be able to login until we get API set up
    private boolean validateCredentials(String username, String password) {
        return username.equals("testuser") && password.equals("password123");
    }
}
