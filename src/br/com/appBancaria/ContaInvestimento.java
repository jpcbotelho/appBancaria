package br.com.appBancaria;

public class ContaInvestimento extends Conta {
		
	public ContaInvestimento(int idConta, EnumPessoa tipoPessoa, EnumConta tipoConta) {
		super(idConta, tipoPessoa, tipoConta);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void abrirConta() {
		System.out.println();
		System.out.println("    ✰✰✰✰✰✰✰✰✰✰ CONTA Nº "+this.getIdConta()+" ✰✰✰✰✰✰✰✰✰✰");
		System.out.println("☑☑☑ Conta Investimento aberta com sucesso! ☑☑☑");
		System.out.println();
	}
}
