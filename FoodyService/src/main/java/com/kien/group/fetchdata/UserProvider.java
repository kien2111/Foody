package com.kien.group.fetchdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kien.group.model.User;

public class UserProvider extends AbstractProvider{
	public UserProvider(){
		super();		
	}
	public Boolean UpdateDetail(User user){
		PreparedStatement pst=null;
		Connection con = null;
		try{
			String sql = "update user_tbl set nameuser=?,ho=?,honnhan=?, ngaysinh=?,gioitinh=? where userid=?";
			con = this.databasemanagement.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1,user.getNameuser());
			pst.setString(2,user.getHo());
			pst.setString(3,user.getHonnhan());
			pst.setDate(4,user.getNgaysinh());
			pst.setString(5,user.getGioitinh());
			pst.setInt(6,user.getUserid());
			int success = pst.executeUpdate();
			if(success==0){
				return false;
			}
			return true;
		
		}catch(Exception e){
			System.err.println(e.getMessage()+" SERVE ERROR PLEASE");
		}
		return null;
	}
	public User checkUserExist(String username,String password){
		ResultSet rs=null;
		PreparedStatement pst=null;
		Connection con = null;
		try{
			String sql = "select * from user_tbl where username=? and password=?";
			con = this.databasemanagement.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1,username);
			pst.setString(2,password);
			rs = pst.executeQuery();
			while(rs.next()){
				User user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),
						rs.getInt(4),rs.getString(6),rs.getString(5),rs.getString(7)
						,rs.getDate(8),rs.getString(9),rs.getDate(10),rs.getString(11));
				return user;
			}
			
			return null;
			
		}catch(Exception e){
			System.err.println(e.getMessage());
			return null;
		}finally{
			try {
				if(rs.isClosed()){
					rs.close();
				}
				if(pst.isClosed()){
					pst.close();
				}
				if(con.isClosed()){
					con.close();
				}
			} catch (SQLException e) {				
				System.err.println(e.getMessage());
				return null;
			}
		}
	}

}
