package ch.cern.it.db.ims.mwod.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T> {
	
	public List<T> findAll();
	public void save(T entity);
	public void update(T entity);
	public void delete(T entity);
	public T findById(Class<T> cl, Serializable id);
	public List<T> findByExample(Class<T> cl, Object example);
	public List<T> findByExample(final T exampleEntity);
}
