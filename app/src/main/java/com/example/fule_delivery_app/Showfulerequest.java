package com.example.fule_delivery_app;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Showfulerequest extends AppCompatActivity {


    RecyclerView recyclerView;
    SearchView searchView;
    PRequestAdapter adapter;
    DatabaseHelper db;
    ArrayList<PetrolRequest> userList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_showfulerequest);


        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);

        db = new DatabaseHelper(this);
        userList = db.getAllPREQUEST();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PRequestAdapter(this, userList);
        recyclerView.setAdapter(adapter);

        // Search function
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}