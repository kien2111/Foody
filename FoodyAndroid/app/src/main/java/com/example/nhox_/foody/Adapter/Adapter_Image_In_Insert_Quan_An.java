package com.example.nhox_.foody.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nhox_.foody.Activity.GalleryActivity.Pick_Gallery_Image_Adapter;
import com.example.nhox_.foody.R;

import java.util.ArrayList;

import Model.ImageInGallery;
import UtilPackage.MyInterface.IOnClickImageGallery;

/**
 * Created by nhox_ on 4/5/2017.
 */

public class Adapter_Image_In_Insert_Quan_An extends RecyclerView.Adapter<Adapter_Image_In_Insert_Quan_An.ViewHolder> {
    Context context;
    public ArrayList<ImageInGallery> lstimage;
    IOnClickImageGallery iOnClickImageGallery;
    ///////////////////
    // input :
    // purpose : Gán giá trị để tạo callback function
    // output :
    /////////////////////
    public void setOnClickImageGallery(IOnClickImageGallery iOnClickImageGallery) {
        this.iOnClickImageGallery = iOnClickImageGallery;
    }

    public Adapter_Image_In_Insert_Quan_An(Context context,ArrayList<ImageInGallery> lstimage) {
        this.context = context;
        this.lstimage = lstimage;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Adapter_Image_In_Insert_Quan_An.ViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.image_insert_quanan_item, parent, false),iOnClickImageGallery);
    }
    ///////////////////
    // input :
    // purpose : Tạo giao diện
    // output :
    /////////////////////
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageInGallery item = (ImageInGallery) this.lstimage.get(position);

        Glide.with(context).load("file://" + item.getPath())
                .into(holder.image_view);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    ///////////////////
    // input :
    // purpose : Xóa chọn
    // output :
    /////////////////////
    public void removeSelectedPosition(int position, int clickPosition) {
        this.lstimage.remove(position);
        notifyItemRemoved(clickPosition);
    }

    @Override
    public int getItemCount() {
        return lstimage.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View v;
        ImageView image_view;
        ImageView remove_image;
        public int position;
        IOnClickImageGallery iOnClickImageGallery;

        public ViewHolder(View v, IOnClickImageGallery iOnClickImageGallery) {
            super(v);
            this.v = v;
            image_view = (ImageView) v.findViewById(R.id.image_view_file);
            remove_image = (ImageView) v.findViewById(R.id.remove_image);
            remove_image.bringToFront();
            this.iOnClickImageGallery = iOnClickImageGallery;
            this.remove_image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iOnClickImageGallery.onClickImageGallery(v, getAdapterPosition());
        }
    }
}
