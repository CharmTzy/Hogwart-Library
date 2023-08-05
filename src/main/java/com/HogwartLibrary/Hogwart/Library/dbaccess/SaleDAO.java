package com.HogwartLibrary.Hogwart.Library.dbaccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.HogwartLibrary.Hogwart.Library.dbaccess.Sale;
import com.HogwartLibrary.Hogwart.Library.dbaccess.DBConnection;
public class SaleDAO {
	public ArrayList<Sale> listAllSale() throws SQLException{
		ArrayList<Sale> SaleList = new ArrayList<Sale>();
		Sale sBean = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "SELECT orders.order_id, orders.order_date, orders.total_amount, orders.customer_id,user.username,order_items.book_id, order_items.quantity,books.title FROM book_db.orders INNER JOIN book_db.user ON orders.customer_id = user.id INNER JOIN book_db.order_items ON orders.order_id = order_items.order_id INNER JOIN book_db.books ON order_items.book_id = books.id;";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				sBean = new Sale();
				sBean.setOrder_id(rs.getInt("order_id"));
				sBean.setCustomer_id(rs.getInt("customer_id"));
				sBean.setOrder_date(rs.getString("order_date"));
				sBean.setTotal_amount(rs.getFloat("total_amount"));
				sBean.setUsername(rs.getString("username"));
				sBean.setBook_id(rs.getInt("book_id"));
				sBean.setQuantity(rs.getInt("quantity"));
				sBean.setTitle(rs.getString("title"));
				System.out.print(".........done writing to bean!....");
				SaleList.add(sBean);
			}
		}catch(Exception e) {
			System.out.print("............SaleDAO:"+e);
		}finally {
			conn.close();
		}
		return SaleList;
		
	}
	 public void updateOrderStatus(int order_id, String status) throws SQLException {
	        Connection conn = null;
	        try {
	            conn = DBConnection.getConnection();
	            String sqlStr = "UPDATE orders SET status = ? WHERE order_id = ?";
	            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	            pstmt.setString(1, status);
	            pstmt.setInt(2, order_id);
	            pstmt.executeUpdate();
	        } catch (Exception e) {
	            System.out.print("............SaleDAO:" + e);
	        } finally {
	            conn.close();
	        }
	    }
	
}
