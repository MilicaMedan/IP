
package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndContentImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.SyndFeedOutput;

import dao.CallCategoryDAO;
import dao.HelpCallDAO;
import dto.CallCategory;
import dto.HelpCall;

@WebServlet("/rss")
public class RSSController  extends HttpServlet {
	
	public RSSController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<HelpCall> calls= HelpCallDAO.select();
		
		
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		feed.setTitle("test-title");
		feed.setDescription("test-description");
		feed.setLink("https://example.org");
		List<SyndEntry> entries = new ArrayList<>();
		for (int i = 0; i < calls.size(); i++) {
			SyndEntry item = new SyndEntryImpl();
			item.setTitle(calls.get(i).getTitle());
			item.setLink(calls.get(i).getPictureSrc());
			SyndContent description = new SyndContentImpl();
			description.setType("text/html");
			description.setValue(calls.get(i).getDescription());
			item.setDescription(description);
			item.setAuthor(calls.get(i).getLocation());
			CallCategory category = CallCategoryDAO.selectById(calls.get(i).getCategoryId());
			if(category!=null) {
				item.setComments(category.getName());
			}
			
			item.setPublishedDate(calls.get(i).getTime());
			entries.add(item);
		}
		feed.setEntries(entries);
		try {
			response.getWriter().println(new SyndFeedOutput().outputString(feed));
		} catch (Exception ex) {
			System.out.println(ex.toString());
			response.sendError(500);
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
