package com.example.movie.view.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.movie.R;
import com.example.movie.adapter.NowshowingAdapter;
import com.example.movie.model.nowshowing.NowshowingResultsItem;
import com.example.movie.view.activity.DetailNowshowingActivity;
import com.example.movie.view.viewmodel.NowshowingViewModel;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowshowingFragment extends Fragment {

    private ArrayList<NowshowingResultsItem> nowshowingItems;
    private NowshowingViewModel nowshowingViewModel;
    private NowshowingAdapter nowshowingAdapter;
    private RecyclerView rvNowshowing;

    public NowshowingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nowshowing, container, false);
        rvNowshowing = view.findViewById(R.id.nowshowing_fragment);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nowshowingViewModel= ViewModelProviders.of(this).get(NowshowingViewModel.class);
        nowshowingViewModel.getNowshowing().observe(this, new Observer<ArrayList<NowshowingResultsItem>>() {
            @Override
            public void onChanged(final ArrayList<NowshowingResultsItem> nowshowingResultsItems) {
                nowshowingAdapter = new NowshowingAdapter(nowshowingResultsItems, getActivity());
                rvNowshowing.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                rvNowshowing.setAdapter(nowshowingAdapter);

                nowshowingAdapter.setListener(new NowshowingAdapter.Listener() {
                    @Override
                    public void onClick(int position) {
                        Log.d(TAG, "onChanged: " + position);
                        NowshowingResultsItem nowshowingResultsItem = nowshowingResultsItems.get(position);
                        Intent intent = new Intent(getActivity(), DetailNowshowingActivity.class);
                        intent.putExtra(DetailNowshowingActivity.DETAIL_NOWSHOWING, nowshowingResultsItem);
                        getActivity().startActivity(intent);
                    }
                });
            }
        });
    }
}
