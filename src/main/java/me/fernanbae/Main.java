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
                    %n""", country.getName(), country.getCode(), country.getPopulation(), country.getStatus().getConnection(), country.getStatus().isPeace(), country.getStatus().getLatency());
        }
    }
}