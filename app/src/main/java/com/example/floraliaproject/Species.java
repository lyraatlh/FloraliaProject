package com.example.floraliaproject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Species {
    @SerializedName("id")
    private int id;

    @SerializedName("common_name")
    private String commonName;

    @SerializedName("scientific_name")
    private List<String> scientificName;

    @SerializedName("family")
    private String family;

    @SerializedName("image_url")
    private String imageUrl;

    public int getId() { return id; }
    public String getCommonName() { return commonName; }
    public List<String> getScientificName() { return scientificName; }
    public String getFamily() { return family; }
    public String getImageUrl() { return imageUrl; }
}