package com.example.floraliaproject;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment {

    private ImageView imgRecommendation;
    private TextView tvFlowerName, tvMeaning;
    private Handler handler;
    private Runnable imageSwitcher;
    private ArrayList<Flower> flowerList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        imgRecommendation = view.findViewById(R.id.img_recommendation);
        tvFlowerName = view.findViewById(R.id.tv_flower_name);
        tvMeaning = view.findViewById(R.id.tv_flower_meaning);
        flowerList = FlowerDetailFragment.getListData();

        startImageSwitching();

        return view;
    }

    private void startImageSwitching() {
        handler = new Handler();
        imageSwitcher = new Runnable() {
            @Override
            public void run() {
                int randomIndex = new Random().nextInt(flowerList.size());
                Flower randomFlower = flowerList.get(randomIndex);

                imgRecommendation.setImageResource(randomFlower.getPhoto());
                tvFlowerName.setText(randomFlower.getName());
                tvMeaning.setText(randomFlower.getMeaning());
                handler.postDelayed(this, 3000);
            }
        };

        handler.post(imageSwitcher);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (handler != null && imageSwitcher != null) {
            handler.removeCallbacks(imageSwitcher);
        }
    }
}