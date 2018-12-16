package com.example.shim.movieinfosearch.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MyResponse {

    @SerializedName("items")
    ArrayList<Movie> movieList = new ArrayList<>();

    public MyResponse(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }
}
