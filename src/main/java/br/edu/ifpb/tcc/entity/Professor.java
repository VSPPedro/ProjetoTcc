package br.edu.ifpb.tcc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "TB_PROFESSOR")
@DiscriminatorValue(value = "P")
public class Professor extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "ATIVO_PROF")
	private boolean ativa = true;
	@Column(name = "NOME_PROF")
	private String nome;
	
	@Column(name = "TELEFONE_PROF")
	private String telefone;
	
	@ManyToOne
	@JoinColumn(name="curso_id", foreignKey = @ForeignKey(name = "fk_curso"))
	private Curso curso;
	
	@ManyToMany(mappedBy = "banca")
    private List<Tcc> tccs;
	
	public Professor(){}

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

	public boolean isAtiva() {
		return ativa;
	}
	
	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
}
