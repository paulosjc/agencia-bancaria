import java.time.LocalDate;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;

// Classe base Conta
abstract class Conta {
    private String tipoConta;
    private String tipoCliente;
    private String identificacao;
    private LocalDate dataAbertura;
    private double saldo;

    public Conta(String tipoConta, String tipoCliente, String identificacao, LocalDate dataAbertura, double saldoInicial) {
        this.tipoConta = tipoConta;
        this.tipoCliente = tipoCliente;
        this.identificacao = identificacao;
        this.dataAbertura = dataAbertura;
        this.saldo = saldoInicial;
    }

    public void sacar(double valor) {
        saldo -= valor;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public abstract double calcularValorTarifaManutencao();

    // Getters e Setters
    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

// Conta Corrente
class ContaCorrente extends Conta {
    public ContaCorrente(String tipoCliente, String identificacao, LocalDate dataAbertura, double saldoInicial) {
        super("conta corrente", tipoCliente, identificacao, dataAbertura, saldoInicial);
    }

    @Override
    public double calcularValorTarifaManutencao() {
        return 20.0;
    }
}

// Conta Salário
class ContaSalario extends Conta {
    public ContaSalario(String tipoCliente, String identificacao, LocalDate dataAbertura, double saldoInicial) {
        super("conta salario", tipoCliente, identificacao, dataAbertura, saldoInicial);
    }

    @Override
    public double calcularValorTarifaManutencao() {
        return 5.0;
    }
}

// Conta Poupança
class ContaPoupanca extends Conta {
    public ContaPoupanca(String tipoCliente, String identificacao, LocalDate dataAbertura, double saldoInicial) {
        super("conta poupanca", tipoCliente, identificacao, dataAbertura, saldoInicial);
    }

    @Override
    public double calcularValorTarifaManutencao() {
        return 0.0;
    }
}

// Conta Investimento
class ContaInvestimento extends Conta {
    public ContaInvestimento(String tipoCliente, String identificacao, LocalDate dataAbertura, double saldoInicial) {
        super("conta investimento", tipoCliente, identificacao, dataAbertura, saldoInicial);
    }

    @Override
    public double calcularValorTarifaManutencao() {
        return 30.0;
    }
}

// Aspecto para verificar saldo
@Aspect
class VerificarSaldoAspect {
    @Before("execution(* Conta.sacar(..)) && args(valor)")
    public void verificarSaldo(JoinPoint joinPoint, double valor) {
        Conta conta = (Conta) joinPoint.getTarget();
        if (valor > conta.getSaldo()) {
            throw new RuntimeException("Saldo insuficiente para realizar o saque de R$ " + valor + ". Saldo atual: R$ " + conta.getSaldo());
        }
    }
}

// Classe principal
public class AgenciaBancaria {
    public static void main(String[] args) {
        try {
            Conta contaCorrente = new ContaCorrente("PF", "123.456.789-00", LocalDate.now(), 1000.0);
            Conta contaSalario = new ContaSalario("PF", "987.654.321-00", LocalDate.now(), 500.0);
            Conta contaPoupanca = new ContaPoupanca("PF", "123.123.123-12", LocalDate.now(), 2000.0);
            Conta contaInvestimento = new ContaInvestimento("PJ", "12.345.678/0001-99", LocalDate.now(), 5000.0);

            // Testes de operações
            contaCorrente.depositar(200.0);
            System.out.println("Depósito realizado na conta corrente. Saldo atual: R$ " + contaCorrente.getSaldo());

            contaCorrente.sacar(1200.0); // Deve lançar exceção

        } catch (RuntimeException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
