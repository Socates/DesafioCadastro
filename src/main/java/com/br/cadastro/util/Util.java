package com.br.cadastro.util;

public class Util {

    private Util() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean validarCpf(String cpf) {

        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11 || cpf.chars().distinct().count() == 1) {
            return false;
        }

        int soma = 0;
        int peso = 10;

        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso--;
        }

        int digito1 = 11 - (soma % 11);
        digito1 = (digito1 >= 10) ? 0 : digito1;

        if (digito1 != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        soma = 0;
        peso = 11;

        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso--;
        }

        int digito2 = 11 - (soma % 11);
        digito2 = (digito2 >= 10) ? 0 : digito2;

        return digito2 == Character.getNumericValue(cpf.charAt(10));
    }

}