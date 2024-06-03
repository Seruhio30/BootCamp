package com.alura.ScreenMatch.principal;

import com.alura.ScreenMatch.modelos.Titulo;
import com.alura.ScreenMatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalConBusqueda {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner lectura = new Scanner(System.in);
        System.out.println("Name of the movie: ");
        var busqueda = lectura.nextLine();

        String direccion = "http://www.omdbapi.com/?t="+busqueda.replace(" ","+")+"&apikey=ccb5432d&";
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            System.out.println(json);

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();
            TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);
            System.out.println(miTituloOmdb);

            Titulo  miTitulo = new Titulo(miTituloOmdb);
            System.out.println("Titulo ya convertido"+miTitulo);
        }catch (NumberFormatException e){
            System.out.println("Ocurrio un error");
            System.out.println(e.getMessage());
        }catch (IllegalArgumentException e){
            System.out.println("Error en la URI veriique la direccion");
        }catch (Exception e){
            System.out.println("ocurio un error inesperado");
        }
        System.out.println("Finalizo la ejecucion delprograma");
    }
}
