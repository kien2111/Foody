package com.kien.group;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kien.group.fetchdata.ImageProvider;
import com.kien.group.model.Image;

import UtilPackage.UtilClass;

@Path("/ImageController")
public class ImageController extends Application {
	@GET
	@Path("/downloadImage/{filename}")
	@Produces("application/json")
	public String downloadImage(@PathParam("filename")String filename) throws FileNotFoundException, UnsupportedEncodingException {
		JsonObject object = new JsonObject();
		File myfile = new File("C:\\Users\\nhox_\\Desktop\\My Resource\\Restaurant\\"+filename);
		System.out.println("imagecontroller "+filename);
		if(!myfile.exists()){
			System.out.println("no image");
			object.addProperty("success", false);
			object.addProperty("data","no data");
			return object.toString();
		}
		try{
			String encodeString = UtilClass.encodeImageToBase64(myfile);		
			object.addProperty("success", true);
			System.out.println("have image");
			object.addProperty("data",encodeString);	
		}catch(Exception e){
			object.addProperty("success", false);
			object.addProperty("data","no data");
			e.printStackTrace();
		}
				
		return object.toString();
	
	}
	
	@GET
	@Path("/getImageTheoRest/{restid}")
	@Produces("application/json")
	public String getImageTheoRest(@PathParam("restid") int restid){
		JsonObject object = new JsonObject();
		try{
			Gson gson = new Gson();
			System.out.println("call from image controller"+restid);
			JsonElement data;
			List<Image> list = new ImageProvider().getImageTheoRest(restid);
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
	@Path("resource/{imgFilename}") 
	@Produces({"image/jpg","image/png","image/jpeg"})
	public Response getImage(@PathParam("imgFilename") String fileName) {
	    return Response.ok(new ImageProvider().getImage(fileName)).build();      
	}
	
}
