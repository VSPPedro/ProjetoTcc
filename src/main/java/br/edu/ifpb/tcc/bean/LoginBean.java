package br.edu.ifpb.tcc.bean;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpb.tcc.entity.Pessoa;
import br.edu.ifpb.tcc.facade.LoginController;

@ManagedBean
@SessionScoped
public class LoginBean extends GenericBean {
	private String login;
	private String senha;
	private Pessoa pessoa;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public String autenticar(){
		String proxPagina = null;
		LoginController ctrl = new LoginController();
		pessoa = ctrl.isValido(login, senha);
		if(pessoa != null){
			this.setValueOf("#{sessionScope.loginUser}", String.class, pessoa.getEmail());
			proxPagina = "index";
		}else{
			FacesMessage msg = new FacesMessage("Login ou senha inválidos.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return proxPagina;
	}
	
	public String logout(){
		this.invalidateSession();
		return "/login?faces-redirect=true";
	}
	
}
