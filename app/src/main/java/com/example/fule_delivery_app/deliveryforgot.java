package com.example.fule_delivery_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class deliveryforgot extends AppCompatActivity {

    EditText etUsername, etNewPassword;
    Button btnUpdate;
    DatabaseHelper db;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deliveryforgot);

        etUsername = findViewById(R.id.username);
        etNewPassword = findViewById(R.id.newPassword);
        btnUpdate = findViewById(R.id.btnUpdate);

        db = new DatabaseHelper(this);

        btnUpdate.setOnClickListener(view -> {
            boolean updated = db.DeliveryupdatePassword(etUsername.getText().toString(), etNewPassword.getText().toString());
            if(updated){
                Toast.makeText(this, "Password Updated!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "User not found!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}