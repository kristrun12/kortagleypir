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
import com.kla.cardservice.data.Token;
import com.kla.cardservice.data.Transaction;

public class CardDAO extends BaseDAO
{
	
	public List<Card> getAllCards()
	{
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			return run.query(conn,"SELECT * FROM cards ORDER BY card_id DESC", new CardListResultSetHandler());
		}catch (SQLException | URISyntaxException e){
			e.printStackTrace();
			throw new RuntimeException("Could not query cards",e);
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
	}
	public int registerCard(Card card)
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
			Object[] params ={card.getCardholder(),card.getCardnumber(),card.getValidity(), card.getCvv(),card.getDevice_id(),card.getPin(),card.getUsr_id(),300000};
			
			QueryRunner run = new QueryRunner();
			Integer id = run.query(conn, "INSERT INTO cards(cardholder,cardnumber,validity,cvv, device_id, PIN,usr_id, balance) values(?,?,?,?,?,?,?,?) RETURNING card_id",h,params);
			card.setCard_id(id);
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
		return card.getCard_id();
	}
	public Card getCardByID(int card_id) {
		
		final String sql = "SELECT * FROM cards WHERE card_id=?";
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
	
			
			return (Card) run.query(conn, sql, new CardResultSetHandler(), card_id);
		}catch(SQLException | URISyntaxException e){
			
			throw new RuntimeException("Could not query tokens",e);
		}finally
		{
			DbUtils.closeQuietly(conn);
		}		
	
	}
	public void changeBalance(int price, int card_id) 
	{
		final String sql = "UPDATE cards SET balance=balance+? where card_id=?";
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
		
			 run.update(conn, sql, price,card_id);
		}catch(SQLException | URISyntaxException e){
			e.printStackTrace();
			throw new RuntimeException("Could not query cards",e);
		}finally
		{
			DbUtils.closeQuietly(conn);
		}		
		
	}
	
	
	private class CardListResultSetHandler implements ResultSetHandler<List<Card>>
	{
	
		@Override
		public List<Card> handle (ResultSet rs) throws SQLException
		{
			final List <Card> cards = new ArrayList<Card>();
			while(rs.next())
			{
				//move data from the result set into card
				
				final Card card = new Card();
				card.setCard_id(rs.getInt("card_id"));
				card.setCardholder(rs.getString("cardholder"));
				card.setCardnumber(rs.getString("cardnumber"));
				card.setCvv(rs.getString("cvv"));
				card.setDevice_id(rs.getString("device_id"));
				card.setValidity(rs.getString("validity"));
				card.setPin(rs.getString("pin"));
				card.setUsr_id(rs.getInt("usr_id"));
				card.setBalance(rs.getInt("balance"));
				
				cards.add(card);
			}
		
			return cards;
		}
		}
	private class CardResultSetHandler implements ResultSetHandler<Card>
	{
		@Override
		public Card handle (ResultSet rs) throws SQLException
		{
			
			if(rs.next())
			{
				//move data from the result set into card
				
				final Card card = new Card();
				card.setCard_id(rs.getInt("card_id"));
				card.setCardholder(rs.getString("cardholder"));
				card.setCardnumber(rs.getString("cardnumber"));
				card.setCvv(rs.getString("cvv"));
				card.setDevice_id(rs.getString("device_id"));
				card.setValidity(rs.getString("validity"));
				card.setPin(rs.getString("pin"));
				card.setUsr_id(rs.getInt("usr_id"));
				card.setBalance(rs.getInt("balance"));
				
				return card;
			}
			return null;
		}
	}
}
	

