package com.kla.cardservice.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import com.kla.cardservice.data.Card;

public class CardDAO extends BaseDAO
{
	
	
	public int saveCard(Card card)
	{
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			run.update(conn, "INSERT INTO cards(cardholder, cardnumber) values(?,?)", card.getCardHolderName(), card.getCardNumber());
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
		return 0;
	}
	
}
