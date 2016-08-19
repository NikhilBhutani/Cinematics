package com.github.nikhilbhutani.popularmovies2.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nikhilbhutani.popularmovies2.R;
import com.github.nikhilbhutani.popularmovies2.Utils.Constants;
import com.github.nikhilbhutani.popularmovies2.adapters.MovieRecyclerViewAdapter;
import com.github.nikhilbhutani.popularmovies2.models.MovieList;
import com.github.nikhilbhutani.popularmovies2.models.Movie;
import com.github.nikhilbhutani.popularmovies2.network.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nikhil Bhutani on 8/16/2016.
 */
public class MoviesFragment extends Fragment {

    private MovieRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ApiInterface apiInterface;
    RecyclerView recyclerView;

    Toolbar toolbar;
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


     //   Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        //To ensure that movie call is Asynchronous
        Call<MovieList> movieCall = apiInterface.getPopularMovies("Enter API KEY HERE");

        movieCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {

                MovieList allMovieResponse = (MovieList)response.body();

                recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);


                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                recyclerView.setHasFixedSize(true);

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    mLayoutManager = new GridLayoutManager(getActivity(), 2);
                } else {
                    mLayoutManager = new GridLayoutManager(getActivity(), 3);
                }
                recyclerView.setLayoutManager(mLayoutManager);


            //    int statusCode = response.code();

                List<Movie> movieList = allMovieResponse.getResults();

                recyclerViewAdapter = new MovieRecyclerViewAdapter(getActivity(), movieList);
                recyclerView.setAdapter(recyclerViewAdapter);

                for(Movie movie : movieList){

                    System.out.println(movie.getTitle());

                }


            //    Log.d("Movie", "onResponse " +statusCode);

             //   Log.d("Json Response", "Here : "+movie.getTitle());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

                Log.d("OnFailure", "Failed");

            }
        });
    }
}
