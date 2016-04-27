package ch.cern.it.db.ims.mwod.utils;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

import ch.cern.it.db.ims.mwod.model.Action;
import ch.cern.it.db.ims.mwod.model.Event;
import ch.cern.it.db.ims.mwod.services.EventServices;
import ch.cern.it.db.ims.mwod.services.EventServicesImpl;

public class SampleEventManager {

	final static Logger logger = Logger.getLogger(SampleEventManager.class);
	
	public static void main(String[] args) {

		EventServices eventServices = new EventServicesImpl();
		Event theEvent = new Event();
		theEvent.setTitle("Event-" + UUID.randomUUID().getMostSignificantBits());
		theEvent.setDate(new Date());
		logger.info(theEvent.toString());
		Action action = null;
		for (int i = 0; i < 4; i++) {
			action = new Action();
			action.setName("Action-Name-"
					+ UUID.randomUUID().getMostSignificantBits());
			action.setEvent(theEvent);
			theEvent.getActions().add(action);
		}
		eventServices.save(theEvent);
//		theEvent = eventServices.findById(1l);
//		List<Action> actions = theEvent.getActions(); 
//		if(actions!=null && actions.size()>0){
//			for (Iterator iterator = actions.iterator(); iterator.hasNext();) {
//				Action a = (Action) iterator.next();
//				logger.info(a.toString());
//			}
//		} else {
//			logger.error("NO ACTIONS???");
//		}
		
	}
}
