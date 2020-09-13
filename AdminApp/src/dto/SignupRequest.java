package dto;

import java.io.Serializable;

public class SignupRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String surname;
	private String username;
	private String passwordHash;
	private String mail;
	private boolean isApproved;
	
	
	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public SignupRequest(int id, String name, String surname, String username, String passwordHash, String mail,
			boolean isApproved) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.passwordHash = passwordHash;
		this.mail = mail;
		this.isApproved = isApproved;
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
	
	
	
}
