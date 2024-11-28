package com.example.floraliaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class HomeFragment extends Fragment {

    private RecyclerView rvFlowers;
    private ArrayList<Flower> flowerList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Ambil RecyclerView
        rvFlowers = view.findViewById(R.id.rv_flowers);
        rvFlowers.setHasFixedSize(true);

        // Ambil data bunga
        flowerList = FlowerDetailFragment.getListData();

        // Acak daftar bunga
        Collections.shuffle(flowerList);

        // Ambil 5 bunga teratas
        ArrayList<Flower> randomFlowers = new ArrayList<>(flowerList.subList(0, 5));

        // Tampilkan di RecyclerView
        ListFlowerAdapter adapter = new ListFlowerAdapter(randomFlowers);
        rvFlowers.setAdapter(adapter);
        rvFlowers.setLayoutManager(new GridLayoutManager(getContext(), 2));

        return view;
    }
}