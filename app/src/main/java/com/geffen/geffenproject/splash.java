package com.geffen.geffenproject;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myImageView = (ImageView) findViewById(R.id.ivPic);

        Thread mSplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(3000);
                        Animation myFadeInAnimation = AnimationUtils.loadAnimation(splash.this, R.anim.animation);
                        myImageView.startAnimation(myFadeInAnimation);
                    }
                } catch (InterruptedException ex) {
                }

                finish();

                Intent intent = new Intent(splash.this, MainActivity.class);
                startActivity(intent);
            }
        };

        mSplashThread.start();
    }
}