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
import com.kla.cardservice.data.Card;
import com.kla.cardservice.data.Transaction;
import com.kla.cardservice.data.User;

public class TransactionDAO extends BaseDAO{
	
	public List<Transaction> getAllMainTransactions()
	{
	
		Connection conn = null;
		try{
			
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			return run.query(conn, "SELECT * FROM mainCard", new mainCardListResultSetHandler());
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not query mainCard", e);
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
	}
	public List<Transaction> getAllExtraTransactions()
	{
	
		Connection conn = null;
		try{
			
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			return run.query(conn, "SELECT * FROM ExtraCard", new extraCardListResultSetHandler());
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not query extraCard", e);
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
	}
	public List<Transaction> getAllSpareTransactions()
	{
	
		Connection conn = null;
		try{
			
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			return run.query(conn, "SELECT * FROM spareCard", new spareCardListResultSetHandler());
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not query extraCard", e);
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
	}
	
	public void registerMainTransaction(Transaction transaction)
	{
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			run.update(conn, "INSERT INTO mainCard(cardnumber, vendor, prize,total,date ) values(?,?,?,?,?)",transaction.getCardnumber(),transaction.getVendor(),transaction.getPrize(),transaction.getTotal(),transaction.getDate());
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
	}
	
	public void registerExtraTransaction(Transaction transaction)
	{
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			run.update(conn, "INSERT INTO ExtraCard(cardnumber, vendor, prize,total,date ) values(?,?,?,?,?)",transaction.getCardnumber(),transaction.getVendor(),transaction.getPrize(),transaction.getTotal(),transaction.getDate());
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
	}
	public void registerSpareTransaction(Transaction transaction)
	{
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			run.update(conn, "INSERT INTO spareCard(cardnumber, vendor, prize,total,date ) values(?,?,?,?,?)",transaction.getCardnumber(),transaction.getVendor(),transaction.getPrize(),transaction.getTotal(),transaction.getDate());
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
	}
	private class mainCardListResultSetHandler implements ResultSetHandler<List<Transaction>>
	{
	
		@Override
		public List<Transaction> handle (ResultSet rs) throws SQLException
		{
			final List <Transaction> transactions = new ArrayList<Transaction>();
			while(rs.next())
			{
				//move data from the result set into card
				
				final Transaction transaction = new Transaction();
				transaction.setCardnumber(rs.getString("cardnumber"));
				transaction.setVendor(rs.getString("vendor"));
				transaction.setPrize(rs.getString("prize"));
				transaction.setTotal(rs.getString("total"));
				transaction.setDate(rs.getString("date"));
				
				transactions.add(transaction);
				
			}
		
			return transactions;
		}
		}
	private class extraCardListResultSetHandler implements ResultSetHandler<List<Transaction>>
	{
	
		@Override
		public List<Transaction> handle (ResultSet rs) throws SQLException
		{
			final List <Transaction> transactions = new ArrayList<Transaction>();
			while(rs.next())
			{
				//move data from the result set into card
				
				final Transaction transaction = new Transaction();
				transaction.setCardnumber(rs.getString("cardnumber"));
				transaction.setVendor(rs.getString("vendor"));
				transaction.setPrize(rs.getString("prize"));
				transaction.setTotal(rs.getString("total"));
				transaction.setDate(rs.getString("date"));
				
				transactions.add(transaction);
				
			}
		
			return transactions;
		}
		}
	private class spareCardListResultSetHandler implements ResultSetHandler<List<Transaction>>
	{
	
		@Override
		public List<Transaction> handle (ResultSet rs) throws SQLException
		{
			final List <Transaction> transactions = new ArrayList<Transaction>();
			while(rs.next())
			{
				//move data from the result set into card
				
				final Transaction transaction = new Transaction();
				transaction.setCardnumber(rs.getString("cardnumber"));
				transaction.setVendor(rs.getString("vendor"));
				transaction.setPrize(rs.getString("prize"));
				transaction.setTotal(rs.getString("total"));
				transaction.setDate(rs.getString("date"));
				
				transactions.add(transaction);
				
			}
		
			return transactions;
		}
		}

}
