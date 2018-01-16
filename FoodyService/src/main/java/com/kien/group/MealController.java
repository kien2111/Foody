package com.kien.group;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kien.group.fetchdata.MealProvider;
import com.kien.group.model.Meal;

@Path("/MealController")
public class MealController extends Application {
	@GET
	@Path("/getMonAnMoiNhatTheoDistorCity/{typerestid}/{distidorcityid}/{option}/{index}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMonAnMoiNhatTheoDistorCity(@PathParam("typerestid")String typerestid,
			@PathParam("distidorcityid")String distidorcityid,
			@PathParam("option")int option,
			@PathParam("index")int index){
        List<Meal> listmeal = new ArrayList<Meal>();
        JsonObject object = new JsonObject();
        Gson gson = new Gson();
        JsonElement data;
        listmeal = new MealProvider().getMonAnMoiNhatTheoDistorCity(typerestid, distidorcityid, option,index);
		try{
			if (listmeal != null && listmeal.size() > 0) {
				data = gson.toJsonTree(listmeal);
				object.addProperty("success", true);
				object.add("data", data);
			} else {
				object.addProperty("success", false);
				object.addProperty("data", "Not found");
			}
			return object.toString();
		}catch(Exception e){
			object.addProperty("success", false);
			object.addProperty("data", "Not found");
			return object.toString();
		} 
	}
}
