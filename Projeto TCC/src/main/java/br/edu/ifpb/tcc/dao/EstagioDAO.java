package br.edu.ifpb.tcc.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.tcc.entity.Estagio;
import br.edu.ifpb.tcc.entity.Oferta;
import br.edu.ifpb.tcc.entity.OfertaAluno;
import br.edu.ifpb.tcc.entity.Pessoa;
import br.edu.ifpb.tcc.entity.StatusEstagio;

public class EstagioDAO extends GenericDAO<Estagio, Integer> {
	
	public EstagioDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public EstagioDAO(EntityManager em) {
		super(em);
	}

	public List<Estagio> getEstagiosPendentes(){
		Query q = this.getEntityManager().createQuery("select e from Estagio e where e.status = :status");
		q.setParameter("status", StatusEstagio.PENDENTE_DE_APROVACAO);
		return q.getResultList();
	}
	
	public List<Estagio> getEstagiosAtivos(){
		Query q = this.getEntityManager().createQuery("select e from Estagio e where e.status = :status");
		q.setParameter("status", StatusEstagio.ATIVO);
		return q.getResultList();
	}

	public List<Estagio> findAllFromPessoa(Pessoa pessoa) {
		Query q = this.getEntityManager().createQuery("from Estagio e where e.empresa = :pessoa"); 
		q.setParameter("pessoa", pessoa);
		return q.getResultList();
	}
	
	public void criarEstagio(Oferta oferta){
		List<Estagio> estagios = new ArrayList<Estagio>();
		Estagio e;
		this.beginTransaction();
		for(OfertaAluno oa : oferta.getOfertaAlunos()){
			e = new Estagio();
			e.setAluno(oa.getAluno());
			e.setDataInicio(new Date());
			e.setEmpresa(oferta.getEmpresa());			

			estagios.add(e);
			this.insert(e);
		}
		this.commit();
		
	}
	
}
