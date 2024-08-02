package com.example.pet_book;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class UploadActivity extends AppCompatActivity {

    ImageView UploadImage;
    Button buttonSave;
    EditText UploadCategory, UploadName, UploadAgg, UploadBreeding, UploadSex, UploadColor, UploadNote;

    String imageURL;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        UploadImage = findViewById(R.id.Upload_image);
        UploadCategory = findViewById(R.id.Upload_category);
        UploadName = findViewById(R.id.Upload_name);
        UploadAgg = findViewById(R.id.Upload_agg);
        UploadBreeding = findViewById(R.id.Upload_breeding);
        UploadSex = findViewById(R.id.Upload_sex);
        UploadColor = findViewById(R.id.Upload_color);
        UploadNote = findViewById(R.id.Upload_note);
        buttonSave = findViewById(R.id.Button_save);

        ActivityResultCallback<ActivityResult> activityResultCallback = result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                if (data != null && data.getData() != null) {
                    uri = data.getData();
                    UploadImage.setImageURI(uri);
                } else {
                    Toast.makeText(UploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(UploadActivity.this, "Image selection canceled", Toast.LENGTH_SHORT).show();
            }
        };

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                activityResultCallback
        );

        UploadImage.setOnClickListener(view -> {
            Intent photoPicker = new Intent(Intent.ACTION_PICK);
            photoPicker.setType("image/*");
            activityResultLauncher.launch(photoPicker);
        });

        buttonSave.setOnClickListener(view -> saveData());
    }

    public void saveData() {
        if (uri == null) {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference().child("Android Images").child(Objects.requireNonNull(uri.getLastPathSegment()));

        AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(taskSnapshot -> {
            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
            uriTask.addOnCompleteListener(uriTask1 -> {
                if (uriTask1.isSuccessful()) {
                    Uri urlImage = uriTask1.getResult();
                    imageURL = urlImage.toString();
                    uploadData();
                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                    Toast.makeText(UploadActivity.this, "Failed to get image URL", Toast.LENGTH_SHORT).show();
                }
            });
        }).addOnFailureListener(e -> {
            dialog.dismiss();
            Toast.makeText(UploadActivity.this, "Failed to upload image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    public void uploadData() {
        String petcategory = UploadCategory.getText().toString();
        String petname = UploadName.getText().toString();
        String petagg = UploadAgg.getText().toString();
        String petbreeding = UploadBreeding.getText().toString();
        String petsex = UploadSex.getText().toString();
        String petcolor = UploadColor.getText().toString();
        String petnote = UploadNote.getText().toString();

        String petId = FirebaseDatabase.getInstance().getReference("Pet Details").push().getKey();

        DataClass dataClass = new DataClass(petcategory, petname, petagg, petbreeding, petsex, petcolor, petnote, imageURL);

        FirebaseDatabase.getInstance().getReference("Pet Details").child(petname)
                .setValue(dataClass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Pet details saved successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Failed to save pet details", Toast.LENGTH_SHORT).show();
                        Log.e("Firebase", "Error: " + task.getException());
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to save pet details: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("Firebase", "Error: " + e.getMessage());
                });

    }
}
