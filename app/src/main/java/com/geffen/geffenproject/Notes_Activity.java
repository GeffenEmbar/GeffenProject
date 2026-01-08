package com.geffen.geffenproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.geffen.geffenproject.model.Challenge;
import com.geffen.geffenproject.services.DatabaseService;
import com.google.firebase.auth.FirebaseAuth;

public class Notes_Activity extends AppCompatActivity implements View.OnClickListener {

    Spinner spInstru, spCorrect, spFalse1, spFalse2, spFalse3, spDiff;

    String instrument, type, question, correctAnswer, false1, false2, false3, id, difficulty;
    Button btnSub;
    SharedPreferences sharedPreferences;
    DatabaseService databaseService;
    private FirebaseAuth mAuth;
    public static final String MyPREFERENCES = "MyPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        databaseService=DatabaseService.getInstance();
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        spInstru = findViewById(R.id.spInstrument);
        spCorrect = findViewById(R.id.correctAnswer);
        spFalse1 = findViewById(R.id.false1);
        spFalse2 = findViewById(R.id.false2);
        spFalse3 = findViewById(R.id.false3);
        spDiff = findViewById(R.id.diff);
        btnSub = findViewById(R.id.btnAdd);
        btnSub.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnSub.getId())
        {
            instrument = spInstru.getSelectedItem().toString();
            type = "notes";
            question = "What is the note?";
            correctAnswer = spCorrect.getSelectedItem().toString();
            false1 = spFalse1.getSelectedItem().toString();
            false2 = spFalse2.getSelectedItem().toString();
            false3 = spFalse3.getSelectedItem().toString();
            difficulty = spDiff.getSelectedItem().toString();

            Create(correctAnswer, difficulty, false1, false2, false3, instrument, question, type, false);
        }
    }


    private void Create(String correctAnswer, String difficulty, String false1, String false2, String false3, String instrument, String question,String type, boolean completed)
    {
        id = databaseService.generateChallengeId();
        Challenge c1 = new Challenge(completed, correctAnswer, difficulty, false1, false2, false3, id, instrument, question, type);
        databaseService.createNewChallenge(c1, new DatabaseService.DatabaseCallback<Void>() {

            @Override
            public void onFailed(Exception e) {
                return;
            }

            @Override
            public void onCompleted(Void object) {

            }
        });

    }
}