package br.edu.ifpb.tcc.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import br.edu.ifpb.tcc.dao.EstagioDAO;
import br.edu.ifpb.tcc.dao.OfertaAlunoDAO;
import br.edu.ifpb.tcc.dao.OfertaDAO;
import br.edu.ifpb.tcc.entity.Empresa;
import br.edu.ifpb.tcc.entity.Estagio;
import br.edu.ifpb.tcc.entity.Oferta;
import br.edu.ifpb.tcc.entity.OfertaAluno;
import br.edu.ifpb.tcc.entity.StatusAlunoOferta;
import br.edu.ifpb.tcc.facade.EmpresaController;
import br.edu.ifpb.tcc.facade.OfertaController;

@ManagedBean(name="empresaBean")
@ViewScoped
public class EmpresaBean extends GenericBean{
	
	private Integer id;

	private List<Oferta> ofertas = new ArrayList<Oferta>();
	
	private List<Estagio> estagios = new ArrayList<Estagio>();
	
	private Oferta oferta = new Oferta();
	
	private Estagio estagio = new Estagio();
	
	private Empresa empresa = new Empresa();
	

	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	public String cadastrarEmpresa(){
		EmpresaController ctrl = new EmpresaController();
		
		if(ctrl.cadastrar(empresa)){
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
	
	public void listarEstagios(){
		EstagioDAO edao = new EstagioDAO();
		this.estagios = edao.findAllFromPessoa(this.loginBean.getPessoa());
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
	
	public void selecionarEstagio(){
		EstagioDAO edao = new EstagioDAO();
		this.estagio = edao.find(this.id);
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
	
	public void solicitarAprovacaoEstagio(){
		EstagioDAO edao = new EstagioDAO();
		edao.criarEstagio(this.oferta);
		OfertaDAO ofdao = new OfertaDAO();
		ofdao.concluirOferta(this.oferta.getId());
		this.addSuccessMessage("Solicitação de Estágio foi enviada para a Coordenação!");
	}
	

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public List<Estagio> getEstagios() {
		return estagios;
	}

	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	
	
	
}
