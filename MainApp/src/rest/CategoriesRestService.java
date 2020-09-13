package rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.ItemCategoryDAO;
import dto.ItemCategory;
import helper.RSSHelper;

@Path("/categories")
public class CategoriesRestService {

	@GET
	@Path("/getItemCategories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItemCategories() {
		List<ItemCategory> categories = ItemCategoryDAO.select();
		if (categories != null) {
			return Response.status(200).entity(categories).build();
		} else {
			return Response.status(404).build();
		}
	}
	
	
}
