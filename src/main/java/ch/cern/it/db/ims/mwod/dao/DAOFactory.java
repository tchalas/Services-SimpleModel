package ch.cern.it.db.ims.mwod.dao;

import ch.cern.it.db.ims.mwod.utils.Constants;

public class DAOFactory {
	
	public GenericDAO<?> getDAOBean(String daoType) {
		if (daoType.equalsIgnoreCase(Constants.EVENT_DAO)) {
			return new EventDAOImpl();
		}
		if (daoType.equalsIgnoreCase(Constants.ACTION_DAO)) {
			return new ActionDAOImpl();
		}
		if (daoType.equalsIgnoreCase(Constants.COMMAND_DAO)) {
			return new CommandDAOImpl();
		}
		return null;
	}
}
