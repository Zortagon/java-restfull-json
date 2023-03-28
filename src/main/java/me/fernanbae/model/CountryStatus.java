package me.fernanbae.model;

public class CountryStatus {
    private final String connection;
    private final boolean peace;
    private final int latency;

    public CountryStatus(String connection, boolean peace, int latency) {
        this.connection = connection;
        this.peace = peace;
        this.latency = latency;
    }

    public String getConnection() {
        return connection;
    }

    public boolean isPeace() {
        return peace;
    }

    public int getLatency() {
        return latency;
    }
}
