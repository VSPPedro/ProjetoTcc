package br.edu.ifpb.tcc.bean;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import br.edu.ifpb.tcc.dao.AlunoDAO;
import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Tcc;
import br.edu.ifpb.tcc.facade.AlunoController;
import br.edu.ifpb.tcc.facade.TccController;


@ManagedBean(name="alunoBean")
@ViewScoped
public class AlunoBean extends GenericBean{
	
	private Integer id = 0;
	private boolean inscrito;
	
	private Aluno aluno = new Aluno();

	private Tcc tcc = new Tcc();
	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	@PostConstruct
	private void init() {
		this.aluno = (Aluno) loginBean.getPessoa();
		
		if (this.aluno.getTcc() == null) {
			this.aluno.setTcc(new Tcc());
		}
	}
	
	public Tcc getTcc() {
		return tcc;
	}

	public void setTcc(Tcc tcc) {
		this.tcc = tcc;
	}
	
	public String cadastrarAluno(){
		AlunoController ctrl = new AlunoController();
		if(ctrl.cadastrar(aluno)){
			this.addSuccessMessage("Cadastro feito com sucesso!");
			return "/login.jsf?faces-redirect=true";
		}
		this.addErrorMessage("Este email já está em uso.");
		return null;
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
