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
		
		tcc1.setTitulo("Machine Without Learning");
		tcc1.setDescricao("O não aprendizado automático ou não aprendizado de máquina é...");
		
		try {
	        SimpleDateFormat sdf= new SimpleDateFormat("dd.MM.yyyy");
	        Date d = sdf.parse("16.08.2017");
	        tcc1.setDataInicio(d);
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
		
		//------------------
		//TCC 2
		//------------------
		Tcc tcc2 = new Tcc();
		
		//Obter professor
		professorDao = new ProfessorDAO(em);
		professorDao.beginTransaction();
		professor = professorDao.find(6);
		professorDao.commit();
		
		tcc2.setProfessor(professor);
	
		//EXEMPLO SEM ALUNO
		
		tcc2.setTitulo("Internet Offline das Coisas");
		tcc2.setDescricao("A Internet Offline das Coisas (do inglês, dunno Internet of Things) é um sossegamento tecnológico...");
		
		try {
	        SimpleDateFormat sdf= new SimpleDateFormat("dd.MM.yyyy");
	        Date d = sdf.parse("16.08.2017");
	        tcc2.setDataInicio(d);
	    } catch (ParseException ex) {
	       ex.printStackTrace();
	    }
		
		tccDao = new TccDAO(em);
		tccDao.beginTransaction();
		tccDao.insert(tcc2);
		tccDao.commit();
	}
}
