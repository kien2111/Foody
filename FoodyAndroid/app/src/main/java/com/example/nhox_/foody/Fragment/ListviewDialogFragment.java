package com.example.nhox_.foody.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nhox_.foody.Adapter.AdapterForListView;
import com.example.nhox_.foody.R;

import java.util.ArrayList;
import java.util.List;

import Model.City;
import Model.District;
import Model.Image;
import UtilPackage.MyInterface.IOnClickItemListviewDialogFragmentResult;
import UtilPackage.MyInterface.IOnDialogFragmentResult;

/**
 * Created by nhox_ on 7/5/2017.
 */

public class ListviewDialogFragment extends DialogFragment implements View.OnClickListener,AdapterView.OnItemClickListener {
    View rootView;
    IOnClickItemListviewDialogFragmentResult iOnClickItemListviewDialogFragmentResult;
    public ListView listviewoflocation;
    TextView titleTextview;
    public AdapterForListView adapter;
    Button boqua_btn;
    List<City> obcity;
    List<District> obdist;
    ///////////////////
    // input : Danh sách các thành phố , Class
    // purpose : Khởi tạo Dialog listview
    // output : ListviewDialogFragment
    /////////////////////
    public static ListviewDialogFragment createListCityViewDialogFragment(List<City> objects,Class myclass){
        ListviewDialogFragment dialogFragment = new ListviewDialogFragment();
        Bundle b = new Bundle();
        if(b!=null)b.clear();
        b.putSerializable("class",myclass);
        b.putParcelableArrayList("lstcity", (ArrayList<City>) objects);
        dialogFragment.setArguments(b);
        return dialogFragment;
    }
    ///////////////////
    // input : Danh sách các quận huyện , Class
    // purpose : Khởi tạo Dialog listview
    // output : ListviewDialogFragment
    /////////////////////
    public static ListviewDialogFragment createListDistViewDialogFragment(List<District> objects,Class myclass){
        ListviewDialogFragment dialogFragment = new ListviewDialogFragment();
        Bundle b = new Bundle();
        if(b!=null)b.clear();
        b.putSerializable("class",myclass);
        b.putParcelableArrayList("lstdist", (ArrayList<District>) objects);
        dialogFragment.setArguments(b);
        return dialogFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments().getSerializable("class")==City.class){
            obcity = getArguments().getParcelableArrayList("lstcity");
            if(obcity!=null){
                adapter = new AdapterForListView(this.getActivity(),R.layout.item_listview_layout_diadiem_subitem,obcity,City.class);
            }
        }else if(getArguments().getSerializable("class")==District.class){
            obdist = getArguments().getParcelableArrayList("lstdist");
            if(obdist!=null){
                adapter = new AdapterForListView(this.getActivity(),R.layout.item_listview_layout_diadiem_subitem,obdist,District.class);
            }
        }else {

        }

        rootView = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_listview, null);
        Identify();
        titleTextview = (TextView)rootView.findViewById(R.id.titleTextview);
        if(getArguments().getSerializable("class")==City.class){
            titleTextview.setText("Chọn Thành Phố");
        }else if(getArguments().getSerializable("class")==District.class){
            titleTextview.setText("Chọn Quận/Huyện");
        }else{

        }
        System.out.println("dialog create");
        if(adapter!=null){
            listviewoflocation.setAdapter(adapter);
        }
    }
    ///////////////////
    // input :
    // purpose : Xác định các view trong layout và set sự kiện cho view
    // output :
    /////////////////////
    private void Identify(){
        listviewoflocation = (ListView)rootView.findViewById(R.id.listviewoflocation);
        boqua_btn = (Button)rootView.findViewById(R.id.boqua_btn);



        boqua_btn.setOnClickListener(this);
        listviewoflocation.setOnItemClickListener(this);
    }
    int k=0;
    ///////////////////
    // input :
    // purpose : Khởi tạo Dialog
    // output :
    /////////////////////
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setView(rootView);

        System.out.println("dialog createdialog");


        return builder.create();
    }
    ///////////////////
    // input :
    // purpose : Xử lý click trên các view của layout
    // output :
    /////////////////////
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.boqua_btn:
                this.dismiss();
                break;
            default:
                break;
        }
    }

    ///////////////////
    // input :
    // purpose : Xử lý click trên item của listview
    // output :
    /////////////////////
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(getArguments().getSerializable("class")==City.class){
            adapter.setSelectedItem(obcity.get(position));
            iOnClickItemListviewDialogFragmentResult.NotifyResult(City.class,obcity.get(position));
        }else if(getArguments().getSerializable("class")==District.class){
            adapter.setSelectedItem(obdist.get(position));
            iOnClickItemListviewDialogFragmentResult.NotifyResult(District.class,obdist.get(position));
        }
        this.dismiss();
    }

    ///////////////////
    // input :
    // purpose : Lấy giá trị trả về của dialog listview
    // output :
    /////////////////////
    public void setiOnClickItemListviewDialogFragmentResult(IOnClickItemListviewDialogFragmentResult iOnClickItemListviewDialogFragmentResult){
        this.iOnClickItemListviewDialogFragmentResult = iOnClickItemListviewDialogFragmentResult;
    }
}
