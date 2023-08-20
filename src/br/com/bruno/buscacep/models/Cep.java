package br.com.bruno.buscacep.models;

import br.com.bruno.buscacep.exceptions.InvalidCepException;

public class Cep {
    private final String cep;

    public Cep(String cep) {
        var cleanCep = cep.trim();
        if (cleanCep.length() != 8 && cleanCep.length() != 9) {
            throw new InvalidCepException("Cep '%s' inválido.".formatted(cleanCep));
        }
        try {
            this.cep = "%8d".formatted(
                    Integer.valueOf(cleanCep.replace("-", ""))
            );
        } catch (NumberFormatException e) {
            throw new InvalidCepException("Cep '%s' inválido.".formatted(cleanCep));
        }
    }

    @Override
    public String toString() {
        return this.cep;
    }
}
