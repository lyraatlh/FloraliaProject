package com.example.floraliaproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class FlowerDetailActivity extends AppCompatActivity {

    private ImageView imageViewFlower;
    private TextView textViewName, textViewScientificName, textViewDescription;
    private Button buttonAddToFavorites;

    private FirebaseFirestore db;
    private String flowerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_detail);

        db = FirebaseFirestore.getInstance();

        imageViewFlower = findViewById(R.id.imageViewFlower);
        textViewName = findViewById(R.id.textViewName);
        textViewScientificName = findViewById(R.id.textViewScientificName);
        textViewDescription = findViewById(R.id.textViewDescription);
        buttonAddToFavorites = findViewById(R.id.buttonAddToFavorites);

        flowerName = getIntent().getStringExtra("flowerName");

        loadFlowerDetails();

        // Get data from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Log.d("FlowerDetail", "Received name: " + name);
        String scientificName = intent.getStringExtra("scientificName");
        String description = intent.getStringExtra("description");
        String imageUrl = intent.getStringExtra("imageUrl");

        // Set data to views
        textViewName.setText(name);
        textViewScientificName.setText(scientificName);
        textViewDescription.setText(description);
        Picasso.get().load(imageUrl).into(imageViewFlower);

        buttonAddToFavorites.setOnClickListener(v -> {
            Toast.makeText(this, name + " added to favorites!", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadFlowerDetails() {
        db.collection("flowers").whereEqualTo("name", flowerName)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    if (!querySnapshot.isEmpty()) {
                        Flower flower = querySnapshot.toObjects(Flower.class).get(0);
                        if (flower != null) {
                            textViewName.setText(flower.getName());
                            textViewScientificName.setText(flower.getScientificName());
                            textViewDescription.setText(flower.getDescription());
                            Picasso.get().load(flower.getImageUrl()).into(imageViewFlower);
                        }
                    } else {
                        Toast.makeText(this, "Flower not found", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to load details: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}