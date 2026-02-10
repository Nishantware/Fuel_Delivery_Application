package com.example.fule_delivery_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Deliveryregister extends AppCompatActivity {

    EditText etFullname, etAddress, etNumber, etUsername, etPassword;
    Button btnRegister, btnLogin;
    DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deliveryregister);

        etFullname = findViewById(R.id.fullname);
        etAddress = findViewById(R.id.address);
        etNumber = findViewById(R.id.number);
        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        btnRegister = findViewById(R.id.btnRegister);


        db = new DatabaseHelper(this);


        btnRegister.setOnClickListener(view -> {
            String fn = etFullname.getText().toString();
            String ad = etAddress.getText().toString();
            String num = etNumber.getText().toString();
            String un = etUsername.getText().toString();
            String pw = etPassword.getText().toString();

            if (fn.isEmpty() || ad.isEmpty() || num.isEmpty() || un.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show();
            } else {
                boolean inserted = db.registerDelivery(fn, ad, num, un, pw);
                if (inserted) {
                    Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, Deliverylogin.class));
                } else {
                    Toast.makeText(this, "Username already exists!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}