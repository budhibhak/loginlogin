package com.example.movie.adapter;

import android.content.Context;
import android.net.sip.SipSession;
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
import com.example.movie.model.upcoming.UpcomingResultsItem;

import java.util.ArrayList;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.ViewHolder> {

    private ArrayList<UpcomingResultsItem> upcomingItems;
    private Context context;
    public Listener listener;

    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185";

    public UpcomingAdapter(ArrayList<UpcomingResultsItem> upcomingItems, Context context) {
        this.context = context;
        this.upcomingItems = upcomingItems;

    }

    public interface Listener{
        void onClick(int position);
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public UpcomingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_adapter, parent, false);
        return new ViewHolder(cardView);


    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingAdapter.ViewHolder holder, final int position) {
        CardView cardView = holder.cardView;
        ImageView ivThumb = cardView.findViewById(R.id.cv_ivthumbnail);
        TextView tvTitle = cardView.findViewById(R.id.cv_tvtitle);

        tvTitle.setText(upcomingItems.get(position).getTitle());
        Glide.with(context).load(BASE_IMAGE_URL + upcomingItems.get(position).getPosterPath())
                .into(ivThumb);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.onClick(position);
                }
            }
        });

          }

    @Override
    public int getItemCount() {
        return upcomingItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(@NonNull CardView itemView) {
            super(itemView);
           cardView = itemView;
        }
    }
}
