package com.example.fule_delivery_app;

import static androidx.core.location.LocationManagerCompat.isLocationEnabled;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class fuelrequest extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;
    TextView textView;
    Button btnGetLocation,btnSave;
    EditText edtName, edtNumber, edtCarNumber, edtType, edtQuantity;
    private static final int PERMISSION_ID = 44;

    double lat = 0, lon = 0;
    DatabaseHelper db;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fuelrequest);

        db = new DatabaseHelper(this);

        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtCarNumber = findViewById(R.id.edtCarNumber);
        edtType = findViewById(R.id.edtType);
        edtQuantity = findViewById(R.id.edtQuantity);



        btnSave = findViewById(R.id.btnSave);

       textView = findViewById(R.id.txtLocation);
        btnGetLocation = findViewById(R.id.btnLocation);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btnGetLocation.setOnClickListener(v -> getLastLocation());

        btnSave.setOnClickListener(v -> saveData());

    }

    private void saveData() {

        int qty = Integer.parseInt(edtQuantity.getText().toString());

        if (qty >= 3) {
            Toast.makeText(this, "Quantity must be less than 3", Toast.LENGTH_SHORT).show();
            return;
        }

        PetrolRequest user = new PetrolRequest(
                edtName.getText().toString(),
                edtNumber.getText().toString(),
                edtCarNumber.getText().toString(),
                edtType.getText().toString(),
                qty,
                lat, lon);

        if (db.insertpetrolrequest(user)) {
            Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to Save Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void getLastLocation() {
        if (checkPermissions()) {

            if (isLocationEnabled()) {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                fusedLocationProviderClient.getLastLocation()
                        .addOnSuccessListener(location -> {
                            if (location != null) {
                                double latitude = location.getLatitude();
                                double longitude = location.getLongitude();

                                textView.setText("Latitude: " + latitude +
                                        "\nLongitude: " + longitude);

                                lat = latitude;
                                lon = longitude;
                            }
                        });

            } else {
                Toast.makeText(this, "Turn on Location (GPS)", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }

        } else {
            requestPermissions();
        }
    }

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                PERMISSION_ID);
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }
}