package com.kien.group;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import org.json.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kien.group.fetchdata.UserProvider;
import com.kien.group.model.User;
@Path("/UserController")
public class UserController extends Application {
	@GET
	@Path("/checkUser/{username}/{password}")
	@Produces("application/json")
	public String checkUser(@PathParam("username")String username,@PathParam("password")String password){
		JsonObject object = new JsonObject();
		Gson gson = new GsonBuilder()
				   .setDateFormat("dd/MM/yyyy").create();
		try{
			User UserExist = new UserProvider().checkUserExist(username, password);
			JsonElement data ;
			if (UserExist!=null) {				
				
				data = gson.toJsonTree(UserExist);
				object.addProperty("success", true);
				object.add("userexist",data);
			} else {
				object.addProperty("success", false);
				object.addProperty("userexist","no data");
			}
		}catch(Exception e){
			object.addProperty("success", false);
			object.addProperty("userexist", "Not found");
		}
		
		return object.toString();
	}
	
	/*@QueryParam("userid") int userid,
	@QueryParam("nameuser") String nameuser,
	@QueryParam("ho") String ho,
	@QueryParam("honnhan") String honnhan,
	@QueryParam("ngaysinh") String ngaysinh,
	@QueryParam("gioitinh") String gioitinh*/
	@POST
	@Path("/updateDetail")
	@Consumes("application/json")
	@Produces("application/json")
	public String updateDetail(String json){
		JSONObject obj = new JSONObject(json);
		int userid = obj.getInt("userid");
		String nameuser = obj.getString("nameuser");
		String ho = obj.getString("ho");
		String honnhan = obj.getString("honnhan");
		String ngaysinh = obj.getString("ngaysinh");
		String gioitinh = obj.getString("gioitinh");
		System.out.println("get data "+nameuser);
		User user = new User(userid,nameuser,ho,honnhan,convertStringtoSqlDate(ngaysinh),gioitinh);
		JsonObject jsonob = new JsonObject();
		Boolean UpdateSuccess = new UserProvider().UpdateDetail(user);
		if(UpdateSuccess){
			System.out.println("update success");
			jsonob.addProperty("success",true);		
		}else{
			jsonob.addProperty("success",false);		
		}
													
		return jsonob.toString();
		
	}
	public java.sql.Date convertStringtoSqlDate(String input ){
		try {
			java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(input);
			return new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
