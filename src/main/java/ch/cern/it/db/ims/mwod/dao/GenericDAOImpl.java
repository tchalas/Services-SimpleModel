package ch.cern.it.db.ims.mwod.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import ch.cern.it.db.ims.mwod.utils.HibernateUtil;

public abstract class GenericDAOImpl<T> implements GenericDAO<T>{

	private Session session;
	private Class<T> persistentClass;

	public GenericDAOImpl() {
		this.setPersistentClass((Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	public Session getSession() {
		if (session == null || (session != null && !session.isOpen())) {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		return session;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void save(T entity) {
		this.getSession().persist(entity);
	}

	public void update(T entity) {
		this.getSession().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		this.getSession().delete(entity);
	}

	public T findById(Class<T> cl, Serializable id) {
		return (T) this.getSession().get(cl, id);
	}

	public List<T> findByExample(Class<T> cl, Object example) {
		Criterion criterion = Example.create(example);
		return this.findByCriteria(criterion);
	}
	
	public List<T> findByExample(final T exampleEntity) {
	    final Example example = Example.create(exampleEntity).excludeZeroes().enableLike();
	    return this.getSession().createCriteria(this.persistentClass).add(example).list();
	}

	public List<T> findAll() {
		return this.findByCriteria();
	}

	protected List<T> findByCriteria(
			org.hibernate.criterion.Criterion... criterion) {
		Criteria crit = this.getSession().createCriteria(getPersistentClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}
}
