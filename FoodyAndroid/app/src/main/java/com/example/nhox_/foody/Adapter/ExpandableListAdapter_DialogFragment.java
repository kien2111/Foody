package com.example.nhox_.foody.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.nhox_.foody.R;

import java.util.HashMap;
import java.util.List;

import Model.TypeRestaurant;

/**
 * Created by nhox_ on 6/5/2017.
 */

public class ExpandableListAdapter_DialogFragment extends BaseExpandableListAdapter {
    private Context _context;
    public TypeRestaurant selecteditem;
    public List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    public HashMap<String, List<TypeRestaurant>> _listDataChild;

    public ExpandableListAdapter_DialogFragment(Context context, List<String> listDataHeader,
                                 HashMap<String, List<TypeRestaurant>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon).getNametype();
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
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
        final ExpandableListView expl = (ExpandableListView)parent;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_listview_layout_diadiem_subitem, null);
        }

        final TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);
        expl.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                selecteditem = _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition);
                System.out.println("child click");
                TextView txt = (TextView)v.findViewById(R.id.lblListItem);
                txt.setTextColor(_context.getResources().getColor(R.color.colorNavbar));
                notifyDataSetChanged();
                return false;
            }
        });
        txtListChild.setTag(this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition).getTypeid());
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
        return this._listDataHeader.get(groupPosition).toString();
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
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
            convertView = infalInflater.inflate(R.layout.item_listview_layout_header, null);
        }
        ExpandableListView eLV = (ExpandableListView) parent;
        eLV.expandGroup(groupPosition);
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.textViewShow);
        lblListHeader.setTextColor(_context.getResources().getColor(R.color.colorNavbar));

        lblListHeader.setTypeface(null, Typeface.BOLD);

        lblListHeader.setText(headerTitle);

        return convertView;
    }
    ///////////////////
    // input :
    // purpose : Thay đổi cấu trúc adapter
    // output :
    /////////////////////
    public void swapadapter(List<String> lstdist,HashMap<String,List<TypeRestaurant>> lsthasmap){
        this._listDataHeader.clear();
        this._listDataChild.clear();
        this._listDataHeader.addAll(lstdist);
        this._listDataChild.putAll(lsthasmap);
        this.notifyDataSetChanged();
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
