package com.example.floraliaproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListFlowerAdapter extends RecyclerView.Adapter<ListFlowerAdapter.ListViewHolder> {
    private final ArrayList<Flower> listFlower;
    private OnItemClickCallback onItemClickCallback;

    public ListFlowerAdapter(ArrayList<Flower> list) {
        this.listFlower = list;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_flower, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Flower flower = listFlower.get(position);
        holder.imgPhoto.setImageResource(flower.getPhoto());
        holder.tvName.setText(flower.getName());
        holder.tvDescription.setText(flower.getDescription());

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickCallback != null && holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                onItemClickCallback.onItemClicked(listFlower.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFlower.size();
    }

    public interface OnItemClickCallback {
        void onItemClicked(Flower data);
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgPhoto;
        final TextView tvName;
        final TextView tvDescription;

        ListViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
        }
    }
}