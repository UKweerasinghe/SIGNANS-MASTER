package com.example.im2017.signansmaster;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

/**
 * Created by admin on 9/27/2018.
 */


public class FirebaseUtils {
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public FirebaseUtils() {
        super();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        try {
            database.setPersistenceEnabled(true);
        } catch (Exception ignored) {
        }
        reference = database.getReference();
        storageReference = storage.getReference();
    }

    public DatabaseReference getReference() {
        return reference;
    }

    public DatabaseReference getReference(String path) {
        return reference.child(path);
    }

    public String pushObject(String path, Object object) {
        String key = reference.child(path).push().getKey();
        reference.child(path).child(key).setValue(object);
        return key;
    }

    public void updateObject(String path, Object object) {
        reference.child(path).setValue(object);
    }

    public void deleteObject(String path) {
        reference.child(path).setValue(null);
    }

    public StorageReference getStorageReference() {
        return storageReference;
    }


    public void uploadFile(final AppCompatActivity activity, File filePath, final Intent nextIntent) {

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(activity);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("sample_video");
            ref.putFile(Uri.fromFile(filePath))
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(activity, "Video is Successfully Uploaded", Toast.LENGTH_SHORT).show();
                            activity.startActivity(nextIntent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(activity, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("FIREBASELOG",e.getMessage());
                            e.printStackTrace();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
    }
}

