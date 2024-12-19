package com.example.floraliaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class SearchPageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText edSearch;
    private Button btnSearch;
    private ProgressBar progressBar;

    private List<Flower> flowerList;
    private FlowerAdapter flowerAdapter;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        recyclerView = findViewById(R.id.recyclerViewFlowers);
        edSearch = findViewById(R.id.edSearch);
        btnSearch = findViewById(R.id.btnsearch);
        progressBar = findViewById(R.id.progressBar);

        db = FirebaseFirestore.getInstance();

        flowerList = new ArrayList<>();
        flowerAdapter = new FlowerAdapter(flowerList, this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(flowerAdapter);

        loadFlowers(); // Load semua bunga pada awalnya

        btnSearch.setOnClickListener(v -> performSearch());
    }

    private void loadFlowers() {
        progressBar.setVisibility(View.VISIBLE);

        db.collection("flowers")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    flowerList.clear();
                    flowerList.addAll(queryDocumentSnapshots.toObjects(Flower.class));
                    flowerAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to load data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                });
    }

    private void performSearch() {
        String query = edSearch.getText().toString().trim().toLowerCase();

        if (query.isEmpty()) {
            loadFlowers(); // Jika tidak ada input, tampilkan semua bunga
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        db.collection("flowers")
                .orderBy("name")
                .startAt(query)
                .endAt(query + "\uf8ff")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    flowerList.clear();
                    flowerList.addAll(queryDocumentSnapshots.toObjects(Flower.class));
                    flowerAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to search: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                });
    }
}