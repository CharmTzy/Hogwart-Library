package com.HogwartLibrary.Hogwart.Library.dbaccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.HogwartLibrary.Hogwart.Library.dbaccess.Book;
import com.HogwartLibrary.Hogwart.Library.dbaccess.DBConnection;
public class BookDAO {
	public Book getBookDetails(String id) throws SQLException {
		Book Bean= null;
		Connection conn = null; 
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "SELECT * FROM book_db WHERE id=?";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				Bean = new Book();
				Bean.setBookId(rs.getString("id"));
				Bean.setTitle(rs.getString("title"));
				Bean.setAuthor(rs.getString("author"));
				Bean.setQuantity(rs.getInt("quantity"));
				System.out.print(".....done writing to bean!....");
			}
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			conn.close();
		}
		return Bean;
	}
	

}
