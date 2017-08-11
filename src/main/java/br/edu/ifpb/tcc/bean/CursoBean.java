package br.edu.ifpb.tcc.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.tcc.dao.CursoDAO;
import br.edu.ifpb.tcc.entity.Curso;

@ManagedBean(name="cursoBean")
@ViewScoped
public class CursoBean extends GenericBean {
	
	private List<Curso> cursos = new ArrayList<Curso>();

	public List<Curso> getCursos() {
		CursoDAO cdao = new CursoDAO();
		this.cursos = cdao.findAll();
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	

}
