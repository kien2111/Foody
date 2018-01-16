package com.example.nhox_.foody.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.nhox_.foody.R;

/**
 * Created by nhox_ on 26/3/2017.
 */
/////////////
// input:
// purpose: Cài đặt class PagerAdapterForImageSlider cho image slider
// output:
/////////////
public class PagerAdapterForImageSlider extends android.support.v4.view.PagerAdapter {

    int[] mResources;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public PagerAdapterForImageSlider(Context context, int[] Resource) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mResources=Resource;
    }

    //Đếm số lượng item trong adapter
    @Override
    public int getCount() {
        return mResources.length;
    }

    //Trả về view chứa trong image slider
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    //Khởi tạo item trong image slider
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.image_item_for_viewpager, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        Glide.with(mContext).load(mResources[position]).asBitmap().into(imageView);
        container.addView(itemView);

        return itemView;
    }

    //Xóa item khỏi image slider
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}