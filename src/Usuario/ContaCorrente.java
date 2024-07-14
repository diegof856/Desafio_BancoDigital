package Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import Banco.Banco;

// Classe ContaCorrente que herda de Conta
public class ContaCorrente extends Conta {

	private String tipoConta; // Tipo da conta

	// Construtor da ContaCorrente
	public ContaCorrente(LocalDate data, String agencia, Cliente cliente) {
		super(data, agencia, cliente);
		this.tipoConta = "Conta Corrente"; // Define o tipo da conta
	}

	// Getter para o tipo da conta
	public String getTipoConta() {
		return tipoConta;
	}

	// Método para mostrar os dados da conta
	@Override
	public void mostrarDados(List<Conta> contas, Conta contaAtual) {
		System.out.println("======Dados cadastrados=========");
		Optional<Conta> conta = contas.stream().filter(n -> n.equals(contaAtual)).findFirst();

		conta.ifPresent(
				n -> System.out.printf("Agencia: %s%nNumero da conta: %d%n", n.getAgencia(), n.getNumeroConta()));
		mostrarClienteConta(contas, contaAtual); // Chama o método para mostrar os dados do cliente
	}

	// Método para mostrar os dados do cliente e da conta
	@Override
	public void mostrarClienteConta(List<Conta> contas, Conta contaAtual) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		Optional<Conta> conta = contas.stream().filter(n -> n.equals(contaAtual)).findFirst();

		conta.ifPresent(n -> System.out.printf(
				"Nome do cliente: %s%nEmail: %s%nAgencia: %s%ne seu saldo é de: %.2f%nConta criada no dia %s%ne tipo de conta é ",
				n.getCliente().getNome(), n.getCliente().getEmail(), n.getAgencia(), n.getSaldo(),
				n.getData().format(formatter)));
		System.out.println(this.tipoConta); // Mostra o tipo da conta
		System.out.println();
	}

	// Método para depositar dinheiro na conta
	@Override
	public void depositarDinheiro(Double deposito) {
		this.setSaldo(deposito); // Atualiza o saldo com o depósito
	}

	// Método para transferir dinheiro para outra conta
	@Override
	public void transferirDinheiro(Banco b, Scanner sc, List<Conta> contas, Double deposito, Conta contaDestino,
			Conta contaAtual) {
		if (verificarSaldo(deposito)) { // Verifica se há saldo suficiente para a transferência
			contaAtual.sacar(deposito); // Deduz o valor da conta atual
			contaDestino.depositarDinheiro(deposito); // Adiciona o valor na conta de destino
			contaAtual.mostrarClienteConta(contas, contaDestino); // Mostra os dados do cliente da conta de destino
			contaAtual.mostrarClienteConta(contas, contaAtual); // Mostra os dados do cliente da conta atual

			b.mostrarOpcoes(b, sc, contaAtual); // Mostra as opções do banco novamente
		} else {
			System.out.println("Não há saldo suficiente"); // Mensagem de erro se não houver saldo suficiente
		}
	}
}
