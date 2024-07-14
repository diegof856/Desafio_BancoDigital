## Sistema de Banco em Java

Este projeto simula um sistema bancário básico usando os princípios de orientação a objetos em Java. Ele permite que os usuários cadastrem clientes, abram contas (corrente e poupança), realizem depósitos, saques, transferências e visualizem informações sobre suas contas.

### Estrutura do Projeto

#### Pacote Banco

##### Classe Banco

Atributos:
- `nome`: Nome do banco.
- `listaconta`: Lista de contas cadastradas no banco.

Métodos:
- `getNome` e `setNome`: Métodos de acesso para o nome do banco.
- `getListaconta`: Método de acesso para a lista de contas.
- `adicionarConta`: Adiciona uma conta à lista de contas.
- `removerConta`: Remove uma conta da lista com base no número da conta fornecido pelo usuário.
- `sacar`: Permite que um cliente saque dinheiro de sua conta.
- `cadastrarCliente`: Cadastra um novo cliente e abre uma conta (corrente ou poupança).
- `mostrarOpcoes`: Exibe um menu de operações disponíveis para o cliente.
- `digitarValorParaDeposito`: Realiza um depósito em uma conta.
- `tranferencia`: Transfere dinheiro de uma conta para outra.
- `mostrarSaldo`: Exibe o saldo da conta.

##### Classe Main

Método `main`:
- Inicializa um objeto `Scanner` para leitura de entrada do usuário.
- Cria uma instância de `Banco`.
- Inicia o processo de cadastro de um cliente.

#### Pacote Banco.interfaces

##### Interface IBanco

Define a interface para as operações básicas do banco:
- `cadastrarCliente`
- `removerConta`
- `sacar`
- `mostrarOpcoes`
- `digitarValorParaDeposito`
- `tranferencia`
- `mostrarSaldo`

#### Pacote Usuario

##### Classe Cliente

Atributos:
- `nome`: Nome do cliente.
- `email`: Email do cliente.
- `telefone`: Telefone do cliente.

Métodos:
- Métodos de acesso (get e set) para os atributos.

##### Classe Conta (abstract)

Atributos:
- `sequencia`: Número sequencial para identificação das contas.
- `data`: Data de criação da conta.
- `agencia`: Agência da conta.
- `numeroConta`: Número da conta.
- `saldo`: Saldo da conta.
- `cliente`: Cliente associado à conta.

Métodos:
- Métodos de acesso (get e set) para os atributos.
- `sacar`: Realiza um saque na conta.
- `verificarSaldo`: Verifica se há saldo suficiente para uma operação.
- `mostrarTodosClientes`: Mostra todos os clientes cadastrados.
- Métodos abstratos que devem ser implementados pelas subclasses:
  - `mostrarDados`
  - `mostrarClienteConta`
  - `depositarDinheiro`
  - `transferirDinheiro`

##### Classe ContaCorrente

Subclasse de `Conta` que representa uma conta corrente.

Atributos:
- `tipoConta`: Tipo da conta (Conta Corrente).

Métodos:
- Implementa os métodos abstratos de `Conta`:
  - `mostrarDados`
  - `mostrarClienteConta`
  - `depositarDinheiro`
  - `transferirDinheiro`

##### Classe ContaPoupanca

Subclasse de `Conta` que representa uma conta poupança.

Atributos:
- `tipoConta`: Tipo da conta (Conta Poupança).

Métodos:
- Implementa os métodos abstratos de `Conta`:
  - `mostrarDados`
  - `mostrarClienteConta`
  - `depositarDinheiro`
  - `transferirDinheiro`

### Fluxo de Operações

#### Inicialização

O programa é iniciado pela classe `Main`, que cria uma instância de `Banco` e começa o cadastro de um cliente.

#### Cadastro de Cliente

- O cliente fornece seu nome, email e telefone.
- O cliente escolhe o tipo de conta (corrente ou poupança).

#### Operações na Conta

Após o cadastro, um menu de operações é exibido ao cliente:
- Depositar dinheiro
- Sacar dinheiro
- Transferir dinheiro
- Mostrar saldo
- Mostrar todas as contas cadastradas
- Mostrar dados da conta
- Cadastrar uma nova conta
- Remover conta

#### Encerramento

As operações são repetidas conforme a escolha do cliente até que ele decida sair ou encerrar a aplicação.
