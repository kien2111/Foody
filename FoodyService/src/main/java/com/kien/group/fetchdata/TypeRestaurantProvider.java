package com.kien.group.fetchdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.kien.group.model.TypeRestaurant;

public class TypeRestaurantProvider extends AbstractProvider {
	public TypeRestaurantProvider(){			
		super();
	}
	public ArrayList<TypeRestaurant> getTypeRestaurant() {
        ArrayList<TypeRestaurant> lsttype = new ArrayList<TypeRestaurant>();
        // mo ket noi
        Connection con = this.databasemanagement.getConnection();
        try {       	
        	ResultSet rs =null;
        	PreparedStatement pst=null;
            pst =con.prepareStatement("select * from typerest_tbl");                    
            rs = pst.executeQuery();                   
            while (rs.next()) {
            	lsttype.add(new TypeRestaurant(rs.getString("typeid"),rs.getString("nametype")));     
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

        return lsttype;
    }
}
