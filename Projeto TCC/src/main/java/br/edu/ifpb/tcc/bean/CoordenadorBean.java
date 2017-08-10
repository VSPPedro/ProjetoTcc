package br.edu.ifpb.tcc.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import br.edu.ifpb.tcc.dao.EmpresaDAO;
import br.edu.ifpb.tcc.dao.OfertaAlunoDAO;
import br.edu.ifpb.tcc.entity.Coordenador;
import br.edu.ifpb.tcc.entity.Empresa;
import br.edu.ifpb.tcc.entity.Estagio;
import br.edu.ifpb.tcc.entity.Oferta;
import br.edu.ifpb.tcc.entity.OfertaAluno;
import br.edu.ifpb.tcc.facade.AlunoController;
import br.edu.ifpb.tcc.facade.CoordenadorController;
import br.edu.ifpb.tcc.facade.EmpresaController;
import br.edu.ifpb.tcc.facade.EstagioController;
import br.edu.ifpb.tcc.facade.OfertaController;

@ManagedBean(name="coordenadorBean")
@ViewScoped
public class CoordenadorBean extends GenericBean{
	
	private Integer id;
	
	private List<Empresa> empresas = new ArrayList<Empresa>();
	
	private List<Oferta> ofertas = new ArrayList<Oferta>();
	
	private List<OfertaAluno> ofertaalunos = new ArrayList<OfertaAluno>();
	
	private List<Estagio> estagios = new ArrayList<Estagio>();

	private Empresa empresa = new Empresa();
	
	private Coordenador coordenador = new Coordenador();
	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	public void selecionarEmpresa(){
		EmpresaDAO empdao = new EmpresaDAO();
		this.empresa = empdao.find(this.id);
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
	
	public String salvarEmpresa(){
		String proxView = null;
		try {
			EmpresaController ctrl = new EmpresaController();
			ctrl.salvar(this.empresa);
			this.addSuccessMessage("Empresa salva com sucesso!");
			proxView = "/coordenador/listarEmpresas?faces-redirect=true";
			this.empresa = new Empresa();
		} catch (PersistenceException e) {
			this.addErrorMessage("Erro ao tentar salvar a Empresa");
		}
		
		return proxView;
		
	}
	
	public void listarEmpresas(){
		EmpresaDAO empdao = new EmpresaDAO();
		this.empresas = empdao.findAll();
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
	
	public void bloquearEmpresa(Empresa empresa){
		EmpresaController ctrl = new EmpresaController();
		ctrl.bloquearEmpresa(empresa.getId());
	}
	
	public void desbloquearEmpresa(Empresa empresa){
		EmpresaController ctrl = new EmpresaController();
		ctrl.desbloquearEmpresa(empresa.getId());
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

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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
