package dto;

import java.io.Serializable;
import java.util.Date;

public class HelpCall implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private Date time;
	private String location;
	private String description;
	private String pictureSrc;
	private boolean active;
	private int categoryId;
	private String categoryName;
	private boolean falseNews;
	
	
	public boolean isFalseNews() {
		return falseNews;
	}
	public void setFalseNews(boolean falseNews) {
		this.falseNews = falseNews;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public HelpCall() {
		super();
	}
	public HelpCall(int id, String title, Date time, String location, String description, String pictureSrc,
			boolean active, int categoryId, boolean falseNews) {
		super();
		this.id = id;
		this.title = title;
		this.time = time;
		this.location = location;
		this.description = description;
		this.pictureSrc = pictureSrc;
		this.active = active;
		this.categoryId = categoryId;
		this.falseNews=falseNews;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPictureSrc() {
		return pictureSrc;
	}
	public void setPictureSrc(String pictureSrc) {
		this.pictureSrc = pictureSrc;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
}
