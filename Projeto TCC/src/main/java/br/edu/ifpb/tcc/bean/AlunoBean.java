package br.edu.ifpb.tcc.bean;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.ifpb.tcc.dao.OfertaAlunoDAO;
import br.edu.ifpb.tcc.dao.OfertaDAO;
import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Oferta;
import br.edu.ifpb.tcc.entity.OfertaAluno;
import br.edu.ifpb.tcc.facade.AlunoController;
import br.edu.ifpb.tcc.facade.OfertaController;





@ManagedBean(name="alunoBean")
@ViewScoped
public class AlunoBean extends GenericBean{
	
	private Integer id = 0;
	private boolean inscrito;
	
	private Aluno aluno = new Aluno();
	
	private List<Oferta> ofertasDisponiveis = new ArrayList<Oferta>();
	private List<OfertaAluno> ofertas = new ArrayList<OfertaAluno>();
	private Oferta oferta = new Oferta();
	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	public String listarMinhasOfertas(){
		OfertaAlunoDAO ofdao = new OfertaAlunoDAO();
		this.ofertas = ofdao.ofertasAlunoFromAluno((Aluno)this.loginBean.getPessoa());
		return "/aluno/minhasInscricoes?faces-redirect=true";
	}
	
	public String listarOfertasDisponiveis(){
		OfertaDAO ofdao = new OfertaDAO();
		this.ofertasDisponiveis = ofdao.findOfertasDisponiveis();
		return "/aluno/mostrarOfertasDisponiveis?faces-redirect=true";
	}
	
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
		OfertaController ofertaCtrl = new OfertaController();
		ofertaCtrl.inscreverAluno(this.id, (Aluno)this.loginBean.getPessoa());

		FacesMessage msg = new FacesMessage("Inscrição Realizada com Sucesso");
		FacesContext fc= FacesContext.getCurrentInstance();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		fc.addMessage(null,msg);
		
		return null;
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

	public List<OfertaAluno> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<OfertaAluno> ofertas) {
		this.ofertas = ofertas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
   
	public boolean isInscrito() {
		return inscrito;
	}
	
	public List<Oferta> getOfertasDisponiveis() {
		return ofertasDisponiveis;
	}

	public void setOfertasDisponiveis(List<Oferta> ofertasDisponiveis) {
		this.ofertasDisponiveis = ofertasDisponiveis;
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
