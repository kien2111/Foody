package com.example.nhox_.foody.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nhox_.foody.ItemforAdapter;
import com.example.nhox_.foody.MyArrayAdapter;
import com.example.nhox_.foody.R;
import com.example.nhox_.foody.StaticResourceAdapter;

import java.util.ArrayList;
import java.util.List;

import Model.Image;
import UtilPackage.EnumItemDiaDiemClick;
import UtilPackage.INotifier;
import UtilPackage.InfoFragmentClick;
import UtilPackage.NotifierToParent;

import static UtilPackage.Utils.changeChildFragment;
import static UtilPackage.Utils.mynotifiertoparent;
import static UtilPackage.Utils.notifier;


/**
 * Created by nhox_ on 22/3/2017.
 */

public class myListFragment extends AbstractmyListFragment {

    Button btnHuy;
    ListView lv ;


    /////////////
    // input: Danh sách hình ảnh hiển thị,kiểm tra xem có phải dữ liệu lấy từ database không,vị trí mà người dùng đã click trước đó
    // purpose: Tạo đối tượng mylistFragment
    // output: Trả về đối tượng myListFragment
    /////////////
    public static myListFragment newInstance1(List<Image> images,boolean isStaticSource,int positionclick) {
        myListFragment f = new myListFragment();
        Bundle b = new Bundle();
        if(b!=null)b.clear();
        b.putInt("positionclick",positionclick);
        b.putBoolean("isStaticSource",isStaticSource);
        b.putParcelableArrayList("lstimagedatabase", (ArrayList<Image>) images);
        f.setArguments(b);
        return f;
    }

    /////////////
    // input: Danh sách chuỗi,Danh sách id ảnh trong drawable,kiểm tra xem có phải dữ liệu lấy từ database không,vị trí mà người dùng đã click trước đó
    // purpose: Tạo đối tượng mylistFragment
    // output: Trả về đối tượng myListFragment
    /////////////
    public static myListFragment newInstance(List<String> text,List<Integer> source,boolean isStaticSource,int positionclick) {
        myListFragment f = new myListFragment();
        Bundle b = new Bundle();
        if(b!=null)b.clear();
        b.putInt("positionclick",positionclick);
        b.putBoolean("isStaticSource",isStaticSource);
        b.putStringArrayList("lsttext",(ArrayList<String>) text);
        b.putIntegerArrayList("lstimage", (ArrayList<Integer>) source);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        onAttachToParentFragment2(getParentFragment());
        onAttachToParentFragment(getParentFragment().getParentFragment());
    }

    String tag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_layout, container, false);
        boolean isStaticResource = getArguments().getBoolean("isStaticSource");
        List<ItemforAdapter> items = null;
        if(isStaticResource){
            List<String> text =  getArguments().getStringArrayList("lsttext");
            List<Integer> source = getArguments().getIntegerArrayList("lstimage");
            items = createListItem(text,source);
            StaticStyle(items);
        }else{
            List<Image> sourceimage =  getArguments().getParcelableArrayList("lstimagedatabase");
            items = createListItem(sourceimage);
            Style(items);
        }

        btnHuy = (Button)view.findViewById(R.id.btnHuy);
        lv = (ListView)view.findViewById(android.R.id.list);
        tag = this.getTag();
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notifier != null)
                {
                    notifier.notify(new InfoFragmentClick(tag,-1,true,-1,null,null,null,null,0,EnumItemDiaDiemClick.NoneClick,0));

                }
                if(mynotifiertoparent!=null){
                    mynotifiertoparent.notifytoparent(new InfoFragmentClick("Địa điểm",
                            0,
                            true,
                            0,
                            null,
                            null,
                            null,
                            null,
                            -1,
                            EnumItemDiaDiemClick.NoneClick,
                            0));
                }

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(this.getTag()=="Mới nhất"){
            getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView tv = (TextView)view.findViewById(R.id.txtitem);

                    if (notifier != null)
                    {
                        /*notifier.notify(new InfoFragmentClick("Mới nhất",
                                position,
                                false,
                                0,
                                tv.getText().toString(),
                                null,
                                null,
                                null,
                                0));*/
                    }
                    if(mynotifiertoparent!=null){
                        staticadapter.swap(position);
                        mynotifiertoparent.notifytoparent(new InfoFragmentClick("Mới nhất",
                                position,
                                false,
                                0,
                                tv.getText().toString(),
                                null,
                                null,
                                null,
                                -1,
                                EnumItemDiaDiemClick.NoneClick,
                                0));
                    }

                }
            });
        }else if(this.getTag()=="Danh mục"){
            getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView tv = (TextView)view.findViewById(R.id.txtitem);
                    if (notifier != null)
                    {

                        /*notifier.notify(new InfoFragmentClick("Danh mục",
                                position,
                                false,
                                1,
                                tv.getText().toString(),
                                null,
                                null,
                                null,
                                0));*/
                    }
                    if(mynotifiertoparent!=null){
                        myarrayadapter.swap(position);
                        mynotifiertoparent.notifytoparent(new InfoFragmentClick("Danh mục",
                                position,
                                false,
                                1,
                                tv.getText().toString(),
                                null,
                                null,
                                tv.getTag().toString(),
                                -1,
                                EnumItemDiaDiemClick.NoneClick,
                                0));
                    }
                }
            });
        }
    }


    /////////////
    // input: Fragment nhận sự kiện
    // purpose: Tạo sự kiện thông báo cho parent fragment sử dụng interface INotifier
    // output:
    /////////////
    public void onAttachToParentFragment(Fragment fragment)
    {
        try
        {
            notifier = (INotifier)fragment;
            changeChildFragment = (INotifier)fragment;

        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(
                    fragment.toString() + " must implement");
        }
    }
    /////////////
    // input: Fragment nhận sự kiện
    // purpose: Tạo sự kiện thông báo cho parent fragment sử dụng interface NotifierToParent
    // output:
    /////////////
    public void onAttachToParentFragment2(Fragment fragment)
    {
        if(fragment instanceof MainFragment){
            try
            {
                mynotifiertoparent = (NotifierToParent)fragment;


            }
            catch (ClassCastException e)
            {
                throw new ClassCastException(
                        fragment.toString() + " must implement");
            }
        }
    }





    /////////////
    // input: Danh sách đối tượng itemforAdapters
    // purpose: Gán adapter cho listview trong listfragment sử dụng đối tượng MyArrayAdapter
    // output:
    /////////////
    MyArrayAdapter myarrayadapter;
    public void Style(List<ItemforAdapter> itemforAdapters){
        myarrayadapter = new MyArrayAdapter(getActivity(),R.layout.item_listview_layout,itemforAdapters,getArguments().getInt("positionclick"),1);
        setListAdapter(myarrayadapter);
    }
    /////////////
    // input: Danh sách đối tượng itemforAdapters
    // purpose: Gán adapter cho listview trong listfragment sử dụng đối tượng StaticResourceAdapter
    // output:
    /////////////
    StaticResourceAdapter staticadapter;
    public void StaticStyle(List<ItemforAdapter> itemforAdapters){
        staticadapter = new StaticResourceAdapter(getActivity(),R.layout.item_listview_layout,itemforAdapters,getArguments().getInt("positionclick"));
        setListAdapter(staticadapter);

    }





}


