package br.edu.ifpb.tcc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Oferta;
import br.edu.ifpb.tcc.facade.OfertaController;

public class AlunoDAO extends GenericDAO<Aluno, Integer> {
	
	public AlunoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public AlunoDAO(EntityManager em) {
		super(em);
	}

	public Aluno findByLogin(String email) {
		Query q = this.getEntityManager().createQuery("select a from Aluno a where a.email = :email");
		q.setParameter("email", email);
		Aluno a = null;
		try{
			a = (Aluno) q.getSingleResult();
		}catch(NoResultException e){
			
		}
		return a;
	}
	
//	public List<Aluno> getAlunosFromOferta(Oferta oferta){
//		Query q = this.getEntityManager().createQuery("select a from Aluno a where a.");
//		
//	}
	
}
