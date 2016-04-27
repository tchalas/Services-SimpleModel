package ch.cern.it.db.ims.mwod.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import ch.cern.it.db.ims.mwod.model.Action;
import ch.cern.it.db.ims.mwod.model.Event;
import ch.cern.it.db.ims.mwod.services.ComplexServices;
import ch.cern.it.db.ims.mwod.services.ComplexServicesImpl;
import ch.cern.it.db.ims.mwod.services.EventServices;
import ch.cern.it.db.ims.mwod.services.EventServicesImpl;

public class CrudEventTest {

	final static Logger logger = Logger.getLogger(CrudEventTest.class);
	private EventServices eventServices = new EventServicesImpl(); 
	
	@Test
	public void dummyTest() {
		Assert.assertTrue("Implementation in progress...", true);
	}

	@Test
	public void create() {
		// Insert a new event
		Event event1 = new Event();
		event1.setTitle("Event-" + UUID.randomUUID().getMostSignificantBits());
		event1.setDate(new Date());
		Action action = null;
		for (int i = 0; i < 4; i++) {
			action = new Action();
			action.setName("Action-Name-"
					+ UUID.randomUUID().getMostSignificantBits());
			action.setEvent(event1);
			event1.getActions().add(action);
		}
		eventServices.save(event1);
		// Check the event has been successfully created
		Long id = event1.getEvent_id();
		Event event2 = eventServices.findById(id);
		logger.info(event2.getActions());
		// The dates can be different: checking the title can be enough
		Assert.assertEquals(event1.getTitle(), event2.getTitle());
		eventServices.findAll();
	}

	@Test
	public void read() {
		
	}

	@Test
	public void update() {

	}

	@Test
	public void delete() {

	}

}
