package com.example.android.gmovie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.android.gmovie.MovieUtils.QueryUtils;

import java.util.ArrayList;
import java.util.List;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Movie>> {
    private static final String LOG_TAG = MainActivity.class.getName();
    private static final String TMDB_REQUEST_URL =
            "https://api.themoviedb.org/3/discover/movie?api_key=*********";
    private static final String TMDB_USER_RATING ="https://api.themoviedb.org/3/discover/movie?api_key=*********&language=en-US&sort_by=vote_average.desc";
    private static final int Movie_LOADER_ID = 1;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView MovieListView = (ListView) findViewById(R.id.list);

        mAdapter = new MovieAdapter(this, new ArrayList<Movie>());


        MovieListView.setAdapter(mAdapter);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(Movie_LOADER_ID,null,this);


    }
    public Loader<List<Movie>> onCreateLoader(int i, Bundle bundle) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean b = pref.getBoolean("SortBy",false);
        if (b==true) {
            MovieLoader movieLoader = new MovieLoader(this, TMDB_REQUEST_URL);
            return movieLoader;
        }else {
            MovieLoader movieLoader = new MovieLoader(this, TMDB_USER_RATING);
            return movieLoader;
        }

    }

    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> movies) {
        mAdapter.clear();
        if (movies != null && !movies.isEmpty()) {
            mAdapter.addAll(movies);
        }
    }

    public void onLoaderReset(Loader<List<Movie>> loader) {
        mAdapter.clear();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.sort_by,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_settings){
            Intent startSettingsActivity = new Intent(this,settings_activity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
