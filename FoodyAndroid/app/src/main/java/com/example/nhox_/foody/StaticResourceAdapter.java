package com.example.nhox_.foody;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

import UtilPackage.GlobalVariable;

/**
 * Created by nhox_ on 2/4/2017.
 */

/////////////
// input:
// purpose: Cài đặt class StaticResourceAdapter cho item trong listview của myListFragment
// output:
/////////////
public class StaticResourceAdapter extends ArrayAdapter<ItemforAdapter> {
    Activity context = null;
    List<ItemforAdapter> itemforAdapters = null;
    int positionclick;
    int layoutId;
    HashMap<String,Integer> hm;
    private void createHashMap(){
        hm = new HashMap<>();
        for(int i=0;i<itemforAdapters.size();i++){
            hm.put(itemforAdapters.get(i).rawtext,itemforAdapters.get(i).icon);
        }
    }

    public StaticResourceAdapter(Activity context,
                                 int layoutId,
                                 List<ItemforAdapter> itemforAdapters, int positionclick) {
        super(context, layoutId, itemforAdapters);
        this.context = context;
        this.layoutId = layoutId;
        this.itemforAdapters = itemforAdapters;
        this.positionclick = positionclick;
        createHashMap();
    }

    //Xây dựng view cho item tại vị trí position, inflate từ convertView,và chứa trong parent
    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {

        LayoutInflater inflater =
                context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
            final TextView txtdisplay = (TextView)
                    convertView.findViewById(R.id.txtitem);
            final ImageView imgitem = (ImageView)
                    convertView.findViewById(R.id.imgitem);
            final ImageView iconcheck = (ImageView) convertView.findViewById(R.id.checkicon);
            if(positionclick==position){
                txtdisplay.setTextColor(context.getResources().getColor(R.color.colorNavbar));
                txtdisplay.setText(itemforAdapters.get(position).rawtext);
                if(itemforAdapters.get(position).icon==0){

                }else{
                    Glide.with(context).load(GlobalVariable.getItem_for_listfragmentCheck[position]).centerCrop().crossFade().into(imgitem);
                }
                Glide.with(context).load(R.drawable.ic_check_icon).centerCrop().crossFade().into(iconcheck);
            }else{
                txtdisplay.setText(itemforAdapters.get(position).rawtext);
                int resourceid = hm.get(itemforAdapters.get(position).rawtext);
                if(resourceid==0){

                }else{
                    Glide.with(context).load(resourceid).centerCrop().dontAnimate().into(imgitem);
                }

            }

        return convertView;

    }
    ///////////////////
    // input :
    // purpose : Thay đổi giao diện khi người dùng click lên view
    // output :
    /////////////////////
    public void swap(int position){
        this.positionclick=position;
        this.notifyDataSetChanged();
    }

}
