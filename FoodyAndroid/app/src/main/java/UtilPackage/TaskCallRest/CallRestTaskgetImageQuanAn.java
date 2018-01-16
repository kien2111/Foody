package UtilPackage.TaskCallRest;

import android.os.AsyncTask;
import android.util.Base64;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.City;
import UtilPackage.Abstract.RestCall;
import UtilPackage.AsyncResponse;

/**
 * Created by nhox_ on 20/4/2017.
 */

public class CallRestTaskgetImageQuanAn extends AsyncTask<String,Void,byte[]>{
    AsyncResponse response=null;
    View v;
    public CallRestTaskgetImageQuanAn(){}
    String uri="/FoodyService/rest/ImageController/downloadImage/";
    public byte[] getImageQuanAn(String json){
        try {
            JSONObject object = new JSONObject(json);
            boolean success = object.getBoolean("success");
            String base64String = object.getString("data");
            if(success){
                byte[] imagebyte = Base64.decode(base64String,Base64.DEFAULT);
                return imagebyte;
            }
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected byte[] doInBackground(String... params) {
        RestCall restCall = new RestCall();
        return this.getImageQuanAn(restCall.invokecall(RestCall.myurl+uri+params[0]+".jpg","GET"));
    }
    public CallRestTaskgetImageQuanAn(AsyncResponse response,View view) {
        this.response = response;
        this.v = view;
    }
    @Override
    protected void onPostExecute(byte[] bytes) {
        if(response!=null)
            response.processFinish(bytes,v);
    }


}
