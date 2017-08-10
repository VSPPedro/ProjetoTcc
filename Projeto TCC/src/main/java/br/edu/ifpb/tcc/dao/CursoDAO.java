package br.edu.ifpb.tcc.dao;

import javax.persistence.EntityManager;

import br.edu.ifpb.tcc.entity.Curso;

public class CursoDAO extends GenericDAO<Curso, Integer> {
	
	public CursoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public CursoDAO(EntityManager em) {
		super(em);
	}
	
}
