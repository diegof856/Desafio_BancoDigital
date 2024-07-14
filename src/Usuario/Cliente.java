package Usuario;

// Classe Cliente que representa um cliente do banco
public class Cliente {

	private String nome; // Nome do cliente
	private String email; // Email do cliente
	private Integer telefone; // Telefone do cliente

	// Construtor da classe Cliente
	public Cliente(String nome, String email, Integer telefone) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}

	// Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}
}
