package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ConnectionPool;
import dao.DAOUtil;
import dto.User;

public class UserDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user WHERE username=? AND passwordHash=? AND isAdmin=0";
	private static final String SQL_IS_USERNAME_USED = "SELECT * FROM user WHERE username = ?";
	private static final String SQL_IS_MAIL_USED = "SELECT * FROM user WHERE mail = ?";
	private static final String SQL_SET_NUMBER_OF_LOGIN = "UPDATE user SET numberOfLogin=? WHERE id=?";
	private static final String SQL_SET_IS_LOGEDIN = "UPDATE user SET isLogedIn=? WHERE id=?";
	
	private static final String SQL_UPDATE_USER = "UPDATE user SET name=?, surname=?, username=?,mail=?,country=?,notificationsInApp=?,notificationsOnMail=?,picture=?  WHERE id=?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM user WHERE id=?";
	
	private static final String SQL_SELECT_MAILNOTIFICATIONS = "SELECT * FROM user WHERE notificationsOnMail=1 AND active=1";
	
	public static void setLogedIn(User user) {
		Connection connection = null;
		Object values[] = {user.isLogedIn(),user.getId()};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_SET_IS_LOGEDIN, false, values);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(connection);
		}
		
	}
	
	
	
	
	public static List<User> selectByMailNotifications(){
		User user = null;
		List<User> list= new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_SELECT_MAILNOTIFICATIONS, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("passwordHash"),
						 rs.getString("mail"), rs.getString("country"),  rs.getString("picture"), rs.getBoolean("notificationsInApp"),
						 rs.getBoolean("notificationsOnMail"), rs.getBoolean("isAdmin"), rs.getBoolean("active"), rs.getInt("numberOfLogin"),rs.getBoolean("isLogedIn") );
				list.add(user);
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return list;
	}
	
	
	
	public static User selectById(int id){
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_SELECT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("passwordHash"),
						 rs.getString("mail"), rs.getString("country"),  rs.getString("picture"), rs.getBoolean("notificationsInApp"),
						 rs.getBoolean("notificationsOnMail"), rs.getBoolean("isAdmin"), rs.getBoolean("active"), rs.getInt("numberOfLogin"),rs.getBoolean("isLogedIn") );
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}
	
	
	
	public static void updateUser(String name, String surname, String username, String mail, String country, boolean notificationsInApp, boolean notificationsOnMail, String picture, int id ) {
		Connection connection = null;
		Object values[] = {name, surname, username, mail, country, notificationsInApp,notificationsOnMail,picture, id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_UPDATE_USER, false, values);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(connection);
		}
		
	}
	
	
	
	
	public static void setNumberOfLogin(User user) {
		Connection connection = null;
		int number =user.getNumberOfLogin() +1;
		Object values[] = {number,  user.getId()};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_SET_NUMBER_OF_LOGIN, false, values);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(connection);
		}
		
	}
	
	public static User selectByUsernameAndPassword(String username, String password){
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {username, password};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,SQL_SELECT_BY_USERNAME_AND_PASSWORD, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("username"), rs.getString("passwordHash"),
						 rs.getString("mail"), rs.getString("country"),  rs.getString("picture"), rs.getBoolean("notificationsInApp"),
						 rs.getBoolean("notificationsOnMail"), rs.getBoolean("isAdmin"), rs.getBoolean("active"), rs.getInt("numberOfLogin"),rs.getBoolean("isLogedIn") );
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}
	
	
	public static boolean isUserNameUsed(String username) {
		boolean result = true;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {username};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_IS_USERNAME_USED, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				result = false;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	public static boolean isMailUsed(String mail) {
		boolean result = true;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {mail};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_IS_MAIL_USED, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				result = false;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
}
