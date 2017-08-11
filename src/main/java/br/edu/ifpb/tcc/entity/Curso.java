package br.edu.ifpb.tcc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
//@Table(name = "TB_CURSO")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Curso {
	@Id
	@Column(name="ID_CURSO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="NOME_CURSO")
	private String nome;
	
	@Column(name="CH_CURSO")
	private Integer ch;
	
	@OneToMany(mappedBy="curso", cascade = CascadeType.ALL)
	private List<Aluno> alunos = new ArrayList<Aluno>();
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="coord_id")
	private Coordenador coordenador;

	public Coordenador getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Coordenador coordenador) {
		this.coordenador = coordenador;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="prof_id")
	private Professor professor;


	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@OneToMany(mappedBy="curso")
	private List<Oferta> ofertas = new ArrayList<Oferta>();
	
//	@OneToOne
//	private Coordenador coordenador;
	
	public Curso(){}
	
	public void addOferta(Oferta oferta){
		if(this.ofertas == null){
			this.ofertas = new ArrayList<Oferta>();
		}
		this.ofertas.add(oferta);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCh() {
		return ch;
	}

	public void setCh(Integer ch) {
		this.ch = ch;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}


	
	
	
	
	
	
	
	

}
