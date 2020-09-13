package dto;

import java.util.Base64;
import java.util.Date;

import dao.UserDAO;

public class Comment {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int userId;
	private int itemId;
	private String imageSrc;
	private String text;
	public Date date;
	private String picture="";
	
	public String getPicture() {
		User user = UserDAO.selectById(this.userId);
		this.picture=user.getPicture();
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	
	
	public Comment() {
		super();
	}
	public Comment(int id, int userId, int itemId, String imageSrc, String text, Date date) {
		super();
		this.id = id;
		this.userId = userId;
		this.itemId = itemId;
		this.imageSrc = imageSrc;
		this.text = text;
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getImageSrc() {
		return imageSrc;
	}
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
