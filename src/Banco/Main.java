package Banco;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // Cria um objeto Scanner para ler a entrada do usuário

		Banco b = new Banco(); // Cria um novo objeto Banco
		b.cadastrarCliente(b, sc); // Chama o método para cadastrar um cliente no banco
	}
}
