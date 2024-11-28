package com.example.floraliaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EditProfileFragment extends Fragment {

    private EditText etName, etBio;
    private Button btnSave;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        etName = view.findViewById(R.id.etName);
        etBio = view.findViewById(R.id.etBio);
        btnSave = view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            // Logic to save updated profile details
            // You can save it to a database or shared preferences
        });

        return view;
    }
}