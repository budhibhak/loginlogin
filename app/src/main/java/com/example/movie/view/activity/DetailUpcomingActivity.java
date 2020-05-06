package com.example.movie.view.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.movie.R;
import com.example.movie.model.nowshowing.NowshowingResultsItem;
import com.example.movie.model.upcoming.UpcomingResultsItem;

public class DetailUpcomingActivity extends AppCompatActivity {
    private ImageView ivPoster;
    private TextView tvTitle, tvOverview;
    private UpcomingResultsItem upcomingResultsItem;

    public static final String DETAIL_UPCOMING = "detail_upcoming";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_upcoming);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvTitle = findViewById(R.id.detail_upcoming_tvtitle);
        tvOverview = findViewById(R.id.detail_upcoming_tvdesc);
        ivPoster = findViewById(R.id.detail_upcoming_ivposter);

        upcomingResultsItem = (UpcomingResultsItem) getIntent().getExtras().get(DETAIL_UPCOMING);

        tvTitle.setText(upcomingResultsItem.getTitle());
        tvOverview.setText(upcomingResultsItem.getOverview());

        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w185" + upcomingResultsItem.getPosterPath()).into(ivPoster);
    }
}
