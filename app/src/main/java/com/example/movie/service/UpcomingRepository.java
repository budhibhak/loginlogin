package com.example.movie.service;

import com.example.movie.model.upcoming.UpcomingResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UpcomingRepository {
    @GET("3/movie/upcoming?api_key=55c0673da0afcd2cd319e2d9b65bb111&language=en-US&page=1")
    Call<UpcomingResponse> getUpcoming();
}
