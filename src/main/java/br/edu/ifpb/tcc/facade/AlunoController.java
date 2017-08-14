package br.edu.ifpb.tcc.facade;

import java.util.List;

import br.edu.ifpb.tcc.dao.AlunoDAO;
import br.edu.ifpb.tcc.dao.OfertaAlunoDAO;
import br.edu.ifpb.tcc.dao.PersistenceUtil;
import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.util.PasswordUtil;

public class AlunoController {
	private Aluno aluno;
	private List<Mensagem> mensagensErro;
	
	public boolean cadastrar(Aluno aluno) {
		AlunoDAO dao= new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
		Aluno a = dao.findByLogin(aluno.getEmail());
		if(a == null){
			aluno.setSenha( PasswordUtil.encryptMD5(aluno.getSenha()));
			dao.beginTransaction();
			dao.insert(aluno);
			dao.commit();
			return true;
		}
		return false;
	}
	
	public Aluno buscar(int id){
		AlunoDAO dao = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
		return dao.find(id);
	}

	public List<Aluno> buscarAlunosSelecionados() {
		OfertaAlunoDAO ofaldao = new OfertaAlunoDAO(PersistenceUtil.getCurrentEntityManager());
		List<Aluno> listaaluno = ofaldao.buscarAlunosSelecionados();
		return listaaluno;
	}

	
	public Aluno bloquearaluno(int id) {
		AlunoDAO dao = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
		Aluno alu = dao.bloquearAluno(id);
		return alu;
	}
	public Aluno desbloquearaluno(int id) {
		AlunoDAO dao = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
		Aluno alu = dao.desbloquearAluno(id);
		return alu;
	}
}

