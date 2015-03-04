package com.kla.cardservice.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.kla.cardservice.data.User;

public class UserDAO extends BaseDAO{

	public List<User> getAllUsers()
	{
	
		Connection conn = null;
		try{
			
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			return run.query(conn, "SELECT * FROM users", new UserListResultSetHandler());
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("COuld not query users", e);
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
	}
	
	public int addUser(User user)
	{
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			run.update(conn, "INSERT INTO users(firstname, lastname, ssn, dev_id) values(?,?,?,?)", user.getFirstName(),user.getLastName(),user.getSsn(),user.getDev_id());
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
		return 0;
	}
	
	
	/**
	 * Converts results to 
	 * @author kla
	 *
	 */
	private class UserListResultSetHandler implements ResultSetHandler<List<User>>
	{

		@Override
		public List<User> handle(ResultSet rs) throws SQLException {
			
			 final List<User> users = new ArrayList<User>();
		      while (rs.next()) 
		      {
		    	  //
		    	  // Move data from the result set into user object
		    	  final User user = new User();
		    	  user.setFirstName(rs.getString("firstName"));
		    	  user.setLastName(rs.getString("lastName"));
		    	  user.setSsn(rs.getString("ssn"));
		    	  user.setDev_id(rs.getString("dev_id"));
		    	  
		    	  users.add(user);
		      }
		      
		      
		      return users;
		      
		}
		
	}
}
