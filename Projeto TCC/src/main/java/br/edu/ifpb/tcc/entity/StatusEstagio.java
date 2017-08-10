package br.edu.ifpb.tcc.entity;

public enum StatusEstagio {
	PENDENTE_DE_APROVACAO("Aguardando aprovação"),
	FECHADO("Fechado"),
	ATIVO("Ativo"),
	NEGADO("Negado"),
	APROVADO("Aprovado");
	
	private String nome;
	
	StatusEstagio(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	

}
