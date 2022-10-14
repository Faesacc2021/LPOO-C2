package model;

public class ContaEspecial extends Conta implements Transacao{

    double limite;

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public ContaEspecial(int numero, String nome, String cpf, Double limite) {
        super(numero, nome, cpf);
        this.limite = limite;
    }
    @Override
    public double saldo() {
        return this.valorNaConta + limite;
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
