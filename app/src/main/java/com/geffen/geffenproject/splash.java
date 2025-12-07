package com.geffen.geffenproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class splash extends AppCompatActivity {

    private ImageView myImageView;
    private MediaPlayer mp;  // MediaPlayer for splash sound

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        // Adjust for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myImageView = findViewById(R.id.ivPic);

        // ---- PLAY SPLASH SOUND ----
        mp = MediaPlayer.create(this, R.raw.sound); // put your file in res/raw/
        mp.setVolume(1.0f, 1.0f); // left volume, right volume
        mp.start();

        int soundDuration = mp.getDuration();


        // ---- EXISTING SPLASH THREAD ----
        Thread mSplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(soundDuration); // splash duration
                        Animation myFadeInAnimation = AnimationUtils.loadAnimation(splash.this, R.anim.animation);
                        myImageView.startAnimation(myFadeInAnimation);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                // Stop and release the sound
                if (mp != null) {
                    mp.stop();
                    mp.release();
                    mp = null;
                }

                // Move to the next activity
                Intent intent = new Intent(splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        mSplashThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Ensure MediaPlayer is released if activity is destroyed early
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }
}