package viewModel;

import data.DataConta;
import model.Conta;
import model.ContaEspecial;
import util.ContaStrings;
import util.ValidNumber;

import java.text.NumberFormat;
import java.util.Scanner;

public class OperacoesConta {

    private static final Scanner ler = new Scanner(System.in);

    public static void cadastrar() {
        int numero;
        double limite;
        String nome, cpf, especial;

        numero = ValidNumber.checkInt(ContaStrings.CONTA_NUMERO);

        if (DataConta.consultaData(numero) != -1) {
            System.out.println(ContaStrings.CONTA_EXISTENTE);
            return;
        }

        System.out.println(ContaStrings.CONTA_NOME);
        nome = ler.nextLine();

        System.out.println(ContaStrings.CONTA_CPF);
        cpf = ler.nextLine();

        System.out.println(ContaStrings.CONTA_ESPECIAL);
        especial = ler.nextLine();

        if (especial.equals("S") || especial.equals("s")) {
            limite = ValidNumber.checkDouble(ContaStrings.CONTA_LIMITE);
            DataConta.incluiData(new ContaEspecial(numero, nome, cpf, limite));
        } else {
            DataConta.incluiData(new Conta(numero, nome, cpf));
        }
        System.out.println(ContaStrings.CONTA_CADASTRADA);
    }

    public static void consultarSaldo() {
        int arrayPosition = pedeContaAndChecaNoData(ContaStrings.CONTA_NUMERO);
        if (arrayPosition == -1) {
            return;
        }
        Conta conta = DataConta.getContasArray().get(arrayPosition);
        System.out.println(ContaStrings.CONTA_SALDO + conta.getNome() + " " + moedaMask(DataConta.saldoData(arrayPosition)) + "\n");
    }

    private static String moedaMask (Double valor) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(valor);
    }

    public static void realizarSaque() {
        int arrayPosition = pedeContaAndChecaNoData(ContaStrings.CONTA_NUMERO);
        if (arrayPosition == -1) {
            return;
        }

        double valorSaque = ValidNumber.checkDouble(ContaStrings.CONTA_VALOR_SAQUE);

        if (DataConta.saqueData(arrayPosition, valorSaque)) {
            System.out.println(ContaStrings.SAQUE_REALIZADO);
        } else {
           System.out.println(ContaStrings.SALDO_INSUFICIENTE);
        }
   }

    public static void realizarDeposito() {
        int arrayPosition = pedeContaAndChecaNoData(ContaStrings.CONTA_NUMERO);
        if (arrayPosition == -1) {
            return;
        }
        double valorSaque =  ValidNumber.checkDouble(ContaStrings.CONTA_DEPOSITO);

        DataConta.depositoData(arrayPosition, valorSaque);
        System.out.println(ContaStrings.DEPOSITO_REALIZADO);
    }

    public static void realizarTransferencia() {
        int arrayPositionOrigem = pedeContaAndChecaNoData(ContaStrings.CONTA_ORIGEM);
        if (arrayPositionOrigem == -1) {
            return;
        }

        int arrayPositionDestino = pedeContaAndChecaNoData(ContaStrings.CONTA_DESTINO);
        if (arrayPositionDestino == -1) {
            return;
        }

        double valorTransferencia = ValidNumber.checkDouble(ContaStrings.VALOR_TRANSFERENCIA);

        if (DataConta.transferenciaData(arrayPositionOrigem, arrayPositionDestino, valorTransferencia)){
            System.out.println(ContaStrings.TRANSFERENCIA_REALIZADA);
        }else {
            System.out.println(ContaStrings.SALDO_INSUFICIENTE);
        }
    }

    public static void imprimeContas() {
        for (Conta conta : DataConta.getContasArray()) {
            if (conta.getClass() == ContaEspecial.class) {
               double usoLimite = calculaUsoLimite(conta);
                System.out.println(ContaStrings.CONTA_SALDO
                        + "Nº "
                        + conta.getNumero()
                        + " "
                        + conta.getNome()
                        + " "
                        + moedaMask(conta.saldo())
                        + " - Limite especial : "
                        + moedaMask(((ContaEspecial) conta).getLimite())
                        + " - Uso do Limite : "
                        + moedaMask(usoLimite));
            }else {
                System.out.println(ContaStrings.CONTA_SALDO
                        + "Nº "
                        + conta.getNumero()
                        + " "
                        + conta.getNome()
                        + " "
                        + moedaMask(conta.saldo()));
            }
        }
    }

    private static double calculaUsoLimite(Conta conta) {
        if (conta.saldo() < ((ContaEspecial) conta).getLimite()){
            return ((ContaEspecial) conta).getLimite() - conta.saldo();
        }
        return 0.00;
    }

    private static int pedeContaAndChecaNoData(String message) {
        int numeroConta = ValidNumber.checkInt(message);
        int arrayPosition = DataConta.consultaData(numeroConta);
        if (arrayPosition == -1) {
            System.out.println(ContaStrings.CONTA_INEXISTENTE);
        }
        return arrayPosition;
    }
}