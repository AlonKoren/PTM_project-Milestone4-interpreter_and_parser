package alon.flightsim.server;

import java.util.Map;

public interface Server
{
    void listen(int port,int timeout);
    void stop();
    Thread serverThread();
    Double getValue(String key);
    Map<String, Double> getData();
}
