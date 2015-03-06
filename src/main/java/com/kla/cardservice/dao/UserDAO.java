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
			throw new RuntimeException("Could not query users", e);
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
	}
	
	public int addUser(User user)
	{
		ResultSetHandler<Integer> h = new ResultSetHandler<Integer>() {
		    public Integer handle(ResultSet rs) throws SQLException {
		        if (!rs.next()) {
		            return null;
		        }
		    
		        return (Integer) rs.getObject(1);
		    }
		};
		
		Connection conn = null;
		try{
			conn = getConnection();
			Object[] params = new Object[3];
			params[0] = user.getName();
			params[1] = user.getSsn();
			params[2] = user.getDev_id();
			
			QueryRunner run = new QueryRunner();
			Integer id = run.query(conn, "INSERT INTO users(name, ssn, dev_id) values(?,?,?)", 
					h, params);
			user.setId(id);
			// run.update(conn, "INSERT INTO users(name, ssn, dev_id) values(?,?,?)", user.getName(),user.getSsn(),user.getDev_id());
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
		//get the userId to return to phone
		return user.getId();
		
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
		    	  user.setName(rs.getString("name"));
		    	  user.setSsn(rs.getString("ssn"));
		    	  user.setDev_id(rs.getString("dev_id"));
		    	  user.setId(rs.getInt("id"));
		    	  
		    	  users.add(user);
		      }
		      
		      
		      return users;
		      
		}
		
	}
}
