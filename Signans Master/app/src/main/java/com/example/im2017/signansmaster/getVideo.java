package com.example.im2017.signansmaster;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.OnProgressListener;

import java.io.File;

import static java.nio.file.Paths.get;

public class getVideo extends AppCompatActivity {

    Button button;
    private Uri videouri;
    private final int VIDEO_REQUEST_CODE = 100;
    //private StorageReference videoref;
    StorageReference storageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_video);
        button = (Button) findViewById(R.id.btn_submit);
        storageRef = FirebaseStorage.getInstance().getReference();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Uri file = Uri.fromFile(new File("sdcard/Signans Master/sampleVideo.mp4"));
//                StorageReference riversRef = storageRef.child("sampleVideo");
//
//                riversRef.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
//                                                             {
//                                                                 @Override
//                                                                 public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                                                     // Get a URL to the uploaded content
//
//                                                                     //Uri downloadUrl = taskSnapshot.getDownloadUrl();
//
//                                                                     launchActivity();
//                                                                 }
//                                                             }
//                ).addOnFailureListener(new OnFailureListener()
//                {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        // Handle unsuccessful uploads
//                        // ...
//                        exception.printStackTrace();
//                    }
//                });

                File file = new File("sdcard/Signans Master/sampleVideo.mp4");
                FirebaseUtils firebaseUtils = new FirebaseUtils();
                Intent intent = new Intent(getVideo.this, animation.class);
                firebaseUtils.uploadFile(getVideo.this, file, intent);

            }
        });


    }

    public void captureVideo(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File videoFile = getFilePath();
        Uri videoUri = Uri.fromFile(videoFile);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
        cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(cameraIntent, VIDEO_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == VIDEO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Toast.makeText(getApplicationContext(), "video is successfully recorded", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "video is not recorded successfully", Toast.LENGTH_LONG).show();
            }
        }
    }

    public File getFilePath() {
        File folder = new File("sdcard/Signans Master");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File videoFile = new File(folder, "sampleVideo.mp4");
        return videoFile;
    }

    private void launchActivity() {
        Intent intent = new Intent(this, animation.class);
        startActivity(intent);
    }
//*****************************************************************************************************************


}
