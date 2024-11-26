package com.example.floraliaproject;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FlowerFragment extends Fragment {

    private RecyclerView rvFlowers;
    private final ArrayList<Flower> list = new ArrayList<>();

    public FlowerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // Enable options menu in fragment
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flower, container, false);

        rvFlowers = view.findViewById(R.id.rv_flowers);
        rvFlowers.setHasFixedSize(true);

        list.addAll(getListFlowers(requireContext()));
        showRecyclerList();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_list) {
            rvFlowers.setLayoutManager(new LinearLayoutManager(requireContext()));
            Toast.makeText(requireContext(), "Tampilan diubah ke daftar", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_grid) {
            rvFlowers.setLayoutManager(new GridLayoutManager(requireContext(), 2));
            Toast.makeText(requireContext(), "Tampilan diubah ke grid", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Flower> getListFlowers(Context context) {
        String[] dataName = context.getResources().getStringArray(R.array.data_name);
        String[] dataLatinName = context.getResources().getStringArray(R.array.latin_name);
        String[] dataMeaning = context.getResources().getStringArray(R.array.flower_meaning);
        String[] dataOrigin = context.getResources().getStringArray(R.array.flower_origin);
        TypedArray dataPhoto = context.getResources().obtainTypedArray(R.array.data_photo);

        ArrayList<Flower> listFlower = new ArrayList<>();
        int length = Math.min(dataName.length, Math.min(dataMeaning.length, Math.min(dataLatinName.length, Math.min(dataOrigin.length, dataPhoto.length()))));
        for (int i = 0; i < length; i++) {
            Flower flower = new Flower();
            flower.setName(dataName[i]);
            flower.setDescription(dataMeaning[i]);
            flower.setLatinName(dataLatinName[i]);
            flower.setOrigin(dataOrigin[i]);
            flower.setMeaning(dataMeaning[i]);
            flower.setPhoto(dataPhoto.getResourceId(i, -1));
            listFlower.add(flower);
        }
        dataPhoto.recycle();
        return listFlower;
    }

    private void showRecyclerList() {
        rvFlowers.setLayoutManager(new LinearLayoutManager(requireContext()));
        ListFlowerAdapter adapter = new ListFlowerAdapter(list);
        rvFlowers.setAdapter(adapter);

        adapter.setOnItemClickCallback(this::showSelectedFlower);
    }

    private void showSelectedFlower(Flower flower) {
        FlowerDetailFragment detailFragment = FlowerDetailFragment.newInstance(
                flower.getLatinName(),
                flower.getMeaning(),
                flower.getOrigin()
        );

        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}