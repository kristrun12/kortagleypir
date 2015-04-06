package com.kla.cardservice.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

public class BaseDAO {

	//connection to database
		protected  Connection getConnection() throws URISyntaxException, SQLException {
			
			String dbUriString = System.getenv("DATABASE_URL");
			if(dbUriString == null) dbUriString = "postgres://kla:klapass@localhost:5432/kortagleypir";	
			URI dbUri = new URI (dbUriString);

			//URI dbUri = new URI(System.getenv("DATABASE_URL"));
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
				stmt.executeUpdate("DROP TABLE IF EXISTS transactions");
				stmt.executeUpdate("DROP TABLE IF EXISTS tokens");
				stmt.executeUpdate("DROP TABLE IF EXISTS cards");
				stmt.executeUpdate("DROP TABLE IF EXISTS users");
				stmt.executeUpdate("DROP TABLE IF EXISTS mainCard");
				stmt.executeUpdate("DROP TABLE IF EXISTS extraCard");
				stmt.executeUpdate("DROP TABLE IF EXISTS spareCard");
				
				
			    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users "
			    		+ "(usr_id serial PRIMARY KEY,"
			    		+ "name varchar(255), "
			    		+ "ssn varchar(255), "
			    		+ "device_id varchar(255))");
			    
			    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS cards "
			    		+ "(card_id serial PRIMARY KEY,"
			    		+ "cardholder varchar(255), "
			    		+ "cardnumber varchar (255), "
			    		+ "validity varchar (6), "
			    		+ "cvv varchar(255),"
			    		+ "device_id varchar(255),"
			    		+ "pin varchar (255),"
			    		+ "usr_id integer, foreign key (usr_id) references users(usr_id))");
			    
			    
			    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS tokens("
			    		+ "token_id serial PRIMARY KEY,"
			    		+ "tokenitem varchar(255),"
			    		+ "date varchar (255),"
			    		+ "usr_id integer, "
			    		+ "card_id integer, "
			    		+ "used boolean,"
			    		+ "foreign key (usr_id) references users(usr_id),"
			    		+ "foreign key (card_id) references cards (card_id))");
			    
			    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS transactions("
			    		+ "transaction_id serial PRIMARY KEY,"
			    		+ "vendor varchar (255),"
			    		+ "price integer,"
			    		+ "total integer,"
			    		+ "date varchar (255),"
			    		+ "card_id int,"
			    		+ "device_id varchar (255),"
			    		+ "tokenitem varchar (255),"
			    		+ "appPin varchar (255),"
			    		+ "posPin varchar (255),"
			    		+ "token_id integer,"
			    		+ "foreign key (token_id) references tokens (token_id),"
			    		+ "foreign key (card_id) references cards (card_id))");
			    
			    
			    stmt.executeUpdate("DROP INDEX IF EXISTS idx_tokenitem");
			    stmt.executeUpdate("CREATE INDEX idx_tokenitem ON tokens (tokenitem)");
			    stmt.executeUpdate("DROP INDEX IF EXISTS idx_transaction");
			    stmt.executeUpdate("CREATE INDEX idx_transaction ON transactions (transaction_id)");
			    
				
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
}
