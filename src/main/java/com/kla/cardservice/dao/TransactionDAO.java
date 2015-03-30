package com.kla.cardservice.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import com.kla.cardservice.data.Card;
import com.kla.cardservice.data.Transaction;

public class TransactionDAO extends BaseDAO{
	
	public void createTransaction(Transaction transaction)
	{
		try (Connection conn = getConnection();){
			
			Statement stmt = conn.createStatement();
			
		    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS 4174125896325874(time timestamp(),store varchar(255),prize varchar(255),total varchar (255)");
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			run.update(conn, "INSERT INTO 4174125896325874(time, store,prize,total ) values(?,?,?,?,?)", transaction.getTime(),transaction.getStore(),transaction.getPrize(),transaction.getTotal());
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
		
	}
	

}
