package com.example.covid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface covidAPI {
    @GET("summary")
    Call<APIPlaceholder> getPosts();
}
