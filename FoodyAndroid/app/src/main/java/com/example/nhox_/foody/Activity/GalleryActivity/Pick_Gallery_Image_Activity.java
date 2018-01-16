package com.example.nhox_.foody.Activity.GalleryActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhox_.foody.R;

import java.util.ArrayList;

import Model.ImageInGallery;
import UtilPackage.MyInterface.IOnClickImageGallery;

/**
 * Created by nhox_ on 3/5/2017.
 */

public class Pick_Gallery_Image_Activity extends AppCompatActivity implements View.OnClickListener, IOnClickImageGallery {

    public Pick_Gallery_Image_Activity() {
    }

    TextView text_view_name_folder_image_item;
    LinearLayout back_button_gallery;
    TextView text_view_done;
    RecyclerView grid_view_file;

    int mode;
    ArrayList<ImageInGallery> data;
    Pick_Gallery_Image_Adapter adapter;
    String namefolder;

    ImageView test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_gallery_image_activity);

        text_view_name_folder_image_item = (TextView) findViewById(R.id.text_view_name_folder_image_item);
        back_button_gallery = (LinearLayout) findViewById(R.id.back_button_gallery);
        grid_view_file = (RecyclerView) findViewById(R.id.recycle_view);
        text_view_done = (TextView) findViewById(R.id.text_view_done);

        back_button_gallery.setOnClickListener(this);
        text_view_done.setOnClickListener(this);

        this.namefolder = getIntent().getStringExtra("namefolder");
        this.mode = getIntent().getIntExtra("mode", 0);

        this.data = getIntent().getParcelableArrayListExtra("data");
        text_view_name_folder_image_item.setText(namefolder);

        adapter = new Pick_Gallery_Image_Adapter(getApplicationContext(), data);
        adapter.setOnClickImageGallery(this);

        grid_view_file.setLayoutManager(new GridLayoutManager(this, 3));
        grid_view_file.setAdapter(adapter);


    }

    private int selectedPosition(ImageInGallery item){
        for(int i=0;i<this.adapter.imageSelected.size();i++){
            ImageInGallery im=this.adapter.imageSelected.get(i);
            if(item.getPath().equals(im.getPath())){
                return i;
            }
        }
        return -1;
    }
    @Override
    public void onClickImageGallery(View v, int index) {
        int selectedItemPosition=selectedPosition(this.data.get(index));
        if (this.mode == Pick_Gallery_Folder_Activity.MULTI_SELECT) {
            if(selectedItemPosition!=-1){
                this.adapter.removeSelectedPosition(selectedItemPosition,index);
            }else {
                this.adapter.addSelected(this.data.get(index));
            }
        }else if(this.mode==Pick_Gallery_Folder_Activity.SINGLE_SELECT){
            if(selectedItemPosition!=-1){
                this.adapter.removeSelectedPosition(selectedItemPosition,index);
            }else{
                if(this.adapter.imageSelected.size()>0){
                    this.adapter.removeAllSelectedSingleClick();
                }
                this.adapter.addSelected(this.data.get(index));

            }
        }
    }

    @Override
    public void onClickDuong(View v, int grouppos, int childpos) {

    }

    public void text_view_done_HandleClick(){
        if(adapter.imageSelected!=null){
            Intent intent = new Intent();
            System.out.println(adapter.imageSelected.get(0).getPath()+" Test if exist image");
            intent.putParcelableArrayListExtra("imageresult",adapter.imageSelected);
            setResult(RESULT_OK,intent);
            finish();
        }else{
            Toast.makeText(this, "Khong co hinh anh", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button_gallery:
                finish();
                break;
            case R.id.text_view_done:
                text_view_done_HandleClick();
                break;
            default:
                break;
        }
    }

}
