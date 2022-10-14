package util;

import java.util.Scanner;

public class ValidNumber {

    static Scanner ler = new Scanner(System.in);

    public static Double checkDouble(String message) {
        double valorDigitado = 0.0;
        boolean valueIsNumber = false;
        System.out.println(message);
        while (!valueIsNumber) {
            String valor = ler.nextLine();
            try {
                valorDigitado = Double.parseDouble(valor);
                valueIsNumber = true;
            } catch (NumberFormatException e) {
                System.out.println(ContaStrings.NUMERO_VALIDO);
            }
        }
        return valorDigitado;
    }

    public static Integer checkInt(String message) {
        int valorDigitado = 0;
        boolean valueIsNumber = false;
        System.out.println(message);
        while (!valueIsNumber) {
            String valor = ler.nextLine();
            try {
                valorDigitado = Integer.parseInt(valor);
                valueIsNumber = true;
            } catch (NumberFormatException e) {
                System.out.println(ContaStrings.NUMERO_VALIDO);
            }
        }
        return valorDigitado;
    }
}
