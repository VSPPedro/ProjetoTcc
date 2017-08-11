package br.edu.ifpb.tcc.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;


import br.edu.ifpb.tcc.dao.OfertaAlunoDAO;
import br.edu.ifpb.tcc.dao.ProfessorDAO;
import br.edu.ifpb.tcc.entity.Coordenador;
import br.edu.ifpb.tcc.entity.Professor;
import br.edu.ifpb.tcc.entity.Estagio;
import br.edu.ifpb.tcc.entity.Oferta;
import br.edu.ifpb.tcc.entity.OfertaAluno;
import br.edu.ifpb.tcc.facade.AlunoController;
import br.edu.ifpb.tcc.facade.CoordenadorController;
import br.edu.ifpb.tcc.facade.ProfessorController;
import br.edu.ifpb.tcc.facade.EstagioController;
import br.edu.ifpb.tcc.facade.OfertaController;

@ManagedBean(name="coordenadorBean")
@ViewScoped
public class CoordenadorBean extends GenericBean{
	
	private Integer id;
	
	private List<Professor> professores = new ArrayList<Professor>();
	
	private List<Oferta> ofertas = new ArrayList<Oferta>();
	
	private List<OfertaAluno> ofertaalunos = new ArrayList<OfertaAluno>();
	
	private List<Estagio> estagios = new ArrayList<Estagio>();

	private Professor professor = new Professor();
	
	private Coordenador coordenador = new Coordenador();
	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	public void selecionarProfessor(){
		ProfessorDAO prodao = new ProfessorDAO();
		this.professor = prodao.find(this.id);
	}
	
	public String cadastrarCoordenador(){
		CoordenadorController ctrl = new CoordenadorController();
		if(ctrl.cadastrar(coordenador)){
			this.addSuccessMessage("Cadastro feito com sucesso!");
			return "/login.jsf?faces-redirect=true"; 
		}
		this.addErrorMessage("Este email já está em uso.");
		return null;
		
	}
	
	public String salvarProfessor(){
		String proxView = null;
		try {
			ProfessorController ctrl = new ProfessorController();
			ctrl.salvar(this.professor);
			this.addSuccessMessage("Professor salvo com sucesso!");
			proxView = "/coordenador/listarEmpresas?faces-redirect=true";
			this.professor = new Professor();
		} catch (PersistenceException e) {
			this.addErrorMessage("Erro ao tentar salvar a Professor");
		}
		
		return proxView;
		
	}
	
	public void listarProfessores(){
		ProfessorDAO profdao = new ProfessorDAO();
		this.professores = profdao.findAll();
	}
	
	public void listarOfertasPendentes(){
		OfertaController ctrl = new OfertaController();
		this.ofertas = ctrl.buscarPendentes();
	}
	
	public void listarEstagiosPendentes(){
		EstagioController ctrl = new EstagioController();
		this.estagios = ctrl.getEstagiosPendentes();
	}	
	
	public void listarEstagiosAtivos(){
		EstagioController ctrl = new EstagioController();
		this.estagios = ctrl.getEstagiosAtivos();
	}
	
	public void aprovarOferta(Oferta oferta){
		OfertaController ctrl = new OfertaController();
		ctrl.aprovarOferta(oferta.getId());
		this.addSuccessMessage("Oferta aprovada com sucesso!");
	}
	
	public void aprovarEstagio(Estagio estagio){
		EstagioController ctrl = new EstagioController();
		ctrl.aprovarEstagio(estagio.getId());
		this.addSuccessMessage("Estagio aprovado com sucesso!");
	}
	
	public void negarEstagio(Estagio estagio){
		EstagioController ctrl = new EstagioController();
		ctrl.negarEstagio(estagio.getId());
		this.addSuccessMessage("Estagio foi negado com sucesso!");
	}
	
	public void fecharEstagio(Estagio estagio){
		EstagioController ctrl = new EstagioController();
		ctrl.fecharEstagio(estagio.getId());
		this.addSuccessMessage("Estagio foi finalizado com sucesso!");
	}
	
	public void bloquearProfessor(Professor professor){
		ProfessorController ctrl = new ProfessorController();
		ctrl.bloquearprofessor(professor.getId());
	}
	
	public void desbloquearProfessor(Professor professor){
		ProfessorController ctrl = new ProfessorController();
		ctrl.desbloquearprofessor(professor.getId());
	}
	
	public void listarAlunosSelecionados(){
		OfertaAlunoDAO ofaldao = new OfertaAlunoDAO();
		this.ofertaalunos = ofaldao.buscarOfertaAlunoSelecionados();
	}
	
	
	
	public List<Estagio> getEstagios() {
		return estagios;
	}

	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}

	public List<OfertaAluno> getOfertaalunos() {
		return ofertaalunos;
	}

	public void setOfertaalunos(List<OfertaAluno> ofertaalunos) {
		this.ofertaalunos = ofertaalunos;
	}

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}



	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
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

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}
	
	

}
