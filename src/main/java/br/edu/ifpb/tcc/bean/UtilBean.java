package br.edu.ifpb.tcc.bean;

import java.util.List;

import br.edu.ifpb.tcc.dao.PersistenceUtil;
import br.edu.ifpb.tcc.dao.PessoaDAO;
import br.edu.ifpb.tcc.entity.Pessoa;

public class UtilBean {
	public List<Pessoa> getPessoas() {
		PessoaDAO dao= new PessoaDAO(PersistenceUtil.getCurrentEntityManager());
		List<Pessoa> pessoas= dao.findAll();
		return pessoas;
		}
	}