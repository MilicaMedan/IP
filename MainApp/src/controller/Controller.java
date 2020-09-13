package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.UserBean;
import dao.SignupRequestDAO;
import dao.UserDAO;
import dto.User;
import helper.RSSHelper;
import dto.SignupRequest;

@WebServlet("/Controller")
@MultipartConfig(location = "/")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public Controller() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String address = "";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		session.setAttribute("notification", "");

		if (action == null || action.equals("")) {
			address = "/WEB-INF/pages/login.jsp";
		}else if (action.equals("signup")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			try {
				if (username != null) {
					String confirmPassword = request.getParameter("confirmPassword");
					String passwordHash= User.hash(password);
					String name = request.getParameter("name");
					String surname = request.getParameter("surname");
					String mail = request.getParameter("mail");
					UserBean userBean= new UserBean();
					if(!confirmPassword.equals(password)) {
						session.setAttribute("notification", "Passwords don't match!");
						address = "/WEB-INF/pages/signup.jsp";
					}else if (userBean.isUserNameAllowed(request.getParameter("username")) && userBean.isMailAllowed(mail)) {
						SignupRequest req = new SignupRequest(0,name,surname,username,passwordHash,mail,false);
						if (SignupRequestDAO.insert(req)) {
							session.setAttribute("notification", "Successful sign up! You will receive mail when your account is approved!");
							address = "/WEB-INF/pages/signup.jsp";
						}
					} else {
						session.setAttribute("notification", "Username or e-mail is already used!");
						address = "/WEB-INF/pages/signup.jsp";
					}
				} else {
					address = "/WEB-INF/pages/signup.jsp";
				}
			} catch (Exception e) {
				session.setAttribute("notification", "ERROR: " + e.getMessage());
			}
			
		}else if (action.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			if(username!= null && password!=null) {
				String passwordHash = User.hash(password);
				UserBean userBean= new UserBean();
				if (userBean.login(username, passwordHash)) {
					
					User user =UserDAO.selectByUsernameAndPassword(username, passwordHash);
					userBean.setUser(user);
					session.setAttribute("userBean", userBean);
					
					String id= String.valueOf(user.getId());
					Cookie cookie = new Cookie("id", id);
					cookie.setMaxAge(14400000);
					response.addCookie(cookie);
					if(userBean.getUser().getNumberOfLogin() == 0) {
						address = "/WEB-INF/pages/wizard.jsp";
					}else {
						UserDAO.setNumberOfLogin(userBean.getUser());
						user.setLogedIn(true);
						UserDAO.setLogedIn(user);
						address = "/WEB-INF/pages/main.jsp";
					}
					
					
				} else {
					
					session.setAttribute("notification", "Wrong username or password.");
					address = "/WEB-INF/pages/login.jsp";
				}
			}else {
				address = "/WEB-INF/pages/login.jsp";
			}
			
			
		}else if (action.equals("logout")) {
			UserBean userBean = (UserBean) session.getAttribute("userBean");
			if(userBean != null) {
				User user=userBean.getUser();
				user.setLogedIn(false);
				UserDAO.setLogedIn(user);
				session.invalidate();
				address = "/WEB-INF/pages/login.jsp";
			}else {
				address = "/WEB-INF/pages/login.jsp";
			}
			
		}else if(action.equals("submitProfile")) {
			UserBean userBean = (UserBean) session.getAttribute("userBean");
			if(userBean != null) {
				String name = request.getParameter("name");
				String surname = request.getParameter("surname");
				String username = request.getParameter("username");
				String mail = request.getParameter("mail");
				String country=request.getParameter("country")+"-"+request.getParameter("region")+"-"+request.getParameter("city");
				
				
				boolean notificationsInApp = request.getParameter("notifications") != null;
				
				boolean notificationsOnMail = request.getParameter("notificationsOnMail") !=null;
				
				
				int id= userBean.getUser().getId();
				
				String imageSrc= request.getParameter("imageSrc");
				if(imageSrc.equals("")) {
					Part image = request.getPart("file");
					InputStream is= image.getInputStream();
					byte[] buffer = new byte[is.available()];
					is.read(buffer);
					File targetFile = new File("C:/Users/Milica/Desktop/IP-Projekti/MainApp/WebContent/postImages/"+id+".jpg");
				    OutputStream outStream = new FileOutputStream(targetFile);
				    outStream.write(buffer);
					outStream.close();
					
					imageSrc+="userImages/"+id+".jpg";
					
				}
				
				
				
				
				
				User user = UserDAO.selectById(id);
				
				UserDAO.updateUser(name, surname, username, mail, country, notificationsInApp, notificationsOnMail, imageSrc,id);
				UserDAO.setNumberOfLogin(user);
				user.setLogedIn(true);
				UserDAO.setLogedIn(user);
				
				User updatedUser = UserDAO.selectById(id);
				userBean.setUser(user);
				session.setAttribute("userBean", userBean);
				Cookie c[]=request.getCookies(); 
				response.addCookie(c[0]);
				address = "/WEB-INF/pages/main.jsp";
			}else {
				address = "/WEB-INF/pages/login.jsp";
			}
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
