package br.com.bruno.buscacep.models;

public record LocationApiRecord(String cep, String logradouro, String bairro, String localidade, String uf) {
}
