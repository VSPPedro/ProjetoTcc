package br.edu.ifpb.tcc.entity;

public enum StatusTcc {
	PENDENTE_DE_APROVACAO("Aguardando aprovação"),
	FECHADO("Fechado"),
	ATIVO("Ativo"),
	NEGADO("Negado"),
	APROVADO("Aprovado");
	
	private String nome;
	
	StatusTcc(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	

}
