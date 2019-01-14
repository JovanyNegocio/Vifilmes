package com.jovavanguiagamil.mooviz.api;


import com.jovavanguiagamil.mooviz.models.Page;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TmdbApi {
    @GET("discover/movie?api_key=aa9bd18dbaba3b0aebaed3e15f13fcf5")
    Call<Page> getPopularMovie();
}
