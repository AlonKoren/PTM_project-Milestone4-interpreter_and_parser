package alon.flightsim.client;

public interface Client
{
    void connect(String ip, int port);
    void set(String path, Double value);
    Double getValue(String key);
    void close();
}