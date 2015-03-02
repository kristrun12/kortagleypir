package com.kla.cardservice.dao;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import com.kla.cardservice.data.User;

public class UserDAO extends BaseDAO{

	public int addUser(User user)
	{
		Connection conn = null;
		try{
			conn = getConnection();
			QueryRunner run = new QueryRunner();
			run.update(conn, "INSERT INTO users(firstname, lastname, ssn, dev_id) values(?,?,?,?)", user.getFirstName(),user.getLastName(),user.getSsn(),user.getDev_id());
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
