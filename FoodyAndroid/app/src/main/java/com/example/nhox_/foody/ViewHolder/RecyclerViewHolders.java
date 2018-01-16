package com.example.nhox_.foody.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhox_.foody.R;

/**
 * Created by nhox_ on 27/3/2017.
 */
/////////////
// input:
// purpose: Cài đặt class RecyclerViewHolders cho recycleview trong mainFragment
// output:
/////////////
public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView text;
    public ImageView icon;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        text = (TextView)itemView.findViewById(R.id.text_of_button);
        icon = (ImageView)itemView.findViewById(R.id.icon_button);
    }



    @Override
    public void onClick(View view) {

    }
}
