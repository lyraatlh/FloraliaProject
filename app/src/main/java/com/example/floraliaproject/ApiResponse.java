package com.example.floraliaproject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName("data")
    private List<Species> data;

    public List<Species> getData() { return data; }
}