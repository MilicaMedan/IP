package dto;

import java.io.Serializable;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String surname;
	private String username;
	private String passwordHash;
	private String mail;
	private String country;
	private String picture;
	private boolean notificationsInApp;
	private boolean notificationsOnMail;
	private boolean isAdmin;
	private boolean active;
	private int numberOfLogin;
	private boolean isLogedIn;
	
	public int getNumberOfLogin() {
		return numberOfLogin;
	}

	public void setNumberOfLogin(int numberOfLogin) {
		this.numberOfLogin = numberOfLogin;
	}

	public boolean isLogedIn() {
		return isLogedIn;
	}

	public void setLogedIn(boolean isLogedIn) {
		this.isLogedIn = isLogedIn;
	}
	
	public User() {
		super();
	}
	
	public User(int id, String name, String surname, String username, String passwordHash, String mail, String country,
			String picture, boolean notificationsInApp, boolean notificationsOnMail, boolean isAdmin, boolean active, int numberOfLogin, boolean isLogedIn ) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.passwordHash = passwordHash;
		this.mail = mail;
		this.country = country;
		this.picture = picture;
		this.notificationsInApp = notificationsInApp;
		this.notificationsOnMail = notificationsOnMail;
		this.isAdmin = isAdmin;
		this.active = active;
		this.numberOfLogin=numberOfLogin;
		this.isLogedIn=isLogedIn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public boolean isNotificationsInApp() {
		return notificationsInApp;
	}
	public void setNotificationsInApp(boolean notificationsInApp) {
		this.notificationsInApp = notificationsInApp;
	}
	public boolean isNotificationsOnMail() {
		return notificationsOnMail;
	}
	public void setNotificationsOnMail(boolean notificationsOnMail) {
		this.notificationsOnMail = notificationsOnMail;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public static String hash(String password) {
		Base64.Encoder enc = Base64.getEncoder();
		byte[] hash = null;
		byte[] salt = new byte[16];
		for(int i=0;i<16;i++) {
			salt [i]= (byte) 1;
		}
		
		try {
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
			SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = f.generateSecret(spec).getEncoded();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return  enc.encodeToString(hash);
	}
	

}
