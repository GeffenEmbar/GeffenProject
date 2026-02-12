package com.geffen.geffenproject;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.geffen.geffenproject.model.Challenge;
import com.geffen.geffenproject.services.DatabaseService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Presentation extends AppCompatActivity {
    private TextView tvQuestion, tvScore;
    private Button btn1, btn2, btn3, btn4, btnPlay;

    private ArrayList<Challenge> challengeList = new ArrayList<>();
    private int currentIndex = 0;

    private int correctCount = 0;
    private int wrongCount = 0;

    private MediaPlayer mediaPlayer;
    private Challenge currentChallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_presentation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);

        btn1 = findViewById(R.id.btnAnswer1);
        btn2 = findViewById(R.id.btnAnswer2);
        btn3 = findViewById(R.id.btnAnswer3);
        btn4 = findViewById(R.id.btnAnswer4);
        btnPlay = findViewById(R.id.btnPlaySound);

        loadChallenges();
    }

    private void loadChallenges() {
        DatabaseService.getInstance().getChallengeList(new DatabaseService.DatabaseCallback<List<Challenge>>() {
            @Override
            public void onCompleted(List<Challenge> challenges) {
                challengeList.addAll(challenges);
                showNextChallenge();
            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(Presentation.this, "Failed to load challenges", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showNextChallenge() {

        if (currentIndex >= challengeList.size()) {
            Toast.makeText(this, "Finished all challenges!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        currentChallenge = challengeList.get(currentIndex);

        tvQuestion.setText(currentChallenge.getQuestion());

        // Play button click
        btnPlay.setOnClickListener(v -> playCorrectNote());

        ArrayList<String> answers = new ArrayList<>();
        answers.add(currentChallenge.getCorrectAnswer());
        answers.add(currentChallenge.getFalse1());
        answers.add(currentChallenge.getFalse2());
        answers.add(currentChallenge.getFalse3());

        Collections.shuffle(answers);

        btn1.setText(answers.get(0));
        btn2.setText(answers.get(1));
        btn3.setText(answers.get(2));
        btn4.setText(answers.get(3));

        btn1.setOnClickListener(v -> checkAnswer(btn1.getText().toString()));
        btn2.setOnClickListener(v -> checkAnswer(btn2.getText().toString()));
        btn3.setOnClickListener(v -> checkAnswer(btn3.getText().toString()));
        btn4.setOnClickListener(v -> checkAnswer(btn4.getText().toString()));
    }

    private void playCorrectNote() {

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        String correctNote = currentChallenge.getCorrectAnswer().toLowerCase();

        // Convert sharp symbol if needed
        correctNote = correctNote.replace("#", "_sharp");

        int soundResId = getResources().getIdentifier(
                correctNote,
                "raw",
                getPackageName()
        );

        if (soundResId != 0) {
            mediaPlayer = MediaPlayer.create(this, soundResId);
            mediaPlayer.start();
        } else {
            Toast.makeText(this, "Sound file not found!", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkAnswer(String userAnswer) {

        if (currentChallenge.checkAnswer(userAnswer)) {
            correctCount++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            wrongCount++;
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }

        updateScore();

        currentIndex++;
        showNextChallenge();
    }

    private void updateScore() {
        tvScore.setText("Correct: " + correctCount + " | Wrong: " + wrongCount);
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}