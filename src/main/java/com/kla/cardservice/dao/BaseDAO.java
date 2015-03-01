package com.kla.cardservice.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BaseDAO {

	//connection to database
		protected  Connection getConnection() throws URISyntaxException, SQLException {
		    URI dbUri = new URI(System.getenv("DATABASE_URL"));

		    String username = dbUri.getUserInfo().split(":")[0];
		    String password = dbUri.getUserInfo().split(":")[1];
		    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

		    Properties props = new Properties();
		    props.setProperty("user",username);
		    props.setProperty("password",password);
		   
		    
		    return DriverManager.getConnection(dbUrl, props);
		}
		
		
}
