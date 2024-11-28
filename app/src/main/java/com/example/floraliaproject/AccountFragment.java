package com.example.floraliaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AccountFragment extends Fragment {

    private ImageView ivProfilePicture;
    private TextView tvProfileName, tvProfileBio;
    private Button btnEditProfile, btnFavorites, btnSettings, btnAbout, btnLogout;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        // Inisialisasi komponen UI
        ivProfilePicture = view.findViewById(R.id.ivProfilePicture);
        tvProfileName = view.findViewById(R.id.tvProfileName);
        tvProfileBio = view.findViewById(R.id.tvProfileBio);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);
        btnFavorites = view.findViewById(R.id.btnFavorites);
        btnSettings = view.findViewById(R.id.btnSettings);
        btnAbout = view.findViewById(R.id.btnAbout);
        btnLogout = view.findViewById(R.id.btnLogout);

        tvProfileName.setText("bluebskyy");
        tvProfileBio.setText("\uD83C\uDF38 Flower lover with a passion for nature's beauty. Always chasing blooms, capturing their magic, and finding joy in every petal. \uD83C\uDF3F✨");

        btnEditProfile.setOnClickListener(v -> openEditProfileFragment());
        btnFavorites.setOnClickListener(v -> openFavorites());
        btnSettings.setOnClickListener(v -> openSettings());
        btnAbout.setOnClickListener(v -> openAbout());
        btnLogout.setOnClickListener(v -> logout());

        return view;
    }

    private void openEditProfileFragment() {
        EditProfileFragment editProfileFragment = new EditProfileFragment();
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, editProfileFragment)
                .addToBackStack(null)
                .commit();
    }

    private void openFavorites() {
    }

    private void openSettings() {

    }

    private void openAbout() {

    }

    private void logout() {

    }
}