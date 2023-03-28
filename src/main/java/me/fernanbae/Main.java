package me.fernanbae;

import me.fernanbae.model.Country;
import me.fernanbae.service.CountryService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        for (Country country : CountryService.getCountries()) {
            System.out.printf("""
                    ----------------------------------
                    Nama: %s
                    Code: %s
                    Population: %s
                    Status:
                      - Connection: %s
                      - Peace: %s
                      - Latency: %s
                    %n""", country.name(), country.code(), country.population(), country.status().connection(), country.status().peace(), country.status().latency());
        }
    }
}