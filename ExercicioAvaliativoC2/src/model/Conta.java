package model;

public class Conta implements Transacao {

    int numero;
    String nome;
    String cpf;
    double valorNaConta;

    public Conta(int numero, String nome, String cpf) {
        this.numero = numero;
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    private double getValorNaConta() {
        return valorNaConta;
    }
    private void setValorNaConta(double valorNaConta) {
        this.valorNaConta = valorNaConta;
    }

    @Override
    public void deposito(double valor) {
        setValorNaConta(this.getValorNaConta() + valor);
    }

    @Override
    public boolean saque(double valor) {
        this.setValorNaConta(this.getValorNaConta() - valor);
        return false;
    }

    @Override
    public double saldo() {
        return getValorNaConta();
    }

    @Override
    public boolean transferencia(double valor, Conta outraConta) {
        if (this.saldo() < valor) {
            return false;
        } else {
            this.saque(valor);
            outraConta.deposito(valor);
        }
        return true;
    }
}
