package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import dao.CallCategoryDAO;
import dao.HelpCallDAO;
import dao.UserDAO;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dto.CallCategory;
import dto.HelpCall;
import dto.User;
import service.HelpCallService;

@ManagedBean(name = "helpcallbean")
@SessionScoped
public class HelpCallBean  implements Serializable{
	private static final long serialVersionUID = -8606687324815905107L;
	
	private List<HelpCall> calls = new ArrayList<>();
	private HelpCall call=new HelpCall();
	private Map<String, Integer> categories = new HashMap<String, Integer>();
	
	@PostConstruct
	public void init() {
		setCalls(HelpCallService.getAll());
		ArrayList<CallCategory> c= CallCategoryDAO.select();
		for(int i=0;i<c.size();i++) {
			categories.put(c.get(i).getName(), c.get(i).getId());
		}
	}

	public void deleteHelpCall() {
		HelpCallService.blockCall(call);
		setCalls(HelpCallService.getAll());
	}
	
	public Map<String, Integer> getCategories() {
		return categories;
	}

	public void setCategories(Map<String, Integer> categories) {
		this.categories = categories;
	}



	public HelpCall getCall() {
		return call;
	}

	public void setCall(HelpCall call) {
		this.call = call;
	}

	public List<HelpCall> getCalls() {
		return calls;
	}

	public void setCalls(List<HelpCall> calls) {
		this.calls = calls;
	}
}
