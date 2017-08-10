package br.edu.ifpb.tcc.entity;

public enum Perfil {
	PESSOA("Pessoa"),
	EMPRESA("Empresa"),
	COORDENADOR("Coordenador"),
	ALUNO("Aluno");
	
	private String nome;
	
	Perfil(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	

}
