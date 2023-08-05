package com.HogwartLibrary.Hogwart.Library.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.HogwartLibrary.Hogwart.Library.dbaccess.*;
@RestController

public class OrderController {
	@RequestMapping(method=RequestMethod.GET, path="/getAllOrder")
	public ArrayList<Order> getAllOrder() {
		Order order = new Order();
		ArrayList<Order> OrderList = new ArrayList<>();
		try {
			OrderDAO db = new OrderDAO();
			OrderList = db.listAllSale();
		}catch(Exception e) {
			System.out.println("Error :"+e);
		}
		return OrderList;
	}
}
