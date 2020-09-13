package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.UserBean;
import dao.ItemDAO;
import dao.UserDAO;
import dto.Item;
import dto.User;
import beans.MailBean;

@WebServlet("/UploadImageController")
@MultipartConfig
public class UploadImageController extends HttpServlet {


	
	private static final long serialVersionUID = 1L;
	
	public UploadImageController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		int id= userBean.getUser().getId();
		
		String type = request.getParameter("type");
		if(type.equals("link")) {
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String link = request.getParameter("link");
			String address = request.getParameter("address");
			String categories = request.getParameter("categories");
			
			boolean emergency=false;
			String emergencyWarning=request.getParameter("emergencyWarning");
			if(emergencyWarning.equals("yes")) {
				emergency=true;
				
				List<User> users= UserDAO.selectByMailNotifications();
				for(int i=0; i<users.size();i++) {
					String mail= users.get(i).getMail();
					String text="EMERGENY WARNING! There is "+categories+". Location "+address+".";
					new MailBean(mail,text);
				}
				
				
			}
			Item item = new Item(0,title,description,link,new Date(),null,null,null,id,address,categories,emergency);
			int idItem=ItemDAO.insert(item);
			
			
		}else if(type.equals("youtubeVideo")) {
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String url = request.getParameter("url");
			String address = request.getParameter("address");
			String categories = request.getParameter("categories");
			
			boolean emergency=false;
			String emergencyWarning=request.getParameter("emergencyWarning");
			if(emergencyWarning.equals("yes")) {
				emergency=true;
				
				List<User> users= UserDAO.selectByMailNotifications();
				for(int i=0; i<users.size();i++) {
					String mail= users.get(i).getMail();
					String text="EMERGENY WARNING! There is "+categories+". Location "+address+".";
					new MailBean(mail,text);
				}
			}
			Item item = new Item(0,title,description,null,new Date(),url,null,null,id,address,categories,emergency);
			int idItem=ItemDAO.insert(item);
			
		}else if(type.equals("video")) {
			
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			Part video = request.getPart("video");
			String address = request.getParameter("address");
			String categories = request.getParameter("categories");
			
			
			
			boolean emergency=false;
			String emergencyWarning=request.getParameter("emergencyWarning");
			if(emergencyWarning.equals("yes")) {
				emergency=true;
				
				List<User> users= UserDAO.selectByMailNotifications();
				for(int i=0; i<users.size();i++) {
					String mail= users.get(i).getMail();
					String text="EMERGENY WARNING! There is "+categories+". Location "+address+".";
					new MailBean(mail,text);
				}
			}
			Item item = new Item(0,title,description,null,new Date(),null,null,null,id,address,categories,emergency);
			int idItem=ItemDAO.insert(item);
			
			
			InputStream is= video.getInputStream();
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			
			
			
		    File targetFile = new File("C:/Users/Milica/Desktop/Milica_Medan/MainApp/WebContent/postVideo/"+idItem+".mp4");
		    System.out.println(targetFile.getAbsolutePath());
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
			outStream.close();
			
			
			
			String videoSrc="postVideo/"+idItem+".mp4";
			ItemDAO.update(idItem, videoSrc, null);
			
			
		}else if(type.equals("pictures")) {
			int postNumber=1;
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String address = request.getParameter("address");
			int length= Integer.parseInt(request.getParameter("length"));
			String categories = request.getParameter("categories");
			
			boolean emergency=false;
			String emergencyWarning=request.getParameter("emergencyWarning");
			if(emergencyWarning.equals("yes")) {
				emergency=true;
				
				List<User> users= UserDAO.selectByMailNotifications();
				for(int i=0; i<users.size();i++) {
					String mail= users.get(i).getMail();
					String text="EMERGENY WARNING! There is "+categories+". Location "+address+".";
					new MailBean(mail,text);
				}
			}
			Item item = new Item(0,title,description,null,new Date(),null,null,null,id,address,categories,emergency);
			int idItem=ItemDAO.insert(item);
			String imageSrc="";
			for(int i=0;i<length;i++) {
				Part image= request.getPart("file"+i);
				
				InputStream is= image.getInputStream();
				byte[] buffer = new byte[is.available()];
				is.read(buffer);
				
				postNumber++;
				String nameOfFile=""+idItem+""+postNumber;
				
			    File targetFile = new File("C:/Users/Milica/Desktop/Milica_Medan/MainApp/WebContent/postImages/"+nameOfFile+".jpg");
			    OutputStream outStream = new FileOutputStream(targetFile);
			    outStream.write(buffer);
				outStream.close();
				
				imageSrc+="postImages/"+nameOfFile+".jpg"+"-";
			}
			
			ItemDAO.update(idItem, null, imageSrc);
			
			
			
			
		};
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
