package br.edu.ifpb.tcc.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.tcc.dao.TccDAO;
import br.edu.ifpb.tcc.entity.Professor;
import br.edu.ifpb.tcc.entity.Tcc;
import br.edu.ifpb.tcc.facade.ProfessorController;

@ManagedBean(name="ProfessorBean")
@ViewScoped
public class ProfessorBean extends GenericBean{
	
	private Integer id;
	
	private List<Tcc> tccs = new ArrayList<Tcc>();
	
	private Tcc tcc = new Tcc();
	
	private Professor professor = new Professor();

	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	public String cadastrarProfessor(){
		ProfessorController ctrl = new ProfessorController();
		
		if(ctrl.cadastrar(professor)){
			this.addSuccessMessage("Cadastro feito com sucesso!");
			return "/login.jsf?faces-redirect=true";
		}
		this.addErrorMessage("Este email já está em uso.");
		return null;
	}
	
	public void listarTccs(){
		TccDAO tdao = new TccDAO();
		this.tccs = tdao.findAllFromPessoa(this.loginBean.getPessoa());
	}
	
	public void selecionarTcc(){
		TccDAO tdao = new TccDAO();
		this.tcc = tdao.find(this.id);
	}

	public Tcc getEstagio() {
		return tcc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Tcc> getTccs() {
		return tccs;
	}

	public void setTccs(List<Tcc> tccs) {
		this.tccs = tccs;
	}

	public Tcc getTcc() {
		return tcc;
	}

	public void setTcc(Tcc tcc) {
		this.tcc = tcc;
	}
}
