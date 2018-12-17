package com.example.shim.movieinfosearch.Main_Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shim.movieinfosearch.Adapter.MovieAdapter;
import com.example.shim.movieinfosearch.Model.Movie;
import com.example.shim.movieinfosearch.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.MainView{

    //무한 스크롤을 위한 변수
    public int start = 1;
    public final int display = 10;

    private Button btn_search;
    private EditText et_movieName;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;
    private MainContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_search = (Button) findViewById(R.id.main_btn_search);
        et_movieName = (EditText) findViewById(R.id.main_et_movieName);
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        presenter = new MainPresenterImpl(this, new GetMovieInteractorImpl());
        progressBar = (ProgressBar) findViewById(R.id.main_progressBar);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start = 1;
                presenter.requestDataFromServer(et_movieName.getText().toString(), start);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if(!recyclerView.canScrollVertically(1) && movieAdapter.getItemCount() >= 10){ //리사이클러뷰가 최하단인 경우
                        if(start + display < 1000) {
                            start = start + display;
                            presenter.requestDataFromServer(et_movieName.getText().toString(), start);
                        }
                    }
                }
            });
        }
    }

    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(Movie movie) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getLink()));
            startActivity(intent);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<Movie> movieList) {
        movieAdapter = new MovieAdapter(movieList , recyclerItemClickListener, this);
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void addDataToRecyclerView(ArrayList<Movie> movieList) {
        movieAdapter.addMovie(movieList);
    }

    @Override
    public void onResponseFailure(Throwable t) {
        Toast.makeText(MainActivity.this,
                "서버와 통신 도중 문제가 발생하였습니다. " + t.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}
