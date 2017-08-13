package br.edu.ifpb.tcc.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import br.edu.ifpb.tcc.dao.AlunoDAO;
import br.edu.ifpb.tcc.dao.OfertaAlunoDAO;
import br.edu.ifpb.tcc.dao.ProfessorDAO;
import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Coordenador;
import br.edu.ifpb.tcc.entity.Professor;
import br.edu.ifpb.tcc.entity.Tcc;
import br.edu.ifpb.tcc.entity.Oferta;
import br.edu.ifpb.tcc.entity.OfertaAluno;
import br.edu.ifpb.tcc.facade.AlunoController;
import br.edu.ifpb.tcc.facade.CoordenadorController;
import br.edu.ifpb.tcc.facade.ProfessorController;
import br.edu.ifpb.tcc.facade.TccController;
import br.edu.ifpb.tcc.facade.OfertaController;

@ManagedBean(name="coordenadorBean")
@ViewScoped
public class CoordenadorBean extends GenericBean{
	
	private Integer id;
	
	private List<Professor> professores = new ArrayList<Professor>();
	
	private List<Oferta> ofertas = new ArrayList<Oferta>();
	
	private List<OfertaAluno> ofertaalunos = new ArrayList<OfertaAluno>();
	
	private List<Tcc> tccs = new ArrayList<Tcc>();

	private Professor professor = new Professor();
	
	private Coordenador coordenador = new Coordenador();
	
	private Aluno aluno = new Aluno();
	
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	public void selecionarProfessor(){
		ProfessorDAO prodao = new ProfessorDAO();
		this.professor = prodao.find(this.id);
	}
	public void selecionarAluno(){
		AlunoDAO aludao = new AlunoDAO();
		this.aluno = aludao.find(this.id);
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
	
	public void listarAlunos(){
		AlunoDAO aludao = new AlunoDAO();
		this.alunos = aludao.findAll();
	}
	
	public void listarOfertasPendentes(){
		OfertaController ctrl = new OfertaController();
		this.ofertas = ctrl.buscarPendentes();
	}
	
	public void listarTccsPendentes(){
		TccController ctrl = new TccController();
		this.tccs = ctrl.getTccsPendentes();
	}	
	
	public void listarTccsAtivos(){
		TccController ctrl = new TccController();
		this.tccs = ctrl.getTccsAtivos();
	}
	
	public void aprovarOferta(Oferta oferta){
		OfertaController ctrl = new OfertaController();
		ctrl.aprovarOferta(oferta.getId());
		this.addSuccessMessage("Oferta aprovada com sucesso!");
	}
	
	public void aprovarTcc(Tcc tcc){
		TccController ctrl = new TccController();
		ctrl.aprovarTcc(tcc.getId());
		this.addSuccessMessage("Tcc aprovado com sucesso!");
	}
	
	public void negarTcc(Tcc tcc){
		TccController ctrl = new TccController();
		ctrl.negarTcc(tcc.getId());
		this.addSuccessMessage("Tcc foi negado com sucesso!");
	}
	
	public void fecharTcc(Tcc tcc){
		TccController ctrl = new TccController();
		ctrl.fecharTcc(tcc.getId());
		this.addSuccessMessage("Tcc foi finalizado com sucesso!");
	}
	
	public void bloquearProfessor(Professor professor){
		ProfessorController ctrl = new ProfessorController();
		ctrl.bloquearprofessor(professor.getId());
	}
	
	public void desbloquearProfessor(Professor professor){
		ProfessorController ctrl = new ProfessorController();
		ctrl.desbloquearprofessor(professor.getId());
	}
	
	public void bloquearAluno(Aluno aluno){
		AlunoController ctrl = new AlunoController();
		ctrl.bloquearaluno(aluno.getId());
	}
	
	public void desbloquearAluno(Aluno aluno){
		AlunoController ctrl = new AlunoController();
		ctrl.desbloquearaluno(aluno.getId());
	}
	
	
	public void listarAlunosSelecionados(){
		OfertaAlunoDAO ofaldao = new OfertaAlunoDAO();
		this.ofertaalunos = ofaldao.buscarOfertaAlunoSelecionados();
	}
	
	
	


	public List<Tcc> getTccs() {
		return tccs;
	}
	public void setTccs(List<Tcc> tccs) {
		this.tccs = tccs;
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
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public List<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	

}
