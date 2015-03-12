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

import com.kla.cardservice.data.Token;

public class TokenDAO extends BaseDAO{
	
	public List <Token> getAllTokens ()
	{
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			return run.query(conn, "SELECT * FROM tokens", new TokenListResultSetHandler());
		}catch(SQLException | URISyntaxException e){
			e.printStackTrace();
			throw new RuntimeException("Could not query tokens",e);
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
			
	}
		
	
	public void registerToken(Token token)
	{
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			run.update(conn, "INSERT INTO tokens(usr_id, device_id,tokenone,tokentwo,tokenthree ) values(?,?,?,?,?)", token.getUsr_id(),token.getDevice_id(),token.getTokenone(),token.getTokentwo(),token.getTokenthree());
		} catch (SQLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			DbUtils.closeQuietly(conn);
		}
		
	}
	private class TokenListResultSetHandler implements ResultSetHandler<List<Token>>
	{

		@Override
		public List<Token> handle(ResultSet rs) throws SQLException {
			
			final List <Token>  tokens = new ArrayList<Token>();
			while( rs.next() )
			{
				final Token token = new Token();
				token.setUsr_id("usr_id");
				token.setDevice_id("device_id");
				token.setTokenone("tokenone");
				token.setTokentwo("tokentwo");
				token.setTokenthree("tokenthree");
				
			}
			return tokens;
		}
		
	}
}
