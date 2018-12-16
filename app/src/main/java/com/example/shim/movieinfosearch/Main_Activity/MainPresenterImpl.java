package com.example.shim.movieinfosearch.Main_Activity;

import com.example.shim.movieinfosearch.Model.Movie;

import java.util.ArrayList;

public class MainPresenterImpl implements MainContract.Presenter, MainContract.GetMovieInteractor.OnFinishedListener {
    private MainContract.MainView mainView;
    private MainContract.GetMovieInteractor getMovieInteractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetMovieInteractor getMovieInteractor) {
        this.mainView = mainView;
        this.getMovieInteractor = getMovieInteractor;
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void requestDataFromServer(String query, int start) {
        if(mainView != null){
            mainView.showProgress();
        }
        getMovieInteractor.getMovieList(this, query, start);
    }

    @Override
    public void onFinished(ArrayList<Movie> movieList, int start) {
        if(mainView != null){
            mainView.hideProgress();
        }
        if(movieList.size() == 0){
            mainView.showToast("검색하신 영화가 존재하지 않습니다.");
            return;
        }

        if(start == 1){
            mainView.setDataToRecyclerView(movieList);
        }
        else{
            mainView.addDataToRecyclerView(movieList);
        }
    }

    @Override
    public void onError(int errorCode) {
        if(mainView != null){
            mainView.hideProgress();
        }

        if(errorCode == 400){
            mainView.showToast("유효하지 않은 검색어입니다.");
        }
        else if(errorCode == 404){
            mainView.showToast("존재하지 않는 api 주소입니다.");
        }
        else if(errorCode == 500){
            mainView.showToast("서버 내부 에러가 발생하였습니다.");
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.hideProgress();
            mainView.onResponseFailure(t);
        }
    }
}
