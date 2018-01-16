package com.example.nhox_.foody.Fragment;

import android.support.v4.app.ListFragment;

import com.example.nhox_.foody.ItemforAdapter;

import java.util.ArrayList;
import java.util.List;

import Model.Image;

/**
 * Created by nhox_ on 10/4/2017.
 */

public abstract class AbstractmyListFragment extends ListFragment {


    /////////////
    // input: Danh sách hình ảnh
    // purpose: Tạo ra danh sách đối tượng ItemforAdapter từ danh sách hình ảnh
    // output:  Danh sách đối tượng ItemforAdapter
    /////////////
    protected List<ItemforAdapter> createListItem(List<Image> images){
        List<ItemforAdapter> items = new ArrayList<ItemforAdapter>();
        if(items.size()>0)items.clear();
        for(int i=0;i<images.size();i++){
            items.add(new ItemforAdapter(images.get(i)));
        }
        return items;
    }
    /////////////
    // input: Danh sách chuỗi và danh sách hình ảnh trong drawable
    // purpose: Tạo ra danh sách đối tượng ItemforAdapter từ danh sách chuỗi và danh sách hình ảnh trong drawable
    // output:  Danh sách đối tượng ItemforAdapter
    /////////////
    protected List<ItemforAdapter> createListItem(List<String> str,List<Integer> src){
        List<ItemforAdapter> items = new ArrayList<ItemforAdapter>();
        if(items.size()>0)items.clear();
        if(src ==null){
            for(int i=0;i<str.size();i++){
                items.add(new ItemforAdapter(str.get(i),0));
            }
        }else{
            for(int i=0;i<str.size();i++){
                items.add(new ItemforAdapter(str.get(i),src.get(i)));
            }
        }
        return items;
    }

    /////////////
    // input: Danh sách đối tượng itemforAdapters
    // purpose: Gán adapter cho listview trong listfragment sử dụng đối tượng MyArrayAdapter
    // output:
    /////////////
    /*protected void Style(List<ItemforAdapter> itemforAdapters){
        MyArrayAdapter adapter = new MyArrayAdapter(getActivity(),R.layout.item_listview_layout,itemforAdapters,getArguments().getInt("positionclick"),1);
        setListAdapter(adapter);
    }*/


    /////////////
    // input: Danh sách đối tượng itemforAdapters
    // purpose: Gán adapter cho listview trong listfragment sử dụng đối tượng StaticResourceAdapter
    // output:
    /////////////
    /*protected void StaticStyle(List<ItemforAdapter> itemforAdapters){
        StaticResourceAdapter adapter = new StaticResourceAdapter(getActivity(),R.layout.item_listview_layout,itemforAdapters,getArguments().getInt("positionclick"));
        setListAdapter(adapter);

    }*/
}
