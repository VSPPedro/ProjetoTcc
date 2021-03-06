package br.edu.ifpb.tcc;

import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.ifpb.tcc.dao.AlunoDAO;
import br.edu.ifpb.tcc.dao.CoordenadorDAO;
import br.edu.ifpb.tcc.dao.ManagedEMContext;
import br.edu.ifpb.tcc.dao.PersistenceUtil;
import br.edu.ifpb.tcc.dao.ProfessorDAO;
import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Coordenador;
import br.edu.ifpb.tcc.entity.Professor;
import br.edu.ifpb.tcc.util.PasswordUtil;

public class InsereUsuarios{
	private static EntityManagerFactory emf;
	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	private EntityManager em;

	@BeforeClass
	public static void init() {
		PersistenceUtil.createEntityManagerFactory("tcc");
		emf = PersistenceUtil.getEntityManagerFactory();
		ManagedEMContext.bind(emf, emf.createEntityManager());
		System.out.println("init() - Inserir Pessoas");
	}

	@AfterClass
	public static void destroy() {
		if (emf != null) {
			emf.close();
			System.out.println("destroy() - Inserir Pessoas");
		}
	}

	@Before
	public void initEM() {
		em = emf.createEntityManager();
	}
	
	@Test
	public void test1(){
		try{
			
			//---------------------------------------
			//Coordenador
			//---------------------------------------
			Coordenador cod1= new Coordenador();
			cod1.setNome("Valéria");
			cod1.setEmail("valeria@ifpb.edu.br");
			cod1.setSenha(PasswordUtil.encryptMD5("senhaadm"));
			cod1.setTelefone("8396148795");
			
			CoordenadorDAO coddao= new CoordenadorDAO(em);
			coddao.beginTransaction();
			coddao.insert(cod1);
			coddao.commit();
			
			//---------------------------------------
			//Professor
			//---------------------------------------
			Professor pro1 = new Professor();
			pro1.setNome("Fred");
			pro1.setEmail("fred@ifpb.edu.br");
			pro1.setSenha(PasswordUtil.encryptMD5("nabucodonosor"));
			pro1.setTelefone("8336031285");
			
			Professor pro2 = new Professor();
			pro2.setNome("Denio");
			pro2.setEmail("denio@ifpb.edu.br");
			pro2.setSenha(PasswordUtil.encryptMD5("denioseguranca"));
			pro2.setTelefone("8336345878");
			
			Professor pro3 = new Professor();
			pro3.setNome("Fausto");
			pro3.setEmail("fausto@ifpb.edu.br");
			pro3.setSenha(PasswordUtil.encryptMD5("faustopoo"));
			pro3.setTelefone("8312345678");
			
			Professor pro4 = new Professor();
			pro4.setNome("Damires");
			pro4.setEmail("damires@ifpb.edu.br");
			pro4.setSenha(PasswordUtil.encryptMD5("damiresbd"));
			pro4.setTelefone("8387363661");
			
			Professor pro5 = new Professor();
			pro5.setNome("Petrônio");
			pro5.setEmail("petronio@ifpb.edu.br");
			pro5.setSenha(PasswordUtil.encryptMD5("petroniopadrao"));
			pro5.setTelefone("8384353161");
			
			ProfessorDAO pro = new ProfessorDAO(em);
			pro.beginTransaction();
			pro.insert(pro1);
			pro.insert(pro2);
			pro.insert(pro3);
			pro.insert(pro4);
			pro.insert(pro5);
			pro.commit();
			
			//---------------------------------------
			//Aluno
			//---------------------------------------
			Aluno al1 = new Aluno();
			al1.setMatricula("20132370179");
			al1.setNome("Yuri Canuto");
			al1.setEmail("yuri@academico.ifpb.edu.br");
			al1.setSenha(PasswordUtil.encryptMD5("yurialuno"));
			al1.setTelefone("88881234");
			
			Aluno al2 = new Aluno();
			al2.setMatricula("20132370233");
			al2.setNome("João Victor");
			al2.setEmail("joao@academico.ifpb.edu.br");
			al2.setSenha(PasswordUtil.encryptMD5("joaoaluno"));
			al2.setTelefone("88881212");
			
			Aluno al3 = new Aluno();
			al3.setMatricula("20132370241");
			al3.setNome("Rui Braz");
			al3.setEmail("rui@academico.ifpb.edu.br");
			al3.setSenha(PasswordUtil.encryptMD5("ruialuno"));
			al3.setTelefone("88883331");
			
			Aluno al4 = new Aluno();
			al4.setMatricula("20141370372");
			al4.setNome("Pedro Paiva");
			al4.setEmail("pedro@academico.ifpb.edu.br");
			al4.setSenha(PasswordUtil.encryptMD5("pedroaluno"));
			al4.setTelefone("88667777");
			
			Aluno al5 = new Aluno();
			al5.setMatricula("20121370300");
			al5.setNome("Nicolas Rodrigues");
			al5.setEmail("nicolas@academico.ifpb.edu.br");
			al5.setSenha(PasswordUtil.encryptMD5("nicolasaluno"));
			al5.setTelefone("87513232");
			
			Aluno al6 = new Aluno();
			al6.setMatricula("20132370233");
			al6.setNome("Thiago Henrique");
			al6.setEmail("thiago@academico.ifpb.edu.br");
			al6.setSenha(PasswordUtil.encryptMD5("joaoaluno"));
			al6.setTelefone("88551234");
			
			Aluno al7 = new Aluno();
			al7.setMatricula("20092370165");
			al7.setNome("Idjinne Ribeiro");
			al7.setEmail("idjinne@academico.ifpb.edu.br");
			al7.setSenha(PasswordUtil.encryptMD5("idjinnealuno"));
			al7.setTelefone("87554123");

			AlunoDAO aldao = new AlunoDAO(em);
			aldao.beginTransaction();
			aldao.insert(al1);
			aldao.insert(al2);
			aldao.insert(al3);
			aldao.insert(al4);
			aldao.insert(al5);
			aldao.insert(al6);
			aldao.insert(al7);
			aldao.commit();
			
		}catch(Exception exc){
			Assert.fail("Erro de BD: " + exc.getMessage());
		}
	}
	
	@Test
	public void test2(){
		ProfessorDAO prodao = new ProfessorDAO(em);
		Professor pro1 = prodao.find(1);
		Professor pro2 = prodao.find(2);

		prodao.beginTransaction();
		prodao.update(pro1);
		prodao.update(pro2);
		prodao.commit();
	}
}
