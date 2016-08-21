package com.github.nikhilbhutani.popularmovies2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.nikhilbhutani.popularmovies2.R;
import com.github.nikhilbhutani.popularmovies2.activities.MovieDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nikhil Bhutani on 8/18/2016.
 */
public class MovieDetailsFragment extends Fragment {

    private static final String LOGTAG = MovieDetailActivity.class.getSimpleName();

    View view;

    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.detailstoolbar)
    Toolbar toolbar;

    @BindView(R.id.image_backdrop)
    ImageView backDropImage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.detail_fragment, container, false);
        ButterKnife.bind(this, view);



        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
