package com.HogwartLibrary.Hogwart.Library.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.HogwartLibrary.Hogwart.Library.dbaccess.*;
@RestController

public class SaleController {
	@RequestMapping(method=RequestMethod.GET, path="/getAllSale")
	public ArrayList<Sale> getAllUsers() {
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
}
