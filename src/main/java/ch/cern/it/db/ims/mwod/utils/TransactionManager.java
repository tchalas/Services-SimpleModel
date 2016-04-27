package ch.cern.it.db.ims.mwod.utils;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionManager {
	public static <T> T doInTransaction(TransactionCallable<T> callable) {
		T result = null;
		Session session = null;
		Transaction txn = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			txn = session.beginTransaction();
			result = callable.execute();
			txn.commit();
		} catch (RuntimeException e) {
			if (txn != null && txn.isActive())
				txn.rollback();
			throw e;
		} finally {
			if (session != null) {
				if (session.isOpen()) {
					session.close();
				}
			}
		}
		return result;
	}
	
	public static <T> T doOutTransaction(TransactionCallable<T> callable) {
		T result = null;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			result = callable.execute();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				if (session.isOpen()) {
					session.close();
				}
			}
		}
		return result;
	}
}
