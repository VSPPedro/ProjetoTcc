package br.edu.ifpb.tcc.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.tcc.entity.Pessoa;

@ManagedBean (name="pessoaBean")
@SessionScoped
public class PessoaBean {
	private Pessoa pessoa;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
}
