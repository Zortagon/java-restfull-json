package me.fernanbae.service;

import me.fernanbae.api.CountryAPI;
import me.fernanbae.model.Country;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class CountryService {
    public static @NotNull List<Country> getCountries() throws IOException {
        return CountryAPI.getCountries();
    }
}
