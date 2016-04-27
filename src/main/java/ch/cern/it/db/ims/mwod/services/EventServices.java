package ch.cern.it.db.ims.mwod.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.cern.it.db.ims.mwod.model.Event;

@Path("/events")
public interface EventServices {
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> findAll();
	public void createAndUpdate(Event event);
	public void save(Event event);
	public Event findById(Long id);
	public List<Event> findByExample(Event example);
}
