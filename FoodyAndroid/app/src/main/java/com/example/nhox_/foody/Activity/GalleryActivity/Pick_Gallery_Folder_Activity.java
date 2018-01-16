package com.example.nhox_.foody.Activity.GalleryActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhox_.foody.R;

import java.util.ArrayList;

import Model.FolderImage;
import Model.ImageInGallery;

/**
 * Created by nhox_ on 3/5/2017.
 */

public class Pick_Gallery_Folder_Activity extends Activity implements View.OnClickListener, GridView.OnItemClickListener {
    public Pick_Gallery_Folder_Activity() {

    }



    public static final int SINGLE_SELECT = 0;
    public static final int MULTI_SELECT = 1;


    LinearLayout back_button_gallery;
    TextView text_view_done;
    GridView grid_view_folder;
    Pick_Gallery_Folder_Adapter adapter;
    int mode;
    private static final int REQUEST_PERMISSIONS = 100;
    public ArrayList<FolderImage> imageGalleryList = new ArrayList<>();
    boolean isFile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_gallery_folder_activity);
        this.mode=getIntent().getIntExtra("mode",0);

        initView();
        initEvent();
        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(Pick_Gallery_Folder_Activity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(Pick_Gallery_Folder_Activity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {

            } else {
                ActivityCompat.requestPermissions(Pick_Gallery_Folder_Activity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        }else {
            Log.e("Else","Else");
            imageGalleryList = getData();
            adapter = new Pick_Gallery_Folder_Adapter(this.getApplicationContext(), imageGalleryList);
            grid_view_folder.setAdapter(adapter);
            grid_view_folder.setOnItemClickListener(this);
        }

    }

    private void initView() {
        back_button_gallery = (LinearLayout) findViewById(R.id.back_button_gallery);
        text_view_done = (TextView) findViewById(R.id.text_view_done);
        grid_view_folder = (GridView) findViewById(R.id.grid_view_folder);
    }

    private void initEvent() {
        back_button_gallery.setOnClickListener(this);
        text_view_done.setOnClickListener(this);
    }


    public ArrayList<FolderImage> getData() {
        ArrayList<FolderImage> list = new ArrayList<>();
        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;

        cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);


            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getFolder().equals(cursor.getString(column_index_folder_name))) {
                    isFile = true;
                    int_position = i;
                    break;
                } else {
                    isFile = false;
                }
            }
            if (isFile) {

                ArrayList<ImageInGallery> al_path = new ArrayList<>();
                al_path.addAll(list.get(int_position).getImageInFolder());
                al_path.add(new ImageInGallery(absolutePathOfImage, false));
                list.get(int_position).setImageInFolder(al_path);

            } else {
                ArrayList<ImageInGallery> al_path = new ArrayList<>();
                al_path.add(new ImageInGallery(absolutePathOfImage, false));
                FolderImage obj_model = new FolderImage();
                obj_model.setFolder(cursor.getString(column_index_folder_name));
                obj_model.setImageInFolder(al_path);

                list.add(obj_model);
            }
        }

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), Pick_Gallery_Image_Activity.class);
        intent.putExtra("namefolder", ((FolderImage) this.adapter.getItem(position)).getFolder());
        intent.putExtra("mode", this.mode);
        intent.putParcelableArrayListExtra("data", ((FolderImage) this.adapter.getItem(position)).getImageInFolder());
        startActivityForResult(intent,MULTI_SELECT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_done:
                text_view_done_HandleClick();
                break;
            case R.id.back_button_gallery:
                finish();
                break;
        }
    }
    ArrayList<ImageInGallery> lstimageingalleryreturn;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode == MULTI_SELECT){
                Log.d("folder activity","lstimageingallery");
                lstimageingalleryreturn = data.getParcelableArrayListExtra("imageresult");
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        imageGalleryList = getData();
                        adapter = new Pick_Gallery_Folder_Adapter(this.getApplicationContext(), imageGalleryList);
                        grid_view_folder.setAdapter(adapter);
                        grid_view_folder.setOnItemClickListener(this);
                    } else {
                        Toast.makeText(Pick_Gallery_Folder_Activity.this, "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    public void text_view_done_HandleClick(){
        if(lstimageingalleryreturn!=null){
            Intent intent = new Intent();
            intent.putParcelableArrayListExtra("lstimagefromfolder",lstimageingalleryreturn);
            setResult(RESULT_OK,intent);
            finish();
        }else {
            Toast.makeText(this,"Khong lua chon hinh anh",Toast.LENGTH_SHORT);
        }
    }
}