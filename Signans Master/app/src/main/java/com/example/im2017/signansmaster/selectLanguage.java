package com.example.im2017.signansmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class selectLanguage extends AppCompatActivity {

    Button button_langNext;
    RadioGroup radioGroup_l;
    RadioButton radioButton;

    RadioButton radioButton_sinhala;
    RadioButton radioButton_tamil;
    RadioButton radioButton_english;
    dataBaseHelper thedb;
    RadioGroup radioGroupFromInputLang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
        thedb = new dataBaseHelper(this);
        button_langNext = (Button) findViewById(R.id.button_langNext);
        radioButton_sinhala = (RadioButton) findViewById(R.id.radioButton_sinhala);
        radioButton_tamil = (RadioButton) findViewById(R.id.radioButton_tamil);
        radioButton_english = (RadioButton) findViewById(R.id.radioButton_english);
        radioGroup_l = (RadioGroup) findViewById(R.id.radioGroup_l);

        button_langNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButton_sinhala.isChecked() ){
                    addLanSinhala();


                }
                else if(radioButton_tamil.isChecked()) {
                    addLanTamil();

                } else if(radioButton_english.isChecked()) {
                    addLanEnglish();

                }else
                {
                    Toast.makeText(getApplicationContext(),"Please select a mode for the output",Toast.LENGTH_LONG);
                }

            }
        });
    }

    private void launchActivity() {

        Intent intent = new Intent(this, getVideo.class);
        startActivity(intent);
    }
    public void addLanSinhala(){
        button_langNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdated = thedb.updateData("","","Sinhala");
                        if (isUpdated== true)
                            Toast.makeText(selectLanguage.this,"Language selected as Sinhala",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(selectLanguage.this,"Data is not inserted",Toast.LENGTH_LONG).show();
                        launchActivity();
                        check();
                    }

                });
    }
    public void addLanEnglish(){
        button_langNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdated = thedb.updateData("","","English");
                        if (isUpdated== true)
                            Toast.makeText(selectLanguage.this,"Language selected as English",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(selectLanguage.this,"Data is not inserted",Toast.LENGTH_LONG).show();
                        launchActivity();
                    }

                });
    }
    public void addLanTamil(){
        button_langNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdated = thedb.updateData("","","Tamil");
                        if (isUpdated== true)
                            Toast.makeText(selectLanguage.this,"Language selected as Tamil",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(selectLanguage.this,"Data is not inserted",Toast.LENGTH_LONG).show();
                        launchActivity();
                    }

                });
    }
    public void check(){
        button_langNext.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String result = thedb.selectData();
                        System.out.print(result);
                        System.out.print("****************************************************");
                    }

                });
    }
    }

