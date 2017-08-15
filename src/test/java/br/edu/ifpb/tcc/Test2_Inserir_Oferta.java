package br.edu.ifpb.tcc;

import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.ifpb.tcc.dao.AlunoDAO;
import br.edu.ifpb.tcc.dao.CoordenadorDAO;
import br.edu.ifpb.tcc.dao.CursoDAO;
import br.edu.ifpb.tcc.dao.ProfessorDAO;
//import br.edu.ifpb.sistemaestagio.dao.ContatoDAO;
import br.edu.ifpb.tcc.dao.ManagedEMContext;
import br.edu.ifpb.tcc.dao.OfertaAlunoDAO;
import br.edu.ifpb.tcc.dao.OfertaDAO;
//import br.edu.ifpb.sistemaestagio.dao.OperadoraDAO;
import br.edu.ifpb.tcc.dao.PersistenceUtil;
import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Coordenador;
import br.edu.ifpb.tcc.entity.Curso;
import br.edu.ifpb.tcc.entity.Professor;
import br.edu.ifpb.tcc.entity.Oferta;
import br.edu.ifpb.tcc.entity.OfertaAluno;
import br.edu.ifpb.tcc.entity.StatusOferta;

public class Test2_Inserir_Oferta {
	private static EntityManagerFactory emf;
	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	private EntityManager em;

	@BeforeClass
	public static void init() {
		PersistenceUtil.createEntityManagerFactory("tcc");
		emf = PersistenceUtil.getEntityManagerFactory();
		ManagedEMContext.bind(emf, emf.createEntityManager());
		System.out.println("init()");
	}

	@AfterClass
	public static void destroy() {
		if (emf != null) {
			emf.close();
			System.out.println("destroy()");
		}
	}

	@Before
	public void initEM() {
		em = emf.createEntityManager();
	}
	
	@Test
	public void test1(){
    
    CoordenadorDAO coddao= new CoordenadorDAO(em);	
		Coordenador cod1= coddao.find(3);
    
		ProfessorDAO prodao = new ProfessorDAO(em);
		Professor pro1 = prodao.find(1);
		Professor pro2 = prodao.find(2);
		
		AlunoDAO aldao = new AlunoDAO(em);
		Aluno al1 = aldao.find(4);
		Aluno al2 = aldao.find(5);
		
		CursoDAO cdao = new CursoDAO(em);
		Curso c1 = cdao.find(1);
		
		Oferta of1 = new Oferta();
		of1.setDescricao("Com a rápida ascensão e adesão...");
		of1.setTitulo("Banco de Dados aplicado ao BI");
		of1.setProfessor(pro1);
//		of1.addAluno(al1);
//		of1.addAluno(al2);
		of1.setCurso(c1);
		
		Oferta of2 = new Oferta();
		of2.setDescricao("Com a rápida ascensão e adesão...");
		of2.setTitulo("Tomada de decisao no contexto de Big Data");
		of2.setProfessor(pro1);
//		of2.addAluno(al1);
//		of2.addAluno(al2);
		of2.setCurso(c1);
		of2.setStatus(StatusOferta.APROVADO);
		
		Oferta of3 = new Oferta();
		of3.setDescricao("Com a rápida ascensão e adesão...");
		of3.setTitulo("WebSemântica");
		of3.setProfessor(pro2);
//		of3.addAluno(al1);
//		of3.addAluno(al2);
		of3.setCurso(c1);
		of3.setStatus(StatusOferta.APROVADO);
		
		OfertaAluno ofal1 = new OfertaAluno();
		ofal1.setOferta(of1);
		ofal1.setAluno(al1);
		of1.addOfertaAluno(ofal1);
		
		OfertaAluno ofal2 = new OfertaAluno();
		ofal2.setOferta(of2);
		ofal2.setAluno(al2);
		of1.addOfertaAluno(ofal2);
		
		c1.addOferta(of1);
		c1.addOferta(of2);
		c1.addOferta(of3);
		
		
		//Set os 2 alunos no curso TSI
		al1.setCurso(c1);
		al2.setCurso(c1);
    
		//Set os curso ao Coordenador
		cod1.setCurso(c1);
		c1.setCoordenador(cod1);
		
		//Set os curso ao Professor
		pro1.setCurso(c1);
		pro2.setCurso(c1);
		c1.setProfessor(pro1);
		c1.setProfessor(pro2);
		
		//Add Oferta em professor
		pro1.addOferta(of1);
		pro1.addOferta(of2);
		pro1.addOferta(of3);
		
		OfertaDAO ofdao = new OfertaDAO(em);
		ofdao.beginTransaction();
		ofdao.insert(of1);
		ofdao.insert(of2);
		ofdao.insert(of3);
		ofdao.commit();
		
		OfertaAlunoDAO ofaldao = new OfertaAlunoDAO(em);
		ofaldao.beginTransaction();
		ofaldao.insert(ofal1);
		ofaldao.insert(ofal2);
		ofaldao.commit();
    
		coddao.beginTransaction();
		coddao.update(cod1);
		coddao.commit();
		
		aldao.beginTransaction();
		aldao.update(al1);
		aldao.update(al2);
		aldao.commit();
		
		cdao.beginTransaction();
		cdao.update(c1);
		cdao.commit();
		
		prodao.beginTransaction();
		prodao.update(pro1);
		prodao.update(pro2);
		prodao.commit();
	}
}

