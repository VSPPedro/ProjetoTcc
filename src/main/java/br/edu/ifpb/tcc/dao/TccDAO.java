package br.edu.ifpb.tcc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Pessoa;
import br.edu.ifpb.tcc.entity.Professor;
import br.edu.ifpb.tcc.entity.StatusTcc;
import br.edu.ifpb.tcc.entity.Tcc;

public class 	TccDAO extends GenericDAO<Tcc, Integer> {
	
	public TccDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public TccDAO(EntityManager em) {
		super(em);
	}

	public List<Tcc> getTccsPendentes(){
		Query q = this.getEntityManager().createQuery("select t from Tcc t where t.status = :status");
		q.setParameter("status", StatusTcc.PENDENTE_DE_APROVACAO);
		return q.getResultList();
	}
	
	public List<Tcc> getTccsAtivos(){
		Query q = this.getEntityManager().createQuery("select t from Tcc t where t.status = :status");
		q.setParameter("status", StatusTcc.ATIVO);
		return q.getResultList();
	}

	public List<Tcc> findAllFromPessoa(Pessoa pessoa) {
		Query q = this.getEntityManager().createQuery("from Tcc t where t.professor = :pessoa"); 
		q.setParameter("pessoa", pessoa);
		return q.getResultList();
	}
	
	public Tcc findTccByTitulo(String titulo) {
		Query q = this.getEntityManager().createQuery("from Tcc t where t.titulo = :titulo"); 
		q.setParameter("titulo", titulo);
		Tcc tcc = null;
		
		try{
			tcc = (Tcc) q.getSingleResult();
		}catch(NoResultException exc){
			System.out.println(exc.getMessage());
		}
		
		return tcc;
	}
	
	public Tcc findTccFromPessoa(Pessoa pessoa) {
		Query q = this.getEntityManager().createQuery("from Tcc t where t.aluno = :pessoa"); 
		q.setParameter("pessoa", pessoa);
		Tcc tcc = null;
		
		try{
			tcc = (Tcc) q.getSingleResult();
		}catch(NoResultException exc){
			System.out.println(exc.getMessage());
		}
		
		return tcc;
	}
}
