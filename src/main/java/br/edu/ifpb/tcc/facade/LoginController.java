package br.edu.ifpb.tcc.facade;

import br.edu.ifpb.tcc.dao.PessoaDAO;
import br.edu.ifpb.tcc.entity.Pessoa;
import br.edu.ifpb.tcc.util.PasswordUtil;

public class LoginController {

	public Pessoa isValido(String login, String passwd) {
		
		PessoaDAO pdao = new PessoaDAO();
		Pessoa p = pdao.findByLogin(login);
		
		if(p != null && p.getSenha().equals(PasswordUtil.encryptMD5(passwd))){
			return p;
		}
		return null;
	}

}
