package com.github.nikhilbhutani.popularmovies2.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.nikhilbhutani.popularmovies2.R;
import com.github.nikhilbhutani.popularmovies2.fragments.MovieDetailsFragment;

/**
 * Created by Nikhil Bhutani on 8/21/2016.
 */
public class MovieDetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(savedInstanceState == null){

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_container, new MovieDetailsFragment())
                    .commit();

        }

    }
}
