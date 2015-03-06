package com.kla.cardservice.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		public void createDatabase(){
			
			
			try (Connection conn = getConnection();){
					
				Statement stmt = conn.createStatement();
				stmt.executeUpdate("DROP TABLE IF EXISTS users");
			    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (id serial PRIMARY KEY,name varchar(255), ssn varchar(255), dev_id varchar(255))");
			    //stmt.executeUpdate("CREATE TABLE IF NOT EXISTS cards (cardHolderName varchar(255), cardNumber int)");
				
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
}
