package br.com.appBancaria;

public enum EnumPessoa {
	PFISICA (0.0), 
	PJURIDICA (0.005);

	double taxa;
	
	EnumPessoa(double taxa) {
		this.taxa = taxa;
	}
}
