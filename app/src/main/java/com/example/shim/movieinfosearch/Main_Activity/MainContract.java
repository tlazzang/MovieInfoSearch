package com.example.shim.movieinfosearch.Main_Activity;

import com.example.shim.movieinfosearch.Model.Movie;

import java.util.ArrayList;

public interface MainContract {

    interface Presenter{

        void onDestroy();

        void requestDataFromServer(String query, int start);

    }

    interface MainView{

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<Movie> movieList);

        void addDataToRecyclerView(ArrayList<Movie> movieList);

        void onResponseFailure(Throwable t);

        void showToast(String s);
    }

    interface GetMovieInteractor {

        interface OnFinishedListener{

            void onFinished(ArrayList<Movie> movieList, int start);

            void onError(int errorCode);

            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener, String query, int start);
    }
}
