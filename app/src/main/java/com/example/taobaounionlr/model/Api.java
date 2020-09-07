package com.example.taobaounionlr.model;

import com.example.taobaounionlr.model.domain.Categories;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("discovery/categories")
    Call<Categories> getCategories();
}
