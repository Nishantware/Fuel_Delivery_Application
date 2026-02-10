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

public class Managerregister extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_managerregister);

        etUsername = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> {
            String un = etUsername.getText().toString();
            String pw = etPassword.getText().toString();

            if(un.equals("Manager@123") && pw.equals("12345"))
            {

                Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),Managerdashbord.class);
                startActivity(intent);

            }else {
                Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_LONG).show();
            }


        });


    }
}