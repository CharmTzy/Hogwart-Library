package com.HogwartLibrary.Hogwart.Library.dbaccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.HogwartLibrary.Hogwart.Library.dbaccess.Order;
import com.HogwartLibrary.Hogwart.Library.dbaccess.DBConnection;
public class OrderDAO {
	public ArrayList<Order> listAllSale() throws SQLException{
		ArrayList<Order> OrderList = new ArrayList<Order>();
		Order oBean = null;
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "SELECT  orders.order_date, orders.total_amount, orders.customer_id, order_items.book_id,order_items.quantity,orders.status,books.title FROM book_db.orders INNER JOIN book_db.user ON orders.customer_id = user.id INNER JOIN book_db.order_items ON orders.order_id = order_items.order_id INNER JOIN book_db.books ON order_items.book_id = books.id;";
			PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				oBean = new Order();
				oBean.setOrder_date(rs.getString("order_date"));
				oBean.setTotal_amount(rs.getFloat("total_amount"));
				oBean.setCustomer_id(rs.getInt("customer_id"));
				oBean.setBook_id(rs.getInt("book_id"));
				oBean.setQuantity(rs.getInt("quantity"));
				oBean.setStatus(rs.getString("status"));
				oBean.setTitle(rs.getString("title"));
				System.out.print(".........done writing to bean!....");
				OrderList.add(oBean);
			}
		}catch(Exception e) {
			System.out.print("............SaleDAO:"+e);
		}finally {
			conn.close();
		}
		return OrderList;
		
	}

}
