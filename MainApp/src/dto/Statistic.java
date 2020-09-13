package dto;

import java.io.Serializable;

public class Statistic implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int numberOfUsers;
	
	public Statistic(int id, int year, int month, int day, int hour, int numberOfUsers) {
		super();
		this.id = id;
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.numberOfUsers = numberOfUsers;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getNumberOfUsers() {
		return numberOfUsers;
	}
	public void setNumberOfUsers(int numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}
	
	

}
