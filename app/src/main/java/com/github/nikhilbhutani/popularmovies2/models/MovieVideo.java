package com.github.nikhilbhutani.popularmovies2.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nikhil Bhutani on 8/22/2016.
 */
public class MovieVideo implements Parcelable {

    String id;
    String key;
    String name;
    String site;
    String size;
    String type;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getSite() {
        return site;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    protected MovieVideo(Parcel in) {
        id = in.readString();
        key = in.readString();
        name = in.readString();
        site = in.readString();
        size = in.readString();
        type = in.readString();
    }

    public static final Creator<MovieVideo> CREATOR = new Creator<MovieVideo>() {
        @Override
        public MovieVideo createFromParcel(Parcel in) {
            return new MovieVideo(in);
        }

        @Override
        public MovieVideo[] newArray(int size) {
            return new MovieVideo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(key);
        parcel.writeString(name);
        parcel.writeString(site);
        parcel.writeString(size);
        parcel.writeString(type);
    }
}
