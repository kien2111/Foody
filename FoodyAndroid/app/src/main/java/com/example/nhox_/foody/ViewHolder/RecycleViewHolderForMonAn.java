package com.example.nhox_.foody.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhox_.foody.R;

/**
 * Created by nhox_ on 10/4/2017.
 */

public class RecycleViewHolderForMonAn extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tenmonan;
    public TextView tenquan;
    public TextView diachiquan;
    public ImageView imageView;
    public RecycleViewHolderForMonAn(View itemView) {
        super(itemView);
        tenmonan = (TextView)itemView.findViewById(R.id.monan);
        tenquan = (TextView)itemView.findViewById(R.id.tenquan);
        diachiquan = (TextView)itemView.findViewById(R.id.diachi);
        imageView = (ImageView)itemView.findViewById(R.id.icon_button);
    }

    @Override
    public void onClick(View v) {

    }
}
