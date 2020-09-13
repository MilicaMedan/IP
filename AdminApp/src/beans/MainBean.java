package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.context.FacesContext;

import dao.CallCategoryDAO;
import dao.ItemCategoryDAO;
import dao.SignupRequestDAO;
import dao.UserDAO;
import dto.CallCategory;
import dto.ItemCategory;
import dto.SignupRequest;
import dto.User;

@ManagedBean(name = "mainBean")
@SessionScoped
public class MainBean implements Serializable {

	private static final long serialVersionUID = -8606687324815905107L;
	private HtmlInputHidden requestId = new HtmlInputHidden();
	private List<SignupRequest> req;
	private SignupRequest request;
	private List<User> users;
	private User selectedUser;
	private int numberOfUsers;
	private List<User> usersLogedIn;
	private int numberOfLogedInUsers;
	private List<ItemCategory> itemCategories;
	private ItemCategory itemCategory = new ItemCategory();
	private List<CallCategory> callCategories;
	private CallCategory callCategory = new CallCategory();
	
	private String callMessage;
	private String itemMessage;
	
	public String getCallMessage() {
		return callMessage;
	}

	public void setCallMessage(String callMessage) {
		this.callMessage = callMessage;
	}

	public String getItemMessage() {
		return itemMessage;
	}

	public void setItemMessage(String itemMessage) {
		this.itemMessage = itemMessage;
	}

	public void deleteCallCategory() {
		CallCategoryDAO.delete(callCategory.getId());
		callCategory.setName("");
	}
	
	public String addCallCategory() {
		if(callCategory.getName().equals("")) {
			this.callMessage="Name is required!";
			return "mainCategories.xhtml?faces-redirect=true";
		}else {
			this.callMessage="";
			CallCategoryDAO.insert(callCategory);
			callCategory.setName("");
			return "mainCategories.xhtml?faces-redirect=true";
		}
		
	}

	
	public List<CallCategory> getCallCategories() {
		callCategories=CallCategoryDAO.select();
		return callCategories;
	}


	public void setCallCategories(List<CallCategory> callCategories) {
		this.callCategories = callCategories;
	}


	public CallCategory getCallCategory() {
		return callCategory;
	}


	public void setCallCategory(CallCategory callCategory) {
		this.callCategory = callCategory;
	}


	public void deleteItemCategory() {
		ItemCategoryDAO.delete(itemCategory.getId());
		itemCategory.setName("");
	}


	public List<ItemCategory> getItemCategories() {
		itemCategories= ItemCategoryDAO.select();
		return itemCategories;
	}
	
	public String addItemCategory() {
		if("".equals(itemCategory.getName())) {
			this.itemMessage="Name is required!";
			return "mainCategories.xhtml?faces-redirect=true";
		}
		else {
			this.itemMessage="";
			ItemCategoryDAO.insert(itemCategory);
			itemCategory.setName("");
			return "mainCategories.xhtml?faces-redirect=true";
		}
		
	}


	public void setItemCategories(List<ItemCategory> itemCategories) {
		this.itemCategories = itemCategories;
	}


	public ItemCategory getItemCategory() {
		return itemCategory;
	}


	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}


	public List<User> getUsersLogedIn() {
		usersLogedIn = UserDAO.selectLogedIn();
		return usersLogedIn;
	}

	public void setUsersLogedIn(List<User> usersLogedIn) {
		this.usersLogedIn = usersLogedIn;
	}

	public int getNumberOfLogedInUsers() {
		getUsersLogedIn();
		if (usersLogedIn != null) {
			numberOfLogedInUsers = usersLogedIn.size();
		} else {
			numberOfLogedInUsers = 0;
		}
		return numberOfLogedInUsers;
	}

	public void setNumberOfLogedInUsers(int numberOfLogedInUsers) {
		this.numberOfLogedInUsers = numberOfLogedInUsers;
	}

	public int getNumberOfUsers() {
		getAllUsers();
		numberOfUsers = users.size() + 1;
		return numberOfUsers;
	}

	public void setNumberOfUsers(int numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<User> getUsers() {
		getAllUsers();
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<SignupRequest> getReq() {
		// if (FacesContext.getCurrentInstance().getRenderResponse()) {
		getRequests();
		// }
		return req;
	}

	public void setReq(ArrayList<SignupRequest> req) {
		this.req = req;
	}

	public HtmlInputHidden getRequestId() {
		return requestId;
	}

	public void setRequestId(HtmlInputHidden requestId) {
		this.requestId = requestId;
	}

	public SignupRequest getRequest() {
		return request;
	}

	public void setRequest(SignupRequest request) {
		this.request = request;
	}

	public String requests() {
		return "main.xhtml?faces-redirect=true";
	}

	public String posts() {
		return "mainPost.xhtml?faces-redirect=true";
	}

	public String users() {
		return "mainUser.xhtml?faces-redirect=true";
	}

	public String statistic() {
		return "mainStatistic.xhtml?faces-redirect=true";
	}
	
	public String categories() {
		return "mainCategories.xhtml?faces-redirect=true";
	}

	public void getRequests() {
		try {
			req = SignupRequestDAO.select();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String editDataItem() {

		SignupRequestDAO.update(request);
		UserDAO.insertFromReq(request);
		MailBean bean = new MailBean(request.getMail(), "Your account has been approved!");
		return "main.xhtml?faces-redirect=true";
	}

	public void getAllUsers() {
		try {

			FacesContext context = FacesContext.getCurrentInstance();
			UserBean userBean = (UserBean) context.getExternalContext().getSessionMap().get("userBean");
			int idOfCurrentUser = userBean.getUser().getId();
			users = UserDAO.select(idOfCurrentUser);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String blockSelectedUser() {
		UserDAO.block(selectedUser);
		MailBean bean = new MailBean(selectedUser.getMail(), "Your account has been suspended!");
		return "mainUser.xhtml?faces-redirect=true";
	}

	public String resetPasswordOfSelectedUser() {
		String password = UserDAO.resetPassword(selectedUser);
		MailBean bean = new MailBean(selectedUser.getMail(),
				"Your password has been reset! Your new password:  " + password + " !");
		return "mainUser.xhtml?faces-redirect=true";
	}

}
