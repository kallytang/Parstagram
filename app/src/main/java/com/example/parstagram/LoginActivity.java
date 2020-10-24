package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button loginButton;
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        signUp = findViewById(R.id.tvlogin_text_link);
        etUsername = findViewById(R.id.etNewUserName);
        etPassword = findViewById(R.id.etNewPassword);
        loginButton = findViewById(R.id.btnSignUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onlclicklistener for button is activated");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                Log.i("test", username + " " + password);
                loginUser(username, password);
            }

            private void loginUser(String username, String password) {
                Log.i(TAG, "Attempt to login the user");
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e != null){
                            // TODO: better error handling
                            Log.e(TAG, "Issue with login", e);
                            Toast.makeText(LoginActivity.this, "Issue with login!", Toast.LENGTH_SHORT);
                            return;
                        }
                        goMainActivity();
                        Toast.makeText(LoginActivity.this, "Success, you're logged in!", Toast.LENGTH_SHORT ).show();
                    }
                });
            }
        });

    }
    //use this to navigate to main activity
    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}