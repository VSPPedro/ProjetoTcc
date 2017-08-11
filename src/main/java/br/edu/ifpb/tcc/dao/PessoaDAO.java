package br.edu.ifpb.tcc.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Coordenador;
import br.edu.ifpb.tcc.entity.Professor;
import br.edu.ifpb.tcc.entity.Pessoa;

public class PessoaDAO extends GenericDAO<Pessoa, Integer> {
	
	public PessoaDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public PessoaDAO(EntityManager em) {
		super(em);
	}

	public Pessoa findByLogin(String email) {
		EntityManager em = this.getEntityManager();
		if(em == null){
			System.out.println("entity manager NULO!!! AFFFS");
			return null;
		}
		Query q = this.getEntityManager().createQuery("select p from Pessoa p where p.email = :email");
		q.setParameter("email", email);
		Pessoa p = null;
		try{
			p = (Pessoa) q.getSingleResult();
		}catch(NoResultException e){
			
		}
		return p;
	}
	
	public Professor findProfessorById(int id){
		Query q = this.getEntityManager().createQuery("select p from Pessoa p where p.id = :id and p.tipo = 'P'");
		q.setParameter("id", id);
		Professor e = null;
		try{
			e = (Professor) q.getSingleResult();
		}catch(NoResultException exc){
			System.out.println(exc.getMessage());
		}
		return e;
	}
	
	public Aluno findAlunoById(int id){
		Query q = this.getEntityManager().createQuery("select p from Pessoa p where p.id = :id and p.tipo = 'A'");
		q.setParameter("id", id);
		Aluno a = null;
		try{
			a = (Aluno) q.getSingleResult();
		}catch(NoResultException exc){
			System.out.println(exc.getMessage());
		}
		return a;
	}
	
	public Coordenador findCoordenadorById(int id){
		Query q = this.getEntityManager().createQuery("select p from Pessoa p where p.id = :id and p.tipo = 'C'");
		q.setParameter("id", id);
		Coordenador c = null;
		try{
			c = (Coordenador) q.getSingleResult();
		}catch(NoResultException exc){
			System.out.println(exc.getMessage());
		}
		return c;
	}
	
}
