package br.edu.ifpb.tcc.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.tcc.entity.Empresa;
import br.edu.ifpb.tcc.entity.Pessoa;

public class EmpresaDAO extends GenericDAO<Empresa, Integer> {
	
	public EmpresaDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public EmpresaDAO(EntityManager em) {
		super(em);
	}

	public Empresa findByLogin(String email) {
		Query q = this.getEntityManager().createQuery("select e from Empresa e where e.email = :email");
		q.setParameter("email", email);
		Empresa e = null;
		try{
			e = (Empresa) q.getSingleResult();
		}catch(NoResultException exc){
			
		}
		return e;
	}
	
	public Empresa buscar(int id){
		Query q = this.getEntityManager().createQuery("select e from Empresa e where e.id = :id");
		q.setParameter("id", id);
		Empresa e = null;
		try{
			e = (Empresa) q.getSingleResult();
		}catch(NoResultException exc){
			System.out.println(exc.getMessage());
		}
		return e;
	}

	public Empresa bloquearEmpresa(int id) {
		Empresa emp = this.find(id);
		this.beginTransaction();
		emp.setAtiva(false);
		this.update(emp);
		this.commit();
		return emp;
		
	}
	
	public Empresa desbloquearEmpresa(int id) {
		Empresa emp = this.find(id);
		this.beginTransaction();
		emp.setAtiva(true);
		this.update(emp);
		this.commit();
		return emp;
		
	}

	public boolean isBloqueado(Pessoa pessoa) {
		Empresa e = this.find(pessoa.getId());
		if(!e.isAtiva()){
			return true;
		}
		return false;
	}
	
}
