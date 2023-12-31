package com.HogwartLibrary.Hogwart.Library.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.HogwartLibrary.Hogwart.Library.dbaccess.Book;
import com.HogwartLibrary.Hogwart.Library.dbaccess.BookDAO;
import com.HogwartLibrary.Hogwart.Library.dbaccess.LowStockBook;

import java.util.ArrayList;
import java.util.List;
@RestController
public class BookController {
	
	@RequestMapping(method=RequestMethod.GET, path="/getBook/{bid}")
	public Book getBook(@PathVariable("bid") String bid) {
		Book book = new Book();
		try {
			BookDAO db = new BookDAO();
			book = db.getBookDetails(bid);
		}catch(Exception e) {
			System.out.println("Error :"+e);
		}
		return book;
	}
	
	
}
