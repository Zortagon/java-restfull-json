package me.fernanbae;

import me.fernanbae.model.Country;
import me.fernanbae.service.CountryService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        for (Country country : CountryService.getCountries()) {
            System.out.println(country.name() + " " + country.code() + " " + country.population());
        }
    }
}