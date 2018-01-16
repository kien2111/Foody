package com.example.nhox_.foody.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhox_.foody.ItemforAdapter;
import com.example.nhox_.foody.MyArrayAdapter;
import com.example.nhox_.foody.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DbConnect.DataBaseHelper;
import Model.City;
import UtilPackage.DbAdapter;
import UtilPackage.GlobalVariable;

/**
 * Created by nhox_ on 10/4/2017.
 */

public class ChonTinhThanh_Activity1 extends AppCompatActivity {
    ListView lv;
    TextView tv;
    ImageButton imgbtn;
    DbAdapter dbAdapter = null;

    /////////////
    // input:
    // purpose: Xác định các view có trong layout
    // output:
    /////////////
    private void Identify(){
        tv = (TextView)findViewById(R.id.text_view_done_choose_provine);
        lv = (ListView)findViewById(R.id.list_view_choose_province);
        imgbtn = (ImageButton)findViewById(R.id.back_button_choose_province);
    }
    String tagofcurrentcityid;
    String nameofcity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chontinhthanh);
        createDB();
        Identify();
        final MyArrayAdapter myArrayAdapter = new MyArrayAdapter(this,R.layout.item_listview_layout,createListItem( dbAdapter.getTinhThanhForChonTinhThanhActivity()),
                GlobalVariable.oldpositionchontinhthanhactivityclick_1,2);
        lv.setAdapter(myArrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GlobalVariable.oldpositionchontinhthanhactivityclick_1=position;
                final TextView txtdisplay = (TextView)view.findViewById(R.id.txtitem);
                tagofcurrentcityid = txtdisplay.getTag().toString();
                nameofcity = txtdisplay.getText().toString();
                myArrayAdapter.selectedItem(position);
                myArrayAdapter.notifyDataSetChanged();
            }
        });
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameofcity!=null){
                    Intent intent = new Intent();
                    intent.putExtra("nameofcity1",nameofcity);
                    setResult(RESULT_OK, intent);
                    GlobalVariable.currentcityid_1 = tagofcurrentcityid;
                    GlobalVariable.currentdistid_1=null;
                    GlobalVariable.currentstreetid_1 = -1;
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Chưa chọn thành phố",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /////////////
    // input: Danh sách thành phố
    // purpose: Tạo ra danh sách các đối tượng ItemforAdapter từ danh sách các thành phố
    // output: Trả về danh sách các đối tượng ItemforAdapter
    /////////////
    public List<ItemforAdapter> createListItem(List<City> lstcity){
        List<ItemforAdapter> items = new ArrayList<ItemforAdapter>();
        if(items.size()>0)items.clear();
        for(int i=0;i<lstcity.size();i++){
            items.add(new ItemforAdapter(lstcity.get(i).getCityid(),lstcity.get(i).getNamecity()));
        }
        return items;
    }

    /////////////
    // input:
    // purpose: Tạo database
    // output:
    /////////////
    private void createDB() {
        try {
            dbAdapter = DbAdapter.getInstace(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
