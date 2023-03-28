package me.fernanbae.model;

public class Country {

    private final String name;
    private final String code;
    private final int population;
    private final CountryStatus status;

    public Country(String name, String code, int population, CountryStatus status) {
        this.name = name;
        this.code = code;
        this.population = population;
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public int getPopulation() {
        return this.population;
    }

    public CountryStatus getStatus() {
        return this.status;
    }

}
