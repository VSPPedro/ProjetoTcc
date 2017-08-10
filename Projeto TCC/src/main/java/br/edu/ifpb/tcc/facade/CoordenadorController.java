package br.edu.ifpb.tcc.facade;

import br.edu.ifpb.tcc.dao.AlunoDAO;
import br.edu.ifpb.tcc.dao.CoordenadorDAO;
import br.edu.ifpb.tcc.dao.PersistenceUtil;
import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Coordenador;
import br.edu.ifpb.tcc.util.PasswordUtil;

public class CoordenadorController {
	
	public CoordenadorController(){}
	
	public boolean cadastrar(Coordenador coordenador) {
		CoordenadorDAO dao= new CoordenadorDAO(PersistenceUtil.getCurrentEntityManager());
		Coordenador c = dao.findByLogin(coordenador.getEmail());
		if(c == null){
			coordenador.setSenha( PasswordUtil.encryptMD5(coordenador.getSenha()));
			dao.beginTransaction();
			dao.insert(coordenador);
			dao.commit();
			return true;
		}
		return false;
		
	}

}
