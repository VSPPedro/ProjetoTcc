package br.edu.ifpb.tcc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "TB_ALUNO")
@DiscriminatorValue(value = "A")
public class Aluno extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "ATIVO_AlUNO")
	private boolean ativa = true;
	
	@Column(name = "MAT_ALUNO")
	private String matricula;
	
	@ManyToOne
	@JoinColumn(name="curso_id", foreignKey = @ForeignKey(name = "fk_curso"))
	private Curso curso;
	
	@OneToOne
	@JoinColumn(name="tcc_id")
	private Tcc tcc;

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Tcc getTcc() {
		return tcc;
	}

	public void setTcc(Tcc tcc) {
		this.tcc = tcc;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
}
