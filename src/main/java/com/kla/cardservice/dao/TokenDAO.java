package com.kla.cardservice.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
		final String sql = "SELECT * FROM tokens ORDER BY date DESC";
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			logger.debug("Running all tokens query");
			return run.query(conn, sql, new TokenListResultSetHandler());
		}catch(SQLException | URISyntaxException e){
			logger.error("error getting all tokens", e);
			throw new RuntimeException("Could not query tokens "+e.getMessage(),e);
		}finally
		{
			DbUtils.closeQuietly(conn);
		}		
	}
			
	/**
	 * Saves a new token to database
	 * @param token Token to store in database
	 */
	public int registerToken(Token token)
	{
		ResultSetHandler<Integer> h = new ResultSetHandler<Integer>() {
		    public Integer handle(ResultSet rs) throws SQLException {
		        if (!rs.next()) {
		            return null;
		        }
		    
		        return (Integer) rs.getObject(1);
		    }
		};
	//	token.setUsed(true);
		final String sql = "INSERT INTO tokens (tokenitem, date,usr_id, card_id,used) values(?,?,?,?,?) RETURNING token_id";
		Connection conn = null;
		Object[] params ={token.getTokenitem(),new Timestamp(token.getDate().getTime()),token.getUsr_id(),token.getCard_id(),token.isUsed()};
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			Integer id = run.query(conn, sql,h,params); 
			token.setToken_id(id);
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
		return token.getToken_id();
	}
	
	/**
	 * Searches token table for a specific token
	 * @param tokenID the tokenID to search for
	 * @return token if found, null if not found
	 */
	public Token getTokenByID(String tokenitem) {	
		final String sql = "SELECT * FROM tokens WHERE tokenitem=?";
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			logger.debug("Running all tokens query");
			
			return (Token) run.query(conn, sql, new TokenResultSetHandler(), tokenitem);
		}catch(SQLException | URISyntaxException e){
			logger.error("error getting all tokens", e);
			throw new RuntimeException("Could not query tokens",e);
		}finally
		{
			DbUtils.closeQuietly(conn);
		}		
	}
	public void setTokenAsUsed(Token t)
	{
		final String sql = "UPDATE tokens SET used=? WHERE token_id=?";
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			logger.debug("Running all tokens query");
		
			 run.update(conn, sql, true, t.getToken_id());
		}catch(SQLException | URISyntaxException e){
			logger.error("error getting all tokens", e);
			throw new RuntimeException("Could not query tokens",e);
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
				token.setToken_id(rs.getInt("token_id"));
				token.setTokenitem(rs.getString("tokenitem"));
				token.setDate(rs.getTimestamp("date"));
				token.setUsr_id(rs.getInt("usr_id"));
				token.setCard_id(rs.getInt("card_id"));
				token.setUsed(rs.getBoolean("used"));
				
				//
				// Add the processed token to the tokens list
				tokens.add(token);
				
			}
			return tokens;
		}
		
	}

	private class TokenResultSetHandler implements ResultSetHandler<Token>
	{

		@Override
		public Token handle(ResultSet rs) throws SQLException 
		{

			while( rs.next() )
			{
				//
				// Process token data from database and insert into new token object
				final Token token = new Token();
				token.setToken_id(rs.getInt("token_id"));
				token.setTokenitem(rs.getString("tokenitem"));
				token.setDate(rs.getTimestamp("date"));
				token.setUsr_id(rs.getInt("usr_id"));
				token.setCard_id(rs.getInt("card_id"));
				token.setUsed(rs.getBoolean("used"));
				
				
				return token;
				
			}
			return null;
		}
		
	}
}