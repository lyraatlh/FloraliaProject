package com.example.floraliaproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder> {
    private List<Flower> flowerList;
    private Context context;

    public FlowerAdapter(List<Flower> flowerList, Context context) {
        this.flowerList = flowerList;
        this.context = context;
    }

    @NonNull
    @Override
    public FlowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_flower, parent, false);
        return new FlowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlowerViewHolder holder, int position) {

        Flower flower = flowerList.get(position);
        holder.textViewName.setText(flower.getName());

        holder.textViewFunFact.setText("Fun Fact: " + flower.getFunFact());
        holder.textViewDescription.setText(flower.getDescription());

        Picasso.get().load(flower.getImageUrl()).into(holder.imageViewFlower);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FlowerDetailActivity.class);
            intent.putExtra("name", flower.getName());
            intent.putExtra("scientificName", flower.getScientificName());
            intent.putExtra("description", flower.getDescription());
            intent.putExtra("imageUrl", flower.getImageUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return flowerList.size();
    }

    static class FlowerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewFlower;
        TextView textViewName, textViewFunFact, textViewDescription;

        public FlowerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewFlower = itemView.findViewById(R.id.imageViewFlower);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewFunFact = itemView.findViewById(R.id.textViewFunFact);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }
}