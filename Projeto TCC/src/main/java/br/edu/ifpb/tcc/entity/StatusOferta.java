package br.edu.ifpb.tcc.entity;

public enum StatusOferta {
	PENDENTE_DE_APROVACAO("Pendente de aprovacao"),
	APROVADO("Aprovado"),
	CONCLUIDO("Concluido"),
	FINALIZADO("Finalizado");
	
	private String nome;
	
	StatusOferta(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	

}
