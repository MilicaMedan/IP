package rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.hk2.api.UseProxy;

import dao.HelpCallDAO;
import dto.HelpCall;

@Path("/call")
public class HelpCallRest {

	@GET
	@Path("/calls")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCalls() {
		List<HelpCall> calls = HelpCallDAO.select(); 
		if (calls != null) {
			return Response.status(200).entity(calls).build();
		} else {
			return Response.status(404).build();
		}
	}
	
	@DELETE
	@Path("/block/{id}")
	public void blockCall(@PathParam("id")int id) {
		HelpCallDAO.delete(id);
		
	}
	
}
