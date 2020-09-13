package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import dto.User;

public class UserDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user WHERE username=? AND passwordHash=? AND active=1";
	private static final String SQL_IS_USERNAME_USED = "SELECT * FROM user WHERE username = ?";
	private static final String SQL_IS_MAIL_USED = "SELECT * FROM user WHERE mail = ?";
	private static final String SQL_INSERT = "INSERT INTO user (name, surname, username, passwordHash, mail, country, picture, notificationsInApp, notificationsOnMail,isAdmin,active)"
			+ " VALUES (?,?,?,?,?,NULL,NULL,1,1,0,1)";

	private static final String SQL_SELECT = "SELECT * FROM user WHERE id!=?";
	private static final String SQL_BLOCK="UPDATE user SET active=0 WHERE id=?";
	private static final String SQL_RESET_PASSWORD="UPDATE user SET passwordHash=? WHERE id=?";
	
	private static final String SQL_SET_NUMBER_OF_LOGIN = "UPDATE user SET numberOfLogin=? WHERE id=?";
	
	private static final String SQL_SELECT_ALL_LOGED_IN = "SELECT * FROM user WHERE isLogedIn=1";
	
	
	private static final String  SQL_UPDATE_PICTURE = "UPDATE user SET picture=? WHERE id=?";
	
	
	public static void updatePicture(User user) {
		System.out.println(""+user.getId());
		System.out.println(""+user.getPicture());
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE_PICTURE);
			stmt.setString(1,user.getPicture());
			stmt.setInt(2, user.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		
	}
	
	
	
	public static ArrayList<User> selectLogedIn() {
		ArrayList<User> users = new ArrayList<>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_ALL_LOGED_IN);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
						rs.getString("username"), rs.getString("passwordHash"), rs.getString("mail"),
						rs.getString("country"), rs.getString("picture"), rs.getBoolean("notificationsInApp"),
						rs.getBoolean("notificationsOnMail"), rs.getBoolean("isAdmin"), rs.getBoolean("active"),
						rs.getInt("numberOfLogin"),rs.getBoolean("isLogedIn"));

				users.add(user);
			}
			rs.close();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return users;
	}
	

	public static void setNumberOfLogin(User user) {
		Connection conn = null;
		int number =user.getNumberOfLogin() +1;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_SET_NUMBER_OF_LOGIN);
			stmt.setInt(1,number);
			stmt.setInt(2, user.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		
	}
	
	
	public static User selectByUsernameAndPassword(String username, String passwordHash) {
		User user = null;
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_USERNAME_AND_PASSWORD);
			stmt.setObject(1, username);
			stmt.setObject(2, passwordHash);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
						rs.getString("username"), rs.getString("passwordHash"), rs.getString("mail"),
						rs.getString("country"), rs.getString("picture"), rs.getBoolean("notificationsInApp"),
						rs.getBoolean("notificationsOnMail"), rs.getBoolean("isAdmin"), rs.getBoolean("active"),
						rs.getInt("numberOfLogin"),rs.getBoolean("isLogedIn"));
			}
			rs.close();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return user;

	}

	public static boolean isUserNameUsed(String username) {
		boolean result = true;
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_IS_USERNAME_USED);
			stmt.setObject(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = false;
			}
			rs.close();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return result;
	}

	public static boolean isMailUsed(String mail) {
		boolean result = true;
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_IS_MAIL_USED);
			stmt.setObject(1, mail);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				result = false;
			}
			rs.close();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return result;
	}


	public static ArrayList<User> select(int idOfCurrentUser) {
		ArrayList<User> users = new ArrayList<>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
			stmt.setInt(1, idOfCurrentUser);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"),
						rs.getString("username"), rs.getString("passwordHash"), rs.getString("mail"),
						rs.getString("country"), rs.getString("picture"), rs.getBoolean("notificationsInApp"),
						rs.getBoolean("notificationsOnMail"), rs.getBoolean("isAdmin"), rs.getBoolean("active"),
						rs.getInt("numberOfLogin"),rs.getBoolean("isLogedIn"));

				users.add(user);
			}
			rs.close();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return users;
	}
	
	public static void block(User user){
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_BLOCK);
			stmt.setInt(1, user.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
	}
	
	public static String resetPassword(User user) {
		Random rnd= new Random();
		String password="";
		for(int i=0;i<8;i++) {
			Integer br = rnd.nextInt();
			password= password + br.toString();
		}
		String passwordHash= User.hash(password);
		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionPool().checkOut();
			PreparedStatement stmt = conn.prepareStatement(SQL_RESET_PASSWORD);
			stmt.setString(1,passwordHash );
			stmt.setInt(2, user.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
		} finally {
			ConnectionPool.getConnectionPool().checkIn(conn);
		}
		return password;
	}

}
