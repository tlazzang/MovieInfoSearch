package com.example.shim.movieinfosearch.Model;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("title")
    String title;
    @SerializedName("userRating")
    double userRating;
    @SerializedName("pubDate")
    int pubDate;
    @SerializedName("director")
    String director;
    @SerializedName("actor")
    String actor;
    @SerializedName("link")
    String link;
    @SerializedName("image")
    String image;

    public Movie(String title, double userRating, int pubDate, String director, String actor, String link, String image) {
        this.title = title;
        this.userRating = userRating;
        this.pubDate = pubDate;
        this.director = director;
        this.actor = actor;
        this.link = link;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getUserRating() {
        return userRating;
    }

    public void setUserRating(double userRating) {
        this.userRating = userRating;
    }

    public int getPubDate() {
        return pubDate;
    }

    public void setPubDate(int pubDate) {
        this.pubDate = pubDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
