package dao;

import java.io.Console;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import dto.SignupRequest;
import dto.User;
import dao.ConnectionPool;

public class SignupRequestDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT = "INSERT INTO signup_request (name, surname, username, passwordHash, mail) VALUES (?,?,?,?,?)";
	private static final String SQL_SELECT = "SELECT * FROM signup_request";
	private static final String SQL_UPDATE = "UPDATE signup_request SET isApproved=1 WHERE id=?";
	
	
	public static boolean insert(SignupRequest req) {
		
		boolean result = false;
		ResultSet generatedKeys = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, req.getName());
			stmt.setString(2, req.getSurname());
			stmt.setString(3, req.getUsername());
			stmt.setString(4, req.getPasswordHash());
			stmt.setString(5, req.getMail());
			stmt.executeUpdate();
			generatedKeys = stmt.getGeneratedKeys();
			if(stmt.getUpdateCount()>0) {
				result = true;
			}
			if (generatedKeys.next())
				req.setId(generatedKeys.getInt(1));
			stmt.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return result;
	}
	
	public static ArrayList<SignupRequest> select(){
		ArrayList<SignupRequest> req = new ArrayList<>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SignupRequest request = new SignupRequest(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("passwordHash"),
						 rs.getString("mail"),  rs.getBoolean("isApproved") );
				
				req.add(request);
			}
			rs.close();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		Collections.sort(req, new Comparator<SignupRequest>() {
		    @Override
		    public int compare(SignupRequest lhs, SignupRequest rhs) {
		    	if(lhs.isApproved() == true && rhs.isApproved() == false) {
		    		return 1;
		    	}else if(lhs.isApproved() == false && rhs.isApproved() == true){
		    		return -1;
		    	}
		    	return 0;
		    }
		});
		return req;
	}
	
	
	public static void update(SignupRequest req){
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE);
			stmt.setInt(1, req.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
	}
	
}
