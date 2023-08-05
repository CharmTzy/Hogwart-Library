package com.HogwartLibrary.Hogwart.Library.dbaccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.HogwartLibrary.Hogwart.Library.dbaccess.DBConnection;
import com.HogwartLibrary.Hogwart.Library.dbaccess.User;
public class UserDAO {
	public User getUserDetails(String id) throws SQLException {
		User uBean= null;
		Connection conn = null; 
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "SELECT * FROM user WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				uBean = new User();
				uBean.setId(rs.getInt("id"));
				uBean.setUsername(rs.getString("username"));
				uBean.setEmail(rs.getString("email"));
				uBean.setAddress(rs.getString("address"));
				uBean.setPhnumber(rs.getString("phnumber"));
				System.out.print(".....done writing to bean!....");
			}
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			conn.close();
		}
		return uBean;
	}
	
	public int insertUser(int id,String username,String email,String address,String phnumber)throws SQLException,ClassNotFoundException{
		Connection conn = null;
		int nrow = 0;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "INSERT INTO user VALUES (?,?,?,?,?)";
			PreparedStatement pstmt= conn.prepareStatement(sqlStr);
			pstmt.setInt(1, id);
			pstmt.setString(2, username);
			pstmt.setString(3, email);
			pstmt.setString(4, address);
			pstmt.setString(5, phnumber);
			nrow = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return nrow;
	}
	
	public int updateUser(int id,User user) throws SQLException {
		Connection conn = null; 
		int nrow = 0;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "SELECT * FROM user WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				sqlStr = "UPDATE user SET username=?,email=?,address=?,phnumber=? WHERE id=?";
				pstmt = conn.prepareStatement(sqlStr);
				pstmt.setString(1,user.getUsername());
				pstmt.setString(2, user.getEmail());
				pstmt.setString(3, user.getAddress());
				pstmt.setString(4,user.getPhnumber());
				nrow = pstmt.executeUpdate();
				System.out.print(".....done updating user!....");
			}
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			conn.close();
		}
		return nrow;
	}
	
	public int deleteUser(int id) throws SQLException{
		Connection conn = null;
		int nrow = 0;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "DELETE FROM user WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setInt(1, id);
			nrow = pstmt.executeUpdate();
				
			System.out.print("...Done Deleting user..");
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			conn.close();
		}
		return nrow;
		
	}
	public ArrayList<User> listAllUsers() throws SQLException{
		ArrayList<User> userList = new ArrayList<User>();
		User uBean = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "SELECT * FROM user where role='member'";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				uBean = new User();
				uBean.setId(rs.getInt("id"));
				uBean.setUsername(rs.getString("username"));
				uBean.setEmail(rs.getString("email"));
				uBean.setAddress(rs.getString("address"));
				uBean.setPhnumber(rs.getString("phnumber"));
				System.out.print(".........done writing to bean!....");
				userList.add(uBean);
			}
		}catch(Exception e) {
			System.out.print("............UserDAO:"+e);
		}finally {
			conn.close();
		}
		return userList;
		
	}

}