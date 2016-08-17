package com.github.nikhilbhutani.popularmovies2.network;

import com.github.nikhilbhutani.popularmovies2.models.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nikhil Bhutani on 8/17/2016.
 */
public interface ApiInterface {

    @GET("/top_rated?")
    Call<Movie> getTopRatedMovies(@Query("api_key") String apiKey);


    @GET("/popular?")
    Call<Movie> getPopularMovies(@Query("api_key") String apiKey);

}
