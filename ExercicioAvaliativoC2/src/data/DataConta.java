package data;
import model.Conta;
import model.ContaEspecial;
import util.ContaStrings;

import java.util.ArrayList;

public class DataConta {
    private static final ArrayList<Conta> contasData = new ArrayList<>();

    public static void incluiData(Conta conta) {
        contasData.add(conta);
    }

    public static ArrayList<Conta> getContasArray() {
        return contasData;
    }

    public static int consultaData(int numeroConta) {
        for (Conta conta : contasData) {
            if (conta.getNumero() == numeroConta) {
                return contasData.indexOf(conta);
            }
        }
       return -1;
    }

    public static double saldoData(int index) {
         return contasData.get(index).saldo()  ;
    }

    public static void depositoData (int index, double valor) {
        contasData.get(index).deposito(valor);
    }

    public static boolean saqueData(int index, double valor) {
        if (saldoData(index) < valor){
           return false;
        } else {
            contasData.get(index).saque(valor);
            return true;
        }
    }

    public static boolean transferenciaData(int indexOrigem, int indexDestino, Double valorTransferencia){
        return contasData.get(indexOrigem).transferencia(valorTransferencia, contasData.get(indexDestino));
    }
}
