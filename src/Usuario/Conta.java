package Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import Banco.Banco;

// Classe abstrata Conta que define a estrutura básica de uma conta bancária
public abstract class Conta {

	private static Integer sequencia = 1; // Sequência usada para gerar números únicos de conta

	private LocalDate data; // Data de criação da conta
	private String agencia; // Nome da agência
	private Integer numeroConta; // Número da conta
	private Double saldo; // Saldo da conta
	private Cliente cliente; // Cliente associado à conta

	// Construtor da Conta
	public Conta(LocalDate data, String agencia, Cliente cliente) {
		this.data = data;
		this.agencia = agencia;
		this.numeroConta = sequencia++; // Incrementa a sequência para garantir número único
		this.cliente = cliente;
		this.saldo = 0.0; // Inicializa o saldo como 0.0
	}

	// Getters e Setters
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Double getSaldo() {
		return saldo;
	}

	// Adiciona ao saldo atual
	public void setSaldo(Double saldo) {
		this.saldo = +saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	// Método para sacar um valor do saldo da conta
	public void sacar(Double sacar) {
		this.saldo -= sacar;
	}

	// Método para verificar se há saldo suficiente para uma operação
	protected boolean verificarSaldo(Double saldoVerificacao) {
		if (this.saldo <= 0) {
			return false;
		} else if (this.saldo < saldoVerificacao) {
			return false;
		} else {
			return true;
		}
	}

	// Método para mostrar todos os clientes e suas contas
	public void mostrarTodosClientes(Banco b, List<Conta> contas, Conta contaAtual, Scanner sc) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("=======Contas Cadastradas Até o momento=======");
		contas.forEach(n -> System.out.printf(
				"Agencia: %s%nNumero da conta: %d%nNome do cliente: %s%nEmail: %s%nSaldo: %.2f%nConta criada no dia %s%n",
				n.getAgencia(), n.getNumeroConta(), n.getCliente().getNome(), n.getCliente().getEmail(), n.getSaldo(),
				n.getData().format(formatter)));
		System.out.println("=======Contas Cadastradas Até o momento=======");
		b.mostrarOpcoes(b, sc, contaAtual); // Mostra as opções do banco novamente
	}

	// Métodos abstratos que devem ser implementados pelas subclasses
	public abstract void mostrarDados(List<Conta> contas, Conta contaAtual);

	public abstract void mostrarClienteConta(List<Conta> contas, Conta contaAtual);

	public abstract void depositarDinheiro(Double deposito);

	public abstract void transferirDinheiro(Banco b, Scanner sc, List<Conta> contas, Double deposito,
			Conta contaDestino, Conta contaAtual);
}
