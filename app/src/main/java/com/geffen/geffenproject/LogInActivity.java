package com.geffen.geffenproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.geffen.geffenproject.services.DatabaseService;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private EditText etEmail, etPassword;
    private Button btnLogin, btnMove;
    private TextView tvRegister;
    DatabaseService databaseService;



    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedPreferences;
    private String email2, pass2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        /// get the views
        etEmail = findViewById(R.id.etEmailLogIn);
        etPassword = findViewById(R.id.etPasswordLogIn);
        btnLogin = findViewById(R.id.btnLogIn);
        databaseService=DatabaseService.getInstance();


        /// set the click listener
        btnLogin.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        email2=sharedPreferences.getString("email","");
        pass2=sharedPreferences.getString("password","");
        etEmail.setText(email2);
        etPassword.setText(pass2);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnLogin.getId()) {
            Log.d(TAG, "onClick: Login button clicked");

            /// get the email and password entered by the user
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();




            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("email", email);
            editor.putString("password", password);

            editor.commit();

            /// log the email and password
            Log.d(TAG, "onClick: Email: " + email);
            Log.d(TAG, "onClick: Password: " + password);

            Log.d(TAG, "onClick: Validating input...");

            /*

            /// Validate input
            if (!checkInput(email, password)) {
                /// stop if input is invalid
                return;
            }

            */
            Log.d(TAG, "onClick: Logging in user...");

            /// Login user
            loginUser(email, password);
        } else if (view.getId() == btnMove.getId()) {
            /// Navigate to Register Activity
            Intent registerIntent = new Intent(LogInActivity.this, Register.class);
            startActivity(registerIntent);
        }




    }


    /// Method to check if the input is valid
    /// It checks if the email and password are valid
    /// @see Validator#isEmailValid(String)
    /// @see Validator#isPasswordValid(String)
    private boolean checkInput(String email, String password) {
        if (!Validator.isEmailValid(email)) {
            Log.e(TAG, "checkInput: Invalid email address");
            /// show error message to user
            etEmail.setError("Invalid email address");
            /// set focus to email field
            etEmail.requestFocus();
            return false;
        }

        if (!Validator.isPasswordValid(password)) {
            Log.e(TAG, "checkInput: Invalid password");
            /// show error message to user
            etPassword.setError("Password must be at least 6 characters long");
            /// set focus to password field
            etPassword.requestFocus();
            return false;
        }

        return true;
    }

    private void loginUser(String email, String password) {
        databaseService.LoginUser(email, password, new DatabaseService.DatabaseCallback<String>() {
            /// Callback method called when the operation is completed

            @Override
            public void onCompleted(String  uid) {
                Log.d(TAG, "onCompleted: User logged in: " + uid.toString());
                /// save the user data to shared preferences
                // SharedPreferencesUtil.saveUser(LoginActivity.this, user);
                /// Redirect to main activity and clear back stack to prevent user from going back to login screen
                Intent mainIntent = new Intent(LogInActivity.this, MainActivity.class);
                /// Clear the back stack (clear history) and start the MainActivity
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
            }

            @Override
            public void onFailed(Exception e) {
                Log.e(TAG, "onFailed: Failed to retrieve user data", e);
                /// Show error message to user
                etPassword.setError("Invalid email or password");
                etPassword.requestFocus();
                /// Sign out the user if failed to retrieve user data
                /// This is to prevent the user from being logged in again

            }
        });
    }


}


