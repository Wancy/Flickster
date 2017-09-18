package com.wancy.flickster.viewHolders;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wancy.flickster.R;

public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView title;
    private TextView overview;
    private ImageView ivImage;

    public TextView getTitle() {
        return title;
    }

    public TextView getOverview() {
        return overview;
    }

    public ImageView getIvImage() {
        return ivImage;
    }

    public ViewHolder(View v) {
        super(v);
        title = (TextView) v.findViewById(R.id.tvTitle);
        overview = (TextView) v.findViewById(R.id.tvOverview);
        ivImage = (ImageView) v.findViewById(R.id.ivMovieImage);
    }
}
