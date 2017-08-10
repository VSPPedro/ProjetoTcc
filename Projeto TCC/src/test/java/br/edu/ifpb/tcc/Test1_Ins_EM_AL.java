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
import br.edu.ifpb.tcc.dao.CursoDAO;
import br.edu.ifpb.tcc.dao.EmpresaDAO;

//import br.edu.ifpb.sistemaestagio.dao.ContatoDAO;
import br.edu.ifpb.tcc.dao.ManagedEMContext;
//import br.edu.ifpb.sistemaestagio.dao.OperadoraDAO;
import br.edu.ifpb.tcc.dao.PersistenceUtil;
import br.edu.ifpb.tcc.entity.Aluno;
import br.edu.ifpb.tcc.entity.Coordenador;
import br.edu.ifpb.tcc.entity.Curso;
import br.edu.ifpb.tcc.entity.Empresa;

//import br.edu.ifpb.sistemaestagio.dao.UsuarioDAO;
//import br.edu.ifpb.sistemaestagio.entity.Contato;
//import br.edu.ifpb.sistemaestagio.entity.Operadora;
//import br.edu.ifpb.sistemaestagio.entity.Perfil;
//import br.edu.ifpb.sistemaestagio.entity.Usuario;
import br.edu.ifpb.tcc.util.PasswordUtil;

public class Test1_Ins_EM_AL{
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
		try{
			Coordenador cod1= new Coordenador();
			cod1.setNome("Valéria");
			cod1.setEmail("valeria@ifpb.edu.br");
			cod1.setSenha(PasswordUtil.encryptMD5("senhaadm"));
			cod1.setTelefone("8396148795");

			
			Empresa emp1 = new Empresa();
			emp1.setNome("Fred");
			emp1.setEmail("soft@ifpb.edu.br");
			emp1.setSenha(PasswordUtil.encryptMD5("senhasoftcom"));
			emp1.setTelefone("8336031285");
			
			
			Empresa emp2 = new Empresa();
			emp2.setNome("Denio");
			emp2.setEmail("elfa@ifpb.edu.br");
			emp2.setSenha(PasswordUtil.encryptMD5("senhaelfa"));
	        emp2.setTelefone("8336345878");
			
			Aluno al1 = new Aluno();
			al1.setMatricula("20132370233");
			al1.setNome("João Victor");
			al1.setEmail("joao@ifpb");
		
			al1.setSenha(PasswordUtil.encryptMD5("joaoaluno"));
			al1.setTelefone("232365656");
			
			Aluno al2 = new Aluno();
			al2.setMatricula("20132374405");
			al2.setNome("Vicente Correia");
			al2.setEmail("vicente@ifpb");
			al2.setSenha(PasswordUtil.encryptMD5("vicentealuno"));
			al2.setTelefone("456646546");
			
			Curso c1 = new Curso();
			c1.setNome("Sistemas para Internet");
			c1.setCh(30);
//			c1.setCoordenador(cod1);
			
//			cod1.setCurso(c1);
			
			System.out.println(c1.toString());
			CursoDAO cdao = new CursoDAO(em);
			cdao.beginTransaction();
			cdao.insert(c1);
			cdao.commit();
			



			EmpresaDAO emp = new EmpresaDAO(em);
			emp.beginTransaction();
			emp.insert(emp1);
			emp.insert(emp2);
			emp.commit();
			

			
			CoordenadorDAO coddao= new CoordenadorDAO(em);
			coddao.beginTransaction();
			cod1=coddao.insert(cod1);
			coddao.commit();
			
			AlunoDAO aldao = new AlunoDAO(em);
			aldao.beginTransaction();
			al1 = aldao.insert(al1);
			al2 = aldao.insert(al2);
			aldao.commit();
			
			
//				
		}catch(Exception exc){
			Assert.fail("Erro de BD: " + exc.getMessage());
		}
	}
	
	
	@Test
	public void test2(){
		
		
		

		EmpresaDAO empdao = new EmpresaDAO(em);
		Empresa emp1 = empdao.find(1);
		Empresa emp2 = empdao.find(2);
		System.out.println(emp1.toString());
		System.out.println(emp2.toString());
		


		
		empdao.beginTransaction();
		empdao.update(emp1);
		empdao.update(emp2);
		empdao.commit();
	}
	
	
	
	

}
