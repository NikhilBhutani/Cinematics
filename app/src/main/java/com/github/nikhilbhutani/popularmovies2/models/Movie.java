package com.github.nikhilbhutani.popularmovies2.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.SyncStateContract;


import com.github.nikhilbhutani.popularmovies2.Utils.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ckm.simple.sql_provider.annotation.SimpleSQLColumn;
import ckm.simple.sql_provider.annotation.SimpleSQLTable;

/**
 * Created by Nikhil Bhutani on 8/16/2016.
 */

@SimpleSQLTable(table = "moviesTable", provider = "MovieProvider")

public class Movie implements Parcelable {

    @SimpleSQLColumn("col_posterPath")
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    private boolean adult;

    @SimpleSQLColumn("col_overview")
    private String overview;

    @SimpleSQLColumn("col_releaseDate")
    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SimpleSQLColumn("col_id")
    private int id;

    @SimpleSQLColumn("col_originalTitle")
    @SerializedName("original_title")
    @Expose
    private String originalTitle;

    @SimpleSQLColumn("col_originalLanguage")
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;

    @SimpleSQLColumn("col_title")
    private String title;

    @SimpleSQLColumn("col_backdropPath")
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @SimpleSQLColumn("col_popularity")
    private double popularity;

    @SimpleSQLColumn("col_voteCount")
    @SerializedName("vote_count")
    @Expose
    private int voteCount;

    private boolean video;

    @SimpleSQLColumn("col_voteAverage")
    @SerializedName("vote_average")
    @Expose
    private double voteAverage;

    public Movie(){

    }

    public Movie(String posterPath, String overview, String releaseDate, String originalTitle, String originalLanguage,
                 String backdropPath, double popularity, int voteCount, double voteAverage){
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;

    }


    private Movie(Parcel in) {


        this.posterPath = in.readString();
        this.adult = in.readByte() != 0;  ////myBoolean == true if byte != 0
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.id = in.readInt();
        this.originalTitle = in.readString();
        this.originalLanguage = in.readString();
        this.title = in.readString();
        this.backdropPath = in.readString();
        this.popularity = in.readDouble();
        this.voteCount = in.readInt();
        this.video = in.readByte() != 0;
        this.voteAverage = in.readDouble();

    }

    public String getPosterPath() {
        return Constants.BASE_URL_IMAGE_POSTER + posterPath;
        //  return posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getTitle() {
        return title;
    }


    public String getBackdropPath() {

        return Constants.BASE_URL_IMAGE_BACKDROP + backdropPath;

    }

    public double getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(posterPath);
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeInt(id);
        parcel.writeString(originalTitle);
        parcel.writeString(originalLanguage);
        parcel.writeString(title);
        parcel.writeString(backdropPath);
        parcel.writeDouble(popularity);
        parcel.writeInt(voteCount);
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeDouble(voteAverage);
    }

    //Setters for SimpleSQLprovider library

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
