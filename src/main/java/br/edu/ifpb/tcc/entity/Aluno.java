package br.edu.ifpb.tcc.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


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
	
	@OneToMany(mappedBy="aluno")  
	private List<OfertaAluno> ofertaAlunos;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tcc_id")
	private Tcc tcc;

    
	
	
	public void addOfertaAluno(OfertaAluno ofertaAluno){
		if(ofertaAlunos == null){
			this.ofertaAlunos = new ArrayList<OfertaAluno>();
		}
		this.ofertaAlunos.add(ofertaAluno);
	}

	public List<OfertaAluno> getOfertaAlunos() {
		return ofertaAlunos;
	}

	public void setOfertaAlunos(List<OfertaAluno> ofertaAlunos) {
		this.ofertaAlunos = ofertaAlunos;
	}

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
