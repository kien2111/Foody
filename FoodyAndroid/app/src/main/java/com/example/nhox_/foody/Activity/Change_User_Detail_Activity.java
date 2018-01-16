package com.example.nhox_.foody.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nhox_.foody.R;
import com.example.nhox_.foody.User_Group.User_Store_Local;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import Model.User;
import UtilPackage.Abstract.RestCall;
import UtilPackage.TaskCallRest.CallRestTaskUpdateUserDetail;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.entity.mime.Header;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by nhox_ on 22/4/2017.
 */

public class Change_User_Detail_Activity extends AppCompatActivity implements View.OnClickListener {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    EditText honnhan;
    EditText gioitinh;
    Button save_button;
    Button back_button;
    EditText edit_username;
    EditText edit_nameuser;
    EditText edit_ho;
    EditText edit_ngaythamgia;
    TextView show_date;
    TextView show_nameuser;
    User_Store_Local store_local;
    EditText edit_email;
    Dialog dialoggioitinh;
    Dialog dialoghonnhan;
    private void Identify(){
        honnhan = (EditText)findViewById(R.id.honnhan);
        gioitinh = (EditText)findViewById(R.id.gioitinh);
        save_button = (Button) findViewById(R.id.save_button);
        back_button = (Button)findViewById(R.id.back_button);
        edit_username = (EditText)findViewById(R.id.edit_username);
        edit_nameuser = (EditText)findViewById(R.id.edit_nameuser);
        edit_ho = (EditText)findViewById(R.id.edit_ho);
        edit_ngaythamgia = (EditText)findViewById(R.id.edit_ngaythamgia);
        show_date = (TextView)findViewById(R.id.show_date);
        show_nameuser = (TextView)findViewById(R.id.show_nameuser);
        edit_email = (EditText)findViewById(R.id.edit_email);
    }
    private void handleUI(){
        show_nameuser.setText(store_local.getUserData().getNameuser()+" "+store_local.getUserData().getHo());
        edit_email.setText(store_local.getUserData().getEmail());
        edit_ho.setText(store_local.getUserData().getHo());
        edit_username.setText(store_local.getUserData().getUsername());
        edit_nameuser.setText(store_local.getUserData().getNameuser());
        edit_ngaythamgia.setText(dateFormatter.format(store_local.getUserData().getNgaythamgia()));
        honnhan.setText(store_local.getUserData().getHonnhan());
        gioitinh.setText(store_local.getUserData().getGioitinh());
        show_date.setText(dateFormatter.format(store_local.getUserData().getNgaysinh()));


        show_date.setOnClickListener(this);
        gioitinh.setOnClickListener(this);
        honnhan.setOnClickListener(this);
        save_button.setOnClickListener(this);


        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_detail_layout);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        store_local = new User_Store_Local(getApplicationContext());
        Identify();
        handleUI();
        setDateTimeField();
        dialoggioitinh = createGioiTinhDialog();
        dialoghonnhan =createHonNhanDialog();
    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                show_date.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    ProgressDialog mProgressDialog;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_date:
                datePickerDialog.show();
                break;
            case R.id.honnhan:
                dialoghonnhan.show();
                break;
            case R.id.gioitinh:
                dialoggioitinh.show();
                break;
            case R.id.save_button:
                Save_Click_Handle();
                break;
            default:
                break;
        }
    }

    public java.sql.Date convertStringtoSqlDate(String inputString){
        Date ngayutil = null;
        java.sql.Date sqldate = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            ngayutil = format.parse(inputString);
            sqldate = new java.sql.Date(ngayutil.getTime());
            return sqldate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqldate;
    }



    public Dialog createGioiTinhDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(R.array.gioitinh, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                gioitinh.setText("Nam".toString());
                                break;
                            case 1:
                                gioitinh.setText("Nữ".toString());
                                break;
                            default:
                                break;
                        }
                        dialog.dismiss();
                    }
                });
        return builder.create();

    }

    public Dialog createHonNhanDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(R.array.honnhan, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        honnhan.setText("Độc thân".toString());
                        break;
                    case 1:
                        honnhan.setText("Đã cưới".toString());
                        break;
                    case 2:
                        honnhan.setText("Phức tạp".toString());
                        break;
                    case 3:
                        honnhan.setText("Đang hẹn hò".toString());
                        break;
                    case 4:
                        honnhan.setText("Đã đính hôn".toString());
                        break;
                    case 5:
                        honnhan.setText("Quan hệ mở".toString());
                        break;
                    case 6:
                        honnhan.setText("Góa".toString());
                        break;
                    case 7:
                        honnhan.setText("Ly dị".toString());
                        break;
                    case 8:
                        honnhan.setText("Ly thân".toString());
                        break;
                    default:
                        break;
                }
                dialog.dismiss();
            }
        });
        return builder.create();

    }

    public void Save_Click_Handle(){
        JSONObject  jsonParams = new JSONObject ();
        StringEntity entity = null;
        try {
            jsonParams.put("updatedetail","updatedetail");
            jsonParams.put("nameuser",edit_nameuser.getText().toString());
            jsonParams.put("ho",edit_ho.getText().toString());
            jsonParams.put("honnhan",honnhan.getText().toString());
            jsonParams.put("ngaysinh",show_date.getText().toString());
            jsonParams.put("gioitinh",gioitinh.getText().toString());
            jsonParams.put("userid",store_local.getUserData().getUserid());
            entity = new StringEntity(jsonParams.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        store_local.storeUserData(new User(store_local.getUserData().getUserid(),
                edit_nameuser.getText().toString(),
                edit_email.getText().toString(),store_local.getUserData().getPhonenumber(),
                store_local.getUserData().getPassword(),edit_username.getText().toString(),
                edit_ho.getText().toString(),convertStringtoSqlDate(edit_ngaythamgia.getText().toString()),honnhan.getText().toString(),
                convertStringtoSqlDate(show_date.getText().toString()),gioitinh.getText().toString()));
        String url = RestCall.myurl+"/FoodyService/rest/UserController/updateDetail";
        AsyncHttpClient client = new AsyncHttpClient();

        client.post(this,url , entity,"application/json",  new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    boolean success = response.getBoolean("success");
                    if(success){

                    }else{

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                System.out.println("statusCode ="+statusCode+" header "+headers.toString()+
                 " response String = "+responseString);
                throwable.printStackTrace();
            }
        });
        setResult(RESULT_OK);
        finish();
    }
}
