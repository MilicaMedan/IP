package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Application;

import java.util.List;

import javax.ws.rs.ApplicationPath;

import dao.CommentDAO;
import dao.ItemDAO;
import dto.Comment;
import dto.Item;
import helper.RSSHelper;

@Path("/item")
public class ItemRestService {

	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItems() {
		List<Item> items = RSSHelper.read();
		if (items != null) {
			return Response.status(200).entity(items).build();
		} else {
			return Response.status(404).build();
		}
	}

	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItemById(@PathParam("id") int id) {
		System.out.println("getItemById");
		Item item =ItemDAO.selectById(id);
		System.out.println(""+item.getTitle());
		System.out.println(""+item.getCategories());
		if (item != null) {
			return Response.status(200).entity(item).build();
		} else {
			return Response.status(404).build();
		}
	}
	
	
	@GET
	@Path("/comments/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getComments(@PathParam("id") int id) {
		List<Comment> comments= CommentDAO.selectByItemId(id);
		if (comments != null) {
			return Response.status(200).entity(comments).build();
		} else {
			return Response.status(404).build();
		}
	}
	
	
	
	
	
}
