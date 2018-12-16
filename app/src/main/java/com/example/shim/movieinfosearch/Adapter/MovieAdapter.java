package com.example.shim.movieinfosearch.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shim.movieinfosearch.Main_Activity.RecyclerItemClickListener;
import com.example.shim.movieinfosearch.Model.Movie;
import com.example.shim.movieinfosearch.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Movie> movieList = new ArrayList<>();
    private RecyclerItemClickListener recyclerItemClickListener;
    private Context context;

    public MovieAdapter(ArrayList<Movie> movieList ,RecyclerItemClickListener recyclerItemClickListener, Context context) {
        this.movieList = movieList;
        this.recyclerItemClickListener = recyclerItemClickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ((CustomViewHolder)holder).tv_title.setText(movieList.get(position).getTitle().replaceAll("\\<.*?>",""));
        ((CustomViewHolder)holder).tv_actor.setText(movieList.get(position).getActor());
        ((CustomViewHolder)holder).tv_director.setText(movieList.get(position).getDirector());
        ((CustomViewHolder)holder).tv_pubDate.setText(String.valueOf(movieList.get(position).getPubDate()));
        ((CustomViewHolder)holder).ratingBar.setRating((float) (movieList.get(position).getUserRating() / 2.0));
        Glide
                .with(context)
                .load(movieList.get(position).getImage())
                .into(((CustomViewHolder)holder).iv_image);

        ((CustomViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(movieList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void addMovie(ArrayList<Movie> movieList){
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_image;
        public TextView tv_title;
        public RatingBar ratingBar;
        public TextView tv_pubDate;
        public TextView tv_director;
        public TextView tv_actor;

        public CustomViewHolder(View view) {
            super(view);
            iv_image = (ImageView)view.findViewById(R.id.movieList_iv_img);
            tv_title = (TextView)view.findViewById(R.id.movieList_tv_title);
            ratingBar = (RatingBar)view.findViewById(R.id.movieList_ratingBar);
            tv_pubDate = (TextView)view.findViewById(R.id.movieList_tv_pubDate);
            tv_director = (TextView)view.findViewById(R.id.movieList_tv_director);
            tv_actor = (TextView)view.findViewById(R.id.movieList_tv_actor);
        }
    }
}
