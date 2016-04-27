package ch.cern.it.db.ims.mwod.utils;

public abstract class NonTransactionCallable<T> {
	public abstract T execute();
}
