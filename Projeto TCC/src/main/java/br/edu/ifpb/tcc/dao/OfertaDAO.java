package br.edu.ifpb.tcc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Oferta;
import br.edu.ifpb.tcc.entity.Pessoa;
import br.edu.ifpb.tcc.entity.StatusOferta;

public class OfertaDAO extends GenericDAO<Oferta, Integer> {
	
	public OfertaDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public OfertaDAO(EntityManager em) {
		super(em);
	}
	
	public List<Oferta> findAllFromPessoa(Pessoa pessoa) {
		Query q = this.getEntityManager().createQuery("from Oferta o where o.empresa = :pessoa"); 
		q.setParameter("pessoa", pessoa);
		return q.getResultList();
	}
	
	public List<Oferta> findOfertasDisponiveis(){
		Query q = this.getEntityManager().createQuery("from Oferta o where o.status = :st"); 
		q.setParameter("st", StatusOferta.APROVADO);
		return q.getResultList();
	}

	public List<Oferta> findOfertasPendentes() {
		Query q = this.getEntityManager().createQuery("from Oferta o where o.status = :st"); 
		q.setParameter("st", StatusOferta.PENDENTE_DE_APROVACAO);
		return q.getResultList();
	}

	public Oferta aprovarOferta(Oferta o) {
		this.beginTransaction();
		o.setStatus(StatusOferta.APROVADO);
		o = this.update(o);
		this.commit();
		return o;
		
	}

	public Oferta fecharInscricoes(Integer id) {
		Oferta o = this.find(id);
		this.beginTransaction();
		o.setStatus(StatusOferta.FINALIZADO);
		this.update(o);
		this.commit();
		return o;
	}

	public Oferta concluirOferta(Integer id) {
		Oferta o = this.find(id);
		this.beginTransaction();
		o.setStatus(StatusOferta.CONCLUIDO);
		this.update(o);
		this.commit();
		return o;
	}
	
	
	



	
}
