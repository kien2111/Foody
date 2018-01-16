package com.example.nhox_.foody.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.signature.StringSignature;
import com.example.nhox_.foody.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Model.Comment;
import Model.Image;
import Model.Restaurant;
import UtilPackage.AsyncResponse;
import UtilPackage.EnumTaskOption;
import UtilPackage.GlobalVariable;
import UtilPackage.MyInterface.IOnLoadMore;
import UtilPackage.TaskCallRest.CallRestTaskgetImageQuanAn;
import UtilPackage.TaskCallRest.CallRestTaskgetList;

import static com.example.nhox_.foody.Fragment.MainFragment.offSet;

/**
 * Created by nhox_ on 10/5/2017.
 */

public class RestaurantAdapter extends ArrayAdapter<Restaurant>  {
    public List<Restaurant> restaurantList;
    public boolean loadingMore=false;
    Activity context = null;
    IOnLoadMore iOnLoadMore=null;
    int Layoutid;
    public RestaurantAdapter(Activity context, int resource,List<Restaurant> restaurantList) {
        super(context, resource);
        this.context = context;
        this.Layoutid = resource;
        this.restaurantList = restaurantList;
    }
    ///////////////////
    // input :
    // purpose : add tất cả dữ liệu đầu vào vào trong adapter
    // output :
    /////////////////////
    @Override
    public void addAll(final Collection<? extends Restaurant> collection) {
        this.restaurantList.addAll(collection);
        if(restaurantList.size()>0){
            offSet = restaurantList.get(restaurantList.size()-1).getRestid();
        }
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        try{
            return restaurantList.size();
        }catch (Exception e){

        }
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =
                context.getLayoutInflater();
        HolderRestaurant holder =null;
        if(convertView==null){
            convertView = inflater.inflate(Layoutid, null);
            holder = new HolderRestaurant(convertView);
            convertView.setTag(holder);
        }else{
            holder = (HolderRestaurant)convertView.getTag();
        }
        if(restaurantList.size()>0){
            if(this.restaurantList.get(position).getLstimg()!=null){
                if(this.restaurantList.get(position).getLstimg().size()>0){
                    final HolderRestaurant finalHolder = holder;
                    Glide.with(context).load(GlobalVariable.resourceurl+(this.restaurantList.get(position).getLstimg().get(0).getFilepath()))
                            .asBitmap()
                            .centerCrop()
                            .dontAnimate()
                            .placeholder(R.drawable.mydefault)
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    finalHolder.HinhAnhQuanAn.setImageBitmap(resource);
                                }
                            });
                }
            }
            if(this.restaurantList.get(position).getLstcomment()!=null){
                if(this.restaurantList.get(position).getLstcomment().size()>0){
                    for(Comment com :restaurantList.get(position).getLstcomment())
                        if(((LinearLayout)holder.commentarea).getChildCount()<2){
                            ((LinearLayout)holder.commentarea).addView(createViewComment(context,com.getUser().getNameuser(),com.getComment(),R.drawable.banner1));
                        }
                }
            }
            holder.DiemQuanAn.setText("6.5");
            holder.DiaChiQuanAn.setText(restaurantList.get(position).getAddress());
            holder.TenQuanAn.setText(restaurantList.get(position).getNamerest());
            if(DetectEndListView(position)){
                if(!loadingMore){
                    iOnLoadMore.loadmore();
                }
            }
        }
        return convertView;
    }

    public void setOnLoadMore(IOnLoadMore iOnLoadMore){
        this.iOnLoadMore = iOnLoadMore;
    }
    ///////////////////
    // input :
    // purpose : Thay đổi cấu trúc adapter
    // output :
    /////////////////////
    public void Swap(List<Restaurant> lstrest){
        this.restaurantList.clear();
        restaurantList.addAll(lstrest);
        notifyDataSetChanged();
    }
    ///////////////////
    // input :
    // purpose : Xóa tất cả dữ liệu
    // output :
    /////////////////////
    public void DeleteAll(){
        this.restaurantList.clear();
        notifyDataSetChanged();
    }

    private boolean DetectEndListView(int position){
        return this.restaurantList.size()-1 == position;
    }
    ///////////////////
    // input :
    // purpose : Tạo view comment
    // output :
    /////////////////////
    private View createViewComment(final Context context,String param_tennguoicom,String param_comment,int imageint){
        View v = LayoutInflater.from(context).inflate(R.layout.comment_layout_user,null,false);
        TextView tennguoicomment = (TextView)v.findViewById(R.id.tennguoicomment);
        TextView comment = (TextView)v.findViewById(R.id.comment);
        LinearLayout lineardiem = (LinearLayout) v.findViewById(R.id.DiemQuanAn);
        CircularImageView im = (CircularImageView)v.findViewById(R.id.circularimage);
        Glide.with(context).load(imageint).fitCenter().crossFade().into(im);
        tennguoicomment.setText(param_tennguoicom);
        comment.setText(param_comment);
        return v;
    }



    static class HolderRestaurant{
        ImageView HinhAnhQuanAn;
        TextView TenQuanAn;
        TextView DiaChiQuanAn;
        TextView DiemQuanAn;
        LinearLayout commentarea;
        public HolderRestaurant(View v){
            HinhAnhQuanAn = (ImageView)v.findViewById(R.id.HinhAnhQuanAn);
            TenQuanAn = (TextView)v.findViewById(R.id.TenQuanAn);
            DiaChiQuanAn = (TextView)v.findViewById(R.id.DiaChiQuanAn);
            DiemQuanAn = (TextView)v.findViewById(R.id.DiemQuanAn);
            commentarea = (LinearLayout)v.findViewById(R.id.commentarea);
            HinhAnhQuanAn.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }
}
