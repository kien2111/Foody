package com.kien.group.fetchdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.kien.group.model.*;
public class CommentProvider extends AbstractProvider {
	public CommentProvider(){
		super();		
	}

	public ArrayList<Comment> getCommentTheoRest(int idquanan) {
        ArrayList<Comment> lstcomment = new ArrayList<Comment>();
        Connection con = this.databasemanagement.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        // mo ket noi
        try {
        	pst = con.prepareStatement("select a.restid,a.userid,b.nameuser,a.comment from comment_tbl as a,user_tbl as b  where restid=?" +
                    "  and a.userid=b.userid limit 2;");
        	pst.setInt(1, idquanan);
            rs = pst.executeQuery();
         
            while (rs.next()) {
            	lstcomment.add(new Comment(new Restaurant.Builder().setRestid(rs.getInt(1)).build(),
                        new User(rs.getInt(2),rs.getString(3),null,0),rs.getString(4)));
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

        return lstcomment;
    }
}
