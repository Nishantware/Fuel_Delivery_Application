package com.example.fule_delivery_app;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
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

import java.util.List;

public class Uploadgarage extends AppCompatActivity {

    private static final int REQUEST_CODE_PICK_IMAGE = 101;
    EditText etName, etAddress, etNumber, etTime, etServices;
    ImageView imagePreview;
    Button selectImageBtn, saveBtn;
    Uri selectedImageUri;
    DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_uploadgarage);

        db = new DatabaseHelper(this);

        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etNumber = findViewById(R.id.etNumber);
        etTime = findViewById(R.id.etTime);
        etServices = findViewById(R.id.etServices);
        imagePreview = findViewById(R.id.imagePreview);
        selectImageBtn = findViewById(R.id.selectImageBtn);
        saveBtn = findViewById(R.id.saveBtn);

        selectImageBtn.setOnClickListener(v -> openImage());

        saveBtn.setOnClickListener(v -> saveData());


    }

    private void openImage() {
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
                    imagePreview.setImageURI(selectedImageUri);
                }
            }
        }
    }

    private void saveData() {
        String name = etName.getText().toString();
        String address = etAddress.getText().toString();
        String number = etNumber.getText().toString();
        String time = etTime.getText().toString();
        String services = etServices.getText().toString();

        if (name.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        double lat = 0, lng = 0;
        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> list = geocoder.getFromLocationName(address, 1);
            if (!list.isEmpty()) {
                lat = list.get(0).getLatitude();
                lng = list.get(0).getLongitude();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Garage garage = new Garage(name, address, number, time, services,
                selectedImageUri != null ? selectedImageUri.toString() : "",
                lat, lng);

        long id = db.insertGarage(garage);

        if (id > 0) {
            Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_SHORT).show();
           // finish();
        } else {
            Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
        }
    }
}