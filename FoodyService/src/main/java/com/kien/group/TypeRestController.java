package com.kien.group;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kien.group.fetchdata.TypeRestaurantProvider;
import com.kien.group.model.TypeRestaurant;
@Path("/TypeRestController")
public class TypeRestController extends Application {
	@GET
	@Path("/getTypeRestaurant")
	@Produces("application/json")
	public String getTypeRestaurant(){
		JsonObject object = new JsonObject();
		try{
			Gson gson = new Gson();
			
			JsonElement data;
			List<TypeRestaurant> list = new TypeRestaurantProvider().getTypeRestaurant();
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

}
