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
	public void registerCard(Card card)
	{
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			run.update(conn, "INSERT INTO cards(usr_id, cardholder,cardnumber,validity,cvv, device_id ) values(?,?,?,?,?,?)", card.getUsr_id(),card.getCardholder(),card.getCardnumber(),card.getValidity(), card.getCvv(),card.getDevice_id());
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				card.setCardholder(rs.getString("cardholder"));
				card.setCardnumber(rs.getString("cardnumber"));
				card.setCvv(rs.getString("cvv"));
				card.setDevice_id(rs.getString("device_id"));
				card.setValidity(rs.getString("validity"));
				card.setUsr_id(rs.getString("usr_id"));
				
				cards.add(card);
			}
		
			return cards;
		}
		}
	}
	

