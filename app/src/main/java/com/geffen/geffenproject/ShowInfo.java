package com.geffen.geffenproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShowInfo extends AppCompatActivity {
    TextView tvInfo;
    String fName, lName, email, phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvInfo = findViewById(R.id.tvInfo);
        Intent intent = getIntent();
        fName = intent.getStringExtra("fname");
        lName = intent.getStringExtra("lname");
        email = intent.getStringExtra("email");
        phone = intent.getStringExtra("phone");
        password = intent.getStringExtra("password");

        String msg = "First name: " + fName + "\n " +
                "Last name: " + lName + "\n " +
                "Email: " + email + "\n " +
                "Phone: " + phone + "\n " +
                "Password: " + password

                ;

        tvInfo.setText(msg);
    }
}