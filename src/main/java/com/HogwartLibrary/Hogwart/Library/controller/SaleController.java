package com.HogwartLibrary.Hogwart.Library.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.HogwartLibrary.Hogwart.Library.dbaccess.*;
@RestController

public class SaleController {
	@RequestMapping(method=RequestMethod.GET, path="/getAllSale")
	public ArrayList<Sale> getAllSale() {
		Sale sale = new Sale();
		ArrayList<Sale> SaleList = new ArrayList<>();
		try {
			SaleDAO db = new SaleDAO();
			SaleList = db.listAllSale();
		}catch(Exception e) {
			System.out.println("Error :"+e);
		}
		return SaleList;
	}
	
	 @RequestMapping(method = RequestMethod.POST, path = "/updateOrderStatus/{order_id}")
	    public String updateOrderStatus(@PathVariable("order_id") int orderId, @RequestBody UpdateOrderStatusRequest request) {
	        SaleDAO saleDAO = new SaleDAO();
	        try {
	            saleDAO.updateOrderStatus(orderId, request.getStatus());
	            return "Order status updated successfully.";
	        } catch (SQLException e) {
	            System.out.println("Error updating order status: " + e);
	            return "Failed to update order status.";
	        }
	    }
}
