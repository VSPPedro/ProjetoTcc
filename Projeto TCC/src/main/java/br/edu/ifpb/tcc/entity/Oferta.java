package br.edu.ifpb.tcc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_TCC_OFERTA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Oferta {

	@Id
	@Column(name = "ID_TCC")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name="ST_TCC")
	@Enumerated(EnumType.STRING)
	private StatusOferta status = StatusOferta.PENDENTE_DE_APROVACAO;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PROF_ID")
	private Empresa empresa;
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name="TB_OFERTA_ALUNO",
//    joinColumns={@JoinColumn(name="OFERTA_ID")},
//    inverseJoinColumns={@JoinColumn(name="ALUNO_ID")})
//	private List<Aluno> alunos;
	
	@OneToMany(mappedBy="oferta")
	private List<OfertaAluno> ofertaAlunos;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="curso_id")
	private Curso curso;
	
	
	public Oferta(){}
	
	public Oferta(String titulo, String descricao,Empresa empresa, ArrayList<Aluno> alunos) {
		super();
		this.titulo= titulo;
		this.descricao= descricao;
		this.empresa = empresa;
	}
	
	
	
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public void setAlunos(List<Aluno> alunos) {
//		this.alunos = alunos;
//	}


	
	public Empresa getEmpresa() {
		return empresa;
	}
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<OfertaAluno> getOfertaAlunos() {
		return ofertaAlunos;
	}

	public void setOfertaAlunos(List<OfertaAluno> ofertaAluno) {
		this.ofertaAlunos = ofertaAluno;
	}
	
	public void addOfertaAluno(OfertaAluno ofertaAluno){
		if(this.ofertaAlunos == null){
			this.ofertaAlunos = new ArrayList<OfertaAluno>();
		}
		this.ofertaAlunos.add(ofertaAluno);
	}

	public StatusOferta getStatus() {
		return status;
	}

	public void setStatus(StatusOferta status) {
		this.status = status;
	}


	
}
