package com.kien.group.fetchdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kien.group.model.City;
import com.kien.group.model.District;
import com.kien.group.model.Restaurant;
import com.kien.group.model.Street;
import com.kien.group.model.TypeRestaurant;

public class StreetProvider extends AbstractProvider {
	public StreetProvider(){			
		super();
	}
	public ArrayList<Street> getStreetTheoDist(String dist_id) {
        ArrayList<Street> lststreet = new ArrayList<Street>();
        // mo ket noi
        Connection con = this.databasemanagement.getConnection();
        try {       	
        	ResultSet rs =null;
        	PreparedStatement pst=null;
            pst =con.prepareStatement("select * from street_tbl where dist_id=?  ");
            pst.setString(1, dist_id);           
            rs = pst.executeQuery();                   
            while (rs.next()) {
            	lststreet.add(new Street(rs.getInt("idduong"),rs.getString("tenduong"),
            			new District(rs.getString("dist_id"),null,new City(rs.getString("city_id"),null)))); 
       
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        return lststreet;
    }

}
