package beans;

import dao.ItemDAO;
import dto.Item;


public class ItemBean {
	private static final long serialVersionUID = 1L;
	
	public Item getItemById(int id) {
		return ItemDAO.selectById(id);
	}
}
