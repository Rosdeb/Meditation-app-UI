package com.example.test.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.test.Adapter.UserAdapter;
import com.example.test.ApiClient;
import com.example.test.Interface.Apiservice;
import com.example.test.Models.User;
import com.example.test.Models.CategoryResponse;
import com.example.test.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Music extends Fragment {


    SearchView searchView;
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    Handler handler = new Handler(Looper.getMainLooper());
    Runnable runnable;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_music, container, false);
        searchView=view.findViewById(R.id.searchView);


        recyclerView=view.findViewById(R.id.recylcerviewsearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(userAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fatchUser(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (runnable !=null){
                    handler.removeCallbacks(runnable);
                }
                runnable =() ->fatchUser(newText);
                handler.postDelayed(runnable,100);

                return true ;
            }
        });

        // Initialize Retrofit



        return view;
    }

private void fatchUser(String query){

    Apiservice apiService = ApiClient.getClient().create(Apiservice.class);
    // Call the API to fetch users
    Call<CategoryResponse> call = apiService.searchUsers(query);

    call.enqueue(new Callback<CategoryResponse>() {
        @Override
        public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                // Set up the adapter with the response data
                userAdapter = new UserAdapter(response.body().getItems());
                recyclerView.setAdapter(userAdapter);
            } else {
                Toast.makeText(getContext(), "No users found", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<CategoryResponse> call, Throwable t) {
            Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
        }
    });
}
}