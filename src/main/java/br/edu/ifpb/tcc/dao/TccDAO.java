package br.edu.ifpb.tcc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.tcc.entity.Tcc;
import br.edu.ifpb.tcc.entity.Oferta;
import br.edu.ifpb.tcc.entity.OfertaAluno;
import br.edu.ifpb.tcc.entity.Pessoa;
import br.edu.ifpb.tcc.entity.StatusTcc;

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
	
	public void criarTcc(Oferta oferta){
		List<Tcc> Tccs = new ArrayList<Tcc>();
		Tcc t;
		this.beginTransaction();
		for(OfertaAluno oa : oferta.getOfertaAlunos()){
			t = new Tcc();
			t.setAluno(oa.getAluno());
			t.setDataInicio(new Date());
		    t.setProfessor(oferta.getProfessor());
		    t.setDescricao(oferta.getDescricao());
		    t.setTitulo(oferta.getTitulo());

			Tccs.add(t);
			this.insert(t);
		}
		this.commit();
		
	}
	
}
