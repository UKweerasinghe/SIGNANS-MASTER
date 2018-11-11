package com.example.im2017.signansmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

import java.lang.reflect.Array;

public class displayOutput extends AppCompatActivity {
    Button ok;
    DatabaseReference dbSignansMaster;
    dataBaseHelper thedb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_output);

        ok = (Button)findViewById(R.id.buttonNext);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launchActivity();

                       // thedb.selectData();
               //System.out.print("***************************************************"+array);
            }
        });
    }

    private void launchActivity() {

        Intent intent = new Intent(this, viewOutput.class);
        startActivity(intent);
    }
}
