package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.CallCategory;

public class CallCategoryDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT = "SELECT * FROM callcategory";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM callcategory WHERE id=?";
	private static final String SQL_DELETE="DELETE FROM callcategory WHERE id=?";
	private static final String SQL_INSERT = "INSERT INTO callcategory (name) VALUES (?)";
	public static boolean insert(CallCategory c) {
		
		boolean result = false;
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, c.getName());
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return result;
	}
	public static void delete(int id) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_DELETE);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
	}
	
	public static CallCategory selectById(int id) {
		CallCategory category = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				 category = new CallCategory(rs.getInt("id"), rs.getString("name"));

			}
			rs.close();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return category;
	}
	
	public static ArrayList<CallCategory> select() {
		ArrayList<CallCategory> categories = new ArrayList<>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				CallCategory category = new CallCategory(rs.getInt("id"), rs.getString("name"));

				categories.add(category);
			}
			rs.close();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return categories;
	}
	
}
