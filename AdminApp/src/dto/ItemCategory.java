package dto;

public class ItemCategory {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	
	
	public ItemCategory() {
		super();
	}
	public ItemCategory(int id, String name) {
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
