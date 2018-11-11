package com.example.im2017.signansmaster;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class browseVideo extends AppCompatActivity {
    private static final int PICK_VIDEO = 100;
    private final int VIDEO_REQUEST_CODE =100;
    Button buttonBrowse;
    VideoView videoView;
    Uri videoUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_video);

        buttonBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallary();
            }
        });
    }
    private void openGallary(){
        Intent galary = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galary, PICK_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == VIDEO_REQUEST_CODE){
            if(resultCode == RESULT_OK && requestCode == PICK_VIDEO){
                videoUri = data.getData();
                videoView.setVideoURI(videoUri);

            }

        }
    }
}
