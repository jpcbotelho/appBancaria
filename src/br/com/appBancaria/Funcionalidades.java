package br.com.appBancaria;

import java.math.BigDecimal;

public interface Funcionalidades {
	
	void abrirConta();

	void sacar(Conta origem, String valor);
	
	void depositar(Conta destino, String valor);
	
	void transferir(Conta origem, Conta destino, String valor);
	
	void investir(Conta origem, String valor);
	
	void consultarSalto(Conta origem);
}
