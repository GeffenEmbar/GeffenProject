package com.geffen.geffenproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.geffen.geffenproject.model.User;

import com.geffen.geffenproject.services.DatabaseService;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Register";


    EditText etFname, etLname, etEmail, etPhone, etPassword;
    String fName, lName, email, phone, password;
    Button submit, move;

    DatabaseService databaseService;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        databaseService=DatabaseService.getInstance();
        mAuth = FirebaseAuth.getInstance();

        etFname = findViewById(R.id.etFname);
        etLname = findViewById(R.id.etLname);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        submit = findViewById(R.id.btnSubmit);
        move = findViewById(R.id.Move);
        submit.setOnClickListener(this);
        move.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == submit.getId())
        {
            fName = etFname.getText().toString();
            lName = etLname.getText().toString();
            email = etEmail.getText().toString();
            phone = etPhone.getText().toString();
            password = etPassword.getText().toString();

            Intent intent = new Intent(this, ShowInfo.class);

            intent.putExtra("fname", fName);
            intent.putExtra("lname", lName);
            intent.putExtra("email", email);
            intent.putExtra("phone", phone);
            intent.putExtra("password", password);

            /// Register user
            registerUser(fName, lName, phone, email, password);


            startActivity(intent);
        }
        else
        {
            Intent i = new Intent(this, ShowInfo.class);
            startActivity(i);
        }

    }


    /// Register the user
    private void registerUser(String fname, String lname, String phone, String email, String password) {
        Log.d(TAG, "registerUser: Registering user...");

        databaseService.checkIfEmailExists(email, new DatabaseService.DatabaseCallback<>() {
            @Override
            public void onCompleted(Boolean exists) {
                if (exists) {
                    Log.e(TAG, "onCompleted: Email already exists");
                    /// show error message to user
                    Toast.makeText(Register.this, "Email already exists", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(authTask -> {

                                if (!authTask.isSuccessful()) {
                                    Toast.makeText(Register.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                User user = new User(uid, fname, lname, email, phone, password, null);

                                createUserInDatabase(user);

                            });
                }
            }
            @Override
            public void onFailed(Exception e) {
                Log.e(TAG, "onFailed: Failed to check if email exists", e);
                /// show error message to user
                Toast.makeText(Register.this, "Failed to register user", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createUserInDatabase(User user) {
        databaseService.createNewUser(user, new DatabaseService.DatabaseCallback<Void>() {
            @Override
            public void onCompleted(Void object) {
                Log.d(TAG, "createUserInDatabase: User created successfully");
                /// save the user to shared preferences

                Log.d(TAG, "createUserInDatabase: Redirecting to MainActivity");
                /// Redirect to MainActivity and clear back stack to prevent user from going back to register screen
                Intent intent = new Intent(Register.this, MainActivity.class);
                /// clear the back stack (clear history) and start the MainActivity
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

            @Override
            public void onFailed(Exception e) {
                Log.e(TAG, "createUserInDatabase: Failed to create user", e);
                /// show error message to user
                Toast.makeText(Register.this, "Failed to register user", Toast.LENGTH_SHORT).show();
                /// sign out the user if failed to register

            }
        });
    }
}