package com.example.floraliaproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FlowerFragment extends Fragment {

    private RecyclerView recyclerView;
    private EditText edSearch;
    private Button btnSearch;
    private ProgressBar progressBar;

    private List<Flower> flowerList;
    private FlowerAdapter flowerAdapter;

    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flower, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewFlowers);
        edSearch = view.findViewById(R.id.edSearch);
        btnSearch = view.findViewById(R.id.btnSearch);
        progressBar = view.findViewById(R.id.progressBar);

        db = FirebaseFirestore.getInstance();

        flowerList = new ArrayList<>();
        flowerAdapter = new FlowerAdapter(flowerList, getContext());

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(flowerAdapter);

        loadFlowers(); // Load semua bunga saat fragment dibuka

        btnSearch.setOnClickListener(v -> performSearch());

        return view;
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
                    Toast.makeText(getContext(), "Failed to load data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                });
    }

    private void performSearch() {
        String query = edSearch.getText().toString().trim().toLowerCase();

        if (query.isEmpty()) {
            loadFlowers(); // Jika input kosong, tampilkan semua bunga
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        db.collection("flowers")
                .whereEqualTo("name", query)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        flowerList.clear();
                        flowerList.addAll(queryDocumentSnapshots.toObjects(Flower.class));
                        flowerAdapter.notifyDataSetChanged();
                        Log.d("SearchResults", "Found: " + queryDocumentSnapshots.size() + " results");
                    } else {
                        Log.d("SearchResults", "No results found for query: " + query);
                        Toast.makeText(getContext(), "No results found", Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);
                })
                .addOnFailureListener(e -> {
                    Log.e("SearchError", "Failed to search: " + e.getMessage());
                    Toast.makeText(getContext(), "Failed to search: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                });
    }
}