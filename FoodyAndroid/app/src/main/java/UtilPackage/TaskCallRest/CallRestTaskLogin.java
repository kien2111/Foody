package UtilPackage.TaskCallRest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Model.User;
import UtilPackage.Abstract.RestCall;
import UtilPackage.AsyncResponse;

/**
 * Created by nhox_ on 22/4/2017.
 */

public class CallRestTaskLogin extends AsyncTask<String,Void,User> {
    public AsyncResponse delegate = null;
    ProgressDialog progressDialog;
    public CallRestTaskLogin(AsyncResponse delegate, ProgressDialog progressDialog){
        this.delegate = delegate;
        this.progressDialog = progressDialog;
    }
    public void mywait(){
        for(int d=0;d<10000;d++){
            for (d=0;d<100000;d++);
        }
    }
    public User checkUserExist(String json){
        User user = null;
        try{
            JSONObject jsonObject = new JSONObject(json);
            Boolean success = jsonObject.getBoolean("success");
            System.out.println(success+" success");

            if(success){
                JSONObject userexist = jsonObject.getJSONObject("userexist");
                System.out.println("ngay tham gia hihi = "+userexist.getString("ngaythamgia"));
                user = new User(userexist.getInt("userid"),
                        userexist.getString("nameuser"),
                        userexist.getString("email"),
                        userexist.getInt("phonenumber"),
                        userexist.getString("password"),
                        userexist.getString("username"),
                        userexist.getString("ho"),
                        convertStringtoSqlDate(userexist.getString("ngaythamgia")),
                        userexist.getString("honnhan"),
                        convertStringtoSqlDate(userexist.getString("ngaysinh")),
                        userexist.getString("gioitinh"));
                System.out.println("userid ="+user.getUserid());
                return user;
            }else{
                return user;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return user;
        }
    }
    public java.sql.Date convertStringtoSqlDate(String inputString){
        Date ngayutil;
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
    @Override
    protected User doInBackground(String... params) {
        String uri = "/FoodyService/rest/UserController/checkUser/";
        return this.checkUserExist(new RestCall().invokecall(RestCall.myurl+uri+params[0]+"/"+params[1],"GET"));
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
    }



    @Override
    protected void onPostExecute(User user) {
        mywait();
        progressDialog.dismiss();
        if(user==null)System.out.println("user không tồn tại");
        delegate.processFinish(user,null);
    }
}
