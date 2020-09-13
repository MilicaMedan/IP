package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Comment;
import dto.Item;

public class CommentDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_ITEM_ID = "SELECT * FROM comment WHERE item_id=?";
	private static final String SQL_INSERT="INSERT into comment (imageSrc,text,user_id,item_id,date) VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATE="UPDATE comment SET imageSrc=? WHERE id=?";

	
	
	
	
	public static boolean update(int id, String imageSrc) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = {imageSrc, id };
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
	
	
	
	
	
	public static int insert(Comment com) {
		int result = 0;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = {com.getImageSrc(),com.getText(),com.getUserId(),com.getItemId(),com.getDate() };
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
	
	
	public static List<Comment> selectByItemId(int itemId) {

		Comment comment = null;
		List<Comment> list=new ArrayList<Comment>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { itemId };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_ITEM_ID, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				comment= new Comment(rs.getInt("id"), rs.getInt("user_id"),rs.getInt("item_id"),rs.getString("imageSrc"),
						rs.getString("text"),rs.getTimestamp("date"));
				list.add(comment);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return list;
	}
}
