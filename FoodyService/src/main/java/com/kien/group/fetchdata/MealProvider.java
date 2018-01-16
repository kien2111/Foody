package com.kien.group.fetchdata;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kien.group.model.*;
public class MealProvider extends AbstractProvider {
	public MealProvider(){
		super();
	}

	public List<Meal> getMonAnMoiNhatTheoDistorCity(String typerestid, String distidorcityid,int option,int index){
        ArrayList<Meal> listmeal = new ArrayList<Meal>();
        Connection con = this.databasemanagement.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        // mo ket noi
        try {
        	if(option==0){
        		pst = con.prepareStatement("select * from view_moi_nhat_meal where typerestid =?" +
                        "and distid=? and mealid>? limit 5");           	
        	}else if(option==1){
        		pst = con.prepareStatement("select * from view_moi_nhat_meal where typerestid =?" +
                        "and cityid=? and mealid>? limit 5");        		
        	}else if(option==2){
        		pst = con.prepareStatement("select * from view_moi_nhat_meal where typerestid =?" +
                        "and idduong=? and mealid>? limit 5");        	
        	}
        	pst.setString(1, typerestid);
        	pst.setString(2, distidorcityid);
        	pst.setInt(3,index);
            rs = pst.executeQuery();                       
            while (rs.next()) {
            	listmeal.add(new Meal(rs.getInt("mealid"),
            			rs.getString("namemeal"),
                new Restaurant.Builder().setRestid(rs.getInt("restid")).setNamerest(rs.getString("namerest")).setAddress(rs.getString("address"))
                .setDist(new District(rs.getString("distid"),null,
                        new City(rs.getString("cityid"),null)))
                .setTyperestaurant(new TypeRestaurant(rs.getString("typerestid"),null))
                .setStreet(new Street(rs.getInt("idduong"),null,null)).build(),
                        new Image(rs.getInt("imageid"),new Meal(rs.getInt("mealid"),null,null),rs.getString("filepath"))));
                
            }
            rs.close();
            pst.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        return listmeal;
    }
}
