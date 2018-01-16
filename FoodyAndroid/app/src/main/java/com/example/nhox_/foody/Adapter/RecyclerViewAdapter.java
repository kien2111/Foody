package com.example.nhox_.foody.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.nhox_.foody.R;
import com.example.nhox_.foody.ViewHolder.RecyclerViewHolders;

/**
 * Created by nhox_ on 27/3/2017.
 */
/////////////
// input:
// purpose: Cài đặt class RecyclerViewAdapter cho recycleview trong mainFragment
// output:
/////////////
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {
    Integer[] imagesource;
    String[] text;
    private Context context;

    //recycleview for quanan
    //List<Image> lstimage;
    public RecyclerViewAdapter(Context context,Integer[] imagesource,String[] text) {
        this.imagesource=imagesource;
        this.text = text;
        this.context = context;
    }


    //Trả về RecyclerViewHolders mở rộng từ layout layout_button_gridview
    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_button_gridview, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;


    }




    //Tạo giao diện cho item trong recycleview
    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
            holder.text.setText(text[position]);
            Glide
                    .with(context)
                    .load(imagesource[position])
                    .fitCenter()
                    .crossFade()
                    .into(holder.icon);

    }




    @Override
    public int getItemCount() {
        return this.imagesource.length;
    }
}
