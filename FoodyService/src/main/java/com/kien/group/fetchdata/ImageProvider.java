package com.kien.group.fetchdata;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.kien.group.model.*;


public class ImageProvider extends AbstractProvider {
	public ImageProvider(){
		super();		
	}

	public ArrayList<Image> getImageTheoRest(int idquanan) {
        ArrayList<Image> listimagetheorest = new ArrayList<Image>();
        Connection con = this.databasemanagement.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        // mo ket noi
        try {
        	pst = con.prepareStatement("select * from view_image_rest where restid=?");
        	pst.setInt(1, idquanan);
            rs = pst.executeQuery();
            while(rs.next()){
            	listimagetheorest.add(new Image(rs.getInt(1),new Restaurant.Builder()
            			.setRestid(rs.getInt(2)).
            			setNamerest(rs.getString(3)).
            			setAddress(rs.getString(4))
            			.setDist(new District(rs.getString(5),null,null))
            			.setTyperestaurant(new TypeRestaurant(null,null))
            			.setStreet(new Street(rs.getInt("idduong"),null,null)).build(),
            			rs.getString(6)));
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

        return listimagetheorest;
    }
	
	public ArrayList<Image> getListImageInType() {
        ArrayList<Image> listimage = new ArrayList<Image>();
        Connection con = this.databasemanagement.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        // mo ket noi
        try {
        	pst = con.prepareStatement("select * from image_typerest_view");        	
            rs = pst.executeQuery();                        
            Image image;
            while (rs.next()) {
                image = new Image(rs.getInt(0),new TypeRestaurant(rs.getString(1),rs.getString(3)), rs.getString(2));
                listimage.add(image);
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

        return listimage;
    }
	public File getImage(String fileName) {
        return new File(this.databasemanagement.getResourceurl()+"\\"+fileName);
    }
	
	public Boolean InsertImageIntoRestaurant(Image image){
		Connection con = this.databasemanagement.getConnection();
        PreparedStatement pst = null;
        // mo ket noi
        try {
        	pst = con.prepareStatement("insert into image_tbl  (restid,filepath)"
        			+ " values (?,?) ");
        	pst.setInt(1,image.getRest().getRestid());
        	pst.setString(2,image.getFilepath());
            int result = pst.executeUpdate();
            if(result>0){
            	return true;
            }
            
            return false;
            
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	try {
        		if(!pst.isClosed()){
        			pst.close();
        		}
        		if(!con.isClosed()){
        			con.close();
        		}	
			} catch (SQLException e) {
				e.printStackTrace();
			}           
        }
		return false;
	}
	
}
