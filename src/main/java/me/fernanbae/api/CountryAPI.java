package me.fernanbae.api;

import me.fernanbae.model.Country;
import me.fernanbae.model.CountryStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CountryAPI {
    private static final String API_URL = "https://raw.githubusercontent.com/Zortagon/kapita-falcon-backend/main/raw/json_country_advanced.json";

    public static List<Country> getCountries() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code " + conn.getResponseCode());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuilder response = new StringBuilder();
        String output;

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        JSONArray jsonArray = new JSONArray(new JSONTokener(response.toString()));
        List<Country> countries = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            String name     = jsonArray.getJSONObject(i).getString("name");
            String code     = jsonArray.getJSONObject(i).getString("code");
            int population  = jsonArray.getJSONObject(i).getInt("population");

            JSONObject status = jsonArray.getJSONObject(i).getJSONObject("status");

            String connection   = status.getString("connection");
            boolean peace       = status.getBoolean("peace");
            int latency         = status.getInt("latency");

            CountryStatus countryStatus = new CountryStatus(connection, peace, latency);

            Country country = new Country(name, code, population, countryStatus);

            countries.add(country);
        }

        conn.disconnect();
        return countries;
    }
}
