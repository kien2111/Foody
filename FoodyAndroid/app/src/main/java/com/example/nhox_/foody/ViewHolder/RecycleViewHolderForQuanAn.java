package com.example.nhox_.foody.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.nhox_.foody.R;

/**
 * Created by nhox_ on 6/4/2017.
 */
/////////////
// input:
// purpose: Cài đặt class RecycleViewHolderForQuanAn cho item trong recycleview layout layout_for_quanan_container_have_recycleview
// output:
/////////////
public class RecycleViewHolderForQuanAn extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView subimage;
    public RecycleViewHolderForQuanAn(View itemView) {
        super(itemView);
        subimage = (ImageView)itemView.findViewById(R.id.subimage);
    }

    @Override
    public void onClick(View v) {

    }
}
