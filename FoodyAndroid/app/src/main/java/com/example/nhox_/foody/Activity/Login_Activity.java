package com.example.nhox_.foody.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.TimeUnit;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhox_.foody.R;
import com.example.nhox_.foody.User_Group.User_Store_Local;

import Model.User;
import UtilPackage.AsyncResponse;
import UtilPackage.TaskCallRest.CallRestTaskLogin;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by nhox_ on 14/4/2017.
 */

public class Login_Activity extends AppCompatActivity {
    Button back_button;
    TextView foody_title;
    View content;
    LinearLayout includeview;
    LinearLayout layout;
    LinearLayout main_container;
    Button login_phonenumber_btn;
    Button show_include_login_email;
    Button login_email_btn;
    EditText username;
    EditText password;
    Context context;
    User_Store_Local store_local;
    private ProgressDialog mProgressDialog;
    ///////////////////
    // input :
    // purpose : Xác định các View trong layout
    // output :
    /////////////////////
    private void Identify(){
        layout = (LinearLayout)findViewById(R.id.mainlayout);
        content = findViewById(R.id.toolbar_of_login).getRootView();
        login_email_btn = (Button) findViewById(R.id.login_email_btn);
        back_button = (Button)findViewById(R.id.back_button);
        foody_title = (TextView)findViewById(R.id.foody_title);
        main_container = (LinearLayout)findViewById(R.id.main_container);
        includeview = (LinearLayout)findViewById(R.id.include_layout_login_with_email);
        login_phonenumber_btn = (Button)findViewById(R.id.login_phonenumber_btn);
        login_email_btn = (Button)findViewById(R.id.login_email_btn);
        show_include_login_email = (Button)findViewById(R.id.show_include_login_email);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        context =this;
        this.Identify();
        store_local = new User_Store_Local(getApplicationContext());
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMessage("Authenticate...");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        if(!store_local.isUserLoggedIn()){
            username.setText(store_local.getUserData().getUsername());
        }
        if(username.getText()!=null && password.getText()!=null){
            login_email_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CallRestTaskLogin taskLogin = (CallRestTaskLogin) new CallRestTaskLogin(new AsyncResponse() {
                        @Override
                        public void processFinish(Object object,View v) {
                            if((User)object!=null){
                                store_local.clearUserData();
                                store_local.storeUserData((User)object);
                                store_local.setUserLogin(true);
                                Intent intent = new Intent();
                                setResult(RESULT_OK, intent);
                                finish();
                            }else{
                                Toast.makeText(Login_Activity.this,"User không tồn tại",Toast.LENGTH_SHORT).show();
                            }
                        }
                    },mProgressDialog).execute(username.getText().toString(),password.getText().toString());
                }
            });
        }else{
            Toast.makeText(this,"Không nhập username hoặc password",Toast.LENGTH_SHORT).show();
        }
        login_phonenumber_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"btn click",Toast.LENGTH_SHORT).show();
            }
        });
        show_include_login_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleUIForShowLoginEmailBtn();
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleUIForBackButton();
            }
        });
    }


    ///////////////////
    // input :
    // purpose : Xử lý giao diện cho nút login email button
    // output :
    /////////////////////
    public void handleUIForShowLoginEmailBtn(){
        if(includeview.getVisibility()==View.GONE){
            foody_title.setText("Đăng nhập với Email");
            main_container.setVisibility(View.GONE);
            includeview.setVisibility(View.VISIBLE);
        }
    }
    ///////////////////
    // input :
    // purpose : Xử lý click vào nút back
    // output :
    /////////////////////
    public void handleUIForBackButton(){
        if(includeview.getVisibility()==View.VISIBLE){
            foody_title.setText("Đăng nhập với Foody");
            main_container.setVisibility(View.VISIBLE);
            includeview.setVisibility(View.GONE);
        }else{
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
    }


}
