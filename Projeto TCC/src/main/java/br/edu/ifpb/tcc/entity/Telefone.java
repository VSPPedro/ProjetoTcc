//package br.edu.ifpb.sistemaestagio.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "TB_TELEFONE")
//public class Telefone {
//	
//	@Id
//	@Column(name = "ID_TELEFONE")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int idTelefone;
//	
//	@Column(name = "NUM_TELEFONE")
//	private String numero;
//	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="empresa_id")
//	private Empresa empresa;
//	
//	public Telefone(){}
//	
//	public Empresa getEmpresa() {
//		return empresa;
//	}
//
//	public void setEmpresa(Empresa empresa) {
//		this.empresa = empresa;
//	}
//
//	public int getIdTelefone() {
//		return idTelefone;
//	}
//
//	public void setIdTelefone(int idTelefone) {
//		this.idTelefone = idTelefone;
//	}
//
//	public String getNumero() {
//		return numero;
//	}
//
//	public void setNumero(String numero) {
//		this.numero = numero;
//	}
//
//	@Override
//	public String toString() {
//		return "Telefone [idTelefone=" + idTelefone + ", numero=" + numero + ", empresa=" + empresa + "]";
//	}
//	
//	
//	
//}