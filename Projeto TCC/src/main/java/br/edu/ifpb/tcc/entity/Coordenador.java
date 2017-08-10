package br.edu.ifpb.tcc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_COOD")
@DiscriminatorValue(value = "C")
public class Coordenador extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
	@Column(name = "NOME_COOD")
	private String nome;
	
	@Column(name = "TELEFONE_COOD")
	private String telefone;
	
	/*@ManyToOne
	@JoinColumn(name="curso_id", foreignKey = @ForeignKey(name = "fk_curso"))
	private Curso curso;*/
	
	@OneToOne
	@JoinColumn(name="curso_id")
	private Curso curso;

	
	public Coordenador() {super();} 
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;

	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

	public Curso getCurso() {
		return curso;
	}



	public void setCurso(Curso curso) {
		this.curso = curso;
	}



	@Override
	public String toString() {
		return "Coordenador [nome=" + nome + ", telefone=" + telefone + "]";
	}
	
	
}
