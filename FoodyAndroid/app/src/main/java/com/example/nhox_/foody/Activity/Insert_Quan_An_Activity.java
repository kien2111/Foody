package com.example.nhox_.foody.Activity;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.nhox_.foody.Activity.GalleryActivity.Pick_Gallery_Folder_Activity;
import com.example.nhox_.foody.Adapter.Adapter_Image_In_Insert_Quan_An;
import com.example.nhox_.foody.Fragment.ExpandableDialogFragment;
import com.example.nhox_.foody.Fragment.ListviewDialogFragment;
import com.example.nhox_.foody.R;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Model.City;
import Model.District;
import Model.ImageInGallery;
import Model.RequestImageModel;
import Model.Restaurant;
import Model.TypeRestaurant;
import UtilPackage.Abstract.RestCall;
import UtilPackage.AsyncHttpHandle.HandleHttpResponse;
import UtilPackage.AsyncHttpHandle.IListener;
import UtilPackage.DbAdapter;
import UtilPackage.EnumTaskOption;
import UtilPackage.MyInterface.IOnClickImageGallery;
import UtilPackage.MyInterface.IOnClickItemListviewDialogFragmentResult;
import UtilPackage.MyInterface.IOnDialogFragmentResult;
import UtilPackage.TaskCallRest.CallRestTaskgetList;
import UtilPackage.Utility;
import cz.msebera.android.httpclient.entity.StringEntity;


import static com.example.nhox_.foody.Activity.GalleryActivity.Pick_Gallery_Folder_Activity.MULTI_SELECT;

/**
 * Created by nhox_ on 24/4/2017.
 */

public class Insert_Quan_An_Activity extends FragmentActivity implements IListener,IOnClickItemListviewDialogFragmentResult,IOnDialogFragmentResult, View.OnFocusChangeListener, View.OnClickListener,IOnClickImageGallery {
    String currentcityidinthiscontext;
    String previouscityidinthiscontext;
    Button back_button;
    TextView send_btn;
    Button from_time_btn;
    Button to_time_btn;
    Button chon_city_btn;
    Button chon_dist_btn;
    EditText mota_editText;
    TextView locationAddress_textView;
    TextView loaihinhdiadiem_txt;
    RecyclerView recycleview_contain_image;
    LinearLayout edit_text_container_left;
    LinearLayout edit_text_container_right;
    LinearLayout loaihinhdiadiem_lnr_btn;
    EditText namerest_editText;
    EditText edit_giathapnhat;
    EditText edit_giacaonhat;
    List<City> lstcity;
    List<District> lstdist;
    LinearLayout tim_vitri_btn;
    LinearLayout pick_image_gallery_btn;
    GridLayoutManager gridimage;
    FrameLayout no_image;
    RelativeLayout have_image;
    EditText address_editText;
    TextView soimage;
    EditText sodienthoai_editText;
    Adapter_Image_In_Insert_Quan_An adapter_image_in_insert_quan_an;
    DbAdapter dbAdapter;
    AsyncHttpClient client;
    ProgressBar progressBar;
    private Time toTime;
    private Time fromTime;
    private ArrayList<ImageInGallery> imagesreturnfromfolder;
    private static int RESULT_LOAD_IMAGE = 1;
    private TimePickerDialog totimePickerDialog;
    private TimePickerDialog fromtimePickerDialog;
    ///////////////////
    // input :
    // purpose : Xác định view cho layout
    // output :
    /////////////////////
    private void Identify(){
        mota_editText = (EditText)findViewById(R.id.mota_editText);
        sodienthoai_editText = (EditText)findViewById(R.id.sodienthoai_editText);
        back_button = (Button)findViewById(R.id.back_button);
        send_btn = (TextView)findViewById(R.id.send_btn);
        from_time_btn = (Button) findViewById(R.id.from_time_btn);
        to_time_btn = (Button)findViewById(R.id.to_time_btn);
        locationAddress_textView = (TextView)findViewById(R.id.locationAddress_textView);
        edit_text_container_left = (LinearLayout)findViewById(R.id.edit_text_container_left);
        edit_text_container_right = (LinearLayout)findViewById(R.id.edit_text_container_right);
        edit_giacaonhat = (EditText)findViewById(R.id.edit_giacaonhat);
        edit_giathapnhat = (EditText)findViewById(R.id.edit_giathapnhat);
        tim_vitri_btn = (LinearLayout)findViewById(R.id.tim_vitri_btn);
        pick_image_gallery_btn = (LinearLayout)findViewById(R.id.pick_image_gallery_btn);
        recycleview_contain_image = (RecyclerView)findViewById(R.id.recycleview_contain_image);
        no_image =(FrameLayout)findViewById(R.id.no_image);
        have_image = (RelativeLayout)findViewById(R.id.have_image);
        soimage = (TextView)findViewById(R.id.soimage);
        address_editText = (EditText)findViewById(R.id.address_editText);
        loaihinhdiadiem_lnr_btn = (LinearLayout)findViewById(R.id.loaihinhdiadiem_lnr_btn);
        loaihinhdiadiem_txt = (TextView)findViewById(R.id.loaihinhdiadiem_txt);
        chon_city_btn = (Button)findViewById(R.id.chon_city_btn);
        chon_dist_btn = (Button)findViewById(R.id.chon_dist_btn);
        namerest_editText = (EditText)findViewById(R.id.namerest_editText);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        chon_dist_btn.setOnClickListener(this);
        chon_city_btn.setOnClickListener(this);

        //Callback event
        loaihinhdiadiem_lnr_btn.setOnClickListener(this);
        send_btn.setOnClickListener(this);
        pick_image_gallery_btn.setOnClickListener(this);
        tim_vitri_btn.setOnClickListener(this);
        edit_giathapnhat.setOnFocusChangeListener(this);
        edit_giacaonhat.setOnFocusChangeListener(this);
        from_time_btn.setOnClickListener(this);
        to_time_btn.setOnClickListener(this);
        back_button.setOnClickListener(this);
        response = new HandleHttpResponse();
        response.setOnSuccess(this);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_quan_an);
        Identify();
        setDateTimeField();
        InitializeData();



    }
    ///////////////////
    // input :
    // purpose : Khởi tạo dữ liệu cho city
    // output :
    /////////////////////
    public void InitializeData(){
        toTime = new Time(10,00,00);
        fromTime = new Time(10,00,00);
        dbAdapter = DbAdapter.getInstace(this);
        client = new AsyncHttpClient();
        lstcity = dbAdapter.getTinhThanhForChonTinhThanhActivity();
        if(lstcity!=null){
            chon_city_btn.setText(lstcity.get(0).getNamecity());
            chon_city_btn.setTag(lstcity.get(0).getCityid());
            currentcityidinthiscontext = lstcity.get(0).getCityid();
            previouscityidinthiscontext = lstcity.get(0).getCityid();
            lstdist = dbAdapter.getTinhTheoTP(currentcityidinthiscontext);
            dialogcity=ListviewDialogFragment.createListCityViewDialogFragment(lstcity,City.class);
            dialogcity.setiOnClickItemListviewDialogFragmentResult(this);
            dialogdist= ListviewDialogFragment.createListDistViewDialogFragment(lstdist,District.class);
            dialogdist.setiOnClickItemListviewDialogFragmentResult(this);
        }
    }
    ///////////////////
    // input :
    // purpose : set giá trị ngày giờ cho các biến totimePickerDialog và fromtimePickerDialog
    // output :
    /////////////////////
    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        totimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar newTime= Calendar.getInstance();

                newTime.set(hourOfDay,minute);
                String AM_PM ;
                if(hourOfDay < 12) {
                    AM_PM = "AM";
                } else {
                    AM_PM = "PM";
                }
                to_time_btn.setText(hourOfDay+":"+minute+" "+AM_PM);
                toTime = new Time(hourOfDay,minute,00);

            }

        }, newCalendar.get(Calendar.HOUR), newCalendar.get(Calendar.MINUTE), false);




        Calendar newfromCalendar = Calendar.getInstance();
        fromtimePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar newfromTime= Calendar.getInstance();

                newfromTime.set(hourOfDay,minute);
                String AM_PM ;
                if(hourOfDay < 12) {
                    AM_PM = "AM";
                } else {
                    AM_PM = "PM";
                }
                from_time_btn.setText(hourOfDay+":"+minute+" "+AM_PM);
                fromTime = new Time(hourOfDay,minute,00);

            }

        }, newfromCalendar.get(Calendar.HOUR), newfromCalendar.get(Calendar.MINUTE), false);
    }
    ///////////////////
    // input :
    // purpose : Xử lý khi người dùng click lên các view trong layout
    // output :
    /////////////////////
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
                Back_Button_HandleClick();
                break;
            case R.id.send_btn:
                Send_btn_HandleClick();
                break;
            case R.id.from_time_btn:
                Toast.makeText(this,"from time click",Toast.LENGTH_SHORT);
                fromtimePickerDialog.show();
                break;
            case R.id.to_time_btn:
                Toast.makeText(this,"to time click",Toast.LENGTH_SHORT);
                totimePickerDialog.show();
                break;
            case R.id.tim_vitri_btn:
                Tim_vi_tri_HandleClick();
                break;
            case R.id.pick_image_gallery_btn:
                pick_image_gallery_btn_HandleClick();
                break;
            case R.id.loaihinhdiadiem_lnr_btn:
                loaihinhdiadiem_lnr_btn_HandleClick();
                break;
            case R.id.chon_city_btn:
                chon_city_btn_HandleClick();
                break;
            case R.id.chon_dist_btn:
                chon_dist_btn_HandleClick();
                break;
            default:
                break;
        }
    }
    ///////////////////
    // input :
    // purpose : Chuyển activity khi người dùng muốn chọn hình ảnh
    // output :
    /////////////////////
    public void pick_image_gallery_btn_HandleClick(){
        Intent intent = new Intent(Insert_Quan_An_Activity.this,Pick_Gallery_Folder_Activity.class);
        intent.putExtra("mode",MULTI_SELECT);
        startActivityForResult(intent,PICK_IMAGE_MULTIPLE);
    }
    ///////////////////
    // input :
    // purpose : Xử lý khi người dùng click lên view Back_Button
    // output :
    /////////////////////
    public void Back_Button_HandleClick(){
        setResult(RESULT_CANCELED);
        finish();
    }
    ExpandableDialogFragment alertdFragment;
    ///////////////////
    // input :
    // purpose : Xử lý khi người dùng click lên view loaihinhdiadiem_lnr_btn
    // output :
    /////////////////////
    public void loaihinhdiadiem_lnr_btn_HandleClick(){
        alertdFragment = new ExpandableDialogFragment();
        // Show Alert DialogFragment
        FragmentManager fm = getSupportFragmentManager();
        alertdFragment.setOnDialogResultListener(this);
        alertdFragment.show(fm, "Alert Dialog Fragment");
    }
    ///////////////////
    // input :
    // purpose : Xử lý khi người dùng click lên Send_btn_HandleClick;
    // output :
    /////////////////////
    HandleHttpResponse response;
    public void Send_btn_HandleClick(){
        Gson gson = new Gson();
        try{

            if(this.adapter_image_in_insert_quan_an!=null){
                if(this.adapter_image_in_insert_quan_an.lstimage!=null){
                    if(namerest_editText.getText()!=null && loaihinhdiadiem_txt.getTag()!=null &&
                        address_editText.getText()!=null && chon_city_btn.getTag()!=null
                        && chon_dist_btn.getTag()!=null){
                        progressBar.setVisibility(View.VISIBLE);
                        String url= RestCall.myurl+"/FoodyService/rest/RestController/InsertRestaurant";
                        List<String> encodeString = new ArrayList<String>();
                        List<String> filename = new ArrayList<String>();
                        for(ImageInGallery image :adapter_image_in_insert_quan_an.lstimage){
                            encodeString.add(Utility.encodeImageToBase64(new File(image.getPath())));
                            filename.add(this.getImageName(image.getPath()));
                        }
                        RequestImageModel modelreq = new RequestImageModel(filename,encodeString);
                        Restaurant.Builder builder = new Restaurant.Builder();

                        JSONObject  jsonParams = new JSONObject ();
                        StringEntity entity = null;
                        try {
                            Restaurant rest = builder.setNamerest(namerest_editText.getText().toString())
                                    .setLatlocation(latgeo)
                                    .setLonglocation(longgeo)
                                    .setPhonenumber(Integer.parseInt(sodienthoai_editText.getText().toString()))
                                    .setFromTimeMoCua(fromTime)
                                    .setToTimeMoCua(toTime)
                                    .setGiacaonhat(Double.parseDouble(edit_giacaonhat.getText().toString()))
                                    .setGiathapnhat(Double.parseDouble(edit_giathapnhat.getText().toString()))
                                    .setMota(mota_editText.getText().toString())
                                    .setAddress(address_editText.getText().toString())
                                    .setTyperestaurant(new TypeRestaurant(loaihinhdiadiem_txt.getTag().toString(),null))
                                    .setDist(new District(chon_dist_btn.getTag().toString(),chon_dist_btn.getText().toString(),
                                            new City(chon_city_btn.getTag().toString(),chon_city_btn.getText().toString())))
                                    .setRegisterday(new java.sql.Date(System.currentTimeMillis()))
                                    .build();
                            jsonParams.put("rest",gson.toJson(rest));
                            jsonParams.put("data",gson.toJson(modelreq));
                            System.out.println(" Notify data"+jsonParams.toString());
                            entity = new StringEntity(jsonParams.toString());
                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }catch(NumberFormatException e){
                            e.printStackTrace();
                        }
                        client.post(this,url,entity,"application/json",response);
                    }
                }else{
                    Toast.makeText(this,"Not fill Infomation",Toast.LENGTH_SHORT).show();
                }
            }else{
                String url= RestCall.myurl+"/FoodyService/rest/RestController/InsertRestaurantNotFullInformation";
                JSONObject  jsonParams = new JSONObject ();
                StringEntity entity = null;
                try {
                    Restaurant.Builder builder = new Restaurant.Builder();
                    Restaurant rest = builder.setNamerest(namerest_editText.getText().toString())
                            .setAddress(address_editText.getText().toString())
                            .setTyperestaurant(new TypeRestaurant(loaihinhdiadiem_txt.getTag().toString(),null))
                            .setDist(new District(chon_dist_btn.getTag().toString(),chon_dist_btn.getText().toString(),
                                    new City(chon_city_btn.getTag().toString(),chon_city_btn.getText().toString())))
                            .setRegisterday(new java.sql.Date(System.currentTimeMillis()))
                            .build();
                    jsonParams.put("rest",gson.toJson(rest));
                    System.out.println(" Notify data"+jsonParams.toString());
                    entity = new StringEntity(jsonParams.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }catch(NumberFormatException e){
                    e.printStackTrace();
                }

                client.post(this,url,entity,"application/json",response);
            }
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Insert fail",Toast.LENGTH_SHORT).show();
        }
    }
    public void Tim_vi_tri_HandleClick(){
        Intent i = new Intent(this,MapsActivity.class);
        startActivityForResult(i,PICK_LOCATION);
    }
    ///////////////////
    // input :
    // purpose : Xử lý hiệu ứng cho 2 edit text edit_text_container_right và  edit_text_container_left
    // output :
    /////////////////////
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(v.getId()==R.id.edit_giacaonhat && hasFocus){
            edit_text_container_right.setBackground(getResources().getDrawable(R.drawable.edit_text_insert_quan_an_layout_enable));
        }else{
            edit_text_container_right.setBackground(getResources().getDrawable(R.drawable.edit_text_insert_quan_an_layout_disable));
        }
        if(v.getId()==R.id.edit_giathapnhat && hasFocus){
            edit_text_container_left.setBackground(getResources().getDrawable(R.drawable.edit_text_insert_quan_an_layout_enable));
        }else{
            edit_text_container_left.setBackground(getResources().getDrawable(R.drawable.edit_text_insert_quan_an_layout_disable));
        }
    }
    private final int PICK_IMAGE_MULTIPLE =1;
    private final int PICK_LOCATION =10;
    double latgeo = 0.0;
    double longgeo = 0.0;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode == PICK_IMAGE_MULTIPLE){
                imagesreturnfromfolder = data.getParcelableArrayListExtra("lstimagefromfolder");
                adapter_image_in_insert_quan_an = new Adapter_Image_In_Insert_Quan_An(this,imagesreturnfromfolder);
                adapter_image_in_insert_quan_an.setOnClickImageGallery(this);
                gridimage = new GridLayoutManager(this,3){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                recycleview_contain_image.setLayoutManager(gridimage);
                recycleview_contain_image.setAdapter(adapter_image_in_insert_quan_an);
                RecycleView_CountImage_HandleEvent();
            }else if(requestCode == PICK_LOCATION){
                latgeo = data.getDoubleExtra("lat",0);
                longgeo = data.getDoubleExtra("long",0);
                locationAddress_textView.setText("Lat "+latgeo+" - Long "+longgeo);
            }
        }

    }
    ///////////////////
    // input :
    // purpose : Lấy tên hình ảnh
    // output :
    /////////////////////
    private String getImageName(String path){
        for(int i=path.length()-1;i>0;i--){
            if(path.charAt(i)=='/'){
                return path.substring(i+1,path.length());
            }
        }
        return null;
    }
    ///////////////////
    // input :
    // purpose : chọn hình ảnh so sánh đường dẫn
    // output :
    /////////////////////
    private int selectedPosition(ImageInGallery item){
        for(int i=0;i<this.adapter_image_in_insert_quan_an.lstimage.size();i++){
            ImageInGallery im=this.adapter_image_in_insert_quan_an.lstimage.get(i);
            if(item.getPath().equals(im.getPath())){
                return i;
            }
        }
        return -1;
    }

    ///////////////////
    // input :
    // purpose : Xử lý giao diện khi có dữ liệu trong adapter
    // output :
    /////////////////////
    public void RecycleView_CountImage_HandleEvent(){
        if(adapter_image_in_insert_quan_an.lstimage!=null){
            if(adapter_image_in_insert_quan_an.lstimage.size()>0){
                have_image.setVisibility(View.VISIBLE);
                no_image.setVisibility(View.GONE);
                soimage.setText(Integer.toString(adapter_image_in_insert_quan_an.getItemCount()));
            }else{
                no_image.setVisibility(View.VISIBLE);
                have_image.setVisibility(View.GONE);
            }
        }
    }


    ///////////////////
    // input :
    // purpose : Xử lý khi người dùng click lên view chon_city_btn
    // output :
    /////////////////////
    @Override
    public void onClickImageGallery(View v, int index) {
        if(v.getId()==R.id.remove_image){
            System.out.println("Call back onClickImageGallery");
            int selectedItemPosition=selectedPosition(this.imagesreturnfromfolder.get(index));
            if(selectedItemPosition!=-1) {
                System.out.println(Integer.toString(selectedItemPosition)+"+++++++++++++"+Integer.toString(index));
                this.adapter_image_in_insert_quan_an.removeSelectedPosition(selectedItemPosition, index);
                RecycleView_CountImage_HandleEvent();
            }
        }
    }

    @Override
    public void onClickDuong(View v, int grouppos, int childpos) {

    }

    AlertDialog dialog;
    ///////////////////
    // input :
    // purpose : TẠo dialog
    // output :
    /////////////////////
    private void setUpAlertDialog(String message){
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setMessage(message)
                        .setTitle("Alert message")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which==DialogInterface.BUTTON_POSITIVE){
                                    System.out.println("finish activity");
                                    finishActivity(200);
                                }
                            }
                        });
        // Create the AlertDialog
        dialog = builder.create();
    }
    ///////////////////
    // input :
    // purpose : Xử lý khi người dùng chấp nhận
    // output :
    /////////////////////
    @Override
    public void onPositiveResult(Object value) {
        if(value!=null){
            loaihinhdiadiem_txt.setText(((TypeRestaurant)value).getNametype().toString());
            loaihinhdiadiem_txt.setTag(((TypeRestaurant)value).getTypeid().toString());
        }
    }
    ListviewDialogFragment dialogcity;
    ///////////////////
    // input :
    // purpose : Xử lý khi người dùng click lên view chon_city_btn
    // output :
    /////////////////////
    public void chon_city_btn_HandleClick(){
        FragmentManager manager = getSupportFragmentManager();
        if(dialogcity!=null){
            dialogcity.show(manager,"City Fragment Coming");
        }else{
            dialogcity=ListviewDialogFragment.createListCityViewDialogFragment(lstcity,City.class);
            dialogcity.setiOnClickItemListviewDialogFragmentResult(this);
            dialogcity.show(manager,"City Fragment Coming");
        }
    }
    ListviewDialogFragment dialogdist;
    ///////////////////
    // input :
    // purpose : Xử lý khi người dùng click lên view chon_dist_btn
    // output :
    /////////////////////
    public void chon_dist_btn_HandleClick(){
        FragmentManager manager = getSupportFragmentManager();
        if(dialogdist!=null){
            dialogdist.show(manager,"District Fragment Coming");
        }else{
            dialogdist= ListviewDialogFragment.createListDistViewDialogFragment(lstdist,District.class);
            dialogdist.setiOnClickItemListviewDialogFragmentResult(this);
            dialogdist.show(manager,"District Fragment Coming");
        }
    }
    ///////////////////
    // input :
    // purpose : LLayas dữ liệu quận huyện từ server
    // output :
    /////////////////////
    public void getDist(){
        if(currentcityidinthiscontext!=previouscityidinthiscontext){
            lstdist.clear();
            lstdist = dbAdapter.getTinhTheoTP(currentcityidinthiscontext);
            dialogdist = ListviewDialogFragment.createListDistViewDialogFragment(lstdist,District.class);
            dialogdist.setiOnClickItemListviewDialogFragmentResult(new IOnClickItemListviewDialogFragmentResult() {
                @Override
                public void NotifyResult(Class myclass, Object value) {
                    chon_dist_btn.setText(((District)value).getNamedist());
                    chon_dist_btn.setTag(((District)value).getDistid());
                }
            });
            previouscityidinthiscontext = currentcityidinthiscontext;
        }else{

        }
    }
    ///////////////////
    // input :
    // purpose : Xử lý giao diện khi người dùng chọn giá trị trong các dialog
    // output :
    /////////////////////
    @Override
    public void NotifyResult(Class myclass, Object value) {
        if(myclass == City.class){
            currentcityidinthiscontext = ((City)value).getCityid();
            chon_city_btn.setText(((City)value).getNamecity());
            chon_city_btn.setTag(((City)value).getCityid());
            chon_dist_btn.setText("Chọn Quận/Huyện");
            getDist();
        }else if(myclass == District.class){
            chon_dist_btn.setText(((District)value).getNamedist());
            chon_dist_btn.setTag(((District)value).getDistid());
        }else {

        }
    }

    @Override
    public void onSuccess(Object... data) {
        progressBar.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if((boolean)data[0]){
            setUpAlertDialog("Insert success");
        }else{
            setUpAlertDialog("Insert fail");
        }
        dialog.show();

    }
}
