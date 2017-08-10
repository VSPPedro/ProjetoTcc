package br.edu.ifpb.tcc.entity;

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
import javax.persistence.Table;

@Entity
@Table(name = "TB_TCC_ALUNO")
@Inheritance(strategy = InheritanceType.JOINED)
public class OfertaAluno {
	
	@Id
	@Column(name = "ID_TCC_ALU")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="aluno_id")
	private Aluno aluno;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="TCC_ID")
	private Oferta oferta;
	
	@Column(name="ST_TCC_ALU")
	@Enumerated(EnumType.STRING) 
	private StatusAlunoOferta status = StatusAlunoOferta.PENDENTE_DE_APROVACAO;
	
	public OfertaAluno(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public StatusAlunoOferta getStatus() {
		return status;
	}

	public void setStatus(StatusAlunoOferta status) {
		this.status = status;
	}
	
	
}
