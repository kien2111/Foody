package UtilPackage.TaskCallRest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import UtilPackage.Abstract.RestCall;
import UtilPackage.AsyncResponse;
/**
 * Created by nhox_ on 23/4/2017.
 */

public class CallRestTaskUpdateUserDetail extends AsyncTask<String,Void,Object> {
    ProgressDialog mprogress;
    public AsyncResponse asyncinstance=null;
    public CallRestTaskUpdateUserDetail(AsyncResponse asyncinstance,ProgressDialog progressDialog){
        this.asyncinstance = asyncinstance;
        this.mprogress = progressDialog;

    }

    public Boolean updateDetail(String json){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            Boolean success = jsonObject.getBoolean("success");
            if(success){
                return true;
            }else{
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;

    }




    @Override
    protected Object doInBackground(String... params) {
        String uri = "/FoodyService/rest/UserController/updateDetail/";
        return this.updateDetail(new RestCall().invokecall(RestCall.myurl+uri+params[0]+"/"+params[1]+"/"+params[2]+"/"+params[3]+"/"+params[4]
                +"/"+params[5]+"/"+params[6],"POST"));
    }

    @Override
    protected void onPostExecute(Object o) {
        asyncinstance.processFinish(o,null);

    }
}
