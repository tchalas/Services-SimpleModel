package ch.cern.it.db.ims.mwod.utils;

public abstract class TransactionCallable<T> {
	public abstract T execute();
}
