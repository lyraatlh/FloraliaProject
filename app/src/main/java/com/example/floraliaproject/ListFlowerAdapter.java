package com.example.floraliaproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListFlowerAdapter extends RecyclerView.Adapter<ListFlowerAdapter.ListViewHolder> {
    private ArrayList<Flower> listFlower;

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListFlowerAdapter(ArrayList<Flower> list) {
        this.listFlower = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_flower, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Flower flower = listFlower.get(position);
        holder.imgPhoto.setImageResource(flower.getPhoto());
        holder.tvName.setText(flower.getName());
        holder.tvLatinName.setText(flower.getLatinName());
        holder.tvOrigin.setText(flower.getOrigin());
        holder.tvMeaning.setText(flower.getMeaning());

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(holder.itemView.getContext(),
                    "You selected " + listFlower.get(holder.getAdapterPosition()).getName(),
                    Toast.LENGTH_SHORT).show();
        });

        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listFlower.get(holder.getAdapterPosition())));
    }

    public interface OnItemClickCallback {
        void onItemClicked(Flower data);
    }

    @Override
    public int getItemCount() {
        return listFlower.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvLatinName, tvOrigin, tvMeaning;

        ListViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_flower_photo);
            tvName = itemView.findViewById(R.id.tv_flower_name);
            tvLatinName = itemView.findViewById(R.id.tv_latin_name);
            tvMeaning = itemView.findViewById(R.id.tv_flower_meaning);
            tvOrigin = itemView.findViewById(R.id.tv_flower_origin);
        }
    }
}