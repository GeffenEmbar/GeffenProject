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

public class Admin extends AppCompatActivity implements View.OnClickListener {

    Button chBtn, btnTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        chBtn = findViewById(R.id.challengeBtn);
        btnTable = findViewById(R.id.btnTable);
        chBtn.setOnClickListener(this);
        btnTable.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == chBtn.getId())
        {
            Intent intent = new Intent(Admin.this, Notes_Activity.class);
            startActivity(intent);
        }
        else if (view.getId() == btnTable.getId())
        {
            Intent intent = new Intent(Admin.this, UserList.class);
            startActivity(intent);
        }
    }
}