package com.example.fule_delivery_app;

import android.annotation.SuppressLint;
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

public class mechaniclogin extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin, btnForgot, btnRegister;
    DatabaseHelper db;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mechaniclogin);

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        btnForgot = findViewById(R.id.btnForgot);
        btnRegister = findViewById(R.id.btnRegister);

        db = new DatabaseHelper(this);

        btnLogin.setOnClickListener(view -> {
            String un = etUsername.getText().toString();
            String pw = etPassword.getText().toString();

            if(db.loginmechanic(un, pw)){
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), mechanicdashboard.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
            }
        });
        btnForgot.setOnClickListener(view -> startActivity(new Intent(this, mechanicforgot.class)));
        btnRegister.setOnClickListener(view -> startActivity(new Intent(this, mechanicregister.class)));
    }
}