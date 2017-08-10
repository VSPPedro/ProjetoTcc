package br.edu.ifpb.tcc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Oferta;
import br.edu.ifpb.tcc.entity.OfertaAluno;
import br.edu.ifpb.tcc.entity.StatusAlunoOferta;
import br.edu.ifpb.tcc.entity.StatusOferta;

public class OfertaAlunoDAO extends GenericDAO<OfertaAluno, Integer> {
	
	public OfertaAlunoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public OfertaAlunoDAO(EntityManager em) {
		super(em);
	}
	
	public OfertaAluno aprovaAluno(int ofAlId){
		OfertaAluno oa = this.find(ofAlId);
		oa.setStatus(StatusAlunoOferta.APROVADO);
		this.beginTransaction();
		oa = this.update(oa);
		this.commit();
		return oa;
	}
	
	public List<OfertaAluno> ofertasAlunoFromAluno(Aluno aluno){
		Query q = this.getEntityManager().createQuery("from OfertaAluno oa where oa.aluno = :aluno"); 
		q.setParameter("aluno", aluno);
		return q.getResultList();
	}

	public boolean alunoEstaInscrito(Aluno aluno, Oferta oferta) {
		Query q = this.getEntityManager().createQuery("from OfertaAluno oa where oa.aluno = :aluno and oa.oferta = :oferta"); 
		q.setParameter("aluno", aluno);
		q.setParameter("oferta", oferta);
		return !q.getResultList().isEmpty();
	}
	
	public List<Aluno> buscarAlunosSelecionados() {
		Query q = this.getEntityManager().createQuery("from OfertaAluno oa where oa.status = :status"); 
		q.setParameter("status", StatusAlunoOferta.APROVADO);
		List<OfertaAluno> oas = q.getResultList();
		List<Aluno> alunos = new ArrayList<Aluno>();
		for(OfertaAluno oa : oas){
			alunos.add(oa.getAluno());
		}
		return alunos;
	}
	
	public List<OfertaAluno> buscarOfertaAlunoSelecionados() {
		Query q = this.getEntityManager().createQuery("from OfertaAluno oa where oa.status = :status"); 
		q.setParameter("status", StatusAlunoOferta.APROVADO);
		return q.getResultList();
	}
	
	
}
