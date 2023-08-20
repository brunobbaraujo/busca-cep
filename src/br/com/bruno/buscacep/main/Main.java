package br.com.bruno.buscacep.main;

import br.com.bruno.buscacep.models.*;

import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reading = new Scanner(System.in);
        System.out.println("Digite um CEP para buscar: ");
        Cep cep = new Cep(reading.nextLine());

        try {
            LocationApiRecord record = new ViaCepApi(ViaCepResponseFormats.json).getLocation(cep);
            Location loc = new Location(record);
            FileWriter writer = new FileWriter("cep.json");
            writer.write(Location.toJson(loc));
            writer.close();
            System.out.println(loc);
        } catch (Exception e) {
            System.out.println("Houve um erro: " + e);
        } finally {
            System.out.println("Programa finalizado.");
        }
    }
}
