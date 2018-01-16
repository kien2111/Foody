package com.example.nhox_.foody.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.nhox_.foody.R;

import java.util.List;

import Model.City;
import Model.Comment;
import Model.District;

/**
 * Created by nhox_ on 7/5/2017.
 */

public class AdapterForListView<T> extends ArrayAdapter<T> {
    Activity context = null;
    int Layoutid;
    Class myclass;
    List<T> objects;
    T selectedItem;
    public AdapterForListView(Activity context, int resource, List<T> objects,Class myclass) {
        super(context, resource, objects);
        this.context = context;
        this.myclass = myclass;
        this.Layoutid = resource;
        this.objects= objects;
    }
    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater =
                context.getLayoutInflater();
        convertView = inflater.inflate(Layoutid, null);
        if(convertView!=null){
            TextView textView =(TextView)convertView.findViewById(R.id.lblListItem);
            if(myclass == City.class){
                textView.setText(((City)objects.get(position)).getNamecity());
                textView.setTag(((City)objects.get(position)).getCityid());
            }else if(myclass == District.class){
                textView.setText(((District)objects.get(position)).getNamedist());
                textView.setTag(((District)objects.get(position)).getDistid());
            }else if(myclass == Comment.class) {

            }else {

            }

        }
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    ///////////////////
    // input :
    // purpose : gán giá trị user chọn cho một biến
    // output :
    /////////////////////
    public void setSelectedItem(T ob){
        selectedItem =  ob;
    }

    ///////////////////
    // input :
    // purpose : thay đổi cấu trúc adapter
    // output :
    /////////////////////
    public void swapAdapter(List<T> objects){
        if(this.objects!=null){
            System.out.println("swap adapter" +((District)objects.get(0)).getNamedist());
            this.objects.clear();
            this.objects.addAll(objects);
            notifyDataSetChanged();
        }
    }
}
