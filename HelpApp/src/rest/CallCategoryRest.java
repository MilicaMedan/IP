package rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.CallCategoryDAO;
import dto.CallCategory;

@Path("/category")
public class CallCategoryRest {

	@GET
	@Path("/categories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getItems() {
		List<CallCategory> categories = CallCategoryDAO.select();
		if (categories != null) {
			return Response.status(200).entity(categories).build();
		} else {
			return Response.status(404).build();
		}
	}
}
