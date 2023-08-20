package br.com.bruno.buscacep.exceptions;

public class InvalidCepException extends RuntimeException {
    private final String message;

    public InvalidCepException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
