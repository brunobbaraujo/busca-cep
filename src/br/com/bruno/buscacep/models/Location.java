package br.com.bruno.buscacep.models;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Location {
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting()
            .create();

    public Location(LocationApiRecord loc) {
        this.cep = loc.cep();
        this.rua = loc.logradouro();
        this.bairro = loc.bairro();
        this.cidade = loc.localidade();
        this.estado = loc.uf();
    }

    @Override
    public String toString() {
        return this.rua + ", " + this.bairro + ", " +
                this.cidade + " - " + this.estado + " (" + this.cep + ")";
    }

    static public String toJson(Location loc) {
        return gson.toJson(loc);
    }
}
