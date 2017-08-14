package br.edu.ifpb.tcc.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.tcc.entity.Professor;
import br.edu.ifpb.tcc.entity.Pessoa;

public class ProfessorDAO extends GenericDAO<Professor, Integer> {
	
	public ProfessorDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public ProfessorDAO(EntityManager em) {
		super(em);
	}

	public Professor findByLogin(String email) {
		Query q = this.getEntityManager().createQuery("select e from Professor e where e.email = :email");
		q.setParameter("email", email);
		Professor e = null;
		try{
			e = (Professor) q.getSingleResult();
		}catch(NoResultException exc){
			
		}
		return e;
	}
	
	public Professor buscar(int id){
		Query q = this.getEntityManager().createQuery("select e from Professor e where e.id = :id");
		q.setParameter("id", id);
		Professor e = null;
		try{
			e = (Professor) q.getSingleResult();
		}catch(NoResultException exc){
			System.out.println(exc.getMessage());
		}
		return e;
	}

	public Professor bloquearProfessor(int id) {
		Professor pro = this.find(id);
		this.beginTransaction();
		pro.setAtiva(false);
		this.update(pro);
		this.commit();
		return pro;
	}
	
	public Professor desbloquearProfessor(int id) {
		Professor emp = this.find(id);
		this.beginTransaction();
		emp.setAtiva(true);
		this.update(emp);
		this.commit();
		return emp;
		
	}

	public boolean isBloqueado(Pessoa pessoa) {
		Professor e = this.find(pessoa.getId());
		if(!e.isAtiva()){
			return true;
		}
		return false;
	}
	
}
