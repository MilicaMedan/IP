package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Item;
import dto.User;

public class ItemDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT = "INSERT INTO item (title, description, link, date, youtubeVideoUrl, imagesSrc, videoSrc, user_id,address,categories,emergency) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_SELECT = "SELECT * FROM item";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM item WHERE id=?";
	private static final String SQL_UPDATE="UPDATE item SET videoSrc=?, imagesSrc=? WHERE id=?";
	
	
	
	public static boolean update(int id, String videoSrc, String imageSrc) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = {videoSrc, imageSrc, id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0) {
				result = false;
			}
			if (generatedKeys.next())
				result = true;
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	
	
	
	
	
	public static int insert(Item item) {
		int result = 0;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { item.getTitle(), item.getDescription(),item.getLink(), item.getDate(), item.getYoutubeVideoUrl(), item.getImagesSrc(),item.getVideoSrc(),item.getUserId(), item.getAddress(),item.getCategories(),item.isEmergency() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0) {
				result = 0;
			}
			if (generatedKeys.next())
				result = generatedKeys.getInt(1);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	public static List<Item> select(){
		Item item = null;
		List<Item> items= new ArrayList<Item>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_SELECT, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				item = new Item(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("link"), rs.getTimestamp("date"),
						rs.getString("youtubeVideoUrl"),rs.getString("imagesSrc"),rs.getString("videoSrc"),rs.getInt("user_id"),rs.getString("address"),rs.getString("categories"),rs.getBoolean("emergency"));
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
	
	public static Item selectById(int id){
		
		
		Item item = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_SELECT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				item = new Item(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("link"), rs.getTimestamp("date"),
						rs.getString("youtubeVideoUrl"),rs.getString("imagesSrc"),rs.getString("videoSrc"),rs.getInt("user_id"),rs.getString("address"),rs.getString("categories"),rs.getBoolean("emergency"));
				
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return item;
	}
}
