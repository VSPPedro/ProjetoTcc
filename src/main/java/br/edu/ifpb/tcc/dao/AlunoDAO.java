package br.edu.ifpb.tcc.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.tcc.entity.Aluno;

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
			System.out.println(e.getMessage());	
		}
		return a;
	}
	
	public Aluno bloquearAluno(int id) {
		Aluno alu = this.find(id);
		this.beginTransaction();
		alu.setAtiva(false);
		this.update(alu);
		this.commit();
		return alu;
		
	}
	public Aluno desbloquearAluno(int id) {
		Aluno alu = this.find(id);
		this.beginTransaction();
		alu.setAtiva(true);
		this.update(alu);
		this.commit();
		return alu;
		
	}
}
