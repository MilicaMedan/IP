package dto;

import java.io.Serializable;

public class CallCategory implements Serializable  {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	
	
	public CallCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CallCategory(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	
}
