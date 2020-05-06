package com.example.movie.view.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movie.R;
import com.example.movie.adapter.UpcomingAdapter;
import com.example.movie.model.nowshowing.NowshowingResultsItem;
import com.example.movie.model.upcoming.UpcomingResultsItem;
import com.example.movie.view.activity.DetailUpcomingActivity;
import com.example.movie.view.viewmodel.UpcomingViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment {

    private ArrayList<UpcomingResultsItem> upcomingItems;
    private UpcomingViewModel upcomingViewModel;
    private UpcomingAdapter upcomingAdapter;
    private RecyclerView rvUpcoming;

    public UpcomingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false) ;
           rvUpcoming = view.findViewById(R.id.upcoming_fragment);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    upcomingViewModel = ViewModelProviders.of(this).get(UpcomingViewModel.class);
    upcomingViewModel.getUpcoming().observe(this, new Observer<ArrayList<UpcomingResultsItem>>() {
        @Override
        public void onChanged(final ArrayList<UpcomingResultsItem> upcomingResultsItems) {
            upcomingAdapter = new UpcomingAdapter(upcomingResultsItems, getActivity());
            rvUpcoming.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            rvUpcoming.setAdapter(upcomingAdapter);

            upcomingAdapter.setListener(new UpcomingAdapter.Listener() {
                @Override
                public void onClick(int position) {
                    UpcomingResultsItem upcomingResultsItem = upcomingResultsItems.get(position);
                    Intent intent = new Intent(getActivity(), DetailUpcomingActivity.class);
                    intent.putExtra(DetailUpcomingActivity.DETAIL_UPCOMING, upcomingResultsItem);
                    getActivity().startActivity(intent);
                }
            });


        }
    });

    }

}
