package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import dto.HelpCall;


public class HelpCallDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT = "SELECT * FROM helpcall WHERE active=1";
	private static final String SQL_INSERT = "INSERT INTO helpcall (title,time,location,description,pictureSrc,active,callcategory_id)"
			+ " VALUES (?,?,?,?,?,1,?)";
	private static final String SQL_BLOCK="UPDATE helpcall SET active=0 WHERE id=?";
	
	
	public static void block(HelpCall call){
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_BLOCK);
			stmt.setInt(1, call.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
	}
	
	public static boolean insert(HelpCall call ) {
		System.out.println("insert");
		boolean result = false;
		ResultSet generatedKeys = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, call.getTitle());
			stmt.setTimestamp(2, new Timestamp(call.getTime().getTime()));
			stmt.setString(3, call.getLocation());
			stmt.setString(4, call.getDescription());
			stmt.setString(5, call.getPictureSrc());
			stmt.setInt(6, call.getCategoryId());
			stmt.executeUpdate();
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return result;
	}
	
	
	public static ArrayList<HelpCall> select() {
		ArrayList<HelpCall> calls = new ArrayList<>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HelpCall call = new HelpCall(rs.getInt("id"), rs.getString("title"),rs.getTimestamp("time"),rs.getString("location"),
						rs.getString("description"),rs.getString("pictureSrc"),rs.getBoolean("active"),rs.getInt("callcategory_id"),rs.getBoolean("falseNews"));

				calls.add(call);
			}
			rs.close();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return calls;
	}
	
	
}
