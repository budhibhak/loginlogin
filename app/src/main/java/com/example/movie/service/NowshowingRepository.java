package com.example.movie.service;

import com.example.movie.model.nowshowing.NowshowingResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NowshowingRepository {
    @GET("3/movie/now_playing?api_key=55c0673da0afcd2cd319e2d9b65bb111")
    Call<NowshowingResponse> getNowshowing();
}
