package com.example.nhox_.foody.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nhox_.foody.R;

import java.util.HashMap;
import java.util.List;

import Model.District;
import Model.Street;
import UtilPackage.EnumItemDiaDiemClick;
import UtilPackage.GlobalVariable;
import UtilPackage.MyInterface.IOnClickImageGallery;

/**
 * Created by nhox_ on 24/4/2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter  {
    private Context _context;
    public List<District> _listDataHeader; // header titles
    // child data in format of header title, child title
    public HashMap<District, List<Street>> _listDataChild;
    IOnClickImageGallery iOnClickImageGallery;
    public ExpandableListAdapter(Context context, List<District> listDataHeader,
                                 HashMap<District, List<Street>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon).getTenduong();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    ///////////////////
    // input :
    // purpose : Tạo giao diện cho node con
    // output :
    /////////////////////
    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_listview_layout_diadiem_subitem, null);
        }

        final TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);
        LinearLayout subitem_click = (LinearLayout)convertView.findViewById(R.id.sub_item_click);
        subitem_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickImageGallery.onClickDuong(v,groupPosition,childPosition);
                if(GlobalVariable.oldpositiontabinlistview3click!=-1 && GlobalVariable.currentstateExpandableclick == EnumItemDiaDiemClick.DuongClick
                         && GlobalVariable.groupposition==groupPosition){
                    txtListChild.setTextColor(_context.getResources().getColor(R.color.colorNavbar));
                }else{
                    txtListChild.setTextColor(_context.getResources().getColor(R.color.black));
                }
            }
        });
        txtListChild.setTag(this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition).getIdduong());
        txtListChild.setText(childText);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        try{
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition).getNamedist();
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    public static int keyid =0;
    public static int text = 1;
    ///////////////////
    // input :
    // purpose : Tạo giao diện cho node cha
    // output :
    /////////////////////
    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_listview_layout_diadiem, null);
        }
        final ExpandableListView mExpandableListView = (ExpandableListView) parent;
        final TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.txttendiadiem);
        TextView txtSoDuong =(TextView)convertView
                .findViewById(R.id.txtsoduong);
        LinearLayout click_area = (LinearLayout)convertView.findViewById(R.id.click_area);
        click_area.setTag(this._listDataHeader.get(groupPosition).getDistid()+":"+this._listDataHeader.get(groupPosition).getNamedist());
        click_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickImageGallery.onClickImageGallery(v,groupPosition);
            }
        });
        if(GlobalVariable.oldpositiontabinlistview3click!=-1 && GlobalVariable.currentstateExpandableclick == EnumItemDiaDiemClick.QuanClick){
            lblListHeader.setTextColor(_context.getResources().getColor(R.color.colorNavbar));
        }else{
            lblListHeader.setTextColor(_context.getResources().getColor(R.color.black));
        }

        LinearLayout expand_group_btn = (LinearLayout)convertView.findViewById(R.id.expand_group_btn);
        expand_group_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isExpanded){
                    mExpandableListView.collapseGroup(groupPosition);
                }else{
                    mExpandableListView.expandGroup(groupPosition);
                }
            }
        });
        lblListHeader.setTypeface(null, Typeface.BOLD);
        try{
            txtSoDuong.setText(String.valueOf((getChildrenCount(groupPosition))+" đường"));
        }catch (Exception e){
            txtSoDuong.setText("0 đường");
        }

        lblListHeader.setTag(this._listDataHeader.get(groupPosition).getDistid());
        click_area.setTag(this._listDataHeader.get(groupPosition).getDistid());
        lblListHeader.setText(headerTitle);

        return convertView;
    }
    ///////////////////
    // input :
    // purpose : Thay đổi cấu trúc adapter
    // output :
    /////////////////////
    public void swapadapter(List<District> lstdist,HashMap<District,List<Street>> lsthasmap){
        this._listDataHeader.clear();
        this._listDataChild.clear();
        this._listDataHeader.addAll(lstdist);
        this._listDataChild.putAll(lsthasmap);
        this.notifyDataSetChanged();
    }
    ///////////////////
    // input :
    // purpose : gán delegate tạo callback function
    // output :
    /////////////////////
    public void setOnGroupClick(IOnClickImageGallery iOnClickImageGallery){
        this.iOnClickImageGallery = iOnClickImageGallery;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
