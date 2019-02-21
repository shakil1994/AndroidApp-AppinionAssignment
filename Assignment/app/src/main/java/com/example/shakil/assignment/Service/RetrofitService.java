package com.example.shakil.assignment.Service;

import com.example.shakil.assignment.Model.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitService {
    @GET
    Call<Model> getAllItems(@Url String urlString);
}
