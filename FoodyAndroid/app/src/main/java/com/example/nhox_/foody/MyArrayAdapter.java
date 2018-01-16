package com.example.nhox_.foody;

/**
 * Created by nhox_ on 22/3/2017.
 */

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class MyArrayAdapter extends ArrayAdapter<ItemforAdapter>{
    Activity context = null;
    List<ItemforAdapter> itemforAdapters = null;
    int positionclick;
    int layoutId;
    int option;

    int position=-1;
    public void selectedItem(int position)
    {
        this.position = position; //position must be a global variable
    }
    //Define option in this
    //0 fragment địa điểm
    //1 fragment danh mục
    //2 activity chon tinh thanh
    public MyArrayAdapter(Activity context,
                          int layoutId,
                          List<ItemforAdapter> itemforAdapters, int positionclick,int option) {
        super(context, layoutId, itemforAdapters);
        this.option = option;
        if(option==0){
            this.context = context;
            this.layoutId = layoutId;
            this.itemforAdapters = itemforAdapters;
            this.positionclick = positionclick;
        }else if(option==1 || option==2){
            this.context = context;
            this.layoutId = layoutId;
            this.itemforAdapters = itemforAdapters;
            this.positionclick = positionclick;

        }
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    /////////////
    // input: vị trí item, convertview mở rộng từ layout, ViewGroup chứa item
    // purpose: Tạo view cho item trong listview
    // output: View cho item
    /////////////
    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {

        LayoutInflater inflater =
                context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        if(option==1){
            final TextView txtdisplay = (TextView)
                    convertView.findViewById(R.id.txtitem);
            final ImageView imgitem = (ImageView)
                    convertView.findViewById(R.id.imgitem);
            final ImageView iconcheck = (ImageView) convertView.findViewById(R.id.checkicon);
            if (itemforAdapters.size() > 0 && position >= 0) {
                txtdisplay.setText(itemforAdapters.get(position).image.getType().getNametype());
                int imageint = context.getResources().getIdentifier(itemforAdapters.get(position).image.getFilepath(),"drawable",context.getPackageName());
                if (positionclick == position) {
                    txtdisplay.setTextColor(context.getResources().getColor(R.color.colorNavbar));
                    if (imageint == 0) {
                        imgitem.setVisibility(View.GONE);
                    }else{
                        Glide
                                .with(context)
                                .load(imageint)
                                .centerCrop()
                                .dontAnimate()
                                .into(imgitem);
                    }

                    Glide
                            .with(context)
                            .load(R.drawable.ic_check_icon)
                            .centerCrop()
                            .dontAnimate()
                            .into(iconcheck);
                } else {
                    if (imageint == 0) {
                        imgitem.setVisibility(View.GONE);
                    } else {
                        Glide
                                .with(context)
                                .load(imageint)
                                .centerCrop()
                                .dontAnimate()
                                .into(imgitem);
                    }
                    /*Glide
                            .with(context)
                            .load(imageint)
                            .centerCrop()
                            .dontAnimate()
                            .into(imgitem);*/
                }
            }

            txtdisplay.setTag(itemforAdapters.get(position).image.getType().getTypeid());
        }else if(option==0){
            final TextView tv = (TextView)convertView.findViewById(R.id.txttendiadiem);
            final TextView soduong = (TextView)convertView.findViewById(R.id.txtsoduong);
            tv.setText(itemforAdapters.get(position).dist.getNamedist());
            tv.setTag(itemforAdapters.get(position).dist.getDistid());
            soduong.setText("144 đường");
            if(this.positionclick==position){
                tv.setTextColor(context.getResources().getColor(R.color.colorNavbar));
            }

        }else if(option == 2){
            final TextView txtdisplay = (TextView)
                    convertView.findViewById(R.id.txtitem);
            final ImageView imgitem = (ImageView)
                    convertView.findViewById(R.id.imgitem);
            if(this.position==-1){
                if(positionclick==position){
                    Glide.with(context).load(R.drawable.ic_check_icon).centerCrop().crossFade().into(imgitem);
                    txtdisplay.setTextColor(context.getResources().getColor(R.color.dark_blue));
                }
            }
            if(positionclick==position){
                txtdisplay.setTextColor(context.getResources().getColor(R.color.dark_blue));
            }
            txtdisplay.setText(itemforAdapters.get(position).anothertext);
            txtdisplay.setTag(itemforAdapters.get(position).rawtext);
            if(this.position==position){
                convertView = inflater.inflate(R.layout.item_listview_layout_selected,null);
                ImageView im =  (ImageView)convertView.findViewById(R.id.imgitem);
                TextView tv =  (TextView)convertView.findViewById(R.id.txtitem);
                Glide.with(context).load(R.drawable.ic_check_icon).crossFade().centerCrop().into(im);
                tv.setText(itemforAdapters.get(position).anothertext);
                tv.setTextColor(context.getResources().getColor(R.color.dark_blue));
                return convertView;
            }
        }
        return convertView;
    }
    ///////////////////
    // input :
    // purpose : Thay đổi giao diện khi người dùng click lên item
    // output :
    /////////////////////
    public void swap(int position){
        this.positionclick=position;
        this.notifyDataSetChanged();
    }

    ///////////////////
    // input :
    // purpose : Thay đổi cấu trúc của adapter
    // output :
    /////////////////////
    public void swapadapter(List<ItemforAdapter> lstitem){
        this.itemforAdapters.clear();
        this.itemforAdapters.addAll(lstitem);
        this.positionclick=-1;
        this.notifyDataSetChanged();
    }
}