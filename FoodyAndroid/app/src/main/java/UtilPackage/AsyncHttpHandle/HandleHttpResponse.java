package UtilPackage.AsyncHttpHandle;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by nhox_ on 19/12/2017.
 */

public class HandleHttpResponse extends JsonHttpResponseHandler {
    IListener listener = null;
    public void setOnSuccess(IListener listener){
        this.listener = listener;
    }
    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);
        Log.d("log","statusCode: "+statusCode+" response : "+response);
        try {
            boolean success = response.getBoolean("insertInsertRestaurantSuccess");
            JSONArray successimage = response.getJSONArray("insertImageSuccess");
            listener.onSuccess(success,successimage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);
        Log.e("Error","statusCode : "+statusCode+" header : "+headers.toString()+
                " response String : "+responseString+" throwable : "+throwable.getMessage());
    }
}
