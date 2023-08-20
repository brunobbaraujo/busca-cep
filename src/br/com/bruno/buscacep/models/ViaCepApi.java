package br.com.bruno.buscacep.models;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepApi {
    private final String baseUrl = "https://viacep.com.br/ws";
    private String responseFormat;
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting()
            .create();

    public ViaCepApi(ViaCepResponseFormats responseFormat) {
        this.responseFormat = responseFormat.toString();
    }

    public void setResponseFormat(String responseFormat) {
        this.responseFormat = ViaCepResponseFormats.valueOf(responseFormat).toString();
    }

    public String getRawResponse(Cep cep) throws IOException, InterruptedException {
        var uri = "%s/%s/%s".formatted(this.baseUrl, cep, this.responseFormat);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public LocationApiRecord getLocation(Cep cep) throws IOException, InterruptedException {
        var rawResponse = getRawResponse(cep);
        return gson.fromJson(rawResponse, LocationApiRecord.class);
    }
}