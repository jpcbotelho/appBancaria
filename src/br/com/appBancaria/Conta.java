package br.com.appBancaria;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public abstract class Conta implements Funcionalidades {

	//ATRIBUTOS
	private int idConta;
	private EnumPessoa tipoPessoa;
	private EnumConta tipoConta;
	private BigDecimal saldo;
	private double rend;

	
	//CONSTRUTORES
	public Conta(int idConta, EnumPessoa tipoPessoa, EnumConta tipoConta) {
		super();
		this.idConta = idConta;
		this.tipoPessoa = tipoPessoa;
		this.tipoConta = tipoConta;
		this.saldo = BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP);
	}	
	
	//GETTERS E SETTERS
	public int getIdConta() {
		return idConta;
	}
	
	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
	
	public EnumPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(EnumPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public EnumConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(EnumConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public double getRend() {
		return rend;
	}

	public void setRend(double rend) {
		this.rend = rend;
	}


	
	//MÉTODOS
	
	//abertura de conta
	@Override
	public abstract void abrirConta();

	//método sacar (sem taxa)
	@Override
	public void sacar(Conta origem, String valor) {
		if(Double.parseDouble(valor) > 0) {
			BigDecimal x = new BigDecimal(valor);
			BigDecimal taxa = null;
			
			if(origem.tipoPessoa == EnumPessoa.PJURIDICA) {
				taxa = x.multiply(new BigDecimal(EnumPessoa.PJURIDICA.taxa));
			}
			BigDecimal total = x.add(taxa);
			BigDecimal res = origem.saldo.subtract(total);
		
			//validacao de saldo suficiente
			if(res.doubleValue() >= 0) {
				origem.saldo = res;
				System.out.println("Saque de R$ "+x+" efetuado com sucesso!");
				
				if(taxa.doubleValue() != 0) {
					System.out.println("Foi cobrada uma taxa de R$ "+taxa.setScale(2, RoundingMode.HALF_UP)+" por essa operação.");
				}
			}else {
				System.out.println("O saque falhou! Saldo insuficiente.");
			}
		}else {
			System.out.println();
			System.out.println("O saque falhou! Valor não suportado.");
		}
	}

	@Override
	public void depositar(Conta destino, String valor) {
		if(Double.parseDouble(valor) > 0) {
			BigDecimal x = new BigDecimal(valor);
			destino.saldo = destino.saldo.add(x);
			System.out.println();
			System.out.println("Depósito de R$ "+x+" efetuado com sucesso!");
		}else {
			System.out.println();
			System.out.println("O depósito falhou! Valor não suportado.");
		}
	}

	@Override
	public void transferir(Conta origem, Conta destino, String valor) {
		if(Double.parseDouble(valor) > 0) {
			BigDecimal x = new BigDecimal(valor);
			BigDecimal taxa = null;
			
			if(origem.tipoPessoa == EnumPessoa.PJURIDICA) {
				taxa = x.multiply(new BigDecimal(EnumPessoa.PJURIDICA.taxa));
			}
			BigDecimal total = x.add(taxa);
			BigDecimal res = origem.saldo.subtract(total);
				
			//validacao de saldo suficiente
			if(res.doubleValue() >= 0) {
				origem.saldo = res;
				destino.saldo = destino.saldo.add(x);
				System.out.println("Transferência de R$ "+x+" para a conta "+destino.idConta+" efetuada com sucesso!");
				
				if(taxa.doubleValue() != 0) {
					System.out.println("Foi cobrada uma taxa de R$ "+taxa.setScale(2, RoundingMode.HALF_UP)+" por essa operação.");
				}
			}else {
				System.out.println("A transferência falhou! Saldo insuficiente.");
			}
		}else {
			System.out.println();
			System.out.println("A transferência falhou! Valor não suportado.");
		}
	}
	
	@Override
	public void investir(Conta origem, String valor) {
		System.out.println();
		
		if(Double.parseDouble(valor) > 0) {	
			
			if(origem.getSaldo().doubleValue() >= Double.parseDouble(valor)) {
				
				double rend = Math.random();
				BigDecimal res = new BigDecimal(Double.parseDouble(valor)*rend);
				
				System.out.println("Você investiu R$ "+new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP));
				System.out.println("E obteve um rendimento de "+String.format("%.0f", rend*100)+"% = R$"+res.setScale(2, RoundingMode.HALF_UP));
				origem.setSaldo(origem.getSaldo().add(res));
				System.out.println("Saldo atual: "+origem.getSaldo().setScale(2, RoundingMode.HALF_UP));
			}else {
				System.out.println("O investimento falhou! Saldo insuficiente.");
			}
		}else {
			System.out.println();
			System.out.println("O investimento falhou! Valor não suportado.");
		}
	}

	@Override
	public void consultarSalto(Conta origem) {
		System.out.println();
		System.out.println("Seu saldo é de: ");
		System.out.println("𓇼 𓇼 𓇼 𓇼 𓇼 𓇼 𓇼 𓇼 𓇼 𓇼 R$ "+origem.getSaldo().setScale(2, RoundingMode.HALF_UP)+" 𓇼 𓇼 𓇼 𓇼 𓇼 𓇼 𓇼 𓇼 𓇼 𓇼");
		System.out.println();
	}
	
	public void render(Conta origem) {
		
		if(origem.tipoPessoa == EnumPessoa.PFISICA) {
			if(origem.tipoConta == EnumConta.CONTA_CORRENTE) {
				origem.rend = 0;
			}else {
				if(origem.tipoConta == EnumConta.CONTA_POUPANCA) {
					origem.rend = 0.005;
				}else {
					if(origem.tipoConta == EnumConta.CONTA_INVESTIMENTO) {
						origem.rend = 0.01;
					}
				}
			}
		}else {
			if(origem.tipoPessoa == EnumPessoa.PJURIDICA) {
				if(origem.tipoConta == EnumConta.CONTA_CORRENTE) {
					origem.rend = 0;
				}else {
					if(origem.tipoConta == EnumConta.CONTA_INVESTIMENTO) {
						origem.rend = 0.03;
					}
				}
			}
		}
		
		BigDecimal novoRend = new BigDecimal(1+origem.getRend());
		
		System.out.println();
		System.out.println("◞◜◝◟ˍ◞◜◝◟ˍ◞◜◝◟ˍ◞◜◝◟ˍ◞◜◝◟ˍ◞◜◝◟ˍ◞◜◝◟ˍ◞◜◝◟");
		System.out.println("〙〙〙CONTA Nº "+origem.getIdConta()+" - "+origem.getTipoPessoa()+" - "+origem.getTipoConta());
		System.out.println("〙〙〙Saldo anterior: R$ "+origem.getSaldo().setScale(2, RoundingMode.HALF_UP));
		System.out.println("〙〙〙Rendimento ("+origem.getRend()*100+"%): R$ "+origem.getSaldo().multiply(new BigDecimal(origem.rend)).setScale(2, RoundingMode.HALF_UP));
		origem.setSaldo(origem.getSaldo().multiply(novoRend).setScale(2, RoundingMode.HALF_UP));
		System.out.println("〙〙〙Saldo atual: R$ "+origem.getSaldo().setScale(2, RoundingMode.HALF_UP));
		System.out.println("◞◜◝◟ˍ◞◜◝◟ˍ◞◜◝◟ˍ◞◜◝◟ˍ◞◜◝◟ˍ◞◜◝◟ˍ◞◜◝◟ˍ◞◜◝◟");
	}
	
	//MÉTODO TOSTRING
	@Override
	public String toString() {
		return "Conta [idConta="+ idConta + 
				", tipoPessoa=" + tipoPessoa + 
				", tipoConta=" + tipoConta + 
				", saldo=" + saldo + "]";
	}
	
}
