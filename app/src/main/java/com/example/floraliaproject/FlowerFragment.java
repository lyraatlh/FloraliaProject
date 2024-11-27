package com.example.floraliaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FlowerFragment extends Fragment {

    private RecyclerView rvFlowers;
    private ArrayList<Flower> flowerList = new ArrayList<>();
    private boolean isGridLayout = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flower, container, false);

        rvFlowers = view.findViewById(R.id.rv_flowers);
        rvFlowers.setHasFixedSize(true);

        flowerList.addAll(FlowerDetailFragment.getListData());

        ListFlowerAdapter adapter = new ListFlowerAdapter(flowerList);
        rvFlowers.setAdapter(adapter);

        adapter.setOnItemClickCallback(data ->
                Toast.makeText(getContext(), "You selected " + data.getName(), Toast.LENGTH_SHORT).show()
        );

        setLayoutManager(isGridLayout);

        setHasOptionsMenu(true);

        return view;
    }

    private void setLayoutManager(boolean isGrid) {
        if (isGrid) {
            rvFlowers.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            rvFlowers.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_list) {
            setLayoutManager(false);
        } else if (item.getItemId() == R.id.action_grid) {
            setLayoutManager(true);
        }
        return super.onOptionsItemSelected(item);
    }
}