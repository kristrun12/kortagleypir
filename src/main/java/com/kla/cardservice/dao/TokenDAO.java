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
import org.apache.log4j.Logger;

import com.kla.cardservice.data.Token;

/**
 * Handles queryes and updates for Tokens in database
 * @author kla
 *
 */
public class TokenDAO extends BaseDAO{
	 
	private static final Logger logger = Logger.getLogger(TokenDAO.class);
	
	/** 
	 * @return Returns a list of all the tokens from database
	 */
	public List <Token> getAllTokens ()
	{
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			logger.debug("Running all tokens query");
			return run.query(conn, "SELECT * FROM tokens ORDER BY desc", new TokenListResultSetHandler());
		}catch(SQLException | URISyntaxException e){
			logger.error("error getting all tokens", e);
			throw new RuntimeException("Could not query tokens",e);
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
			
	}
		
	
	/**
	 * Saves a new token to database
	 * @param token Token to store in database
	 */
	public void registerToken(Token token)
	{
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			run.update(conn, "INSERT INTO tokens (usr_id, device_id,token,date ) values(?,?,?,?)", token.getUsr_id(),token.getDevice_id(),token.getToken(),token.getDate());
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
		
	}
	
	
	/**
	 * Handler to return results from Select * from tokens query.  
	 * @author kla
	 *
	 */
	private class TokenListResultSetHandler implements ResultSetHandler<List<Token>>
	{

		@Override
		public List<Token> handle(ResultSet rs) throws SQLException 
		{
			
			final List <Token>  tokens = new ArrayList<Token>();
			while( rs.next() )
			{
				//
				// Process token data from database and insert into new token object
				final Token token = new Token();
				token.setUsr_id(rs.getString("usr_id"));
				token.setDevice_id(rs.getString("device_id"));
				token.setToken(rs.getString("token"));
				token.setDate(rs.getString("date"));
				
				
				//
				// Add the processed token to the tokens list
				tokens.add(token);
				
			}
			return tokens;
		}
		
	}
}
