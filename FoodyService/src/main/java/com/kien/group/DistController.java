package com.kien.group;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kien.group.fetchdata.DistrictProvider;
import com.kien.group.model.City;
import com.kien.group.model.District;
@Path("/DistController")
public class DistController extends Application {
	@GET
	@Path("/getTinhThanhForChonTinhThanhActivity/")
	@Produces("application/json")
	public String getTinhThanhForChonTinhThanhActivity() throws SQLException {	
		JsonObject object = new JsonObject();
		try{
			Gson gson = new Gson();
			
			JsonElement data;
			List<City> list = new DistrictProvider().getTinhThanhForChonTinhThanhActivity();
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
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello(){
		return "hello";
	}
	
	@GET
	@Path("/getTinhTheoTP/{strquery}")
	@Produces("application/json")
	public String getTinhTheoTP(@PathParam("strquery")String strquery) throws SQLException {
		JsonObject object = new JsonObject();
		try{
			Gson gson = new Gson();
			
			JsonElement data;
			List<District> list = new DistrictProvider().getTinhTheoTP(strquery);
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
	@Path("/getDist")
	@Consumes("application/json")
	@Produces("application/json")
	public String getDist(String strquery) throws SQLException {
		JSONObject jsonrequest = new JSONObject(strquery);
		String query = jsonrequest.getString("cityid");
		JsonObject object = new JsonObject();
		try{
			Gson gson = new Gson();
			System.out.println("getDist Call "+query);
			JsonElement data;
			List<District> list = new DistrictProvider().getTinhTheoTP(query);
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
