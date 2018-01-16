package com.example.nhox_.foody;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nhox_.foody.Activity.ChonTinhThanh_Activity1;
import com.example.nhox_.foody.Adapter.ExpandableListAdapter;
import com.example.nhox_.foody.Fragment.MainFragment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.District;
import Model.Street;
import UtilPackage.DbAdapter;
import UtilPackage.EnumItemDiaDiemClick;
import UtilPackage.GlobalVariable;
import UtilPackage.InfoFragmentClick;
import UtilPackage.MyInterface.IOnClickImageGallery;
import UtilPackage.NotifierToParent1;

import static UtilPackage.Utils.mynotifiertoparent1;
import static android.app.Activity.RESULT_OK;

/**
 * Created by nhox_ on 10/4/2017.
 */

public class myListFragmentfordiadiem1 extends Fragment implements IOnClickImageGallery {
    Button btnHuy;

    TextView namecity;
    LinearLayout btn_change_province;
    DbAdapter dbAdapter = null;
    //View of parent

    /////////////
    // input: Danh sách quận huyện thị xã,vị trí đã click trong listview
    // purpose: Tạo đối tượng myListFragmentfordiadiem
    // output: Trả về đối tượng myListFragmentfordiadiem
    /////////////
    public static myListFragmentfordiadiem1 newInstance1(List<District> lst,int positiondistclick,int parentfragmentinviewpager) {
        myListFragmentfordiadiem1 f = new myListFragmentfordiadiem1();
        Bundle b = new Bundle();
        if(b!=null)b.clear();
        b.putInt("parentfragmentinviewpager1",parentfragmentinviewpager);
        b.putParcelableArrayList("listdist1", (ArrayList<District>) lst);
        b.putInt("positiondistclick1",positiondistclick);
        f.setArguments(b);
        return f;
    }

    ///////////////////
    // input :
    // purpose : Lấy dữ liệu từ database local
    // output :
    /////////////////////
    private List<District> getTinh(String query) {
        return dbAdapter.getTinhTheoTP(query);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String strnameofcity = data.getStringExtra("nameofcity1");

                if (mynotifiertoparent1 != null)
                {
                    //Swap data for listviewfragment
                    listDataHeader=null;
                    listDataChild.clear();
                    listDataHeader=getTinh (GlobalVariable.currentcityid_1);
                    prepareListData();

                    listAdapter.swapadapter(listDataHeader,listDataChild);

                    namecity.setText(listDataHeader.get(0).getCity().getNamecity());
                    namecity.setTag(listDataHeader.get(0).getCity().getCityid());
                    mynotifiertoparent1.notifytoparent(new InfoFragmentClick(strnameofcity,
                            0,
                            false,
                            2,
                            strnameofcity,
                            null,
                            namecity.getTag().toString(),
                            GlobalVariable.currenttyperestid_1,
                            -1,
                            EnumItemDiaDiemClick.ThanhPhoClick,
                            getArguments().getInt("parentfragmentinviewpager1")));
                }
            }
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        onAttachToParentFragment(getParentFragment());
        dbAdapter = DbAdapter.getInstace(getContext());
    }

    String tag;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<District> listDataHeader;
    HashMap<District, List<Street>> listDataChild;
    ///////////////////
    // input :
    // purpose : Tạo dữ liệu đường cho expandablelistview
    // output :
    /////////////////////
    private void prepareListData() {
        listDataChild = new HashMap<District, List<Street>>();
        for(int j=0;j<listDataHeader.size();j++){
            List<Street> lststreet = dbAdapter.getStreet(listDataHeader.get(j).getDistid());
            listDataChild.put(listDataHeader.get(j), lststreet);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_for_diadiem1, container, false);
        btn_change_province = (LinearLayout) view.findViewById(R.id.btn_change_province1);
        listDataHeader =   getArguments().getParcelableArrayList("listdist1");
        prepareListData();

        namecity = (TextView)view.findViewById(R.id.nameofcity1);
        namecity.setTag(listDataHeader.get(0).getCity().getCityid());
        namecity.setText(listDataHeader.get(0).getCity().getNamecity());
        btnHuy = (Button)view.findViewById(R.id.btnHuy3);
        btn_change_province.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),ChonTinhThanh_Activity1.class);
                startActivityForResult(i,1);
            }
        });
        expListView = (ExpandableListView)view.findViewById(R.id.expandlistviewdiadiem1);
        listAdapter = new ExpandableListAdapter(this.getContext(), listDataHeader, listDataChild);
        listAdapter.setOnGroupClick(this);
        expListView.setAdapter(listAdapter);


        tag = this.getTag();
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mynotifiertoparent1 != null)
                {
                    if(mynotifiertoparent1==null)System.out.println("iNotifierFromDiaDiemFragment null");
                    mynotifiertoparent1.notifytoparent(new InfoFragmentClick(tag,
                            -1,
                            true,
                            2,
                            null,
                            null,
                            null,
                            null,
                            -1,
                            EnumItemDiaDiemClick.NoneClick,
                            getArguments().getInt("parentfragmentinviewpager1")));
                }
            }
        });
        return view;
    }

    //InfoFragmentClick argument constructor:
    /* *
            String tagoffragment
            int positionlistview
            boolean hideFragment
            int positionoftab
            String tabtextdisplay
            String tagdistid
            String tagcityid
            String tagtypeid
            int parentfragmentoflistmenu
    * */

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    /////////////
    // input: Fragment nhận sự kiện
    // purpose: Tạo sự kiện thông báo cho parent fragment sử dụng interface NotifierToParent
    // output:
    /////////////
    public void onAttachToParentFragment(Fragment fragment)
    {
        if(fragment instanceof MainFragment1){
            try
            {
                mynotifiertoparent1 = (NotifierToParent1) fragment;

            }
            catch (ClassCastException e)
            {
                throw new ClassCastException(
                        fragment.toString() + " must implement");
            }
        }
    }
    ///////////////////
    // input :
    // purpose : Xử lý khi người dùng click lên dữ liệu Quận huyện
    // output :
    /////////////////////
    public void click_group_Handle(View view,int groupindex){
        if (mynotifiertoparent1 != null)
        {
            mynotifiertoparent1.notifytoparent(new InfoFragmentClick(GlobalVariable.tabtext3display_1,
                    groupindex,
                    false,
                    2,
                    listAdapter._listDataHeader.get(groupindex).getNamedist(),
                    listAdapter._listDataHeader.get(groupindex).getDistid(),
                    namecity.getTag().toString(),
                    GlobalVariable.currenttyperestid_1,
                    -1,
                    EnumItemDiaDiemClick.QuanClick,
                    -1));
        }
    }
    ///////////////////
    // input :
    // purpose : Xử lý khi user click lên dữ liệu Đường
    // output :
    /////////////////////
    public void click_child_Handle(View v,int groupPosition,int childPosition){
        if (mynotifiertoparent1 != null)
        {
            mynotifiertoparent1.notifytoparent(new InfoFragmentClick(tag,
                    childPosition,
                    false,
                    2,
                    listAdapter._listDataChild.get(listAdapter._listDataHeader.get(groupPosition)).get(childPosition).getTenduong(),
                    namecity.getTag().toString(),
                    GlobalVariable.currentdistid_1,
                    GlobalVariable.currenttyperestid_1,
                    listAdapter._listDataChild.get(listAdapter._listDataHeader.get(groupPosition)).get(childPosition).getIdduong(),
                    EnumItemDiaDiemClick.DuongClick,
                    groupPosition));
        }
    }


///////////////////
    // input :
    // purpose : Xử lý khi user click lên view
    // output :
    /////////////////////

    @Override
    public void onClickImageGallery(View v, int index) {
        switch (v.getId()){
            case R.id.click_area:
                click_group_Handle(v,index);
                break;
            default:
                break;
        }
    }
    ///////////////////
    // input :
    // purpose : Xử lý khi user click lên view
    // output :
    /////////////////////
    @Override
    public void onClickDuong(View v, int grouppos, int childpos) {
        switch (v.getId()){
            case R.id.sub_item_click:
                click_child_Handle(v,grouppos,childpos);
                break;
            default:
                break;
        }
    }
}
