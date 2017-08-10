package br.edu.ifpb.tcc.facade;

import java.util.List;

import br.edu.ifpb.tcc.dao.CoordenadorDAO;
import br.edu.ifpb.tcc.dao.EmpresaDAO;
import br.edu.ifpb.tcc.dao.PersistenceUtil;
import br.edu.ifpb.tcc.dao.PessoaDAO;
import br.edu.ifpb.tcc.entity.Coordenador;
import br.edu.ifpb.tcc.entity.Empresa;
import br.edu.ifpb.tcc.entity.Pessoa;
import br.edu.ifpb.tcc.util.PasswordUtil;

public class EmpresaController {

	public List<Empresa>consultar(){
		EmpresaDAO dao = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
		List<Empresa> empresas = dao.findAll();
		return empresas;
	}
	
	public EmpresaController(){}
	
	public boolean cadastrar(Empresa empresa) {
		EmpresaDAO dao= new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
		Empresa e = dao.findByLogin(empresa.getEmail());
		if(e == null){
			empresa.setSenha( PasswordUtil.encryptMD5(empresa.getSenha()));
			dao.beginTransaction();
			dao.insert(empresa);
			dao.commit();
			return true;
		}
		return false;
		
	}
	
	public boolean isBloqueado(Pessoa pessoa){
		EmpresaDAO empdao = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
		return empdao.isBloqueado(pessoa);
	}
	
	public void salvar(Empresa empresa) {
		
		EmpresaDAO dao= new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		if(empresa.getId() == null) {
			dao.insert(empresa);
		} else{
			if(empresa.getSenha().isEmpty()){
				//ISSUE HERE - PASSWORD SET
				System.out.println("Senha tá vazia: "+empresa.getSenha());
				PessoaDAO pdao = new PessoaDAO();
				Pessoa emp = pdao.findByLogin(empresa.getEmail());
				System.out.println("Senha: "+emp.getSenha());
				empresa.setSenha(emp.getSenha());
			}else{
				System.out.println("Senha não tá vazia: "+empresa.getSenha());
				empresa.setSenha(PasswordUtil.encryptMD5(empresa.getSenha()));
			}
			dao.update(empresa);
		}
		dao.commit();
			
	}
	
	public Empresa buscar(int id){
		EmpresaDAO dao = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
		return dao.find(id);
	}
/*	public Empresa consultar(){
		EmpresaDAO dao = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
		List<Empresa> empresas= dao.findAll();
		return empresas;
	}*/

	public Empresa bloquearEmpresa(int id) {
		EmpresaDAO dao = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
		Empresa emp = dao.bloquearEmpresa(id);
		return emp;
	}
	
	public Empresa desbloquearEmpresa(int id) {
		EmpresaDAO dao = new EmpresaDAO(PersistenceUtil.getCurrentEntityManager());
		Empresa emp = dao.desbloquearEmpresa(id);
		return emp;
	}

	
}

