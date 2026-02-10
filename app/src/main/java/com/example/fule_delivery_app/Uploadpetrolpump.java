package com.example.fule_delivery_app;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Uploadpetrolpump extends AppCompatActivity {

    private static final int REQUEST_CODE_PICK_IMAGE = 101;


    private ImageView ivPhoto;
    private Button btnPickImage, btnSave;
    private EditText etName, etAddress, etPhone, etTime, etType;


    private Uri selectedImageUri;
    private DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_uploadpetrolpump);


        ivPhoto = findViewById(R.id.ivPhoto);
        btnPickImage = findViewById(R.id.btnPickImage);
        btnSave = findViewById(R.id.btnSave);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);
        etTime = findViewById(R.id.etTime);
        etType = findViewById(R.id.etCharges);


        db = new DatabaseHelper(this);


        btnPickImage.setOnClickListener(v -> pickImage());


        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String time = etTime.getText().toString().trim();
            String type = etType.getText().toString().trim();


            if (name.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Please enter name and address", Toast.LENGTH_SHORT).show();
                return;
            }


// Geocode address in background thread
            new Thread(() -> {
                double lat = 0.0, lng = 0.0;
                Geocoder geocoder = new Geocoder(Uploadpetrolpump.this, Locale.getDefault());
                try {
                    List<Address> results = geocoder.getFromLocationName(address, 1);
                    if (results != null && !results.isEmpty()) {
                        Address addr = results.get(0);
                        lat = addr.getLatitude();
                        lng = addr.getLongitude();

                    }
                } catch (IOException e) {
                    Log.e("Geocode", "Failed to geocode address", e);
                }
                Petrolpump d = new Petrolpump(name, address, phone, time, type,
                        selectedImageUri != null ? selectedImageUri.toString() : null,lat,lng);
                long id = db.insertPetrolpump(d);



            }).start();
            Toast.makeText(getApplicationContext(),"Upload Successfully",Toast.LENGTH_LONG).show();

        });
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                selectedImageUri = data.getData();
                if (selectedImageUri != null) {
// Persist read permission so we can read this URI later
                    final int takeFlags = data.getFlags() & (Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    getContentResolver().takePersistableUriPermission(selectedImageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    ivPhoto.setImageURI(selectedImageUri);
                }
            }
        }
    }
}