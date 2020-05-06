package com.example.movie.view.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.movie.R;
import com.example.movie.model.nowshowing.NowshowingResultsItem;

public class DetailNowshowingActivity extends AppCompatActivity {

    private ImageView ivPoster;
    private TextView tvTitle, tvOverview, tvRate;
    private NowshowingResultsItem nowshowingResultsItem;

    public static final String DETAIL_NOWSHOWING = "detail_nowshowing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_nowshowing);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tvTitle = findViewById(R.id.detail_activity_tvtitle);
        tvOverview = findViewById(R.id.detail_activity_tvdesc);
        tvRate = findViewById(R.id.detail_activity_tvrate);
        ivPoster = findViewById(R.id.detail_activity_ivposter);

        nowshowingResultsItem = (NowshowingResultsItem) getIntent().getExtras().get(DETAIL_NOWSHOWING);

        tvTitle.setText(nowshowingResultsItem.getTitle());
        tvRate.setText(getResources().getString(R.string.rating) + " Rate : " + String.valueOf(nowshowingResultsItem.getVoteAverage()));
        tvOverview.setText(nowshowingResultsItem.getOverview());

        Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w185" + nowshowingResultsItem.getPosterPath()).into(ivPoster);
    }

}
