package com.example.movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie.R;
import com.example.movie.model.nowshowing.NowshowingResultsItem;

import java.util.ArrayList;

public class NowshowingAdapter extends RecyclerView.Adapter<NowshowingAdapter.ViewHolder> {

    private ArrayList<NowshowingResultsItem> nowshowingItems;
    private Context context;
    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185";
    public Listener listener;

    public NowshowingAdapter(ArrayList<NowshowingResultsItem> nowshowingItems, Context context) {
        this.context = context;
        this.nowshowingItems = nowshowingItems;

    }

    public interface Listener{
        void onClick(int position);
    }
    public void setListener(Listener listener){
        this.listener = listener;
    }


    @NonNull
    @Override
    public NowshowingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.nowshowing_adapter,parent, false);
        return new ViewHolder( view);
    }

    @Override
    public void onBindViewHolder(@NonNull NowshowingAdapter.ViewHolder holder, final int position) {
       CardView cardView = holder.cardView;
       ImageView ivThumb = cardView.findViewById(R.id.card_view_ivthumbnail);
       TextView tvRate = cardView.findViewById(R.id.card_view_tvrate);
       TextView tvTitle = cardView.findViewById(R.id.card_view_tvtitle);


        tvTitle.setText(nowshowingItems.get(position).getTitle());
        tvRate.setText(String.valueOf(nowshowingItems.get(position).getVoteAverage()));
        Glide.with(context).load(BASE_IMAGE_URL + nowshowingItems.get(position).getPosterPath()).into(ivThumb);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return nowshowingItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {
            super(itemView);
            cardView = itemView;
        }
    }
}
