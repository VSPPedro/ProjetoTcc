package br.edu.ifpb.tcc.bean;


import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import br.edu.ifpb.tcc.dao.AlunoDAO;
import br.edu.ifpb.tcc.dao.TccDAO;
import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Professor;
import br.edu.ifpb.tcc.entity.StatusTcc;
import br.edu.ifpb.tcc.entity.Tcc;
import br.edu.ifpb.tcc.facade.AlunoController;
import br.edu.ifpb.tcc.facade.TccController;

@ManagedBean(name="tccBean")
@ViewScoped
public class TccBean extends GenericBean{
	
	private Integer id = 0;
	private boolean inscrito;
	
	private Aluno aluno = new Aluno();
	private Tcc tcc = new Tcc();
	
	private List<StatusTcc> allStatusTcc = Arrays.asList(StatusTcc.values());
	
	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;
	
	public String aprovar(){
		
		this.tcc.setStatus(StatusTcc.APROVADO);
		TccController tccCtrl = new TccController();
		tccCtrl.salvar(this.tcc);
		
		this.addSuccessMessage("Pedido aprovado com sucesso!");
		
		return "/professor/listarOrientandos?faces-redirect=true";
	}
	
	public String atualizarDatas(){
		
		TccController tccCtrl = new TccController();
		tccCtrl.salvar(this.tcc);
		
		this.addSuccessMessage("Datas registradas com sucesso!");
		
		return "/professor/listarOrientandos?faces-redirect=true";
	}
	
	public String recusar(){
		this.tcc.setStatus(StatusTcc.NEGADO);
		TccController tccCtrl = new TccController();
		tccCtrl.salvar(this.tcc);
		
		this.addSuccessMessage("Pedido recusado!");
		
		return "/professor/listarOrientandos?faces-redirect=true";
	}
	
	public void selecionarPedido(){
		TccDAO tccDao = new TccDAO();
		this.tcc = tccDao.find(this.id);
	}

	public List<StatusTcc> getAllStatusTcc() {
		return allStatusTcc;
	}

	public void setAllStatusTcc(List<StatusTcc> allStatusTcc) {
		this.allStatusTcc = allStatusTcc;
	}

	public void obterTcc() {
		this.aluno = (Aluno) loginBean.getPessoa();
		
		if (this.aluno.getTcc() == null) {
			this.aluno.setTcc(new Tcc());
		}
		
		if (this.aluno.getTcc().getProfessor() == null) {
			this.aluno.getTcc().setProfessor(new Professor());
		}
		
		this.tcc = this.aluno.getTcc();
		
		System.out.println("This.tcc.status.nome: " + this.tcc.getStatus().getNome());
	}
	
	public String salvarAlunoETcc(){
		String proxView = null;
		try {
			//Obter email
			AlunoDAO alunoTcc = new AlunoDAO();
			Aluno alun = alunoTcc.findByLogin(this.aluno.getEmail());
			
			if (alun.getTcc().getId() == null) { 
				this.addSuccessMessage("TCC adicionado com sucesso!");
			} else {
				this.tcc.setId(alun.getTcc().getId());
				this.addSuccessMessage("TCC editado com sucesso!");
			}
			
			TccController tccCtrl = new TccController();
			this.tcc.setAluno(this.aluno);
			tccCtrl.salvar(this.tcc);
			
			//Salvar aluno - gambis
			this.aluno.setSenha(alun.getSenha());
			
			AlunoController alunoCtrl = new AlunoController();
			alunoCtrl.atualizar(this.aluno);
			
			proxView = "/aluno/acompanharTcc?faces-redirect=true";
			
		} catch (PersistenceException e) {
			this.addErrorMessage("Erro ao tentar salvar o tcc!");
		}
		
		return proxView;
	}
	
	public String detalhesTcc(){
		
		this.aluno = (Aluno)this.loginBean.getPessoa();
		this.tcc = this.aluno.getTcc();
		
		return "/aluno/meuTcc?faces-redirect=true";
	}
	
	public Tcc getTcc() {
		return tcc;
	}

	public void setTcc(Tcc tcc) {
		this.tcc = tcc;
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
