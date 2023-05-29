package com.licenta.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

import java.util.Objects;

public class BreathingActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView breathtxt;
    private Button startButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing);
        setTitle("Breathing Techniques");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        imageView = findViewById(R.id.flower);

        breathtxt = findViewById(R.id.breathtxt);

        startButton = findViewById(R.id.startbtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });
    }




    private void startAnimation() {
        ViewAnimator
                .animate(imageView)
                .onStart(new AnimationListener.Start() {
                    @Override
                    public void onStart() {
                        breathtxt.setText("Inhale ... Exhale");
                    }
                })
                .decelerate()
                .duration(1000)
                .thenAnimate(imageView)
                .scale(0.02f, 1.5f, 0.02f)
                //roteste animatia pe inspiratie si expiratie)
                .rotation(-360)
                .repeatCount(5)

                .duration(10000)

                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {

                        imageView.setScaleX(1.0f);
                        imageView.setScaleY(1.0f);

                    }
                }).start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}