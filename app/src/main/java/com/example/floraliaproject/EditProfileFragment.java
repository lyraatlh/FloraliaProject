package com.example.floraliaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
            String name = etName.getText().toString().trim();
            String bio = etBio.getText().toString().trim();

            if (name.isEmpty() || bio.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Send data back to AccountFragment
                Bundle result = new Bundle();
                result.putString("name", name);
                result.putString("bio", bio);

                getParentFragmentManager().setFragmentResult("profileUpdate", result);

                // Show confirmation message
                Toast.makeText(getContext(), "Profile updated successfully!", Toast.LENGTH_SHORT).show();

                // Kembali ke AccountFragment
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }
}