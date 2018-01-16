package com.example.nhox_.foody.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.nhox_.foody.R;
import com.example.nhox_.foody.ViewHolder.RecycleViewHolderForQuanAn;

import java.util.List;
import java.util.concurrent.ExecutionException;

import Model.Image;
import UtilPackage.EnumTaskOption;
import UtilPackage.TaskCallRest.CallRestTaskgetImageQuanAn;

/**
 * Created by nhox_ on 6/4/2017.
 */
/////////////
// input:
// purpose: Cài đặt class RecycleViewAdaptersForQuanAn cho item trong recycleview layout layout_for_quanan_container_have_recycleview
// output:
/////////////
public class RecycleViewAdaptersForQuanAn  extends RecyclerView.Adapter<RecycleViewHolderForQuanAn> {
    private Context context;
    List<Image> lstimage;
    public RecycleViewAdaptersForQuanAn(Context context, List<Image> lstimage) {
        this.context = context;
        this.lstimage = lstimage;
    }


    @Override
    public RecycleViewHolderForQuanAn onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_gallery_image, null);
        RecycleViewHolderForQuanAn rcv = new RecycleViewHolderForQuanAn(layoutView);
        return rcv;
    }
    //Tạo giao diện cho item trong recycleview
    @Override
    public void onBindViewHolder(RecycleViewHolderForQuanAn holder, int position) {
        if(position!=0){
            LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams)holder.subimage.getLayoutParams();
            ll.height=context.getResources().getDimensionPixelSize(R.dimen.image_size_in_recycleview_for_quanan);
        }
        if(position<=7){
            holder.subimage.setScaleType(ImageView.ScaleType.FIT_XY);
            //int resid = context.getResources().getIdentifier(lstimage.get(position).getFilepath(),"drawable",context.getPackageName());
            try {
                Glide
                        .with(context)
                        .load(lstimage.get(position).getFilepath())
                        .into(holder.subimage);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    //Trả về số lượng item trong recycleview
    @Override
    public int getItemCount() {
        return this.lstimage.size();
    }
}
