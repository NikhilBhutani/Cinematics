package com.github.nikhilbhutani.popularmovies2.models;

import android.os.Parcel;
import android.os.Parcelable;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Nikhil Bhutani on 8/16/2016.
 */
public class Movie implements Parcelable{

    @SerializedName("poster_path")
    private String posterPath;

    private boolean adult;

    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    private int id;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("original_language")
    private String originalLanguage;

    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    private double popularity;

    @SerializedName("vote_count")
    private int voteCount;

    private boolean video;

    @SerializedName("vote_average")
    private double voteAverage;


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
  //      return SyncStateContract.Constants.BASE_URL_IMAGE_POSTER + posterPath;
        return posterPath;
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
        return backdropPath;
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

}
