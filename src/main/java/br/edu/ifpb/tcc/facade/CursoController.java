package br.edu.ifpb.tcc.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.edu.ifpb.tcc.dao.CursoDAO;
import br.edu.ifpb.tcc.dao.PersistenceUtil;
import br.edu.ifpb.tcc.entity.Curso;
import br.edu.ifpb.tcc.entity.Pessoa;

public class CursoController {
	private Curso curso;
	private List<Mensagem> mensagensErro;

	public CursoController(){}
	
	public Resultado cadastrar(Map<String, String[]> parametros, Pessoa pessoa) {
		Resultado resultado= new Resultado();
		
		if(isParametrosValidos(parametros, pessoa)) {
				System.out.println("DADOS DO CURSO: ");
				CursoDAO dao= new CursoDAO(PersistenceUtil.getCurrentEntityManager());
				dao.beginTransaction();
				if(this.curso.getId() == null) {
					dao.insert(this.curso);
				} else{
					dao.update(this.curso);
				}
				dao.commit();
				
				resultado.setErro(false);
				
				resultado.setMensagens(Collections.singletonList(new Mensagem("Curso foi cadastrado.", Categoria.INFO)));
				
			} else{
				resultado.setEntidade(this.curso);
				resultado.setErro(true);
				resultado.setMensagens(this.mensagensErro);
				}
			return resultado;
	}
	
	private boolean isParametrosValidos(Map<String, String[]> parametros, Pessoa pessoa) {
		// nomes dos parâmetros vêm dos atributos 'name' das tags HTML do formulário
		String[] id = parametros.get("id");
		String[] nome = parametros.get("nome");
		String[] ch = parametros.get("ch");
		
		this.curso= new Curso();
		this.mensagensErro= new ArrayList<Mensagem>();
		
		if(id!= null && id.length>0 && !id[0].isEmpty()) {
			curso.setId(Integer.parseInt(id[0]));
		}
		
		if(nome== null|| nome.length== 0 || nome[0].isEmpty()) {
			this.mensagensErro.add(new Mensagem("Nome é campo obrigatório!", Categoria.ERRO));
		} else{
			curso.setNome(nome[0]);
		}
		
		if(ch== null|| ch.length== 0 || ch[0].isEmpty()) {
			this.mensagensErro.add(new Mensagem("Carga Horária é campo obrigatório!", Categoria.ERRO));
		} else{
			curso.setCh(Integer.parseInt(ch[0]));
		}
		
		return this.mensagensErro.isEmpty();
	}
	
	public Curso buscar(int id){
		CursoDAO dao = new CursoDAO(PersistenceUtil.getCurrentEntityManager());
		return dao.find(id);
	}
	
	public List<Curso> getAll(){
		CursoDAO dao = new CursoDAO(PersistenceUtil.getCurrentEntityManager());
		List<Curso> cursos = dao.findAll();
		return cursos;
	}

}

