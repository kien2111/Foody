package com.kien.group;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kien.group.fetchdata.StreetProvider;
import com.kien.group.model.Restaurant;
import com.kien.group.model.Street;
@Path("/StreetController")
public class StreetController  extends Application {
	@GET
	@Path("/getStreetTheoDist")
	@Produces("application/json")
	public String getStreetTheoDist(@QueryParam("dist_id") String dist_id){
		JsonObject object = new JsonObject();
		try{
			Gson gson = new Gson();
			JsonElement data;
			ArrayList<Street> list = new StreetProvider().getStreetTheoDist(dist_id);
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
