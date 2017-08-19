package br.edu.ifpb.tcc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.ifpb.tcc.dao.AlunoDAO;
import br.edu.ifpb.tcc.dao.ManagedEMContext;
import br.edu.ifpb.tcc.dao.PersistenceUtil;
import br.edu.ifpb.tcc.dao.ProfessorDAO;
import br.edu.ifpb.tcc.dao.TccDAO;
import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Professor;
import br.edu.ifpb.tcc.entity.StatusTcc;
import br.edu.ifpb.tcc.entity.Tcc;

public class InsereTCC {
	private static EntityManagerFactory emf;
	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	private EntityManager em;

	@BeforeClass
	public static void init() {
		PersistenceUtil.createEntityManagerFactory("tcc");
		emf = PersistenceUtil.getEntityManagerFactory();
		ManagedEMContext.bind(emf, emf.createEntityManager());
		System.out.println("init() - Inserir TCC");
	}

	@AfterClass
	public static void destroy() {
		if (emf != null) {
			emf.close();
			System.out.println("destroy() - Inserir TCC");
		}
	}

	@Before
	public void initEM() {
		em = emf.createEntityManager();
	}
	
	@Test
	public void test1(){
		//---------------------------------------
		//TCC 1
		//---------------------------------------
		Tcc tcc1 = new Tcc();
		tcc1.setTitulo("Machine Without Learning");
		tcc1.setDescricao("O não aprendizado automático ou não aprendizado de máquina é...");
		
		//Obter professor
		ProfessorDAO professorDao = new ProfessorDAO(em);
		professorDao.beginTransaction();
		Professor professor = professorDao.find(5);
		professorDao.commit();
		
		tcc1.setProfessor(professor);
		
		//Obter aluno
		AlunoDAO alunoDao = new AlunoDAO(em);
		alunoDao.beginTransaction();
		Aluno aluno = alunoDao.find(7);
		alunoDao.commit();
		
		tcc1.setAluno(aluno);
		
		try {
	        SimpleDateFormat sdf= new SimpleDateFormat("dd.MM.yyyy");
	        Date d = sdf.parse("16.02.2017");
	        tcc1.setDataInicio(d);
	        d = sdf.parse("16.08.2017");
	        tcc1.setDataApresentacao(d);
	    } catch (ParseException ex) {
	       ex.printStackTrace();
	    }
		
		//Add Banca Professor 1
		professorDao = new ProfessorDAO(em);
		professorDao.beginTransaction();
		professor = professorDao.find(2);
		professorDao.commit();
		
		tcc1.addBanca(professor);
		
		//Add Banca Professor 2
		professorDao = new ProfessorDAO(em);
		professorDao.beginTransaction();
		professor = professorDao.find(3);
		professorDao.commit();
		
		tcc1.addBanca(professor);
		
		//Add Banca Professor 2
		professorDao = new ProfessorDAO(em);
		professorDao.beginTransaction();
		professor = professorDao.find(4);
		professorDao.commit();
		
		tcc1.addBanca(professor);
		
		//Aprovar TCC
		tcc1.setStatus(StatusTcc.APROVADO);
		
		TccDAO tccDao = new TccDAO(em);
		tccDao.beginTransaction();
		tccDao.insert(tcc1);
		tccDao.commit();
		
		//Atualiza Aluno
		alunoDao = new AlunoDAO(em);
		alunoDao.beginTransaction();
		aluno.setTcc(tcc1);
		alunoDao.insert(aluno);
		alunoDao.commit();
		
		//------------------
		//TCC 2
		//------------------
		Tcc tcc2 = new Tcc();
		
		//Obter professor
		professorDao = new ProfessorDAO(em);
		professorDao.beginTransaction();
		professor = professorDao.find(3);
		professorDao.commit();
		
		tcc2.setProfessor(professor);
	
		//Obter aluno
		alunoDao = new AlunoDAO(em);
		alunoDao.beginTransaction();
		aluno = alunoDao.find(9);
		alunoDao.commit();
		
		tcc2.setAluno(aluno);
		
		tcc2.setTitulo("Internet Offline das Coisas");
		tcc2.setDescricao("A Internet Offline das Coisas (do inglês, dunno Internet of Things) é um sossegamento tecnológico...");
		
		tccDao = new TccDAO(em);
		tccDao.beginTransaction();
		tccDao.insert(tcc2);
		tccDao.commit();
		
		//Atualiza Aluno
		alunoDao = new AlunoDAO(em);
		alunoDao.beginTransaction();
		aluno.setTcc(tcc2);
		alunoDao.insert(aluno);
		alunoDao.commit();
		
		//---------------------------------------
		//TCC 3
		//---------------------------------------
		Tcc tcc3 = new Tcc();
		
		//Obter professor
		professorDao = new ProfessorDAO(em);
		professorDao.beginTransaction();
		professor = professorDao.find(4);
		professorDao.commit();
		
		tcc3.setProfessor(professor);
		
		//Obter aluno
		alunoDao = new AlunoDAO(em);
		alunoDao.beginTransaction();
		aluno = alunoDao.find(10);
		alunoDao.commit();
		
		tcc3.setAluno(aluno);
		
		tcc3.setTitulo("Shallow Learning");
		tcc3.setDescricao("A aprendizagem rasa, do inglês Shallow Learning ...");
		
		tccDao = new TccDAO(em);
		tccDao.beginTransaction();
		tccDao.insert(tcc3);
		tccDao.commit();
		
		//Atualiza Aluno
		alunoDao = new AlunoDAO(em);
		alunoDao.beginTransaction();
		aluno.setTcc(tcc3);
		alunoDao.insert(aluno);
		alunoDao.commit();
	}
}
