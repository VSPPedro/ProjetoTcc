package br.edu.ifpb.tcc.facade;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.tcc.dao.AlunoDAO;
import br.edu.ifpb.tcc.dao.ProfessorDAO;
import br.edu.ifpb.tcc.dao.OfertaAlunoDAO;
import br.edu.ifpb.tcc.dao.OfertaDAO;
import br.edu.ifpb.tcc.dao.PersistenceUtil;
import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Professor;
import br.edu.ifpb.tcc.entity.Oferta;
import br.edu.ifpb.tcc.entity.OfertaAluno;
import br.edu.ifpb.tcc.entity.Pessoa;
import br.edu.ifpb.tcc.entity.StatusAlunoOferta;

public class OfertaController {
	
	public List<Oferta> consultar(Pessoa pessoa){
		OfertaDAO dao = new OfertaDAO();
		List<Oferta> ofertas = dao.findAllFromPessoa(pessoa);
		return ofertas;
	}
	
	public Resultado aprovarOfertaAluno(int ofAlId){
		OfertaAlunoDAO ofaldao = new OfertaAlunoDAO(PersistenceUtil.getCurrentEntityManager());
		OfertaAluno oa = ofaldao.aprovaAluno(ofAlId);
		Resultado resultado = new Resultado();
		resultado.setEntidade(oa);
		return resultado;
	}
	
	public void cadastrar(Oferta oferta, Pessoa pessoa) {
		
		OfertaDAO dao= new OfertaDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		ProfessorDAO prodao = new ProfessorDAO();
		Professor professor = prodao.findByLogin(pessoa.getEmail());
		oferta.setProfessor(professor);
		if(oferta.getId() == null) {
			dao.insert(oferta);
		} else{
			dao.update(oferta);
		}
		dao.commit();	
	}
	
	public Oferta buscar(int id){
		OfertaDAO dao = new OfertaDAO(PersistenceUtil.getCurrentEntityManager());
		return dao.find(id);
	}

	public List<Oferta> consultarDisponiveis() {
		OfertaDAO dao = new OfertaDAO(PersistenceUtil.getCurrentEntityManager());
		return dao.findOfertasDisponiveis();
	}

	public Oferta inscreverAluno(int ofertaId, Pessoa pessoa) {
		OfertaAlunoDAO ofaldao = new OfertaAlunoDAO(PersistenceUtil.getCurrentEntityManager());
		OfertaDAO ofdao = new OfertaDAO(PersistenceUtil.getCurrentEntityManager());
		AlunoDAO aldao = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
		
		Oferta o = ofdao.find(ofertaId);
		
		Aluno a = aldao.find(pessoa.getId());
				
		OfertaAluno ofal = new OfertaAluno();
		ofal.setAluno(a);
		ofal.setOferta(o);
		ofal.setStatus(StatusAlunoOferta.PENDENTE_DE_APROVACAO);
		
		ofaldao.beginTransaction();
		ofaldao.insert(ofal);
		ofaldao.commit();
		return o;
		
	}

	public List<OfertaAluno> getOfertasAlunoInscritas(Pessoa pessoa) {
		OfertaAlunoDAO ofaldao = new OfertaAlunoDAO(PersistenceUtil.getCurrentEntityManager());
		List<OfertaAluno> ofal = ofaldao.ofertasAlunoFromAluno((Aluno)pessoa);
		return ofal;
	}
	
	public boolean alunoEstaInscrito(Pessoa pessoa, Oferta oferta){
		OfertaAlunoDAO ofaldao = new OfertaAlunoDAO(PersistenceUtil.getCurrentEntityManager());
		return ofaldao.alunoEstaInscrito((Aluno) pessoa, oferta);
	}

	public List<Oferta> buscarPendentes() {
		OfertaDAO dao = new OfertaDAO(PersistenceUtil.getCurrentEntityManager());
		return dao.findOfertasPendentes();
	}
	
	public List<Oferta> buscarOfertasFromAluno(int id) {
		OfertaAlunoDAO ofaldao = new OfertaAlunoDAO(PersistenceUtil.getCurrentEntityManager());
		AlunoDAO aldao = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
		Aluno a = aldao.find(id);
		List<OfertaAluno> ofal = ofaldao.ofertasAlunoFromAluno(a);
		List<Oferta> ofertas = new ArrayList<Oferta>();
		for(OfertaAluno oa : ofal){
			ofertas.add(oa.getOferta());
		}
		return ofertas;
	}

	public Oferta aprovarOferta(int id) {
		OfertaDAO dao = new OfertaDAO(PersistenceUtil.getCurrentEntityManager());
		Oferta o = dao.find(id);
		return dao.aprovarOferta(o);
		
	}
}

