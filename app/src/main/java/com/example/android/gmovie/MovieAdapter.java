package com.example.android.gmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by G on 3/11/2018.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {
    private Context context;
    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
        this.context=context;
    }



    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.movie_list_item,parent,false);
        }
            Movie currentMovie = getItem(position);
            ImageView iv =(ImageView) listItemView.findViewById(R.id.image_iv);
            Picasso.with(context)
                    .load(currentMovie.getMoviePoster())
                    .into(iv);
            TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
            magnitudeView.setText(currentMovie.getMoviePoster());
            TextView magnitudeView1 = (TextView) listItemView.findViewById(R.id.magnitude1);
            magnitudeView1.setText(currentMovie.getOriginalTitle());

            TextView magnitudeView2 = (TextView) listItemView.findViewById(R.id.magnitude2);
            magnitudeView2.setText(currentMovie.getOverView());

            TextView magnitudeView3 = (TextView) listItemView.findViewById(R.id.magnitude3);
            magnitudeView3.setText(currentMovie.getReleaseDate());



        return listItemView;
    }
}
