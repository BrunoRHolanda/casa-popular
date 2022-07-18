package com.digix.challenge.holanda.ms.popular.home.domain.valueobjects;

import java.util.InputMismatchException;

public class Cpf implements ValueObject<String> {
    private final String cpf;

    public Cpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean validate() {
        if (this.cpf.equals("00000000000") ||
                this.cpf.equals("11111111111") ||
                this.cpf.equals("22222222222") || this.cpf.equals("33333333333") ||
                this.cpf.equals("44444444444") || this.cpf.equals("55555555555") ||
                this.cpf.equals("66666666666") || this.cpf.equals("77777777777") ||
                this.cpf.equals("88888888888") || this.cpf.equals("99999999999") ||
                (this.cpf.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(this.cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int) (this.cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            return (dig10 == this.cpf.charAt(9)) && (dig11 == this.cpf.charAt(10));
        } catch (InputMismatchException e) {
            return (false);
        }
    }

    @Override
    public String make() {
        return this.cpf;
    }
    public String toString() {
        return this.make();
    }
}
