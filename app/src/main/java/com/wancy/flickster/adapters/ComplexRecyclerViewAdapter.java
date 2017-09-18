package com.wancy.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.wancy.flickster.R;
import com.wancy.flickster.models.Movie;
import com.wancy.flickster.viewHolders.*;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


public class ComplexRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // The items to display in your RecyclerView
    private List<Movie> movies;
    private Context context;

    private final int NORMAL = 0, POPULAR = 1;

    public ComplexRecyclerViewAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (movies.get(position).getVoteAverage() >= 7.0) {
            return POPULAR;
        } else {
            return NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case POPULAR:
                View v1 = inflater.inflate(R.layout.popular_movie, viewGroup, false);
                viewHolder = new ViewHolderPopular(v1);
                break;
            case NORMAL:
                View v2 = inflater.inflate(R.layout.item_movie, viewGroup, false);
                viewHolder = new ViewHolder(v2);
                break;
            default:
                View v = inflater.inflate(R.layout.item_movie, viewGroup, false);
                viewHolder = new ViewHolder(v);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case POPULAR:
                ViewHolderPopular vh1 = (ViewHolderPopular) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            case NORMAL:
                ViewHolder vh2 = (ViewHolder) viewHolder;
                configureViewHolder2(vh2, position);
                break;
            default:
                ViewHolder vh = (ViewHolder) viewHolder;
                configureViewHolder2(vh, position);
                break;
        }
    }

    //popular
    private void configureViewHolder1(ViewHolderPopular vh1, int position) {
        Movie movie = movies.get(position);
        vh1.getIvImage().setImageResource(0);
        String url = movie.getBackdropPath();
        Picasso.with(context).load(url)
                .transform(new RoundedCornersTransformation(10, 10))
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error).into(vh1.getIvImage());

    }
    //normal
    private void configureViewHolder2(ViewHolder vh2, int position) {
        Movie movie = movies.get(position);
        vh2.getIvImage().setImageResource(0);
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        vh2.getTitle().setText(movie.getOriginalTitle());
        vh2.getOverview().setText(movie.getOverview());

        //display image according to device orientation
        String url = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
                ? movie.getBackdropPath() : movie.getPosterPath();
        Picasso.with(context).load(url)
                .transform(new RoundedCornersTransformation(10, 10))
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_error).into(vh2.getIvImage());
    }
}
