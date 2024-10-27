package models;

import com.google.gson.Gson;
import resources.Config;

import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientRequest {

    public String getExchangeRate(String from, String target, int amount){
        Config config = new Config();
        String API_KEY = config.getAPIKEY();
        String URL = "https://v6.exchangerate-api.com/v6/";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + API_KEY + "/latest/" + from))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();

            ResponseAPI responseObj = gson.fromJson(response.body(), ResponseAPI.class);

            // Obtener dos monedas específicas
            double targetCurrency = responseObj.conversion_rates().get(target);

            String result = String.format("%.2f", amount * targetCurrency);

            return amount + " " + from + " -> " + result + " " + target;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
