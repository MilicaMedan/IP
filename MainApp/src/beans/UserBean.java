package beans;

import dto.Statistic;
import dto.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import dao.StatisticDAO;
import dao.UserDAO;

public class UserBean {

	private static final long serialVersionUID = 1L;
	private User user = new User();
	private boolean isLoggedIn = false;
	private String numberOfLogin = "";
	private String picture="";
	
	public String getPicture() {
		picture=user.getPicture();
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getNumberOfLogin() {
		numberOfLogin = "" + user.getUsername() + " : " + user.getNumberOfLogin() + ". login";
		return numberOfLogin;
	}

	public void setNumberOfLogin(String numberOfLogin) {
		this.numberOfLogin = numberOfLogin;
	}
	
	
	public UserBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public boolean login(String username, String password) {
		if ((user = UserDAO.selectByUsernameAndPassword(username, password)) != null) {
			updateStatistic();
			isLoggedIn = true;
			return true;
		}
		return false;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	
	public void logout() {
		user = new User();
		isLoggedIn = false;
	}
	
	public boolean isUserNameAllowed(String username) {
		return UserDAO.isUserNameUsed(username);
	}
	
	public boolean isMailAllowed(String mail) {
		return UserDAO.isMailUsed(mail);
	}
	
	public void updateStatistic() {
		LocalDateTime now = LocalDateTime.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		int hour = now.getHour();
		Statistic statistic=StatisticDAO.select(year, month, day, hour);
		if(statistic!= null) {
			int numOfUsers=statistic.getNumberOfUsers()+1;
			StatisticDAO.update(statistic.getId(), numOfUsers);
		}else {
			StatisticDAO.insert(year, month, day, hour, 1);
		}
	}
	
	
	
}
