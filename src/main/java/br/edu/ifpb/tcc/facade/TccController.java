package br.edu.ifpb.tcc.facade;

import java.util.List;

import br.edu.ifpb.tcc.dao.ProfessorDAO;
import br.edu.ifpb.tcc.dao.TccDAO;
import br.edu.ifpb.tcc.dao.PersistenceUtil;
import br.edu.ifpb.tcc.entity.Professor;
import br.edu.ifpb.tcc.entity.Tcc;
import br.edu.ifpb.tcc.entity.StatusTcc;

public class TccController {
	
	public TccController(){}
	
	public void cadastrar(Tcc tcc) {
		
		TccDAO dao= new TccDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		if(tcc.getId() == null) {
			dao.insert(tcc);
		} else{
			dao.update(tcc);
		}
		dao.commit();
				
	}
	
	public List<Tcc> getTccsPendentes(){
		TccDAO tdao = new TccDAO();
		return tdao.getTccsPendentes();
	}
	
	public List<Tcc> getTccsAtivos(){
		TccDAO tdao = new TccDAO();
		return tdao.getTccsAtivos();
	}
	
	public Tcc aprovarTcc(int id) {
		TccDAO tdao = new TccDAO();
		Tcc t = tdao.find(id);
		
		if(t!= null){
			tdao.beginTransaction();
			t.setStatus(StatusTcc.ATIVO);
			t = tdao.update(t);
			tdao.commit();
		}
		return t;
	}

	public Tcc negarTcc(int id) {
		TccDAO tdao = new TccDAO();
		Tcc t = tdao.find(id);
		
		if(t != null){
			tdao.beginTransaction();
			t.setStatus(StatusTcc.NEGADO);
			t = tdao.update(t);
			tdao.commit();
		}
		return t;
	}

	public Tcc fecharTcc(Integer id) {
		TccDAO tdao = new TccDAO();
		Tcc t = tdao.find(id);
		
		if(t != null){
			tdao.beginTransaction();
			t.setStatus(StatusTcc.FECHADO);
			t = tdao.update(t);
			tdao.commit();
		}
		return t;
		
	}
	
	

}
