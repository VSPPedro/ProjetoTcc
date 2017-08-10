package br.edu.ifpb.tcc.facade;

import java.util.List;

import br.edu.ifpb.tcc.dao.EmpresaDAO;
import br.edu.ifpb.tcc.dao.EstagioDAO;
import br.edu.ifpb.tcc.dao.PersistenceUtil;
import br.edu.ifpb.tcc.entity.Empresa;
import br.edu.ifpb.tcc.entity.Estagio;
import br.edu.ifpb.tcc.entity.StatusEstagio;

public class EstagioController {
	
	public EstagioController(){}
	
	public void cadastrar(Estagio estagio) {
		
		EstagioDAO dao= new EstagioDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		if(estagio.getId() == null) {
			dao.insert(estagio);
		} else{
			dao.update(estagio);
		}
		dao.commit();
				
	}
	
	public List<Estagio> getEstagiosPendentes(){
		EstagioDAO edao = new EstagioDAO();
		return edao.getEstagiosPendentes();
	}
	
	public List<Estagio> getEstagiosAtivos(){
		EstagioDAO edao = new EstagioDAO();
		return edao.getEstagiosAtivos();
	}
	
	public Estagio aprovarEstagio(int id) {
		EstagioDAO edao = new EstagioDAO();
		Estagio e = edao.find(id);
		
		if(e != null){
			edao.beginTransaction();
			e.setStatus(StatusEstagio.ATIVO);
			e = edao.update(e);
			edao.commit();
		}
		return e;
	}

	public Estagio negarEstagio(int id) {
		EstagioDAO edao = new EstagioDAO();
		Estagio e = edao.find(id);
		
		if(e != null){
			edao.beginTransaction();
			e.setStatus(StatusEstagio.NEGADO);
			e = edao.update(e);
			edao.commit();
		}
		return e;
	}

	public Estagio fecharEstagio(Integer id) {
		EstagioDAO edao = new EstagioDAO();
		Estagio e = edao.find(id);
		
		if(e != null){
			edao.beginTransaction();
			e.setStatus(StatusEstagio.FECHADO);
			e = edao.update(e);
			edao.commit();
		}
		return e;
		
	}
	
	

}
