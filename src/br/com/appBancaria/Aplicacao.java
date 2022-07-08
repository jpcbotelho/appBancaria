package br.com.appBancaria;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Aplicacao {
	
	static HashMap<Integer, Conta> clientes = new HashMap<>();
		
	public static void main(String[] args) {
				
		gerarClientes();
		cabecalho();
		menu();
	}
		
	public static void cabecalho() {
		System.out.println("------------------------------------------------");
		System.out.println("---------------BANCO PROJETOFINAL---------------");
		System.out.println("------------------------------------------------");
	}
	
	public static void menu() {
		System.out.println();
		System.out.println("▹▹▹▹▹▹▹▹▹▹▹▹▹▹▹▹▹▹ MENU ◃◃◃◃◃◃◃◃◃◃◃◃◃◃◃◃◃◃");
		System.out.println("0  - Abrir Conta");
		System.out.println("1  - Entrar na Minha Conta");
		System.out.println("2  - Depositar");
		System.out.println("3  - Render todas as contas");
		System.out.println("99 - SAIR");
		System.out.print(" ➽ Entre com o número da opção desejada: ");
		
		Scanner scan = new Scanner(System.in);
		int opcao = scan.nextInt();
			
		switch(opcao){
		case 0:
			abrirConta();
			break;
		case 1: 
			id();
			break;
		case 2: 
			depositar();
			break;
		case 3: 
			render();
			break;
		case 99:
			fim();
			break;
		default:
			System.out.println("Opção inválida! Tente novamente.");
			System.out.println();
			menu();
			break;
		};

	}

	public static void fim() {
		System.out.println();
		System.out.println(".................Fim do programa................");
	}
	
	private static void abrirConta() {
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~ ABERTURA DE CONTA ~~~~~~~~~~~~~~");
		System.out.println("1  - Pessoa Física");
		System.out.println("2  - Pessoa Jurídica");
		System.out.println("88 - RETORNAR ao menu inicial");
		System.out.println("99 - SAIR");
		System.out.print("➤ Entre com o número da opção desejada: ");
		
		Scanner scan = new Scanner(System.in);
		int pessoa = scan.nextInt();


		switch(pessoa){
			case 1:
				abrirContaPF();
				break;
			
			case 2: 
				abrirContaPJ();
				break;
				
			case 88:
				menu();
				break;
				
			case 99:
				fim();
				break;
				
			default:
				System.out.println("Opção inválida! Tente novamente.");
				System.out.println();
				abrirConta();
				break;	
		};
	}

	private static void abrirContaPF() {
		System.out.println();
		System.out.println("Tipo de Conta");
		System.out.println("1  - Conta Corrente");
		System.out.println("2  - Conta Poupança");
		System.out.println("3  - Conta Investimento");
		System.out.println("88 - RETORNAR ao menu inicial");
		System.out.println("99 - SAIR");
		System.out.print("➤➤ Entre com o número da opção desejada: ");
		
		Scanner scan = new Scanner(System.in);
		int opcao = scan.nextInt();
	
		switch(opcao){
		case 1:
			abrirCCorrente(1);
			break;
		
		case 2: 
			abrirCPoupanca();
			break;
			
		case 3: 
			abrirCInvestimento(1);
			break;
			
		case 88:
			menu();
			break;
			
		case 99:
			fim();
			break;

		default:
			System.out.println("Opção inválida! Tente novamente.");
			System.out.println();
			abrirContaPF();
			break;	
		};

	}

	private static void abrirContaPJ() {
		System.out.println();
		System.out.println("Tipo de Conta");
		System.out.println("1  - Conta Corrente");
		System.out.println("2  - Conta Investimento");
		System.out.println("88 - RETORNAR ao menu inicial");
		System.out.println("99 - SAIR");
		System.out.print("➤➤ Entre com o número da opção desejada: ");
		
		Scanner scan = new Scanner(System.in);
		int opcao = scan.nextInt();

		switch(opcao){
		case 1:
			abrirCCorrente(2);
			break;
		
		case 2: 
			abrirCInvestimento(2);
			break;

		case 88:
			menu();
			break;
			
		case 99:
			fim();
			break;
			
		default:
			System.out.println("Opção inválida! Tente novamente.");
			abrirContaPJ();
			break;	
		};	
	}

	private static void abrirCCorrente(int i) {
		int id = gerarIdValido();
		switch(i) {
		case 1:
			ContaCorrente cc1 = new ContaCorrente(id, EnumPessoa.PFISICA,EnumConta.CONTA_CORRENTE);
			cc1.abrirConta();
			clientes.put(id, cc1);
			menu();
			break;
			
		case 2:
			ContaCorrente cc2 = new ContaCorrente(id, EnumPessoa.PJURIDICA,EnumConta.CONTA_CORRENTE);
			cc2.abrirConta();
			clientes.put(id, cc2);
			menu();
			break;
			
		default:
			throw new IllegalStateException("Opção inválida " + i);
		}
	}
	
	private static void abrirCPoupanca() {
		int id = gerarIdValido();
		ContaPoupanca cp = new ContaPoupanca(id, EnumPessoa.PFISICA,EnumConta.CONTA_POUPANCA);
		cp.abrirConta();
		clientes.put(id, cp);
		menu();		
	}
	
	private static void abrirCInvestimento(int i) {
		int id = gerarIdValido();
		switch(i) {
		case 1:
			ContaInvestimento ci1 = new ContaInvestimento(id, EnumPessoa.PFISICA,EnumConta.CONTA_INVESTIMENTO);
			ci1.abrirConta();
			clientes.put(id, ci1);
			menu();
			break;
		case 2:
			ContaInvestimento ci2 = new ContaInvestimento(id, EnumPessoa.PJURIDICA,EnumConta.CONTA_INVESTIMENTO);
			ci2.abrirConta();
			clientes.put(id, ci2);
			menu();
			break;	
		default:
			throw new IllegalStateException("Opção inválida " + i);
		}	
	}

	private static void id() {
		Scanner scan = new Scanner(System.in);
		Conta cliente = null;
		
		System.out.println();
		System.out.print("Por favor, entre com o número da sua conta: ");
		int id = scan.nextInt();
		
		for(Integer key : clientes.keySet()) {
			if (id == key) {
				cliente = clientes.get(id);
				System.out.println();
				menuLoggado(cliente);
				break;
			}
		}
		if(cliente == null) {
			System.out.println();
			System.out.println("Ops!");
			System.out.println("Número de conta inválida!");
			System.out.println();	
			menu();
		}
	}


	private static void menuLoggado(Conta cliente) {
		System.out.println();
		System.out.println("▹▹▹▹▹▹▹▹▹▹▹▹ BANCO PROJETOFINAL ◃◃◃◃◃◃◃◃◃◃◃◃");
		System.out.println("〙〙〙CONTA Nº "+cliente.getIdConta()+" - "+cliente.getTipoPessoa()+" - "+cliente.getTipoConta()+"〘〘〘");
		System.out.println("1  - Sacar");
		System.out.println("2  - Transferir");
		System.out.println("3  - Investir");
		System.out.println("4  - Consultar Saldo");
		System.out.println("88 - RETORNAR ao menu inicial");
		System.out.println("99 - SAIR");
		System.out.print(" ➽ Entre com o número da opção desejada: ");
		
		Scanner scan = new Scanner(System.in);
		int opcao = scan.nextInt();
			
		switch(opcao){
		case 1:
			sacar(cliente);
			break;
		case 2:
			transferir(cliente);
		case 3:
			investir(cliente);
			break;
		case 4:
			consultarSaldo(cliente);
			break;
		case 88:
			menu();
			break;
		case 99:
			fim();
			break;
		default:
			System.out.println();
			System.out.println("Opção inválida! Tente novamente.");
			System.out.println();
			menuLoggado(cliente);
			break;
		};
		
	}
	


	private static void sacar(Conta cliente) {
		System.out.println();
		System.out.println("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ sacar ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
		System.out.println("Por favor, ");
		System.out.print("entre com o valor do saque: ");
		Scanner scan = new Scanner(System.in);
		String valor = scan.next();
		cliente.sacar(cliente, valor);
		menuLoggado(cliente);
		
	}
	
	private static void depositar() {
		Scanner scan = new Scanner(System.in);
		Conta cliente = null;
		
		System.out.println();
		System.out.println("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ depositar ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
		System.out.println("Por favor, ");
		System.out.print("entre com a conta de destino: ");
		int id = scan.nextInt();
		
		for(Integer key : clientes.keySet()) {
			if (id == key) {
				cliente = clientes.get(id);
				break;
			}
		}
		if(cliente == null) {
			System.out.println();
			System.out.println("Ops!");
			System.out.println("Número de conta inválida!");
			System.out.println();	
			menu();
		}
		
		System.out.print("entre com o valor do depósito: ");
		String valor = scan.next();
		cliente.depositar(cliente, valor);
		
		menu();
	}

	private static void transferir(Conta cliente) {
		Scanner scan = new Scanner(System.in);
		Conta destino = null;
		System.out.println();
		System.out.println("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ transferir ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
		System.out.println("Por favor, ");
		System.out.print("entre com a conta de destino: ");
		int id = scan.nextInt();
		
		for(Integer key : clientes.keySet()) {
			if (id == key) {
				destino = clientes.get(id);
				break;
			}
		}
		if(destino == null) {
			System.out.println();
			System.out.println("Ops!");
			System.out.println("Número de conta inválida!");
			System.out.println();	
			menuLoggado(cliente);
		}
		
		System.out.print("entre com o valor da transferência: ");
		String valor = scan.next();

		cliente.transferir(cliente, destino, valor);
		
		menuLoggado(cliente);
		
	}
	
	private static void consultarSaldo(Conta cliente) {
		cliente.consultarSalto(cliente);
		menuLoggado(cliente);
	}
	
	private static void investir(Conta cliente) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println();
		System.out.println("ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ investir ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
		System.out.println("Valor disponível para investimento: R$"+cliente.getSaldo().setScale(2, RoundingMode.HALF_UP));
		System.out.println("Por favor, ");
		System.out.print("entre com o valor que deseja investir: ");
		String valor = scan.next();
		cliente.investir(cliente, valor);
		
		menuLoggado(cliente);
	}
	
	private static void render() {
		Conta cliente = null;
		for(Integer key : clientes.keySet()) {
			cliente = clientes.get(key);
			cliente.render(cliente);	
		}
		menu();
	}
	
	
	private static int gerarIdValido() {
		Random rdm = new Random();
		int id = rdm.nextInt(9999);
		
		for ( Integer key : clientes.keySet() ) {
			if (id == key) {
				gerarIdValido();
			}
		}
			
		return id;
	}
	
	private static void gerarClientes() {
		ContaCorrente joaopaulocunhabotelho = new ContaCorrente(0001, EnumPessoa.PFISICA, EnumConta.CONTA_CORRENTE);
		ContaPoupanca joaopaulo = new ContaPoupanca(0002, EnumPessoa.PFISICA, EnumConta.CONTA_POUPANCA);
		ContaInvestimento cunhabotelho = new ContaInvestimento(0003, EnumPessoa.PFISICA, EnumConta.CONTA_INVESTIMENTO);
		ContaCorrente jp = new ContaCorrente(0004, EnumPessoa.PJURIDICA, EnumConta.CONTA_CORRENTE);
		ContaInvestimento cb = new ContaInvestimento(0005, EnumPessoa.PJURIDICA, EnumConta.CONTA_INVESTIMENTO);
		
		clientes.put(0001, joaopaulocunhabotelho);
		clientes.put(0002, joaopaulo);
		clientes.put(0003, cunhabotelho);
		clientes.put(0004, jp);
		clientes.put(0005, cb);
	}

}
