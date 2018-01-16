package com.kien.group.fetchdata;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.kien.group.model.*;
public class RestaurantProvider extends AbstractProvider {
	public RestaurantProvider(){			
		super();
	}
	public String getResourcePath() {
		return this.databasemanagement.getResourceurl();
	}
	public ArrayList<Restaurant> getQuanAnTheoDistOrCityPhobien(String strquery,String typeid,int option){
        ArrayList<Restaurant> listrest = new ArrayList<Restaurant>();
        // mo ket noi
        Connection con = this.databasemanagement.getConnection();
        
        try {         
        	ResultSet rs =null;
        	PreparedStatement pst=null;
            if(option ==0){
            	pst= con.prepareStatement("select * from rest_view_all_infomation_fix where distid =?"
                        +" and typerestid=?");
                pst.setString(1,strquery);
                pst.setString(2,typeid);    			
    			rs = pst.executeQuery();   			
            }else if(option==1){                
                pst = con.prepareStatement("select * from rest_view_all_infomation_fix where cityid =?"
                        +" and typerestid=?");
                pst.setString(1,strquery);
                pst.setString(2,typeid);    			
    			rs = pst.executeQuery();   	
            }else if(option==2){
            	pst = con.prepareStatement("select * from rest_view_all_infomation_fix where idduong =?"
                        +" and typerestid=?");
                pst.setInt(1,Integer.parseInt(strquery));
                pst.setString(2,typeid);    			
    			rs = pst.executeQuery();   	
            }
            while(rs.next()){
				listrest.add(new Restaurant.Builder()
            			.setRestid(rs.getInt("restid"))
						.setNamerest(rs.getString("namerest"))
						.setAddress(rs.getString("address"))
						.setDist(new District(rs.getString("distid"), rs.getString("namedist"), new City(rs.getString("cityid"),rs.getString("namecity"))))
                        .setTyperestaurant(new TypeRestaurant(rs.getString("typerestid"),rs.getString("nametype")))
                        .setStreet(new Street(rs.getInt("idduong"),null,null))
            			.build());
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

        return listrest;
    }
	
	//option
	//0 dist
	//1 city
	public ArrayList<Restaurant> getQuanAnMoiNhatTheoDistorCity(String typerestid,String distidorcityid,int option) {
        ArrayList<Restaurant> listrest = new ArrayList<Restaurant>();
        // mo ket noi
        Connection con = this.databasemanagement.getConnection();
        try {       	
        	ResultSet rs =null;
        	PreparedStatement pst=null;
            if(option==0){
            	pst =con.prepareStatement("select * from view_moi_nhat_fix where typerestid =?" +
                        "and distid=?  ");
                pst.setString(1, typerestid);
                pst.setString(2, distidorcityid);
                rs = pst.executeQuery();        
            }else if(option==1){
            	pst =con.prepareStatement("select * from view_moi_nhat_fix where typerestid =?" +
                        "and cityid=?  ");
                pst.setString(1, typerestid);
                pst.setString(2, distidorcityid);
                rs = pst.executeQuery();        
            }else if(option==2){
            	pst =con.prepareStatement("select * from view_moi_nhat_fix where typerestid =?" +
                        "and idduong=?  ");
                pst.setString(1, typerestid);
                pst.setInt(2, Integer.parseInt(distidorcityid));
                rs = pst.executeQuery();
            }
            while (rs.next()) {
            	listrest.add(new Restaurant.Builder()
            			.setRestid(rs.getInt("restid"))
						.setNamerest(rs.getString("namerest"))
						.setAddress(rs.getString("address"))
						.setDist(new District(rs.getString("distid"), rs.getString("namedist"), new City(rs.getString("cityid"),rs.getString("namecity"))))
                        .setTyperestaurant(new TypeRestaurant(rs.getString("typerestid"),rs.getString("nametype")))
                        .setStreet(new Street(rs.getInt("idduong"),null,null))
            			.build());
            
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

        return listrest;
    }
	
	public ArrayList<Restaurant> getQuanAnMoiNhatTheoDistorCityLoadMore(String typerestid,String distidorcityid,int option,int index) {
        ArrayList<Restaurant> listrest = new ArrayList<Restaurant>();
        // mo ket noi
        Connection con = this.databasemanagement.getConnection();
        System.out.println("typerestid ="+typerestid + "distidorcityid ="+distidorcityid+" option = "+option+" index = "+index);
        try {       	
        	ResultSet rs =null;
        	PreparedStatement pst=null;
            if(option==0){
            	pst =con.prepareStatement("select * from view_moi_nhat_fix where typerestid =?" +
                        "and distid=? and restid>?  limit 5");                    
            }else if(option==1){
            	pst =con.prepareStatement("select * from view_moi_nhat_fix where typerestid =?" +
                        "and cityid=? and restid>? limit 5");                  
            }else if(option==2){
            	pst =con.prepareStatement("select * from view_moi_nhat_fix where typerestid =?" +
                        "and idduong=? and restid>? limit 5 ");              
            }
            pst.setString(1, typerestid);
            pst.setString(2, distidorcityid);
            pst.setInt(3, index);
            rs = pst.executeQuery(); 
            while (rs.next()) {
            	listrest.add(new Restaurant.Builder()
            			.setRestid(rs.getInt("restid"))
						.setNamerest(rs.getString("namerest"))
						.setAddress(rs.getString("address"))
						.setDist(new District(rs.getString("distid"), rs.getString("namedist"), new City(rs.getString("cityid"),rs.getString("namecity"))))
                        .setTyperestaurant(new TypeRestaurant(rs.getString("typerestid"),rs.getString("nametype")))
                        .setStreet(new Street(rs.getInt("idduong"),null,null))
            			.build());
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

        return listrest;
    }
	
	
	
	public int countCommentToSpecifyPhoBien(int restid)  {
        int count = 0;
        // mo ket noi
        Connection con = this.databasemanagement.getConnection();
        PreparedStatement pst=null;
        ResultSet rs = null;
        try {
           pst = con.prepareStatement("select count(*) from comment_tbl where restid=?");
           pst.setInt(1, restid);
           rs = pst.executeQuery();
           rs.first();
           count = rs.getInt(1);
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
        return count;
    }
	
	public int countRestaurant()  {
        int count = 0;
        // mo ket noi
        Connection con = this.databasemanagement.getConnection();
        PreparedStatement pst=null;
        ResultSet rs = null;
        try {
           pst = con.prepareStatement("select count(*) from foody.rest_tbl");         
           rs = pst.executeQuery();
           rs.first();
           count = rs.getInt(1);
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
        return count;
    }
	
	public Boolean InsertRestaurant(Restaurant rest){
		Connection con = this.databasemanagement.getConnection();
		PreparedStatement pst=null;
        try {       	
        	
        	String sql="insert into rest_tbl (restid,namerest,distid,typerestid,address,latgeo,longgeo,phonenumber"
        			+ ",togiomocua,fromgiomocua,giacaonhat,giathapnhat,mota,registerday) "
        			+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst =con.prepareStatement(sql);
            pst.setInt(1, rest.getRestid());
            pst.setString(2, rest.getNamerest());
            pst.setString(3, rest.getDist().getDistid());          
            pst.setString(4,rest.getType().getTypeid());
            pst.setString(5,rest.getAddress());
            pst.setBigDecimal(6,new BigDecimal(rest.getLat_location(), MathContext.DECIMAL64));
            pst.setBigDecimal(7, new BigDecimal(rest.getLong_location(), MathContext.DECIMAL64));
            pst.setInt(8,rest.getPhonenumber());
            pst.setTime(9,rest.getToTimeMoCua());
            pst.setTime(10,rest.getFromTimeMoCua());
            pst.setDouble(11,rest.getGiacaonhat());
            pst.setDouble(12,rest.getGiathapnhat());
            pst.setString(13,rest.getMota());
            pst.setDate(14,rest.getRegisterday());
            int result = pst.executeUpdate();
            if(result>0){
            	return true;
            }
            return false;
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	try {
				if(pst.isClosed()){
					pst.close();
				}
				if(con.isClosed()){
	        		con.close();
	        	}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
		return false;
	}
	
	
}
