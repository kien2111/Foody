package com.example.nhox_.foody.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import com.example.nhox_.foody.Adapter.ExpandableListAdapter_DialogFragment;
import com.example.nhox_.foody.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Model.TypeRestaurant;
import UtilPackage.DbAdapter;
import UtilPackage.MyInterface.IOnDialogFragmentResult;


public class ExpandableDialogFragment extends DialogFragment implements ExpandableListView.OnGroupClickListener,ExpandableListView.OnChildClickListener, View.OnClickListener{
    View rootView;
    IOnDialogFragmentResult onDialogResultListener;
    ExpandableListView expandlistviewdiadiem;
    Button ok_btn;
    Button dismiss_btn;
    ExpandableListAdapter_DialogFragment adapter;
    List<String> lstheader;
    HashMap<String,List<TypeRestaurant>> datachild;
    DbAdapter dbAdapter;

    ///////////////////
    // input :
    // purpose : Xác định các view trong layout
    // output :
    /////////////////////
    private void Identify(){
        expandlistviewdiadiem = (ExpandableListView)rootView.findViewById(R.id.expandlistviewdiadiem);
        ok_btn = (Button)rootView.findViewById(R.id.ok_btn);
        dismiss_btn = (Button)rootView.findViewById(R.id.dismiss_btn);

        ok_btn.setOnClickListener(this);
        dismiss_btn.setOnClickListener(this);
        expandlistviewdiadiem.setOnChildClickListener(this);
        expandlistviewdiadiem.setOnGroupClickListener(this);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitializeDataAdapter();
        adapter = new ExpandableListAdapter_DialogFragment(this.getContext(),lstheader,datachild);


    }

    ///////////////////
    // input : Bundle
    // purpose : Tạo dialog
    // output : Trả về Dialog
    /////////////////////
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        rootView = getActivity().getLayoutInflater().inflate(R.layout.expandable_dialog_fragment_layout, null);
        builder.setView(rootView);
        Identify();
        expandlistviewdiadiem.setAdapter(adapter);
        expandlistviewdiadiem.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                for(int i=0;i<adapter.getGroupCount();i++){
                    expandlistviewdiadiem.expandGroup(i);
                }
            }
        });
        return builder.create();
    }



    ///////////////////
    // input :  View
    // purpose : Xử lý sự kiện click cho toàn bộ fragment
    // output :
    /////////////////////
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok_btn:
                onDialogResultListener.onPositiveResult(adapter.selecteditem);
                this.dismiss();
                break;
            case R.id.dismiss_btn:
                this.dismiss();
                break;
            default:
                break;
        }
    }
    List<TypeRestaurant> dataofchildlist;
    ///////////////////
    // input :
    // purpose : Khởi tạo giá trị cho Adapter
    // output :
    /////////////////////
    private void InitializeDataAdapter(){
        lstheader = new ArrayList<>();
        datachild = new HashMap<>();
        dbAdapter = DbAdapter.getInstace(this.getContext());
        dataofchildlist = dbAdapter.getTypeRestaurant();
        lstheader.add("Ăn uống");
        datachild.put(lstheader.get(0),dataofchildlist);



    }

    ///////////////////
    // input : Interface IOnDialogFragmentResult
    // purpose : Lấy giá trị trả về của dialog
    // output :
    /////////////////////
    public void setOnDialogResultListener(IOnDialogFragmentResult listener) {
        this.onDialogResultListener = listener;
    }
    ///////////////////
    // input : ExpandableListView , View , groupposition vị trí của node group , childposition vị trí của node con , id
    // purpose : Xử lý click lên node con của node group
    // output : luận lý true hoặc false
    /////////////////////
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        adapter.selecteditem = adapter._listDataChild.get(adapter._listDataHeader.get(groupPosition)).get(childPosition);
        System.out.println("child click");
        TextView txt = (TextView)v.findViewById(R.id.lblListItem);
        txt.setTextColor(this.getContext().getResources().getColor(R.color.colorNavbar));
        adapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        expandlistviewdiadiem.expandGroup(groupPosition);
        return true;
    }
}
