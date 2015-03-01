package com.kla.cardservice;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseResource {
	
	//connection to database
	
	protected  Connection getConnection() throws URISyntaxException, SQLException {
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	
	private void createDatabase(){
		    Connection connection = null;
		    try {
		      connection = getConnection();

		      Statement stmt = connection.createStatement();
		      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
		      
		     
		      
		    } catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
		      if (connection != null) try{connection.close();} catch(SQLException e){}
		    }
		  }

}
