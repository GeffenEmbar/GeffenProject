package com.geffen.geffenproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NotesQuestions extends AppCompatActivity implements View.OnClickListener {

    TextView tvQuestion;
    Button btnF1, btnF2, btnF3, btnCorrect;
    String strF1, strF2, strF3, strCorrect, strQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes_questions);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvQuestion = findViewById(R.id.question);
        btnF1 = findViewById(R.id.f1);
        btnF2 = findViewById(R.id.f2);
        btnF3 = findViewById(R.id.f3);
        btnCorrect = findViewById(R.id.correct);

        btnF1.setOnClickListener(this);
        btnF2.setOnClickListener(this);
        btnF3.setOnClickListener(this);
        btnCorrect.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {

    }
}