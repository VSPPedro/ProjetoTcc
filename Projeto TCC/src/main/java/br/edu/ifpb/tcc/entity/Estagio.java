package br.edu.ifpb.tcc.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_TCC")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Estagio {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="ALUNO_ID")
	private Aluno aluno;
	
	@OneToOne
	@JoinColumn(name="PROF_ID")
	private Empresa empresa;
		
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INICIO")
	private Date dataInicio;
	
	@Column(name="ST_TCC")
	@Enumerated(EnumType.STRING) 
	private StatusEstagio status = StatusEstagio.PENDENTE_DE_APROVACAO;
	
	public Estagio(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public StatusEstagio getStatus() {
		return status;
	}

	public void setStatus(StatusEstagio status) {
		this.status = status;
	}

	

	
	
	
	
}
