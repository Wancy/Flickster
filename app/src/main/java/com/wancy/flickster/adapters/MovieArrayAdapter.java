package com.wancy.flickster.adapters;


import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wancy.flickster.R;
import com.wancy.flickster.models.Movie;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieArrayAdapter extends ArrayAdapter<Movie>{

    // View lookup cache
    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView ivImage;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        String url;

        /*if (movie.getVoteAverage() >= 7.0) {
            if (convertView == null) {
                // If there's no view to re-use, inflate a brand new view for row
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.popular_movie, parent, false);
                viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);

                // Cache the viewHolder object inside the fresh view
                convertView.setTag(viewHolder);
            } else {
                // View is being recycled, retrieve the viewHolder object from tag
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //display image according to device orientation
            url = movie.getBackdropPath();

        } else {*/
            if (convertView == null) {
                // If there's no view to re-use, inflate a brand new view for row
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_movie, parent, false);
                viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
                viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);
                viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);

                // Cache the viewHolder object inside the fresh view
                convertView.setTag(viewHolder);
            } else {
                // View is being recycled, retrieve the viewHolder object from tag
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // Populate the data from the data object via the viewHolder object
            // into the template view.
            viewHolder.title.setText(movie.getOriginalTitle());
            viewHolder.overview.setText(movie.getOverview());

            //display image according to device orientation
            url = convertView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
                        ? movie.getBackdropPath() : movie.getPosterPath();

        //}

        //clear out image from convert view
        viewHolder.ivImage.setImageResource(0);

        Picasso.with(getContext()).load(url)
                .transform(new RoundedCornersTransformation(10, 10))
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error).into(viewHolder.ivImage);

        return convertView;
    }

}
