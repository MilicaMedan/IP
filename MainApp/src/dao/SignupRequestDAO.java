package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAOUtil;
import dto.SignupRequest;
import dao.ConnectionPool;

public class SignupRequestDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT = "INSERT INTO signup_request (name, surname, username, passwordHash, mail) VALUES (?,?,?,?,?)";
	
	public static boolean insert(SignupRequest req) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { req.getName(), req.getSurname(), req.getUsername(), req.getPasswordHash(), req.getMail() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}
			if (generatedKeys.next())
				req.setId(generatedKeys.getInt(1));
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
}
