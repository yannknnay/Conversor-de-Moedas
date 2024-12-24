package controller;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Currency;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class APIService {
    String apiKey;
    String baseURL;
    HttpClient client;

    public APIService() {
        apiKey = "bf596819cf019e0225e3a52b";
        baseURL = "https://v6.exchangerate-api.com/v6/";
        client = HttpClient.newHttpClient();
    }

    public Currency creatCurrency(String baseCode){
        String endpoint = baseURL + apiKey + "/latest/" + baseCode;
        System.out.println("Endpoint: " + endpoint);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Authorization", "Bearer " + apiKey)
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .setPrettyPrinting()
                        .create();
                return gson.fromJson(response.body(),Currency.class);
            }
            return null;

        } catch (RuntimeException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
