package UtilPackage.TaskCallRest;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import Model.City;
import Model.Comment;
import Model.District;
import Model.Image;
import Model.JsonResponse;
import Model.Meal;
import Model.Restaurant;
import Model.Street;
import Model.TypeRestaurant;
import Model.User;
import UtilPackage.Abstract.RestCall;
import UtilPackage.AsyncResponse;
import UtilPackage.EnumTaskOption;


/**
 * Created by nhox_ on 21/4/2017.
 */

public class CallRestTaskgetList extends AsyncTask<Object,Void,List<?>> {
    AsyncResponse asyncResponse=null;
    View v;
    public CallRestTaskgetList(AsyncResponse asyncResponse,View v){
        this.v = v;
        this.asyncResponse = asyncResponse;
    }
    ///////////////////
    // input :
    // purpose : Lấy Thành phố cho Activity từ server
    // output :
    /////////////////////
    public List<City> getTinhThanhForChonTinhThanhActivity(String json){
        List<City> cities = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            boolean success = object.getBoolean("success");
            if(success){
                JSONArray array = object.getJSONArray("data");
                for (int i=0;i<array.length();i++){
                    JSONObject jsonObject = array.getJSONObject(i);
                    cities.add(new City(jsonObject.getString("cityid"),jsonObject.getString("namecity")));
                }
            }
            return cities;
        } catch (JSONException e) {
            //e.printStackTrace();
        }
        return null;
    }
    ///////////////////
    // input :
    // purpose : Lấy quận huyện theo thành phố từ server
    // output :
    /////////////////////
    public List<District> getTinhTheoTP(String json){
        List<District> districts = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            boolean success = object.getBoolean("success");
            if(success){
                JSONArray array = object.getJSONArray("data");
                for (int i=0;i<array.length();i++){
                    JSONObject jsonObject = array.getJSONObject(i);
                    String distid = jsonObject.getString("distid");
                    String namedist = jsonObject.getString("namedist");
                    JSONObject obj = jsonObject.getJSONObject("city");
                    String cityid = obj.getString("cityid");
                    String namecity = obj.getString("namecity");
                    districts.add(new District(distid,namedist,new City(cityid,namecity)));
                }
            }
            return districts;
        } catch (JSONException e) {
           //e.printStackTrace();
        }
        return null;
    }

    ///////////////////
    // input :
    // purpose : Lấy các món ăn theo tiêu chí do người dùng chọn từ server
    // output :
    /////////////////////
    public List<Meal> getMonAnMoiNhatTheoDistorCity(String json){
        List<Meal> lstmeal = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            boolean success = object.getBoolean("success");
            if(success){
                JSONArray array = object.getJSONArray("data");
                for (int i=0;i<array.length();i++){
                    JSONObject jsonObject = array.getJSONObject(i);
                    int mealid = jsonObject.getInt("mealid");
                    String namemeal = jsonObject.getString("namemeal");
                    JSONObject obj = jsonObject.getJSONObject("rest");
                    int restid = obj.getInt("restid");
                    String namerest = obj.getString("namerest");
                    String address = obj.getString("address");
                    JSONObject distob = obj.getJSONObject("dist");
                    String distid = distob.getString("distid");
                    JSONObject cityob = distob.getJSONObject("city");
                    String cityid = cityob.getString("cityid");
                    JSONObject typeob = obj.getJSONObject("type");
                    String typeid = typeob.getString("typeid");
                    JSONObject streetob = obj.getJSONObject("street");
                    int idduong = streetob.getInt("idduong");
                    JSONObject imageob = jsonObject.getJSONObject("image");
                    int imageid = imageob.getInt("imageid");
                    String filepath = imageob.getString("filepath");
                    lstmeal.add(new Meal(mealid,
                            namemeal,
                            new Restaurant(restid,namerest,address,
                                    new District(distid,null,
                                            new City(cityid,null)),
                                    new TypeRestaurant(typeid,null),new Street(idduong,null,null)),
                            new Image(imageid,new Meal(mealid,null,null),filepath)));

                }
                return lstmeal;

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){

        }
        return null;
    }

    ///////////////////
    // input :
    // purpose : Lấy các quán ăn mói nhất theo tiêu chí do người dùng chọn từ server
    // output :
    /////////////////////
    public List<Restaurant> getQuanAnMoiNhatTheoDistorCity(String json){
        List<Restaurant> listrest = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            boolean success = object.getBoolean("success");
            if(success){
                Type collectionType = new TypeToken<List<Restaurant>>(){}.getType();
                listrest.addAll((List<Restaurant>)new GsonBuilder().create().fromJson(object.getJSONArray("data").toString(), collectionType));
            }
            return listrest;
        } catch (Exception e){

        }
        return null;
    }
    ///////////////////
    // input :
    // purpose : Lấy các bình luận theo nhà hàng từ server
    // output :
    /////////////////////
    public List<Comment> getCommentTheoRest(String json){
        List<Comment> lstcomment = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            boolean success = object.getBoolean("success");
            if(success){
                JSONArray array = object.getJSONArray("data");
                for (int i=0;i<array.length();i++){
                    JSONObject jsonObject = array.getJSONObject(i);
                    JSONObject restob = jsonObject.getJSONObject("rest");
                    int restid = restob.getInt("restid");
                    JSONObject userob = jsonObject.getJSONObject("user");
                    int userid = userob.getInt("userid");
                    String nameuser = userob.getString("nameuser");
                    int phonenumber = userob.getInt("phonenumber");
                    String comment = jsonObject.getString("comment");
                    lstcomment.add(new Comment(new Restaurant(restid,null,null,null,null,null),
                            new User(userid,nameuser,null,phonenumber),comment));
                }
            }
            return lstcomment;
        } catch (JSONException e) {
            //e.printStackTrace();
        }
        return null;
    }
    ///////////////////
    // input :
    // purpose : Lấy hình ảnh theo nhà hàng từ server
    // output :
    /////////////////////
    public List<Image> getImageTheoRest(String json){
        List<Image> lstimage = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            boolean success = object.getBoolean("success");
            if(success){
                JSONArray array = object.getJSONArray("data");
                for (int i=0;i<array.length();i++){
                    JSONObject jsonObject = array.getJSONObject(i);
                    int imageid = jsonObject.getInt("imageid");
                    JSONObject restob = jsonObject.getJSONObject("rest");
                    int restid = restob.getInt("restid");
                    String namerest = restob.getString("namerest");
                    String address = restob.getString("address");
                    JSONObject distob = restob.getJSONObject("dist");
                    String distid = distob.getString("distid");
                    String filepath = jsonObject.getString("filepath");
                    lstimage.add(new Image(imageid,new Restaurant(restid,namerest,address,
                                    new District(distid,null,null),null,null),
                            filepath));
                }
            }
            return lstimage;
        } catch (JSONException e) {
            //e.printStackTrace();
        }
        return null;
    }
    @Override
    protected List<?> doInBackground(Object... params) {
       try{
           RestCall restCall = new RestCall();
           String uri=null;
           if(EnumTaskOption.getTinhThanhForChonTinhThanhActivity==params[0]){
               uri="/FoodyService/rest/DistController/getTinhThanhForChonTinhThanhActivity";
               return this.getTinhThanhForChonTinhThanhActivity(restCall.invokecall(RestCall.myurl+uri,"GET"));
           }else if(EnumTaskOption.getTinhTheoTP==params[0]){
               //param[0] EnumTaskOption
               //param[1] cityid
               uri="/FoodyService/rest/DistController/getTinhTheoTP/";
               return this.getTinhTheoTP(restCall.invokecall(RestCall.myurl+uri+params[1],"GET"));
           }else if(EnumTaskOption.getQuanAnMoiNhatTheoDist==params[0]){
               //param[0] EnumTaskOption
               //param[1] typerestid
               //param[2] distid
               //param[3] option dist = 0
               uri="/FoodyService/rest/RestController/getQuanAnMoiNhatTheoDistorCity/";
               return this.getQuanAnMoiNhatTheoDistorCity(restCall.invokecall(RestCall.myurl+uri+params[1]+"/"+params[2]+"/"+params[3],"GET"));
           }else if(EnumTaskOption.getQuanAnMoiNhatTheoCity==params[0]){
               //param[0] EnumTaskOption
               //param[1] typerestid
               //param[2] cityid
               //param[3] option city = 1
               uri="/FoodyService/rest/RestController/getQuanAnMoiNhatTheoDistorCity/";
               return this.getQuanAnMoiNhatTheoDistorCity(restCall.invokecall(RestCall.myurl+uri+params[1]+"/"+params[2]+"/"+params[3],"GET"));
           }else if(EnumTaskOption.getQuanAnMoiNhatTheoDistorCityorStreetLoadMore==params[0]){
               //param[0] EnumTaskOption
               //param[1] typerestid
               //param[2] distid
               //param[3] option city = 1 dist=0 street = 2
               //param[4] index
               uri="/FoodyService/rest/RestController/getQuanAnMoiNhatTheoDistorCityLoadMore/";
               return this.getQuanAnMoiNhatTheoDistorCity(restCall.invokecall(RestCall.myurl+uri+params[1]+"/"+params[2]+"/"+params[3]+"/"+params[4],"GET"));
           } else if(EnumTaskOption.getCommentTheoRest==params[0]){
               //param[0] EnumTaskOption
               //param[1] restid
               uri="/FoodyService/rest/CommController/getCommentTheoRest/";
               return this.getCommentTheoRest(restCall.invokecall(RestCall.myurl+uri+params[1],"GET"));
           }else if(EnumTaskOption.getImageTheoRest==params[0]){
               //param[0] EnumTaskOption
               //param[1] restid
               uri="/FoodyService/rest/ImageController/getImageTheoRest/";
               return this.getImageTheoRest(restCall.invokecall(RestCall.myurl+uri+params[1],"GET"));
           }else if(EnumTaskOption.getMonAnMoiNhatTheoDistorCity==params[0]){
               //param[0] EnumTaskOption
               //param[1] typerestid
               //param[2] distid
               //param[3] option city = 1 dist=0 street = 2
               //param[4] index
               uri="/FoodyService/rest/MealController/getMonAnMoiNhatTheoDistorCity/";
               return this.getMonAnMoiNhatTheoDistorCity(restCall.invokecall(RestCall.myurl+uri+params[1]+"/"+params[2]+"/"+params[3]+"/"+params[4],"GET"));
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return null;
    }
    public CallRestTaskgetList(){}
    @Override
    protected void onPostExecute(List<?> objects) {
        try{
            if(asyncResponse!=null)asyncResponse.processFinish(objects,v);
        }catch (Exception e){

        }
    }

}
