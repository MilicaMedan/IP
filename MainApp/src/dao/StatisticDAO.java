package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Statistic;

public class StatisticDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT = "SELECT * FROM statistic WHERE year=? AND month=? AND day=? AND hour=?";
	private static final String SQL_UPDATE="UPDATE statistic SET numberOfUsers=? WHERE id=?";
	private static final String SQL_INSERT = "INSERT INTO statistic (id, year, month, day, hour,numberOfUsers)"
			+ " VALUES (?,?,?,?,?,?)";
	
	public static Statistic select(int year, int month, int day, int hour){
		Statistic statistic = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {year, month, day, hour};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_SELECT, false, values);
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
		ResultSet rs = null;
		Object values[] = {numberOfUsers, id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_UPDATE, false, values);
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
		Statistic statistic = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {0, year, month, day, hour,numberOfUsers};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_INSERT, false, values);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
	}
}
