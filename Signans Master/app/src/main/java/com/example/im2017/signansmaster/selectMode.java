package com.example.im2017.signansmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class selectMode extends AppCompatActivity {
    dataBaseHelper thedb;
    Button button_modeNext;
    RadioButton radioButton_text;
    RadioButton radioButton_audio;
    RadioGroup radioGroup;
    RadioButton radioButton;

    DatabaseReference dbSignansMaster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mode);
        thedb = new dataBaseHelper(this);
        dbSignansMaster = FirebaseDatabase.getInstance().getReference();

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButton_text = (RadioButton) findViewById(R.id.radioButton_text);
        radioButton_audio = (RadioButton) findViewById(R.id.radioButton_audio);
        button_modeNext = (Button) findViewById(R.id.button_modeNext);


        button_modeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (radioButton_text.isChecked() ){
                    addModeText();
                }
                else if(radioButton_audio.isChecked()) {
                    addModeAudio();
                } else
                {
                    Toast.makeText(getApplicationContext(),"Please select a mode for the output",Toast.LENGTH_LONG);
                }

            }
        });
    }

    private void launchActivity() {

        Intent intent = new Intent(this, selectLanguage.class);
        startActivity(intent);
    }
    public void addModeText(){
        button_modeNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = thedb.insertData("text","");
                        if (isInserted== true)
                            Toast.makeText(selectMode.this,"Mode selected as text",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(selectMode.this,"Data is not inserted",Toast.LENGTH_SHORT).show();
                        launchActivity();
                    }
                });
    }
    public void addModeAudio(){
        button_modeNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = thedb.insertData("audio","");
                        if (isInserted== true)
                            Toast.makeText(selectMode.this,"Mode selected as audio",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(selectMode.this,"Data is not inserted",Toast.LENGTH_LONG).show();
                        launchActivity();
                    }
                });
    }

    }

