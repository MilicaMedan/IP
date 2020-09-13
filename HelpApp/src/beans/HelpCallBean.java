package beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import dao.CallCategoryDAO;
import dao.HelpCallDAO;
import dao.UserDAO;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dto.CallCategory;
import dto.HelpCall;
import dto.User;
import rest.HelpCallRest;

@ManagedBean(name = "helpcallbean")
@SessionScoped
public class HelpCallBean implements Serializable {
	private static final long serialVersionUID = -8606687324815905107L;

	private List<HelpCall> calls = new ArrayList<>();
	private HelpCall call = new HelpCall();
	private String time;
	private Part image;
	private String category;
	private Map<String, Integer> categories = new HashMap<String, Integer>();
	private String message;

	
	 
	
	
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@PostConstruct
	public void init() {
		setCalls(HelpCallDAO.select());
	}

	public void deleteHelpCall() {
		HelpCallDAO.delete(call.getId());
		setCalls(HelpCallDAO.select());
		call= new HelpCall();
	}

	public void reportHelpCall() {
		HelpCallDAO.report(call.getId());
		setCalls(HelpCallDAO.select());
		call= new HelpCall();
	}

	public Map<String, Integer> getCategories() {
		ArrayList<CallCategory> c = CallCategoryDAO.select();
		for (int i = 0; i < c.size(); i++) {
			categories.put(c.get(i).getName(), c.get(i).getId());
		}
		return categories;
	}

	public void setCategories(Map<String, Integer> categories) {
		this.categories = categories;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Part getImage() {
		return image;
	}

	public void setImage(Part image) {
		this.image = image;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public HelpCall getCall() {
		return call;
	}

	public void setCall(HelpCall call) {
		this.call = call;
	}

	public List<HelpCall> getCalls() {
		setCalls(HelpCallDAO.select());
		for (int i = 0; i < calls.size(); i++) {
			CallCategory category = CallCategoryDAO.selectById(calls.get(i).getCategoryId());
			calls.get(i).setCategoryName(category.getName());
		}
		return calls;
	}

	public void setCalls(List<HelpCall> calls) {
		this.calls = calls;
	}

	public String addCall() {
		System.out.println("addCall");
		if((call.getTime() != null) && (call.getCategoryId() != -1) && (! call.getDescription().equals("")) && (! call.getLocation().equals("")) && (! call.getTitle().equals(""))) {
			HelpCallDAO.insert(call);
			this.call = new HelpCall();
			this.message = "Successfully added!";
		}else{
			this.message = "You must fill in all required fields!";
		}
		
		return "add.xhtml?faces-redirect=true";
	}

}
