package com.github.nikhilbhutani.popularmovies2;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Nikhil Bhutani on 8/23/2016.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
