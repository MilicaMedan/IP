package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

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
import dao.CommentDAO;
import dao.ItemDAO;
import dto.Comment;
import dto.Item;

@WebServlet("/UploadCommentController")
@MultipartConfig
public class UploadCommentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public UploadCommentController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("userBean");
		int userId= userBean.getUser().getId();
		
		String text = request.getParameter("text");
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		String isThereFile=request.getParameter("isThereFile");
		String imageSrc=null;
		
		Comment com= new Comment(0,userId,itemId,null,text, new Date());
		int id= CommentDAO.insert(com);
		
		if(isThereFile.equals("yes")) {
			Part image = request.getPart("file");
			InputStream is= image.getInputStream();
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			
		    File targetFile = new File("C:/Users/Milica/Desktop/Milica_Medan/MainApp/WebContent/commentImages/"+id+".jpg");
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
			outStream.close();
			
			imageSrc="commentImages/"+id+".jpg";
			CommentDAO.update(id, imageSrc);
		}
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
