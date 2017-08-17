package br.edu.ifpb.tcc.bean;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Tcc;

@ManagedBean(name="tccBean")
@ViewScoped
public class TccBean extends GenericBean{
	
	private Integer id = 0;
	private boolean inscrito;
	
	private Aluno aluno = new Aluno();
	private Tcc tcc = new Tcc();
	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	public String detalhesTcc(){
		
		this.aluno = (Aluno)this.loginBean.getPessoa();
		this.tcc = this.aluno.getTcc();
		
		return "/aluno/meuTcc?faces-redirect=true";
	}
	
	public Tcc getTcc() {
		return tcc;
	}

	public void setTcc(Tcc tcc) {
		this.tcc = tcc;
	}
	
	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
   
	public boolean isInscrito() {
		return inscrito;
	}

	public void setInscrito(boolean inscrito) {
		this.inscrito = inscrito;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
