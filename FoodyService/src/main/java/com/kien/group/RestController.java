package com.kien.group;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kien.group.fetchdata.CommentProvider;
import com.kien.group.fetchdata.ImageProvider;
import com.kien.group.fetchdata.RestaurantProvider;
import com.kien.group.model.Image;
import com.kien.group.model.Restaurant;

@Path("/RestController")
public class RestController extends Application {
	@GET
	@Path("/getQuanAnMoiNhatTheoDistorCity/{typerestid}/{distidorcityid}/{option}")
	@Produces("application/json")
	public String getQuanAnMoiNhatTheoDistorCity(@PathParam("typerestid") String typerestid,
			@PathParam("distidorcityid") String distidorcityid,@PathParam("option") int option) throws SQLException {	
		JsonObject object = new JsonObject();
		try{
			Gson gson = new Gson();
			
			JsonElement data;
			List<Restaurant> list = new RestaurantProvider().getQuanAnMoiNhatTheoDistorCity(typerestid,distidorcityid,option);
			if (list != null && list.size() > 0) {
				data = gson.toJsonTree(list);
				object.addProperty("success", true);
				object.add("data", data);
			} else {
				object.addProperty("success", false);
				object.addProperty("data", "Not found");
			}
		}catch(Exception e){
			object.addProperty("success", false);
			object.addProperty("data", "Not found");
		}
		
		return object.toString();
	}
	
	
	@GET
	@Path("/getQuanAnMoiNhatTheoDistorCityLoadMore/{typerestid}/{distidorcityid}/{option}/{index}")
	@Produces("application/json")
	public String getQuanAnMoiNhatTheoDistorCityLoadMore(@PathParam("typerestid") String typerestid,
			@PathParam("distidorcityid") String distidorcityid,@PathParam("option") int option,@PathParam("index") int index) throws SQLException {	
		JsonObject object = new JsonObject();
		try{
			Gson gson = new Gson();
			
			JsonElement data;
			List<Restaurant> list = new RestaurantProvider().getQuanAnMoiNhatTheoDistorCityLoadMore(typerestid, distidorcityid, option, index);
			for(Restaurant res : list) {
				res.setLstimg(new ImageProvider().getImageTheoRest(res.getRestid()));
				res.setLstcomment(new CommentProvider().getCommentTheoRest(res.getRestid()));
			}
			if (list != null && list.size() > 0) {
				data = gson.toJsonTree(list);
				object.addProperty("success", true);
				object.add("data", data);
				System.out.println(" success "+data);
			} else {
				object.addProperty("success", false);
				object.addProperty("data", "Not found");
				System.out.println(" fail ");
			}
		}catch(Exception e){
			object.addProperty("success", false);
			object.addProperty("data", "Not found");
		}
		
		return object.toString();
	}
	
	@POST
	@Path("/InsertRestaurant")
	@Consumes({"multipart/*","application/json"})
	@Produces("application/json")
	public String InsertRestaurant(String json) throws FileNotFoundException, JSONException, IOException{
		System.out.println(json);
		JSONObject object = new JSONObject(json);
		String data = object.getString("data");
		String reststr = object.getString("rest");
		
		JSONObject jsonob = new JSONObject(data);
		JSONArray array = jsonob.getJSONArray("imagebase64");
		JSONArray filenamearray = jsonob.getJSONArray("filename");	
		JsonObject jsonobjec = null;
		Gson gson = new Gson();
		Restaurant restaurant = gson.fromJson(reststr, Restaurant.class);
		
		try{
			
			JsonElement jsonelement;
			
			boolean[] insertImageSuccess = new boolean[array.length()];
			RestaurantProvider provider = new RestaurantProvider();
			int countrest =provider.countRestaurant();
			restaurant.setRestid(countrest);
			boolean insertInsertRestaurantSuccess = provider.InsertRestaurant(restaurant);
			jsonobjec = new JsonObject();
			
			jsonobjec.addProperty("insertInsertRestaurantSuccess", insertInsertRestaurantSuccess);
			for(int i=0;i<array.length();i++){
				if(insertInsertRestaurantSuccess){
					if(new File(provider.getResourcePath()+"\\"+filenamearray.get(i)).exists()){
						
					}else{
						writeToFile(array.get(i).toString(),provider.getResourcePath()+"\\"+filenamearray.get(i));
					}
					Image image = new Image();					
					image.setRest(restaurant);
					image.setFilepath(filenamearray.get(i).toString());
					insertImageSuccess[i] = new ImageProvider().InsertImageIntoRestaurant(image);
				}
			}
			
			jsonelement = gson.toJsonTree(insertImageSuccess);
			jsonobjec.add("insertImageSuccess", jsonelement);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonobjec.toString();
	}
	
	public void writeToFile(String imagebase64String,String uploadir) throws FileNotFoundException{
		BufferedImage image = null;
        byte[] imageByte;
        try {           
            imageByte = Base64.decodeBase64(imagebase64String);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            ImageIO.write(image,"jpg", new File(uploadir));
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	

}
