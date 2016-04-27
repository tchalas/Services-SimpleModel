package ch.cern.it.db.ims.mwod.services;

import java.util.List;

import ch.cern.it.db.ims.mwod.dao.ActionDAO;
import ch.cern.it.db.ims.mwod.dao.CommandDAO;
import ch.cern.it.db.ims.mwod.dao.EventDAO;
import ch.cern.it.db.ims.mwod.model.Action;
import ch.cern.it.db.ims.mwod.model.Command;
import ch.cern.it.db.ims.mwod.model.Event;
import ch.cern.it.db.ims.mwod.utils.Constants;
import ch.cern.it.db.ims.mwod.utils.TransactionCallable;
import ch.cern.it.db.ims.mwod.utils.TransactionManager;

public class ComplexServicesImpl extends GeneralServices implements
		ComplexServices {

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> complexMethod() {
		List<Event> events = (List<Event>) TransactionManager
				.doInTransaction(new TransactionCallable() {
					@Override
					public List<Event> execute() {
						CommandDAO commandDAO = (CommandDAO) daoFactory.getDAOBean(Constants.COMMAND_DAO);
						Command command = new Command();
						command.setName("/etc/init.d/syscontrol");
						command.setParameters("-i entity stop");
						commandDAO.save(command);
						ActionDAO actionDAO = (ActionDAO) daoFactory.getDAOBean(Constants.ACTION_DAO);
						Action action = actionDAO.findById(Action.class, new Long(17l));
						action.setName("Set on the cocodriles");
						actionDAO.update(action);
						EventDAO eventDAO = (EventDAO) daoFactory
								.getDAOBean(Constants.EVENT_DAO);
						Event example = new Event();
						example.setEvent_id(1l);
						eventDAO.delete(((List<Event>) eventDAO.findByExample(example)).get(0));
						List<Event> events = eventDAO.findAll();
						return events;
					}
				});
		return events;
	}
}
