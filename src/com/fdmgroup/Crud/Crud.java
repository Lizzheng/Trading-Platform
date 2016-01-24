package com.fdmgroup.Crud;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.fdmgroup.request.RequestStatus;

public class Crud<T extends Storable> implements IStorage<T> {
	private List<T> storableThings = new ArrayList<T>();
	EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("tradingpersistence");
	private EntityManager em = emf.createEntityManager();

	@Override
	public void create(T t) {
		getEm().getTransaction().begin();
		getEm().persist(t);
		getEm().getTransaction().commit();
	}

	@Override
	public T read(int id, T t) {
		return (T) getEm().find(t.getClass(), id);
	}

	@Override
	public List<T> read(String database, T t) {
		String queryString = "SELECT * FROM " + database;
		Query query = getEm().createNativeQuery(queryString, t.getClass());
		storableThings = (List<T>) query.getResultList();
		return storableThings;
	}

	public List<T> readByAdminID(int adminID, T t) {
		String queryString = "SELECT * FROM request_table WHERE Assigned_Admin="
				+ adminID;
		Query query = getEm().createNativeQuery(queryString, t.getClass());
		storableThings = (List<T>) query.getResultList();
		return storableThings;
	}

	public List<T> readByAdminUserName(String username, T t) {
		String queryString = "SELECT * FROM request_table WHERE user_name='"
				+ username + "'";
		Query query = getEm().createNativeQuery(queryString, t.getClass());
		storableThings = (List<T>) query.getResultList();
		return storableThings;
	}

	public List<T> readAllByStatus(String database, RequestStatus status, T t) {
		String queryString = "SELECT * FROM " + database + " WHERE STATUS = '"
				+ status.toString() + "'";
		Query query = getEm().createNativeQuery(queryString, t.getClass());
		storableThings = (List<T>) query.getResultList();
		return storableThings;
	}

	@Override
	public void update(T t) {
		getEm().getTransaction().begin();
		getEm().merge(t);
		getEm().getTransaction().commit();
	}

	@Override
	public void delete(T t) {
		getEm().getTransaction().begin();
		getEm().remove(t);
		getEm().getTransaction().commit();

	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
