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
	
	@Column(name = "MAT_ALUNO")
	private String matricula;
	
	@ManyToOne
	@JoinColumn(name="curso_id", foreignKey = @ForeignKey(name = "fk_curso"))
	private Curso curso;
	
	@OneToMany(mappedBy="aluno")  
	private List<OfertaAluno> ofertaAlunos;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tcc_id")
	private Estagio estagio;

    
	
	
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




	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}


	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	
}
