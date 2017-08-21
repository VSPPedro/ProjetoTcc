package br.edu.ifpb.tcc.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_TCC")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Tcc {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="ALUNO_ID")
	private Aluno aluno;
	
	@OneToOne
	@JoinColumn(name="PROF_ID")
	private Professor professor;
		
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INICIO")
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_APRESENTACAO")
	private Date dataApresentacao;
	
	@ManyToMany(targetEntity = Professor.class)
	@JoinTable(name="TB_BANCA", foreignKey = @ForeignKey(name = "fk_professor"))
	private List<Professor> banca = new ArrayList<Professor>();

	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name="ST_TCC")
	@Enumerated(EnumType.STRING) 
	private StatusTcc status = StatusTcc.PENDENTE_DE_APROVACAO;
	
	public Tcc(){}

	public Integer getId() {
		return id;
	}
	
	public Date getDataApresentacao() {
		return dataApresentacao;
	}

	public void setDataApresentacao(Date dataApresentacao) {
		this.dataApresentacao = dataApresentacao;
	}

	public void addBanca(Professor professor) {
		banca.add(professor);
	}
	
	public List<Professor> getBanca() {
		return banca;
	}

	public void setBanca(List<Professor> banca) {
		this.banca = banca;
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

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public StatusTcc getStatus() {
		return status;
	}

	public void setStatus(StatusTcc status) {
		this.status = status;
	}
}
