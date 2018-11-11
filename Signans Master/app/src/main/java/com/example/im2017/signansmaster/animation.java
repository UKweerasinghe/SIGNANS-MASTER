package com.example.im2017.signansmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class animation extends AppCompatActivity {

    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                launchActivity();
            }
        },7000);

    }

    private void launchActivity() {

        Intent intent = new Intent(this, viewOutput.class);
        startActivity(intent);
        //finish();
    }
}
