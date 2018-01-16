package com.kien.group.fetchdata;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.kien.group.model.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DistrictProvider extends AbstractProvider {
	public DistrictProvider(){
		super();		
	}
	public ArrayList<District> getTinhTheoTP(String strquery) {
        ArrayList<District> listdist = new ArrayList<District>();
        Connection con = this.databasemanagement.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        // mo ket noi
        try {
        	pst = con.prepareStatement("SELECT dist_tbl.distid,dist_tbl.namedist,city_tbl.namecity," +
                    "dist_tbl.cityid from city_tbl INNER JOIN dist_tbl ON city_tbl.cityid = dist_tbl.cityid where dist_tbl.cityid=?;");
        	pst.setString(1, strquery);        	
            rs = pst.executeQuery();
            
            while (rs.next()) {
                listdist.add( new District(rs.getString(1), rs.getString(2),new City(rs.getString(4),rs.getString(3))));
                System.out.println(rs.getString(1));
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

        return listdist;
    }
	public ArrayList<City> getTinhThanhForChonTinhThanhActivity() {
        ArrayList<City> listcity = new ArrayList<City>();
        // mo ket noi
        Connection con = this.databasemanagement.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement("select * from city_tbl");
            rs = pst.executeQuery();
            while (rs.next()) {
            	System.out.println(rs.getString(1));
            	listcity.add(new City(rs.getString(1),rs.getString(2)));                
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
				con.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
        }

        return listcity;
    }
}
