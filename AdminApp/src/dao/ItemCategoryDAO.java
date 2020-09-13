package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ItemCategory;
import dto.SignupRequest;

public class ItemCategoryDAO {

	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT = "SELECT * FROM itemCategory";
	private static final String SQL_DELETE="DELETE FROM itemCategory WHERE id=?";
	private static final String SQL_INSERT = "INSERT INTO itemCategory (name) VALUES (?)";
	public static boolean insert(ItemCategory c) {
		
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
	
	public static List<ItemCategory> select(){
		ItemCategory item = null;
		List<ItemCategory> items= new ArrayList<ItemCategory>();
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
			rs = stmt.executeQuery();
			while (rs.next()){
				item = new ItemCategory(rs.getInt("id"), rs.getString("name"));
				items.add(item);
			}
			stmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(conn);
		}
		return items;
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
}
