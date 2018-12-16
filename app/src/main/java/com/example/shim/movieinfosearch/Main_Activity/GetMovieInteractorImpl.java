package com.example.shim.movieinfosearch.Main_Activity;

import android.util.Log;

import com.example.shim.movieinfosearch.Model.MyResponse;
import com.example.shim.movieinfosearch.My_interface.GetMovieDataService;
import com.example.shim.movieinfosearch.Network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMovieInteractorImpl implements MainContract.GetMovieInteractor {

    @Override
    public void getMovieList(final OnFinishedListener onFinishedListener, String query, final int start) {
        GetMovieDataService service = RetrofitInstance.getRetrofitInstance().create(GetMovieDataService.class);
        Call<MyResponse> call = service.getMovieData(query, start);

        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                Log.wtf("response", response.toString() + "");
                if(response.isSuccessful()) {
                    onFinishedListener.onFinished(response.body().getMovieList(), start);
                }
                else{
                    onFinishedListener.onError(response.code());
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

}
