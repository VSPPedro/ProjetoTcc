package br.edu.ifpb.tcc.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.edu.ifpb.tcc.dao.AlunoDAO;
import br.edu.ifpb.tcc.dao.EstagioDAO;
import br.edu.ifpb.tcc.dao.OfertaAlunoDAO;
import br.edu.ifpb.tcc.dao.PersistenceUtil;
import br.edu.ifpb.tcc.dao.ProfessorDAO;
import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Curso;
import br.edu.ifpb.tcc.entity.Estagio;
import br.edu.ifpb.tcc.entity.Pessoa;
import br.edu.ifpb.tcc.entity.Professor;
import br.edu.ifpb.tcc.util.PasswordUtil;

public class AlunoController {
	private Aluno aluno;
	private List<Mensagem> mensagensErro;
	
//	public List<Aluno> consultar(Pessoa pessoa){
//		OfertaDAO dao = new OfertaDAO();
//		List<Oferta> ofertas = dao.findAllFromPessoa(pessoa);
//		return ofertas;
//	}
	
//	public EmpresaController(){}
	
	
	
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

	
//	public Resultado remove(Map<String, String[]> parametros){
//		Resultado resultado= new Resultado();
//		EmpresaDAO dao = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
//		dao.beginTransaction();
//		String[] selecionadosform = parametros.get("del_selected");
//		try{
//			for(String s : selecionadosform){
//				Contato c = dao.find(Integer.parseInt(s));
//				dao.delete(c);
//			}
//			resultado.setErro(false);
//			resultado.setMensagens(Collections.singletonList(new Mensagem("Contatos removidos com sucesso", Categoria.INFO)));
//		}catch(Exception exc){
//			resultado.setEntidade(this.contato);
//			resultado.setErro(true);
//			resultado.setMensagens(this.mensagensErro);
//		}
//		dao.commit();
//		
//		return resultado;
//	}
}

