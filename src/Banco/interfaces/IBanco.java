package Banco.interfaces;

import java.util.Scanner;

import Banco.Banco;
import Usuario.Conta;

// Interface IBanco que define os métodos que uma classe de banco deve implementar
public interface IBanco {

	// Método para cadastrar um novo cliente no banco
	void cadastrarCliente(Banco b, Scanner sc);

	// Método para remover uma conta do banco
	void removerConta(Scanner sc);

	// Método para sacar dinheiro de uma conta cadastrada
	void sacar(Scanner sc, Conta contaCadastrada);

	// Método para mostrar as opções disponíveis para uma conta cadastrada
	void mostrarOpcoes(Banco b, Scanner sc, Conta contaCadastrada);

	// Método para solicitar o valor a ser depositado em uma conta cadastrada
	void digitarValorParaDeposito(Banco c, Scanner sc, Conta contaCadastrada);

	// Método para realizar a transferência de dinheiro entre contas
	void tranferencia(Banco b, Scanner sc, Conta contaCadastrada);

	// Método para mostrar o saldo de uma conta cadastrada
	void mostrarSaldo(Banco b, Scanner sc, Conta conta);
}
