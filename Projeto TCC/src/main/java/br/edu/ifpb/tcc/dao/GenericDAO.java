package br.edu.ifpb.tcc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GenericDAO<T, PK extends Serializable> implements IGenericDAO<T, PK> {

	protected Class<T> entityClass;
	
	protected EntityManager entityManager;

	public GenericDAO(EntityManager em) {
		this();
		this.entityManager = em;
	}

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}
	
	 @SuppressWarnings("unchecked")
	    public List<T> findAll() {
	        Query q = entityManager.createQuery("select object(o) from "+entityClass.getSimpleName()+" as o");
	        return q.getResultList();
	    }

	public T insert(T t) {
		this.entityManager.persist(t);
		return t;
	}

	public T find(PK id) {
		return this.entityManager.find(entityClass, id);
	}

	public T update(T t) {
		return this.entityManager.merge(t);
	}

	public void delete(T t) {
		t = this.entityManager.merge(t);
		this.entityManager.remove(t);
	}
	
	public void refresh(T t) {
		this.entityManager.refresh(t);
	}
	
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void beginTransaction() {
		this.entityManager.getTransaction().begin();
		
	}

	public void commit() {
		this.entityManager.flush();
		this.entityManager.getTransaction().commit();
	}

	public void rollback() {
		this.entityManager.getTransaction().rollback();		
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
