package com.example.floraliaproject;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvFlowers;
    private final ArrayList<Flower> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvFlowers = findViewById(R.id.rv_flowers);
        rvFlowers.setHasFixedSize(true);

        list.addAll(getListFlowers());
        showRecyclerList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_list) {
            rvFlowers.setLayoutManager(new LinearLayoutManager(this));
            Toast.makeText(this, "Tampilan diubah ke daftar", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_grid) {
            rvFlowers.setLayoutManager(new GridLayoutManager(this, 2));
            Toast.makeText(this, "Tampilan diubah ke grid", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Flower> getListFlowers() {
        String[] dataName = getResources().getStringArray(R.array.data_name);
        String[] dataLatinName = getResources().getStringArray(R.array.latin_name);
        String[] dataMeaning = getResources().getStringArray(R.array.flower_meaning);
        String[] dataOrigin = getResources().getStringArray(R.array.flower_origin);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_photo);

        ArrayList<Flower> listFlower = new ArrayList<>();

        int length = Math.min(dataName.length, Math.min(dataMeaning.length, Math.min(dataLatinName.length, Math.min(dataOrigin.length, Math.min(dataMeaning.length, dataPhoto.length())))));
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
        rvFlowers.setLayoutManager(new LinearLayoutManager(this));
        ListFlowerAdapter adapter = new ListFlowerAdapter(list);
        rvFlowers.setAdapter(adapter);

        adapter.setOnItemClickCallback(this::showSelectedFlower);
    }

    private void showSelectedFlower(Flower flower) {
        // Ketika gambar bunga dipilih, tampilkan fragment dengan detail bunga
        FlowerDetailFragment detailFragment = FlowerDetailFragment.newInstance(
                flower.getLatinName(),
                flower.getMeaning(),
                flower.getOrigin()
        );

        // Ganti fragment dengan FlowerDetailFragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}