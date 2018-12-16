package com.example.shim.movieinfosearch.My_interface;

import com.example.shim.movieinfosearch.Model.MyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GetMovieDataService {

    @Headers({
            "Content-Type: application/json; charset=utf-8",
            "X-Naver-Client-Id: 0_0FI_30ZbyE9OFzrYf8",
            "X-Naver-Client-Secret: H1TWn2TlOg"
    })
    @GET("search/movie.json")
    Call<MyResponse> getMovieData(@Query("query") String query, @Query("start") int start);
}
