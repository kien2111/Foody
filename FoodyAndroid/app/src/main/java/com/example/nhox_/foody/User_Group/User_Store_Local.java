package com.example.nhox_.foody.User_Group;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.User;

/**
 * Created by nhox_ on 12/4/2017.
 */

public class User_Store_Local {
    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;
    public User_Store_Local(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME,0);
    }
    ///////////////////
    // input :
    // purpose : lưu trữ dữ liệu của user ở local
    // output :
    /////////////////////
    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putInt("id",user.getUserid());
        spEditor.putString("nameuser",user.getNameuser());
        spEditor.putString("emailuser",user.getEmail());
        spEditor.putString("password",user.getPassword());
        spEditor.putInt("phonenumber",user.getPhonenumber());
        spEditor.putString("username",user.getUsername());
        spEditor.putString("ho",user.getHo());
        System.out.println(user.getNgaythamgia().toString()+"test null");
        spEditor.putString("ngaythamgia",user.getNgaythamgia().toString());
        spEditor.putString("honnhan",user.getHonnhan().toString());
        spEditor.putString("ngaysinh",user.getNgaysinh().toString());
        spEditor.putString("gioitinh",user.getGioitinh().toString());
        spEditor.commit();
    }
    ///////////////////
    // input :
    // purpose : Lấy dữ liệu của user ở local
    // output :
    /////////////////////
    public User getUserData(){
        System.out.println("ngay tham gia = "+userLocalDatabase.getString("ngaythamgia","")+" " +
                "ngaysinh = "+ userLocalDatabase.getString("ngaysinh",""));
        return new User(userLocalDatabase.getInt("id",-1),userLocalDatabase.getString("nameuser",""),
                userLocalDatabase.getString("emailuser",""),userLocalDatabase.getInt("phonenumber",-1),
                userLocalDatabase.getString("password",""),userLocalDatabase.getString("username",""),
                userLocalDatabase.getString("ho",""),
                convertStringtoSqlDate(userLocalDatabase.getString("ngaythamgia","")),
                userLocalDatabase.getString("honnhan",""),
                convertStringtoSqlDate(userLocalDatabase.getString("ngaysinh","")),
                userLocalDatabase.getString("gioitinh","")
                );
    }

    ///////////////////
    // input :
    // purpose : Chuyển đổi kiểu dữ liệu từ String sang java.sql.Date
    // output :
    /////////////////////
    public java.sql.Date convertStringtoSqlDate(String inputString){
        Date ngayutil = null;
        java.sql.Date sqldate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            ngayutil = format.parse(inputString);
            sqldate = new java.sql.Date(ngayutil.getTime());
            return sqldate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqldate;
    }
    ///////////////////
    // input :
    // purpose : gán biến boolean cho giá trị islogin
    // output :
    /////////////////////
    public void setUserLogin(boolean islogin){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("islogin",islogin);
        spEditor.commit();
    }
    ///////////////////
    // input :
    // purpose : Xóa dữ liệu của user ở local
    // output :
    /////////////////////
    public void clearUserData(){
        SharedPreferences.Editor editor = userLocalDatabase.edit();
        editor.clear();
        editor.commit();
    }
    ///////////////////
    // input :
    // purpose : Kiểm tra xem user đã login chưa
    // output :
    /////////////////////
    public boolean isUserLoggedIn(){
        if(userLocalDatabase.getBoolean("islogin",false)){
            return true;
        }else{
            return false;
        }
    }
}
