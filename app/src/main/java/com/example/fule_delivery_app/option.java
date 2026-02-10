package com.example.fule_delivery_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class option extends AppCompatActivity {

    ImageView img1,img2,img3,img4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_option);

        img1 = findViewById(R.id.imgmaganer);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Managerregister.class);
                startActivity(intent);
            }
        });

        img2 = findViewById(R.id.imgUser);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Userlogin.class);
                startActivity(intent);
            }
        });

        img3 = findViewById(R.id.imgDelivery);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Deliverylogin.class);
                startActivity(intent);
            }
        });

        img4 = findViewById(R.id.imgMechanic);
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),mechaniclogin.class);
                startActivity(intent);
            }
        });
    }
}