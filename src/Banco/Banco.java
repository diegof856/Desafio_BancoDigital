package Banco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import Banco.interfaces.IBanco;
import Usuario.Cliente;
import Usuario.Conta;
import Usuario.ContaCorrente;
import Usuario.ContaPoupanca;

// Classe principal Banco implementando a interface IBanco
public class Banco implements IBanco {
	private String nome; // Nome do banco
	private List<Conta> listaconta = new ArrayList<>(); // Lista para armazenar as contas bancárias

	// Getter para o nome do banco
	public String getNome() {
		return nome;
	}

	// Setter para o nome do banco
	public void setNome(String nome) {
		this.nome = nome;
	}

	// Getter para a lista de contas
	public List<Conta> getListaconta() {
		return listaconta;
	}

	// Método para adicionar uma nova conta à lista
	public void adicionarConta(Conta conta) {
		this.listaconta.add(conta);
	}

	// Implementação do método removerConta da interface IBanco
	@Override
	public void removerConta(Scanner sc) {
		System.out.println("Digite o numero da conta para remove-la: ");
		int numeroconta = sc.nextInt();
		removerPorNumeroConta(numeroconta);
	}

	// Método privado para remover uma conta pelo número da conta
	private void removerPorNumeroConta(Integer numero) {
		Optional<Conta> contaRemover = this.listaconta.stream().filter(n -> n.getNumeroConta().equals(numero))
				.findFirst();

		contaRemover.ifPresent(valor -> this.listaconta.remove(valor));
	}

	// Implementação do método sacar da interface IBanco
	@Override
	public void sacar(Scanner sc, Conta contaCadastrada) {
		System.out.print("Digite o valor a ser sacado: ");
		Double sacar = sc.nextDouble();
		contaCadastrada.sacar(sacar);
	}

	// Implementação do método cadastrarCliente da interface IBanco
	@Override
	public void cadastrarCliente(Banco b, Scanner sc) {
		System.out.print("Digite seu nome: ");
		String nome = sc.nextLine();

		System.out.print("Agora digite o seu email: ");
		String email = sc.nextLine();

		System.out.print("Digite seu telefone: ");
		int numero = sc.nextInt();
		sc.nextLine();
		cadastrarConta(b, new Cliente(nome, email, numero), sc);
	}

	// Método privado para cadastrar uma nova conta
	private void cadastrarConta(Banco b, Cliente c, Scanner sc) {
		int opcao;

		System.out.print("Escreva o nome da sua Agencia: ");
		this.nome = sc.nextLine();
		while (true) {
			System.out.print("Escolha o Tipo de conta: ");
			System.out.println();
			System.out.println("1 - Conta Corrente");
			System.out.println("2 - Conta Poupança");
			System.out.print("Sua escolha: ");
			opcao = sc.nextInt();
			if (opcao == 1) {
				Conta cc = new ContaCorrente(LocalDate.now(), this.nome, c);
				adicionarConta(cc);
				cc.mostrarDados(this.listaconta, cc);
				mostrarOpcoes(b, sc, cc);
				break;
			} else if (opcao == 2) {
				Conta poupanca = new ContaPoupanca(LocalDate.now(), this.nome, c);
				adicionarConta(poupanca);
				poupanca.mostrarDados(this.listaconta, poupanca);
				mostrarOpcoes(b, sc, poupanca);
				break;
			} else if (opcao <= 0) {
				System.out.println("Opção invalida!! (0 não meu patrão)");
			} else {
				System.out.println("Opção invalida!! (escolha 1 ou 2)");
			}
		}
	}

	// Implementação do método mostrarOpcoes da interface IBanco
	@Override
	public void mostrarOpcoes(Banco b, Scanner sc, Conta contaCadastrada) {
		int opcao = 0;
		while (true) {
			System.out.println(
					"Olá Bem Vindo!!! " + contaCadastrada.getCliente().getNome() + " escolha uma das Opções: ");
			System.out.println("1 - Depositar Dinheiro");
			System.out.println("2 - Sacar Dinheiro");
			System.out.println("3 - Transferir");
			System.out.println("4 - Mostrar saldo");
			System.out.println("5 - Mostrar todas as contas cadastradas");
			System.out.println("6 - Mostrar dados da conta");
			System.out.println("7 - Cadastrar uma nova conta");
			System.out.println("8 - Remover Conta");
			System.out.print("Digite a opção: ");
			opcao = sc.nextInt();
			sc.nextLine();

			if (opcao == 1) {
				digitarValorParaDeposito(b, sc, contaCadastrada);
				break;
			} else if (opcao == 2) {
				sacar(sc, contaCadastrada);
				break;
			} else if (opcao == 3) {
				tranferencia(b, sc, contaCadastrada);
				break;
			} else if (opcao == 4) {
				mostrarSaldo(b, sc, contaCadastrada);
				break;
			} else if (opcao == 5) {
				contaCadastrada.mostrarTodosClientes(b, this.listaconta, contaCadastrada, sc);
				break;
			} else if (opcao == 6) {
				contaCadastrada.mostrarClienteConta(this.listaconta, contaCadastrada);
				mostrarOpcoes(b, sc, contaCadastrada);
				break;
			} else if (opcao == 7) {
				cadastrarCliente(b, sc);
				break;
			} else if (opcao == 8) {
				removerConta(sc);
				break;
			} else {
				System.out.println("Opção invalida");
			}
		}
	}

	// Implementação do método digitarValorParaDeposito da interface IBanco
	@Override
	public void digitarValorParaDeposito(Banco b, Scanner sc, Conta contaCadastrada) {
		System.out.print("Digite o valor para efetuar o deposito: ");
		double deposito = sc.nextDouble();
		sc.nextLine();
		contaCadastrada.depositarDinheiro(deposito);
		mostrarSaldo(b, sc, contaCadastrada);
		mostrarOpcoes(b, sc, contaCadastrada);
	}

	// Implementação do método tranferencia da interface IBanco
	@Override
	public void tranferencia(Banco b, Scanner sc, Conta contaCadastrada) {
		System.out.print("Digite o numero da conta da pessoa cujo valor vai ser transferido: ");
		int nomeTransferencia = sc.nextInt();
		sc.nextLine();
		Conta contaTransferida = contaTransferencia(nomeTransferencia);

		System.out.print("Digite o valor a ser transferido: ");
		double transferencia = sc.nextDouble();

		contaCadastrada.transferirDinheiro(b, sc, this.listaconta, transferencia, contaTransferida, contaCadastrada);

		mostrarOpcoes(b, sc, contaCadastrada);
	}

	// Método privado para obter a conta para transferência pelo número da conta
	private Conta contaTransferencia(Integer numero) {
		Optional<Conta> contaTransferir = this.listaconta.stream().filter(n -> n.getNumeroConta().equals(numero))
				.findFirst();
		return contaTransferir.orElseThrow(() -> new RuntimeException("Conta não encontrada"));
	}

	// Implementação do método mostrarSaldo da interface IBanco
	@Override
	public void mostrarSaldo(Banco b, Scanner sc, Conta conta) {
		System.out.println("O seu saldo é de " + conta.getSaldo());
		mostrarOpcoes(b, sc, conta);
	}
}
