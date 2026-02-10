package com.example.fule_delivery_app;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Showgarage extends AppCompatActivity {

    private RecyclerView recyclerDoctors;
    private EditText etSearch;
    private GarageAdapter adapter;
    private DatabaseHelper db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_showgarage);

        db = new DatabaseHelper(this);


        recyclerDoctors = findViewById(R.id.recyclerDoctors);
        etSearch = findViewById(R.id.etSearch);


        List<Garage> doctors = db.getAllGarages();


        adapter = new GarageAdapter(this, doctors);
        recyclerDoctors.setLayoutManager(new LinearLayoutManager(this));
        recyclerDoctors.setAdapter(adapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }


            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}