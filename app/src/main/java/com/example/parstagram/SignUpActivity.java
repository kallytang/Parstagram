package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {
    public final static String TAG = "SignUpActivity";
    EditText etSignUpUsername;
    EditText etSignUpPassword;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etSignUpUsername = findViewById(R.id.etNewUserName);
        etSignUpPassword = findViewById(R.id.etNewPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = etSignUpUsername.getText().toString();
                String newPassword = etSignUpPassword.getText().toString();
                if(newPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Error.Can't have username empty!", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(newUsername.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Error. Can't have password empty!", Toast.LENGTH_SHORT).show();
                    return;

                }
                ParseUser user = new ParseUser();
                user.setUsername(newUsername);
                user.setPassword(newPassword);
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Toast.makeText(getApplicationContext(), "Success! Signed up for a new account", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(i);
                            finish();

                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            Toast.makeText(getApplicationContext(), "Error, something went wrong, try again!", Toast.LENGTH_SHORT).show();
                            // to figure out what went wrong
                            Log.e(TAG, "Error signing up" + e);
                        }
                    }
                });
            }
        });



    }


}