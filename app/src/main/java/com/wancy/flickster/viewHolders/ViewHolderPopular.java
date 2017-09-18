package com.wancy.flickster.viewHolders;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wancy.flickster.R;

public class ViewHolderPopular extends RecyclerView.ViewHolder{
    private ImageView ivImage;

    public ImageView getIvImage() {
        return ivImage;
    }

    public ViewHolderPopular(View v) {
        super(v);
        ivImage = (ImageView) v.findViewById(R.id.ivMovieImage);
    }
}
