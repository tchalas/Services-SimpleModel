package ch.cern.it.db.ims.mwod.services;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import ch.cern.it.db.ims.mwod.dao.EventDAO;
import ch.cern.it.db.ims.mwod.dao.EventDAOImpl;
import ch.cern.it.db.ims.mwod.model.Event;
import ch.cern.it.db.ims.mwod.utils.TransactionCallable;
import ch.cern.it.db.ims.mwod.utils.TransactionManager;


public class EventServicesImpl implements EventServices {
	
	@Context
	HttpServletRequest request;

	private EventDAO eventDAO = new EventDAOImpl();
	
	public Event findById(final Long id) {
		Event event = (Event)TransactionManager.doInTransaction(new TransactionCallable() {
			@Override
			public Event execute() {
				return eventDAO.findById(Event.class, id);
			}
		});
		return event;
	}
	
	@Override
	public List<Event> findByExample(final Event example) {
		List<Event> events = (List<Event>)TransactionManager.doInTransaction(new TransactionCallable() {
			@Override
			public List<Event> execute() {
				return eventDAO.findByExample(Event.class, example);
			}
		});
		return events;
	}
	
	@Override
	public List<Event> findAll() {
		List<Event> events = (List<Event>)TransactionManager.doInTransaction(new TransactionCallable() {
			@Override
			public List<Event> execute() {
				return eventDAO.findAll();
			}
		});
		return events;
	}

	public void save(final Event event) {
		TransactionManager.doInTransaction(new TransactionCallable() {
			@Override
			public Object execute() {
				eventDAO.save(event);
				return null;
			}
		});
	}
	
	public void createAndUpdate(final Event event) {
		TransactionManager.doInTransaction(new TransactionCallable() {
			@Override
			public Object execute() {
				eventDAO.save(event);
				// Modify the event
				event.setDate(new Date());
				eventDAO.update(event);
				return null;
			}
		});
	}
}
