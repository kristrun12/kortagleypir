package com.kla.cardservice.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.kla.cardservice.data.Transaction;

public class TransactionDAO extends BaseDAO{
	
	public List<Transaction> getAllTransactions()
	{
		Connection conn = null;
		try{
			
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			return run.query(conn, "SELECT * FROM transactions", new TransactionsListResultSetHandler());
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not query transactions", e);
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
	}
	
	public int registerTransaction(Transaction trans)
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
			Object[] params ={trans.getVendor(),trans.getPrice(),trans.getTotal(),new Timestamp(trans.getDate().getTime()),trans.getCard_id(),trans.getDevice_id(),trans.getTokenitem(),trans.getAppPin(),trans.getPosPin(),trans.getToken_id()};
			final String sql = "INSERT INTO transactions (vendor, price,total,date, card_id,device_id,tokenitem,appPin,posPin,token_id) values(?,?,?,?,?,?,?,?,?,?) RETURNING transaction_id";
			QueryRunner run = new QueryRunner();
			Integer id = run.query(conn, sql,h,params);
			trans.setTransaction_id(id);
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
		return trans.getTransaction_id();
	}
	public List <Transaction> getTransactionsByCardID(int card_id)
	{
		Connection conn = null;
		try{
			
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			return run.query(conn, "SELECT * FROM transactions where card_id=?", new TransactionsListResultSetHandler(),card_id);
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Could not query transactions", e);
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
	}
	private class TransactionsListResultSetHandler implements ResultSetHandler<List<Transaction>>
	{
	
		@Override
		public List<Transaction> handle (ResultSet rs) throws SQLException
		{
			final List <Transaction> transactions = new ArrayList<Transaction>();
			while(rs.next())
			{
				//move data from the result set into card
				
				final Transaction transaction = new Transaction();
				transaction.setTransaction_id(rs.getInt("transaction_id"));
				transaction.setVendor(rs.getString("vendor"));
				transaction.setPrice(rs.getInt("price"));
				transaction.setTotal(rs.getInt("total"));
				transaction.setDate(rs.getTimestamp("date"));
				transaction.setAppPin(rs.getString("appPin"));
				transaction.setCard_id(rs.getInt("card_id"));
				transaction.setToken_id(rs.getInt("token_id"));
				transaction.setTokenitem(rs.getString("tokenitem"));
				transaction.setPosPin(rs.getString("posPIn"));
				transaction.setDevice_id(rs.getString("device_id"));
			    
				transactions.add(transaction);
				
			}
		
			return transactions;
		}
	}
}
