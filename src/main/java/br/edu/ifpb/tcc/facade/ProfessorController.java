package br.edu.ifpb.tcc.facade;

import java.util.List;

import br.edu.ifpb.tcc.dao.CoordenadorDAO;
import br.edu.ifpb.tcc.dao.ProfessorDAO;
import br.edu.ifpb.tcc.dao.PersistenceUtil;
import br.edu.ifpb.tcc.dao.PessoaDAO;
import br.edu.ifpb.tcc.entity.Coordenador;
import br.edu.ifpb.tcc.entity.Professor;
import br.edu.ifpb.tcc.entity.Pessoa;
import br.edu.ifpb.tcc.util.PasswordUtil;

public class ProfessorController {

	public List<Professor> consultar(){
		ProfessorDAO dao = new ProfessorDAO(PersistenceUtil.getCurrentEntityManager());
		List<Professor> professores = dao.findAll();
		return professores;
	}
	
	public ProfessorController(){}
	
	public boolean cadastrar(Professor professor) {
		ProfessorDAO dao= new ProfessorDAO(PersistenceUtil.getCurrentEntityManager());
		Professor e = dao.findByLogin(professor.getEmail());
		if(e == null){
			professor.setSenha( PasswordUtil.encryptMD5(professor.getSenha()));
			dao.beginTransaction();
			dao.insert(professor);
			dao.commit();
			return true;
		}
		return false;
		
	}
	
	public boolean isBloqueado(Pessoa pessoa){
		ProfessorDAO prodao = new ProfessorDAO(PersistenceUtil.getCurrentEntityManager());
		return prodao.isBloqueado(pessoa);
	}
	
	public void salvar(Professor professor) {
		
		ProfessorDAO dao= new ProfessorDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		if(professor.getId() == null) {
			dao.insert(professor);
		} else{
			if(professor.getSenha().isEmpty()){
				//ISSUE HERE - PASSWORD SET
				System.out.println("Senha tá vazia: "+professor.getSenha());
				PessoaDAO pdao = new PessoaDAO();
				Pessoa pro = pdao.findByLogin(professor.getEmail());
				System.out.println("Senha: "+pro.getSenha());
				professor.setSenha(pro.getSenha());
			}else{
				System.out.println("Senha não tá vazia: "+professor.getSenha());
				professor.setSenha(PasswordUtil.encryptMD5(professor.getSenha()));
			}
			dao.update(professor);
		}
		dao.commit();
			
	}
	
	public Professor buscar(int id){
		ProfessorDAO dao = new ProfessorDAO(PersistenceUtil.getCurrentEntityManager());
		return dao.find(id);
	}

	public Professor bloquearprofessor(int id) {
		ProfessorDAO dao = new ProfessorDAO(PersistenceUtil.getCurrentEntityManager());
		Professor pro = dao.bloquearProfessor(id);
		return pro;
	}
	
	public Professor desbloquearprofessor(int id) {
		ProfessorDAO dao = new ProfessorDAO(PersistenceUtil.getCurrentEntityManager());
		Professor pro = dao.desbloquearProfessor(id);
		return pro;
	}

	
}

