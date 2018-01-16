package com.kien.group;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kien.group.fetchdata.CommentProvider;
import com.kien.group.model.Comment;
@Path("/CommController")
public class CommController extends Application {
	@GET
	@Path("/getCommentTheoRest/{restid}")
	@Produces("application/json")
	public String getCommentTheoRest(@PathParam("restid") int restid) throws SQLException {	
		JsonObject object = new JsonObject();
		try{
			Gson gson = new Gson();
			
			JsonElement data;
			List<Comment> list = new CommentProvider().getCommentTheoRest(restid);
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
