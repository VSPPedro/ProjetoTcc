package br.edu.ifpb.tcc.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import br.edu.ifpb.tcc.dao.TccDAO;
import br.edu.ifpb.tcc.dao.OfertaAlunoDAO;
import br.edu.ifpb.tcc.dao.OfertaDAO;
import br.edu.ifpb.tcc.entity.Professor;
import br.edu.ifpb.tcc.entity.Tcc;
import br.edu.ifpb.tcc.entity.Oferta;
import br.edu.ifpb.tcc.entity.OfertaAluno;
import br.edu.ifpb.tcc.entity.StatusAlunoOferta;
import br.edu.ifpb.tcc.facade.ProfessorController;
import br.edu.ifpb.tcc.facade.OfertaController;

@ManagedBean(name="ProfessorBean")
@ViewScoped
public class ProfessorBean extends GenericBean{
	
	private Integer id;

	private List<Oferta> ofertas = new ArrayList<Oferta>();
	
	private List<Tcc> tccs = new ArrayList<Tcc>();
	
	private Oferta oferta = new Oferta();
	
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
	
	public String listarOfertas(){
		OfertaDAO ofdao = new OfertaDAO();
		this.ofertas = ofdao.findAllFromPessoa(this.loginBean.getPessoa());
		System.out.println("Ofertas encontradas: "+ this.ofertas.size());
		return "empresa/mostrarOfertas";
	}
	
	public void listarTccs(){
		TccDAO tdao = new TccDAO();
		this.tccs = tdao.findAllFromPessoa(this.loginBean.getPessoa());
	}
	
	public String cadastrarOferta(){
		String proxView = null;
		try {
			OfertaController controller = new OfertaController();
			controller.cadastrar(this.oferta, this.loginBean.getPessoa());
			this.addSuccessMessage("Oferta salva com sucesso!");
			proxView = "/empresa/mostrarOfertas?faces-redirect=true";
			this.oferta = new Oferta();
		} catch (PersistenceException e) {
			this.addErrorMessage("Erro ao tentar salvar a Oferta");
		}
		
		return proxView;
	
	}
	
	public void selecionarOferta(){
		OfertaDAO ofdao = new OfertaDAO();
		this.oferta = ofdao.find(this.id);
	}
	
	public void selecionarTcc(){
		TccDAO tdao = new TccDAO();
		this.tcc = tdao.find(this.id);
	}
	
	public String aprovarAluno(OfertaAluno ofal){
		OfertaAlunoDAO ofaldao = new OfertaAlunoDAO();
		OfertaAluno oa = ofaldao.find(ofal.getId());
		System.out.println("Nome do aluno da oferta: "+oa.getAluno().getNome());
		ofaldao.beginTransaction();
		oa.setStatus(StatusAlunoOferta.APROVADO);
		ofaldao.update(oa);
		ofaldao.commit();
		return "/empresa/oferta?faces-redirect=true&id="+ofal.getOferta().getId();
	}
	
	public void fecharInscricoes(){
		OfertaDAO ofdao = new OfertaDAO();
		ofdao.fecharInscricoes(this.oferta.getId());
		this.addSuccessMessage("Esta oferta foi fechada!");
	}
	
	public void solicitarAprovacaoTcc(){
		TccDAO tdao = new TccDAO();
		tdao.criarTcc(this.oferta);
		OfertaDAO ofdao = new OfertaDAO();
		ofdao.concluirOferta(this.oferta.getId());
		this.addSuccessMessage("Solicitação de Tcc foi enviada para a Coordenação!");
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

	public Oferta getOferta() {
		return oferta;
	}



	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}



	public LoginBean getLoginBean() {
		return loginBean;
	}



	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}



	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
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
