package com.example.android.gmovie;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.gmovie.MovieUtils.QueryUtils;

import java.util.List;

/**
 * Created by G on 3/13/2018.
 */

public class MovieLoader extends AsyncTaskLoader {
    private static final String  LOG_TAG = MovieLoader.class.getName();
    private  String mUrl;

    public MovieLoader(Context context,String url) {
        super(context);
        mUrl=url;
    }
    protected void onStartLoading(){forceLoad();}

    @Override
    public List<Movie> loadInBackground() {
        if (mUrl==null) {
            return null;
        }
        List<Movie> movies= QueryUtils.fetchMovieData(mUrl);
        return movies;
    }
}
