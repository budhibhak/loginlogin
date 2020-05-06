package com.example.movie.view.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movie.model.nowshowing.NowshowingResponse;
import com.example.movie.model.nowshowing.NowshowingResultsItem;
import com.example.movie.service.ApiMain;
import com.example.movie.service.NowshowingRepository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowshowingViewModel extends AndroidViewModel {
    private ApiMain apiMain;

    private ArrayList<NowshowingResultsItem> nowshowingItems;
    private MutableLiveData<ArrayList<NowshowingResultsItem>> ListNowshowingMovie = new MutableLiveData<>();

    public NowshowingViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<ArrayList<NowshowingResultsItem>> getListNowshowingMovie(){
        NowshowingRepository nowshowingRepository = ApiMain.getApiNowshowing();
        Call<NowshowingResponse> call = nowshowingRepository.getNowshowing();
        call.enqueue(new Callback<NowshowingResponse>() {
            @Override
            public void onResponse(Call<NowshowingResponse> call, Response<NowshowingResponse> response) {
                NowshowingResponse nowshowingResponse = response.body();

                nowshowingItems = (ArrayList<NowshowingResultsItem>) nowshowingResponse.getResults();
                ListNowshowingMovie.setValue(nowshowingItems);
            }

            @Override
            public void onFailure(Call<NowshowingResponse> call, Throwable t) {

            }
        });
        return ListNowshowingMovie;
    }


        public LiveData<ArrayList<NowshowingResultsItem>> getNowshowing(){
        return getListNowshowingMovie();
        }
}
