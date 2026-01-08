package com.geffen.geffenproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button register, login, admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        admin = findViewById(R.id.admin);
        register.setOnClickListener(this);
        login.setOnClickListener(this);
        admin.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == register.getId()) {
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        }
        else if (view.getId() == login.getId())
        {
            Intent intent = new Intent(MainActivity.this, LogInActivity.class);
            startActivity(intent);
        }
        else if (view.getId() == admin.getId())
        {
            Intent intent = new Intent(MainActivity.this, Admin.class);
            startActivity(intent);
        }
    }
}