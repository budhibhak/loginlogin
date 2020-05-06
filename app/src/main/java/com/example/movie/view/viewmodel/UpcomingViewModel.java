package com.example.movie.view.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movie.model.upcoming.UpcomingResponse;
import com.example.movie.model.upcoming.UpcomingResultsItem;
import com.example.movie.service.ApiMain;
import com.example.movie.service.UpcomingRepository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingViewModel extends AndroidViewModel {
    private ApiMain apiMain;

    private ArrayList<UpcomingResultsItem> upcomingItems = new ArrayList<>();
    private MutableLiveData<ArrayList<UpcomingResultsItem>> listUpcomingMovie = new MutableLiveData<>();

    public UpcomingViewModel(@NonNull Application application){
        super(application);
    }
    public MutableLiveData<ArrayList<UpcomingResultsItem>> getListUpcomingMovie(){
        UpcomingRepository upcomingRepository = ApiMain.getApiUpcoming();
        Call<UpcomingResponse> call = upcomingRepository.getUpcoming();
        call.enqueue(new Callback<UpcomingResponse>() {
            @Override
            public void onResponse(Call<UpcomingResponse> call, Response<UpcomingResponse> response) {
                UpcomingResponse upcomingResponse = response.body();

                upcomingItems = upcomingResponse.getResults();
                listUpcomingMovie.setValue(upcomingItems);
            }

            @Override
            public void onFailure(Call<UpcomingResponse> call, Throwable t) {

            }
        });

        return listUpcomingMovie;
    }

        public LiveData<ArrayList<UpcomingResultsItem>> getUpcoming(){
        return getListUpcomingMovie();
        }
    }


