package com.HogwartLibrary.Hogwart.Library.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.HogwartLibrary.Hogwart.Library.dbaccess.User;
import com.HogwartLibrary.Hogwart.Library.dbaccess.UserDAO;

@RestController
//@RequestMapping("users") //This context path can also be set in application.properties file.

public class UserController {
	
	
	@RequestMapping(method=RequestMethod.GET, path="/getUser/{uid}")
	public User getUser(@PathVariable("uid") String uid) {
		User user = new User();
		try {
			UserDAO db = new UserDAO();
			user= db.getUserDetails(uid);
		}catch(Exception e) {
			System.out.println("Error :"+e);
		}
		return user;
	}
	
	@RequestMapping(
	path = "/createUser",
	consumes = "application/json",
	method = RequestMethod.POST)
	 public int createUser(@RequestBody User user) {
		int rec = 0;
		try {
			UserDAO db = new UserDAO();
			int uId = user.getId();
			System.out.print("...inside UserController...uId:"+uId);
			String uUsername = user.getUsername();
			String uEmail = user.getEmail();
			String uAddress = user.getAddress();
			String uPhnumber = user.getPhnumber();
			rec = db.insertUser(uId,uUsername,uEmail,uAddress,uPhnumber);
			System.out.print("...done create user.."+rec);
		}catch(Exception e) {
			System.out.print(e.toString());
		}
		return rec;
	 }
	
	@RequestMapping(
	path = "/updateUser/{uid}",
	consumes = "application/json",
	method = RequestMethod.PUT
	)
	 public int updateUser(@PathVariable int uid,@RequestBody User user) {
		 int rec = 0;
		 try {
			 UserDAO db = new UserDAO();
			 rec = db.updateUser(uid,user);
			 System.out.print("...in UserController-done update user.."+rec);
			 
		 }catch(Exception e) {
			 System.out.print(e.toString());
		 }
		 return rec;
	 }
	
	@RequestMapping(path="/deleteUser/{uid}",method=RequestMethod.DELETE)
	 public int deleteUser(@PathVariable int uid) {
		 //return "deleteUser() was being called!";
		int rec=0;
		try {
			UserDAO db = new UserDAO();
			rec = db.deleteUser(uid);
			System.out.print("...in UserController-done deleting user.."+rec);
		}catch(Exception e) {
			System.out.print(e.toString());
		}
		return rec;
	 }
	
	@RequestMapping(method=RequestMethod.GET, path="/getAllUsers")
	public ArrayList<User> getAllUsers() {
		User user = new User();
		ArrayList<User> myList = new ArrayList<>();
		try {
			UserDAO db = new UserDAO();
			myList = db.listAllUsers();
		}catch(Exception e) {
			System.out.println("Error :"+e);
		}
		return myList;
	}
}