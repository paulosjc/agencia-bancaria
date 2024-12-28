Código criado simulando uma Agência Bancária contendo as contas corrente, poupança, salário e investimento. Cada tipo de conta tem uma tarifa diferente. 
Foi criada uma classe Conta com atributos como tipoConta, tipoCliente, dataAbertura e saldo.
Foram criados também métodos para esta classe como sacar(), depositar() e calcularTarifaManutencao().
O programa verifica se o valor sendo sacado é menor do que o saldo de todas as contas. Em caso positivo, o saque é efetuado com sucesso. Caso contrário, o saque não é realizado e uma mensagem de saldo insuficiente é mostrada ao usuário.
Como o saldo existe em todas as contas, é utilizado um código transversal usando o paradigma de programação orientada a aspectos.
O programa principal foi escrito em Java e o código transversal em uma extensão de Java orientada a aspectos chamada de AspectJ. 
