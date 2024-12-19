package com.example.floraliaproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    FlowerAdapter adapter;
    FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        db = FirebaseFirestore.getInstance();

        loadFlowers();

        return view;
    }

    private void loadFlowers() {
        db.collection("flowers").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Flower> flowerList = new ArrayList<>();
                for (DocumentSnapshot doc : task.getResult()) {
                    Flower flower = doc.toObject(Flower.class);
                    flowerList.add(flower);
                }
                adapter = new FlowerAdapter(flowerList, getContext());
                recyclerView.setAdapter(adapter);
            }
        });
    }
}