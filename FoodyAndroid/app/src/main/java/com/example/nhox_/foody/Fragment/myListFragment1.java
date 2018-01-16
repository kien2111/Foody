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
import UtilPackage.InfoFragmentClick;
import UtilPackage.NotifierToParent1;

import static UtilPackage.Utils.mynotifiertoparent1;


/**
 * Created by nhox_ on 22/3/2017.
 */

public class myListFragment1 extends AbstractmyListFragment {

    Button btnHuy;
    ListView lv ;
    /////////////
    // input: Danh sách hình ảnh hiển thị,kiểm tra xem có phải dữ liệu lấy từ database không,vị trí mà người dùng đã click trước đó
    // purpose: Tạo đối tượng mylistFragment1
    // output: Trả về đối tượng myListFragment1
    /////////////
    public static myListFragment1 newInstance1(List<Image> images,boolean isStaticSource,int positionclick) {
        myListFragment1 f = new myListFragment1();
        Bundle b = new Bundle();
        if(b!=null)b.clear();
        b.putInt("positionclick1",positionclick);
        b.putBoolean("isStaticSource1",isStaticSource);
        b.putParcelableArrayList("lstimagedatabase1", (ArrayList<Image>) images);
        f.setArguments(b);
        return f;
    }

    /////////////
    // input: Danh sách chuỗi,Danh sách id ảnh trong drawable,kiểm tra xem có phải dữ liệu lấy từ database không,vị trí mà người dùng đã click trước đó
    // purpose: Tạo đối tượng mylistFragment1
    // output: Trả về đối tượng myListFragment1
    /////////////
    public static myListFragment1 newInstance(List<String> text,List<Integer> source,boolean isStaticSource,int positionclick) {
        myListFragment1 f = new myListFragment1();
        Bundle b = new Bundle();
        if(b!=null)b.clear();
        b.putInt("positionclick1",positionclick);
        b.putBoolean("isStaticSource1",isStaticSource);
        b.putStringArrayList("lsttext1",(ArrayList<String>) text);
        b.putIntegerArrayList("lstimage1", (ArrayList<Integer>) source);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        onAttachToParentFragment(getParentFragment());
    }

    String tag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_layout1, container, false);
        boolean isStaticResource = getArguments().getBoolean("isStaticSource1");
        List<ItemforAdapter> items = null;
        if(isStaticResource){
            List<String> text =  getArguments().getStringArrayList("lsttext1");
            List<Integer> source = getArguments().getIntegerArrayList("lstimage1");
            items = createListItem(text,source);
            StaticStyle(items);
        }else{
            List<Image> source =  getArguments().getParcelableArrayList("lstimagedatabase1");
            items = createListItem(source);
            Style(items);
        }

        btnHuy = (Button)view.findViewById(R.id.btnHuy1);
        lv = (ListView)view.findViewById(android.R.id.list);
        tag = this.getTag();
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mynotifiertoparent1 != null)
                {
                    System.out.println("click huy");
                    mynotifiertoparent1.notifytoparent(new InfoFragmentClick(tag,0,true,0,null,null,null,null,-1, EnumItemDiaDiemClick.NoneClick,1));
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
                    TextView tv = (TextView) view.findViewById(R.id.txtitem);
                    if (mynotifiertoparent1 != null)
                    {
                        staticadapter.swap(position);
                        mynotifiertoparent1.notifytoparent(new InfoFragmentClick("Mới nhất",
                                position,
                                false,
                                0,
                                tv.getText().toString(),
                                null,
                                null,
                                null,
                                -1,
                                EnumItemDiaDiemClick.NoneClick,
                                1));
                    }
                }
            });
        }else if(this.getTag()=="Danh mục"){
            getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView tv = (TextView) view.findViewById(R.id.txtitem);
                    if (mynotifiertoparent1 != null)
                    {
                        myarrayadapter.swap(position);
                        mynotifiertoparent1.notifytoparent(new InfoFragmentClick("Danh mục",
                                position,
                                false,
                                1,
                                tv.getText().toString(),
                                null,
                                null,
                                tv.getTag().toString(),
                                -1,
                                EnumItemDiaDiemClick.NoneClick,
                                1));
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
            mynotifiertoparent1 = (NotifierToParent1)fragment;

        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(
                    fragment.toString() + " must implement OnPlayerSelectionSetListener");
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


