package dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import dao.UserDAO;

public class Item implements Comparable<Item> {
	private int id;
	private String title;
	private String description;
	private String link;
	private String pubDate;
	private Date date;
	private String youtubeVideoUrl;
	private String imagesSrc;
	private String videoSrc;
	private int userId=-1;
	private String address;
	private List<String> imagesSrcList;
	private String picture="";
	private String categories;
	boolean emergency;
	
	
	public boolean isEmergency() {
		return emergency;
	}

	public void setEmergency(boolean emergency) {
		this.emergency = emergency;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getPicture() {
		User user = UserDAO.selectById(this.userId);
		if(user != null) {
			picture=user.getPicture();
		}
		return picture;
	}

	public void setPicture(String picture) {
		picture = picture;
	}
	
	
	

	public List<String> getImagesSrcList() {
		String src = this.imagesSrc;
		if(this.imagesSrc != null) {
			String [] sources= src.split("-");
			imagesSrcList = new ArrayList<String>();
			for(int i=0;i<sources.length;i++) {
				imagesSrcList.add(sources[i]);
			}
		}
		return imagesSrcList;
	}

	public void setImagesSrcList(List<String> imagesSrcList) {
		this.imagesSrcList = imagesSrcList;
	}

	public Item() {
		super();
	}

	public Item(String title, String description, String link, String pubDate) {
		super();
		this.title = title;
		this.description = description;
		this.link = link;
		setPubDate(pubDate);

	}

	public Item(int id, String title, String description, String link, Date date, String youtubeVideoUrl,
			String imagesSrc, String videoSrc, int userId, String address, String categories,boolean emergency) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.link = link;
		this.date = date;
		this.youtubeVideoUrl = youtubeVideoUrl;
		this.imagesSrc = imagesSrc;
		this.videoSrc = videoSrc;
		this.userId = userId;
		this.address = address;
		this.categories=categories;
		this.emergency=emergency;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.ENGLISH);
		sdf.applyPattern("EEE, d MMM yyyy HH:mm:ss");
		pubDate = sdf.format(date);

		
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getYoutubeVideoUrl() {
		return youtubeVideoUrl;
	}

	public void setYoutubeVideoUrl(String youtubeVideoUrl) {
		this.youtubeVideoUrl = youtubeVideoUrl;
	}

	public String getImagesSrc() {
		return imagesSrc;
	}

	public void setImagesSrc(String imagesSrc) {
		this.imagesSrc = imagesSrc;
	}

	public String getVideoSrc() {
		return videoSrc;
	}

	public void setVideoSrc(String videoSrc) {
		this.videoSrc = videoSrc;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = "";

		String publicationDate[] = pubDate.split(" ");
		for (int i = 0; i < 5; i++) {
			this.pubDate += " " + publicationDate[i];
		}
		String time[] = publicationDate[4].split(":");
		int year = Integer.parseInt(publicationDate[3]);
		int day = Integer.parseInt(publicationDate[1]);
		int hours = Integer.parseInt(time[0]);
		int minutes = Integer.parseInt(time[1]);
		int seconds = Integer.parseInt(time[2]);
		int month = 0;
		switch (publicationDate[2]) {
		case "Jan":
			month = 1;
			break;
		case "Feb":
			month = 2;
			break;
		case "Mar":
			month = 3;
			break;
		case "Apr":
			month = 4;
			break;
		case "May":
			month = 5;
			break;
		case "Jun":
			month = 6;
			break;
		case "Jul":
			month = 7;
			break;
		case "Aug":
			month = 8;
			break;
		case "Sep":
			month = 9;
			break;
		case "Oct":
			month = 10;
			break;
		case "Nov":
			month = 11;
			break;
		case "Dec":
			month = 12;
			break;
		}

		Calendar calendar = new GregorianCalendar(year, month, day, hours, minutes, seconds);

		this.date = calendar.getTime();
	}

	@Override
	public int compareTo(Item item) {
		return (-1) * getDate().compareTo(item.getDate());
	}

}
