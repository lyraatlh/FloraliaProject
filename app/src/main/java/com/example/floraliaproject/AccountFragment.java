package com.example.floraliaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;

public class AccountFragment extends Fragment {
    FirebaseAuth auth;
    private TextView textViewName, textViewBio;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        auth = FirebaseAuth.getInstance();

        textViewName = view.findViewById(R.id.textViewName);
        textViewBio = view.findViewById(R.id.textViewBio);

        // Logout Button
        Button logoutButton = view.findViewById(R.id.buttonLogout);
        logoutButton.setOnClickListener(v -> {
            auth.signOut();
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish(); // Stop current activity
        });

        // Edit Profile Button
        Button editProfileButton = view.findViewById(R.id.buttonEditProfile);
        editProfileButton.setOnClickListener(v -> {
            // Replace current fragment with EditProfileFragment
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container_layout, new EditProfileFragment());
            transaction.addToBackStack(null); // Add to backstack to allow navigation back
            transaction.commit();
        });

        // Listen for fragment result (Profile update)
        getParentFragmentManager().setFragmentResultListener("profileUpdate", this, (requestKey, result) -> {
            String updatedName = result.getString("name");
            String updatedBio = result.getString("bio");

            // Update the UI with new data
            textViewName.setText(updatedName);
            textViewBio.setText(updatedBio);
        });

        return view;
    }
}