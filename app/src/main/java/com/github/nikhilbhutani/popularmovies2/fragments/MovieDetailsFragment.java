package com.github.nikhilbhutani.popularmovies2.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.nikhilbhutani.popularmovies2.BuildConfig;
import com.github.nikhilbhutani.popularmovies2.R;
import com.github.nikhilbhutani.popularmovies2.Utils.AlertDialogUtil;
import com.github.nikhilbhutani.popularmovies2.Utils.Constants;
import com.github.nikhilbhutani.popularmovies2.activities.MovieDetailActivity;
import com.github.nikhilbhutani.popularmovies2.models.Movie;
import com.github.nikhilbhutani.popularmovies2.models.MovieReview;
import com.github.nikhilbhutani.popularmovies2.models.MovieReviewList;
import com.github.nikhilbhutani.popularmovies2.models.MovieVideo;
import com.github.nikhilbhutani.popularmovies2.models.MovieVideosList;
import com.github.nikhilbhutani.popularmovies2.network.ApiClient;
import com.github.nikhilbhutani.popularmovies2.network.ApiInterface;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nikhil Bhutani on 8/18/2016.
 */
public class MovieDetailsFragment extends Fragment implements View.OnClickListener {

    private static final String LOGTAG = MovieDetailActivity.class.getSimpleName();

    View view;
    Movie movie;
    ApiInterface apiInterface;
    Call<MovieReviewList> reviewListCall;
    Call<MovieVideosList> videosListCall;
    private int mViewId;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.detailstoolbar)
    Toolbar toolbar;

    @BindView(R.id.image_backdrop)
    ImageView backDropImage;

    @BindView(R.id.image_poster)
    ImageView moviePoster;

    @BindView(R.id.movie_title)
    TextView movieTitle;

    @BindView(R.id.releaseDate)
    TextView releaseDate;

    @BindView(R.id.movieSummary)
    TextView movieSummary;

    @BindView(R.id.tv_rating)
    TextView movieRating;

    @BindView(R.id.fab_share)
    FloatingActionButton mButtonShare;

    @BindView(R.id.fab_favorite)
    FloatingActionButton mButtonFavorite;

    @BindView(R.id.fab_trailer)
    FloatingActionButton mButtonTrailer;

    @BindView(R.id.review_layout0)
    CardView mReviewLayout0;
    @BindView(R.id.review_layout1)
    CardView mReviewLayout1;

    @BindString(R.string.trailers_dialog_title)
    String trailerDialogTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getActivity().getIntent();

        if (intent != null && intent.hasExtra("MovieDetails")) {
            movie = intent.getParcelableExtra("MovieDetails");
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.detail_fragment, container, false);
        ButterKnife.bind(this, view);

        mButtonFavorite.setOnClickListener(this);
        mButtonTrailer.setOnClickListener(this);
        mButtonShare.setOnClickListener(this);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        movieTitle.setText(movie.getTitle());
        movieSummary.setText(movie.getOverview());
        releaseDate.setText(movie.getReleaseDate());
         movieRating.setText((String.valueOf( movie.getVoteAverage())));
        Glide.with(getActivity()).load(movie.getPosterPath()).error(R.drawable.place_holder).into(moviePoster);
        Glide.with(getActivity()).load(movie.getBackdropPath()).error(R.drawable.place_holder).into(backDropImage);

        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);

        reviewListCall = apiInterface.getReview(movie.getId(), BuildConfig.API_KEY);

        reviewListCall.enqueue(new Callback<MovieReviewList>() {
            @Override
            public void onResponse(Call<MovieReviewList> call, Response<MovieReviewList> response) {

                MovieReviewList movieReview = response.body();

                List<MovieReview> movieReviewlist = movieReview.getResults();
                int noOfReviews = movieReviewlist.size();

                if (noOfReviews >= 2) {
                    //We can fill two reviews
                    displayReviewLayout(0, movieReviewlist.get(0));
                    displayReviewLayout(1, movieReviewlist.get(1));
                } else {
                    //We can fill only one review
                    displayReviewLayout(0, movieReviewlist.get(0));
                }


            }

            @Override
            public void onFailure(Call<MovieReviewList> call, Throwable t) {

                Log.d(LOGTAG, "Error getting reviews");

            }
        });


    }

    private void displayReviewLayout(int position, MovieReview movieReview) {
        CardView reviewLayout = null;
        if (position == 0) {
            reviewLayout = mReviewLayout0;
            ((TextView) reviewLayout.findViewById(R.id.tv_reviews_text)).setVisibility(View.VISIBLE);
            reviewLayout.findViewById(R.id.line_reviews_heading).setVisibility(View.VISIBLE);
        } else if (position == 1) {
            reviewLayout = mReviewLayout1;
        }

        if (reviewLayout != null) {
            reviewLayout.setVisibility(View.VISIBLE);
            String author = movieReview.getAuthor();
            String content = movieReview.getContent();

            ((TextView) reviewLayout.findViewById(R.id.tv_review_author)).setText(author);
            ((TextView) reviewLayout.findViewById(R.id.tv_review_content)).setText(content);
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.fab_trailer: {
                videosListCall = apiInterface.getTrailer(movie.getId(), BuildConfig.API_KEY);

                videosListCall.enqueue(new Callback<MovieVideosList>() {
                    @Override
                    public void onResponse(Call<MovieVideosList> call, Response<MovieVideosList> response) {


                        MovieVideosList movieVideo = response.body();

                        final List<MovieVideo> movieVideoList = movieVideo.getResults();

                        int noOfTrailers = movieVideoList.size();
                        String[] trailerNames = new String[noOfTrailers];
                        for (int i = 0; i < noOfTrailers; i++) {
                            trailerNames[i] = movieVideoList.get(i).getName();
                        }

                        AlertDialogUtil.createSingleChoiceItemsAlert(getActivity(), trailerDialogTitle,
                                trailerNames, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        playVideoTrailer(movieVideoList.get(which).getKey());
                                        dialog.dismiss();
                                    }
                                });

                    }

                    @Override
                    public void onFailure(Call<MovieVideosList> call, Throwable t) {

                    }
                });
                break;
            }


            case R.id.fab_share:

            {

                videosListCall = apiInterface.getTrailer(movie.getId(), BuildConfig.API_KEY);

                videosListCall.enqueue(new Callback<MovieVideosList>() {
                    @Override
                    public void onResponse(Call<MovieVideosList> call, Response<MovieVideosList> response) {


                        MovieVideosList movieVideo = response.body();

                        final List<MovieVideo> movieVideoList = movieVideo.getResults();

                        int noOfTrailers = movieVideoList.size();
                        String[] trailerNames = new String[noOfTrailers];
                        for (int i = 0; i < noOfTrailers; i++) {
                            trailerNames[i] = movieVideoList.get(i).getName();
                        }

                        shareVideoTrailer(movieVideoList.get(0).getKey());
                    }

                    @Override
                    public void onFailure(Call<MovieVideosList> call, Throwable t) {

                    }
                });

                break;
            }

        }

    }

    private void shareVideoTrailer(String key) {

        String videoExtraText = Constants.BASE_URL_VIDEO + key;

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        //This flag help you in returning to your app after any app handled the share intent
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.putExtra(Intent.EXTRA_TEXT, videoExtraText);

        Intent shareIntent = Intent.createChooser(intent, "Share trailer via");


        // We only start the activity if it resolves successfully
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {

            startActivity(shareIntent);
        } else {

            Log.d(LOGTAG, "Couldn't share Video Trailer for key: " + key);
        }

    }

    private void playVideoTrailer(String key) {

        Uri videoUri = Uri.parse(Constants.BASE_URL_VIDEO + key);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(videoUri);

        //We only start the activity if it resolves successfully
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(LOGTAG, "Couldn't play video trailer for key: " + key);
        }

    }
}
