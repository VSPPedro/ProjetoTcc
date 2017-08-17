package br.edu.ifpb.tcc.bean;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.facade.AlunoController;

@ManagedBean(name="alunoBean")
@ViewScoped
public class AlunoBean extends GenericBean{
	
	private Integer id = 0;
	private boolean inscrito;
	
	private Aluno aluno = new Aluno();
	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	/*
	public void selecionarOferta(){
		OfertaDAO ofdao = new OfertaDAO();
		this.oferta = ofdao.find(this.id);
	}
	
	public void verificarInscrito(){
		OfertaAlunoDAO ofdao = new OfertaAlunoDAO();
		OfertaDAO ofdaoo = new OfertaDAO();
		this.inscrito = ofdao.alunoEstaInscrito((Aluno)this.loginBean.getPessoa(), ofdaoo.find(this.id));
	}
	
	public String inscreverAluno(){
		//OfertaController ofertaCtrl = new OfertaController();
		//ofertaCtrl.inscreverAluno(this.id, (Aluno)this.loginBean.getPessoa());

		FacesMessage msg = new FacesMessage("Inscrição Realizada com Sucesso");
		FacesContext fc= FacesContext.getCurrentInstance();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		fc.addMessage(null,msg);
		
		return null;
	}*/
	
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
