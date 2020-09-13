package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ItemCategory;

public class ItemCategoryDAO {

	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT = "SELECT * FROM itemCategory";
	
	public static List<ItemCategory> select(){
		ItemCategory item = null;
		List<ItemCategory> items= new ArrayList<ItemCategory>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_SELECT, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				item = new ItemCategory(rs.getInt("id"), rs.getString("name"));
				items.add(item);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return items;
	}
}
