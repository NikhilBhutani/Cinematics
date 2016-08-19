package com.github.nikhilbhutani.popularmovies2.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nikhil Bhutani on 8/19/2016.
 */
public class MovieList {

    private ArrayList<Movie> results;

    public ArrayList<Movie> getResults() {
        return results;
    }

}
