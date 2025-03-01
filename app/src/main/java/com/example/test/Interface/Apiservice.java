package com.example.test.Interface;

import com.example.test.Models.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apiservice {

    @GET("search/users")
    Call<CategoryResponse> searchUsers(@Query("q")String query);

}
