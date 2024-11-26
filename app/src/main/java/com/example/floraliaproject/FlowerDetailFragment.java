package com.example.floraliaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FlowerDetailFragment extends Fragment {

    private static final String ARG_LATIN_NAME = "latin_name";
    private static final String ARG_MEANING = "meaning";
    private static final String ARG_ORIGIN = "origin";

    private String latinName;
    private String meaning;
    private String origin;

    public FlowerDetailFragment() {
        // Required empty public constructor
    }

    public static FlowerDetailFragment newInstance(String latinName, String meaning, String origin) {
        FlowerDetailFragment fragment = new FlowerDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LATIN_NAME, latinName);
        args.putString(ARG_MEANING, meaning);
        args.putString(ARG_ORIGIN, origin);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            latinName = getArguments().getString(ARG_LATIN_NAME);
            meaning = getArguments().getString(ARG_MEANING);
            origin = getArguments().getString(ARG_ORIGIN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flower_detail, container, false);

        TextView tvLatinName = view.findViewById(R.id.tvLatinName);
        TextView tvMeaning = view.findViewById(R.id.tvMeaning);
        TextView tvOrigin = view.findViewById(R.id.tvOrigin);

        tvLatinName.setText("Latin Name: " + latinName);
        tvMeaning.setText("Meaning: " + meaning);
        tvOrigin.setText("Origin: " + origin);

        return view;
    }

    private void showSelectedFlower(Flower flower) {
        if (flower == null) return;  // Pastikan flower tidak null

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