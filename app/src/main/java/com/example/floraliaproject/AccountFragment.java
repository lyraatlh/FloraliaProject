package com.example.floraliaproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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

import java.io.InputStream;
import java.net.URL;

public class AccountFragment extends Fragment {

    private ImageView ivProfilePicture;
    private TextView tvProfileName, tvProfileBio, tvFollowersCount, tvFollowingCount;
    private Button btnEditProfile;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        // Initialize views
        ivProfilePicture = view.findViewById(R.id.ivProfilePicture);
        tvProfileName = view.findViewById(R.id.tvProfileName);
        tvProfileBio = view.findViewById(R.id.tvProfileBio);
        tvFollowersCount = view.findViewById(R.id.tvFollowersCount);
        tvFollowingCount = view.findViewById(R.id.tvFollowingCount);
        btnEditProfile = view.findViewById(R.id.btnEditProfile);

        // Load user data (This can be fetched from a database or API)
        tvProfileName.setText("Jill Valentine");
        tvProfileBio.setText("I am a UX/UI Designer.");
        tvFollowersCount.setText("1.5K");
        tvFollowingCount.setText("507");

        // Load profile picture from URL (or local path)
        String imageUrl = "https://yourimageurl.com/path/to/image.jpg"; // Replace with actual URL
        new DownloadImageTask(ivProfilePicture).execute(imageUrl);

        // Button to edit profile
        btnEditProfile.setOnClickListener(v -> showEditProfileDialog());

        return view;
    }

    private void showEditProfileDialog() {
        // Logic for showing an edit profile dialog or navigating to an edit screen
        EditProfileFragment editProfileFragment = new EditProfileFragment();
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, editProfileFragment)
                .addToBackStack(null)
                .commit();
    }

    // AsyncTask to download and set image in ImageView
    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView imageView;

        public DownloadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageUrl = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(imageUrl).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                imageView.setImageBitmap(result);
            }
        }
    }
}