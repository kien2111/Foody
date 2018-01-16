package com.example.nhox_.foody.Activity.GalleryActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nhox_.foody.R;

import java.util.ArrayList;
import java.util.List;

import Model.ImageInGallery;
import UtilPackage.MyInterface.IOnClickImageGallery;

/**
 * Created by nhox_ on 3/5/2017.
 */

public class Pick_Gallery_Image_Adapter  extends RecyclerView.Adapter<Pick_Gallery_Image_Adapter.ViewHolder> {
    Context context;
    ArrayList<ImageInGallery> lstimage;
    ArrayList<ImageInGallery> imageSelected = new ArrayList<>();
    IOnClickImageGallery iOnClickImageGallery;

    public void setOnClickImageGallery(IOnClickImageGallery iOnClickImageGallery) {
        this.iOnClickImageGallery = iOnClickImageGallery;
    }

    public Pick_Gallery_Image_Adapter(Context context, ArrayList<ImageInGallery> lstimage) {
        this.context = context;
        this.lstimage = lstimage;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.gallery_item, parent, false),iOnClickImageGallery);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ImageInGallery item = (ImageInGallery) this.lstimage.get(position);

        Glide.with(context).load("file://" + item.getPath())
                .into(holder.image_view);

        if (isSelected(item)) {
            holder.image_view_check.setChecked(true);
        } else {
            holder.image_view_check.setChecked(false);
        }

        return;
    }

    private boolean isSelected(ImageInGallery image) {
        for (ImageInGallery selectedImage : this.imageSelected) {
            if (selectedImage.getPath().equals(image.getPath())) {
                return true;
            }
        }
        return false;
    }
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemCount() {
        return lstimage.size();
    }

    public void setData(List<ImageInGallery> images) {
        this.lstimage.clear();
        this.lstimage.addAll(images);
    }

    public void addAll(List<ImageInGallery> images) {
        int startIndex = this.lstimage.size();
        this.lstimage.addAll(startIndex, images);
        notifyItemRangeInserted(startIndex, images.size());
    }

    public void addSelected(ImageInGallery image) {
        for (int i = 0; i < imageSelected.size(); i++) {
            ImageInGallery item = imageSelected.get(i);
            if (item.getPath().equals(image.getPath())) {
                return;
            }
        }
        this.imageSelected.add(image);
        notifyItemChanged(this.lstimage.indexOf(image));
    }

    public void removeSelectedImage(ImageInGallery image) {
        for (int i = 0; i < imageSelected.size(); i++) {
            ImageInGallery item = imageSelected.get(i);
            if (item.getPath().equals(image.getPath())) {
                this.imageSelected.remove(i);
                break;
            }
        }
        notifyItemChanged(this.lstimage.indexOf(image));
    }

    public void removeSelectedPosition(int position, int clickPosition) {
        this.imageSelected.remove(position);
        notifyItemChanged(clickPosition);
    }
    public void removeSelectedPosition(ImageInGallery image, int clickPosition) {
        this.imageSelected.remove(image);
        notifyItemChanged(clickPosition);
    }

    public void removeAllSelectedSingleClick() {
        this.imageSelected.clear();
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View v;
        ImageView image_view;
        CheckedTextView image_view_check;
        public int position;
        IOnClickImageGallery iOnClickImageGallery;

        public ViewHolder(View v, IOnClickImageGallery iOnClickImageGallery) {
            super(v);
            this.v = v;
            image_view = (ImageView) v.findViewById(R.id.image_view_file);
            image_view_check = (CheckedTextView) v.findViewById(R.id.check_text_view);
            image_view_check.bringToFront();
            this.iOnClickImageGallery = iOnClickImageGallery;
            this.v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iOnClickImageGallery.onClickImageGallery(v, getAdapterPosition());
        }
    }
}
