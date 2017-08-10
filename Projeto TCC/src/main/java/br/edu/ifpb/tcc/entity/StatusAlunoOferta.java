package br.edu.ifpb.tcc.entity;

public enum StatusAlunoOferta {
	PENDENTE_DE_APROVACAO("Pendente de aprovação"),
	APROVADO("Aprovado");
	
	private String nome;
	
	StatusAlunoOferta(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	

}
