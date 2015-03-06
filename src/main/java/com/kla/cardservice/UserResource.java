package com.kla.cardservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.kla.cardservice.dao.UserDAO;
import com.kla.cardservice.data.User;



@Path("/user")
public class UserResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHi()
	{
		return "Hi User!";
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAll()
	{
		return new UserDAO().getAllUsers();
		
	}
	
	@POST()
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int createUser(User newUser)
	{
		int userId = new UserDAO().addUser(newUser);
		
		//return userId to the user 
		return userId;
	}
}
