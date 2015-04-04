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

public class CardDAO extends BaseDAO
{
	
	public List<Card> getAllCards()
	{
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			return run.query(conn,"SELECT * FROM cards", new CardListResultSetHandler());
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
			Object[] params ={card.getCardholder(),card.getCardnumber(),card.getValidity(), card.getCvv(),card.getDevice_id(),card.getPin(),card.getUsr_id()};
			
			QueryRunner run = new QueryRunner();
			Integer id = run.query(conn, "INSERT INTO cards(cardholder,cardnumber,validity,cvv, device_id, PIN,usr_id ) values(?,?,?,?,?,?,?) RETURNING card_id",h,params);
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
				
				cards.add(card);
			}
		
			return cards;
		}
		}
	}
	

