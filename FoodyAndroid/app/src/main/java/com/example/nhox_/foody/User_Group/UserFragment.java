package com.example.nhox_.foody.User_Group;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhox_.foody.Activity.Change_User_Detail_Activity;
import com.example.nhox_.foody.Activity.ChonTinhThanh_Activity;
import com.example.nhox_.foody.Activity.Login_Activity;
import com.example.nhox_.foody.R;
import com.google.android.gms.vision.text.Text;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by nhox_ on 12/4/2017.
 */

public class UserFragment extends Fragment {

    public static UserFragment instance(){
        return new UserFragment();
    }
    View view;
    Context context;
    User_Store_Local store_local;
    RelativeLayout login_btn;
    RelativeLayout datcho_btn;
    RelativeLayout logout_btn;
    LinearLayout wrap_of_logout_btn;
    TextView show_nameuser;
    TextView invisible_text;
    RelativeLayout change_user_detail_btn;
    ///////////////////
    // input :
    // purpose : Xác định các view trong layout
    // output :
    /////////////////////
    private void Identify(){
        datcho_btn =(RelativeLayout)view.findViewById(R.id.datcho_btn);
        login_btn = (RelativeLayout)view.findViewById(R.id.login_btn);
        logout_btn = (RelativeLayout)view.findViewById(R.id.logout_btn);
        wrap_of_logout_btn = (LinearLayout)view.findViewById(R.id.wrap_of_logout_btn);
        show_nameuser = (TextView)view.findViewById(R.id.show_nameuser);
        invisible_text = (TextView)view.findViewById(R.id.invisible_text);
        change_user_detail_btn = (RelativeLayout)view.findViewById(R.id.change_user_detail_btn);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                System.out.println("kzkbcvkhbsdkickzdn");
                if(!store_local.isUserLoggedIn()){
                    wrap_of_logout_btn.setVisibility(View.GONE);
                    show_nameuser.setText("Đăng nhập");
                    invisible_text.setVisibility(View.GONE);
                }else{
                    wrap_of_logout_btn.setVisibility(View.VISIBLE);
                    show_nameuser.setText(store_local.getUserData().getNameuser());
                    invisible_text.setVisibility(View.VISIBLE);
                }
            }else if(resultCode==RESULT_CANCELED){
                //do nothing
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_fragment, container, false);
        context = view.getContext();
        store_local = new User_Store_Local(getContext().getApplicationContext());
        Identify();
        createAlertDialog();
        if(store_local.isUserLoggedIn()){
            wrap_of_logout_btn.setVisibility(View.VISIBLE);
            invisible_text.setVisibility(View.VISIBLE);
            show_nameuser.setText(store_local.getUserData().getUsername());
        }else{
            show_nameuser.setText("Đăng nhập");
            wrap_of_logout_btn.setVisibility(View.GONE);
            invisible_text.setVisibility(View.GONE);
        }
        change_user_detail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("check user login"," "+store_local.isUserLoggedIn());
                if(store_local.isUserLoggedIn()){
                    Intent i = new Intent(getActivity(),Change_User_Detail_Activity.class);
                    startActivityForResult(i,1);
                }else{
                    Intent i = new Intent(getActivity(),Login_Activity.class);
                    startActivityForResult(i,1);
                }
            }
        });
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleUIForlnexpandright();
            }
        });
        datcho_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return view;
    }


    ///////////////////
    // input :
    // purpose : Xử lý hiệu ứng
    // output :
    /////////////////////
    public void handleUIForlnexpandright(){
        Intent i = new Intent(getActivity(),Login_Activity.class);
        startActivityForResult(i,1);
    }
    AlertDialog dialog;
    ///////////////////
    // input :
    // purpose : Tạo dialog cảnh báo người dùng
    // output :
    /////////////////////
    public void createAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Xác nhận \nBạn muốn đăng xuất ra khỏi Foody");
        builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                store_local.setUserLogin(false);
                if(!store_local.isUserLoggedIn()){
                    wrap_of_logout_btn.setVisibility(View.GONE);
                    invisible_text.setVisibility(View.GONE);
                    store_local.clearUserData();
                    show_nameuser.setText("Đăng nhập");
                }else{
                    wrap_of_logout_btn.setVisibility(View.VISIBLE);
                    invisible_text.setVisibility(View.VISIBLE);
                    show_nameuser.setText(store_local.getUserData().getNameuser());
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        dialog = builder.create();
    }
}
