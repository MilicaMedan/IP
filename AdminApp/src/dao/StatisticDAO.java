package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Statistic;

public class StatisticDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT = "SELECT * FROM statistic WHERE year=? AND month=? AND day=? AND hour=?";
	private static final String SQL_UPDATE="UPDATE statistic SET numberOfUsers=? WHERE id=?";
	private static final String SQL_INSERT = "INSERT INTO statistic (id, year, month, day, hour,numberOfUsers)"
			+ " VALUES (?,?,?,?,?,?)";
	
	private static final String SQL_SELECT_DATA = "SELECT * FROM statistic WHERE year=? AND month=? AND day=? AND (hour BETWEEN ? AND ?)";
	
	
	
	public static List<Statistic> selectData(int year, int month, int day,int hour1, int hour2){
		Statistic statistic = null;
		List<Statistic> statistics = new ArrayList<Statistic>();
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_DATA);
			pstmt.setInt(1, year);
			pstmt.setInt(2, month);
			pstmt.setInt(3, day);
			pstmt.setInt(4, hour1);
			pstmt.setInt(5, hour2);
			rs = pstmt.executeQuery();
			while (rs.next()){
				statistic= new Statistic(rs.getInt("id"),rs.getInt("year"),rs.getInt("month"),rs.getInt("day"),rs.getInt("hour"),rs.getInt("numberOfUsers"));
				statistics.add(statistic);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return statistics;
	}
	
	public static Statistic select(int year, int month, int day, int hour){
		Statistic statistic = null;
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT);
			pstmt.setInt(1, year);
			pstmt.setInt(2, month);
			pstmt.setInt(3, day);
			pstmt.setInt(4, hour);
			rs = pstmt.executeQuery();
			if (rs.next()){
				statistic= new Statistic(rs.getInt("id"),rs.getInt("year"),rs.getInt("month"),rs.getInt("day"),rs.getInt("hour"),rs.getInt("numberOfUsers"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return statistic;
	}
	public static Statistic update(int id, int numberOfUsers){
		Statistic statistic = null;
		Connection connection = null;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE);
			pstmt.setInt(1, numberOfUsers);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return statistic;
	}
	
	public static void insert(int year, int month, int day, int hour, int numberOfUsers){
		Connection connection = null;
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, year);
			pstmt.setInt(3, month);
			pstmt.setInt(4, day);
			pstmt.setInt(5, hour);
			pstmt.setInt(6, numberOfUsers);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
	}
}
