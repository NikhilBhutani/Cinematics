package com.github.nikhilbhutani.popularmovies2.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import ckm.simple.sql_provider.UpgradeScript;
import ckm.simple.sql_provider.annotation.ProviderConfig;
import ckm.simple.sql_provider.annotation.SimpleSQLConfig;

/**
 * Created by Nikhil Bhutani on 8/16/2016.
 */

/**
 * 1. Created a Content Provider called MovieProvider
 * 2. Authority of the content provider is  "com.github.nikhilbhutani.popularmovies2"
 * 3. Provider uses a database file named movies.db
 * 4. Current database version 1
 */



@SimpleSQLConfig(
        name = "MovieProvider",
        authority = "com.github.nikhilbhutani.popularmovies2",
        database = "movies.db",
        version = 1)
public class MovieProviderConfig implements ProviderConfig {

    @Override
    public UpgradeScript[] getUpdateScripts() {
        return new UpgradeScript[0];
    }
}
