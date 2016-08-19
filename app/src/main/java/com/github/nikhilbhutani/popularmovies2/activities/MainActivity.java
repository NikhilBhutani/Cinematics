package com.github.nikhilbhutani.popularmovies2.activities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.nikhilbhutani.popularmovies2.R;
import com.github.nikhilbhutani.popularmovies2.fragments.MoviesFragment;

public class MainActivity extends AppCompatActivity {


    private boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MoviesFragment())
                    .commit();
        }
    }


    @Override
    public void onBackPressed() {

        if (exit) {
            super.onBackPressed();
        } else {
            Snackbar.make(this.findViewById(R.id.container), R.string.exit, Snackbar.LENGTH_SHORT).show();
            exit = true;
        }
    }
}
