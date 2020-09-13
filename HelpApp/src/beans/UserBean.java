package beans;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.*;
import java.nio.file.StandardOpenOption;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.sun.faces.facelets.util.Path;

import dao.UserDAO;
import dto.User;

@ManagedBean(name = "userBean")
@SessionScoped
@MultipartConfig(location = "/")
public class UserBean implements Serializable {

	private static final long serialVersionUID = -8606687324815905107L;
	private User user = new User();
	private boolean isLoggedIn = false;
	private String username = "";
	private String password = "";
	private String numberOfLogin = "";
	private String message;

	private Part uploadedFile;

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		if (uploadedFile != null) {
			try {
				InputStream is = uploadedFile.getInputStream();
				byte[] buffer = new byte[is.available()];
				is.read(buffer);
				
				
				String nameOfFile=""+user.getId();
				File targetFile = new File("C:/Users/Milica/Desktop/IP-Projekti/AdminApp/WebContent/images/"+nameOfFile+".jpg");
				OutputStream outStream = new FileOutputStream(targetFile);
			    outStream.write(buffer);
				outStream.close();
				
				String imageSrc="images/"+nameOfFile+".jpg";
				
				user.setPicture(imageSrc);
				UserDAO.updatePicture(user);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.uploadedFile = uploadedFile;
		System.out.println("kraj");
	}

	public String getNumberOfLogin() {
		return numberOfLogin;
	}

	public void setNumberOfLogin(String numberOfLogin) {
		this.numberOfLogin = numberOfLogin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String login() {

		if (!(username.contentEquals("") && password.contentEquals(""))) {
			String passwordHash = User.hash(password);
			if ((user = UserDAO.selectByUsernameAndPassword(username, passwordHash)) != null) {
				isLoggedIn = true;
				int num = user.getNumberOfLogin() + 1;
				numberOfLogin = "" + this.username + " : " + num + ". login";
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().getSessionMap().put("userBean", this);

				UserDAO.setNumberOfLogin(user);
				return "main.xhtml?faces-redirect=true";

			} else {
				this.message = "Incorrect Username or Passowrd!";

			}
		} else {
			this.message = "Incorrect Username or Passowrd!";

		}

		return "login.xhtml?faces-redirect=true";
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
		return "login.xhtml?faces-redirect=true";
	}

	public void loadPicture() {
		System.out.println("aaaa");
	}

	
	
	public String addHelpCall() {
		return "add.xhtml?faces-redirect=true";
	}
	public String helpCalls() {
		return "main.xhtml?faces-redirect=true";
	}
}
