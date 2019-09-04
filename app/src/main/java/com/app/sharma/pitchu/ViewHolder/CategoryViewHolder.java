package com.app.sharma.pitchu.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.sharma.pitchu.Interface.ItemClickListner;
import com.app.sharma.pitchu.R;

/**
 * Created by Sharma's on 3/25/2018.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView category_name;
    public ImageView background_image;

    ItemClickListner itemClickListner;


    public CategoryViewHolder(View itemView) {
        super(itemView);
        background_image = (ImageView) itemView.findViewById(R.id.image);
        category_name = (TextView) itemView.findViewById(R.id.name);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    @Override
    public void onClick(View v) {

        itemClickListner.onClick(v, getAdapterPosition());
    }
}
