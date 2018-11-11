package com.example.im2017.signansmaster;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class viewOutput extends AppCompatActivity {
   // EditText MyInputText;
   Button button;
    Button MyTranslateButton;
    TextView MyOutputText;
    String text;
    private Button.OnClickListener MyTranslateButtonOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_output);

       // MyInputText = (EditText)findViewById(R.id.InputText);
        MyTranslateButton = (Button)findViewById(R.id.TranslateButton);
        MyOutputText = (TextView)findViewById(R.id.OutputText);

        //MyTranslateButton.setOnClickListener(MyTranslateButtonOnClickListener);
        MyTranslateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // text = MyInputText.getText().toString();
                text = "Thank you";
                Log.d("TRANSLATOR_api", "input : " + text);

                Translate translate = new Translate();

                translate.execute(text);

                Log.d("TRANSLATOR_api", "translated : " + text);
            }
        });


//        MyTranslateButtonOnClickListener = new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                text = MyInputText.getText().toString();
//                Log.d("TRANSLATOR_api", "input : " + text);
//                Translate translate = new Translate();
//
//                translate.execute(text);
//
//                Log.d("TRANSLATOR_api", "translated : " + text);
//            }
//
//        };
        button = (Button) findViewById(R.id.buttonBackToHome);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchActivityToHome();
            }
        });

    }
    class Translate extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... params) {
            ///https://cloud.google.com/translate/docs/languages

            String text = params[0]; //text to translate
//            TranslateOptions options = TranslateOptions.newBuilder()
//            .setApiKey("AIzaSyBObQyamWBiQS6rG7rKFJnkBdaFPrUezDs")
//                    .build();
//            com.google.cloud.translate.Translate translate=options.getService();
//            TranslateOptions options = TranslateOptions
//            com.google.cloud.translate.Translate translate = TranslateOptions.getDefaultInstance().getService();
            com.google.cloud.translate.Translate translate = TranslateOptions.newBuilder().setApiKey(getString(R.string.TRANSLATOR_api)).build().getService();
            Translation translation =
                    translate.translate(
                            text,
                            TranslateOption.sourceLanguage("en"),
                            TranslateOption.targetLanguage("si"));


            System.out.print("4444444444444444444444444444  asynktask");
            //   MyOutputText.setText(translation.getTranslatedText());

//.........added 7/10/18
            // MyOutputText.setText(translation.getTranslatedText());
//            Toast.makeText(getApplicationContext(), translation.getTranslatedText(), Toast.LENGTH_SHORT).show();
//........
            String te = translation.getTranslatedText();
            Log.d("TRANSLATOR_api", "trnaslated async : " + te);
//            return translation.getTranslatedText();
            return te;
        }

        //this method will run after doInBackground execution
        protected void onPostExecute(String result) {
            Log.d("TRANSLATOR_api", "trnaslated post : " + result);
            MyOutputText.setText(result);
        }
    }
//    private Button.OnClickListener MyTranslateButtonOnClickListener
//            = new Button.OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//            // TODO Auto-generated method stub
//            String InputString;
//            String OutputString = null;
//            InputString = MyInputText.getText().toString();
//
//            try {
//                //Translate.setHttpReferrer("http://android-er.blogspot.com/");
//               // OutputString = Translate.execute(InputString,
//                        //Language.ENGLISH, Language.FRENCH);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                OutputString = "Error";
//            }
//
//            MyOutputText.setText(OutputString);
//
//        }
//    };

    private void launchActivityToHome() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
