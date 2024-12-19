package com.example.floraliaproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("species-list")
    Call<ApiResponse> getSpeciesList(@Query("key") String apiKey);
}