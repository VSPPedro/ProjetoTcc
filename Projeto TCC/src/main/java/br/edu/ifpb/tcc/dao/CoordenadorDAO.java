package br.edu.ifpb.tcc.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Coordenador;



public class CoordenadorDAO extends GenericDAO<Coordenador, Integer>{
	
	//teste
	public CoordenadorDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}
	public CoordenadorDAO(EntityManager em) {
		super(em);
	}
	public Coordenador findByLogin(String email) {
		Query q = this.getEntityManager().createQuery("select c from Coordenador c where c.email = :email");
		q.setParameter("email", email);
		Coordenador c = null;
		try{
			c = (Coordenador) q.getSingleResult();
		}catch(NoResultException e){
			
		}
		return c;
	}
	
}
