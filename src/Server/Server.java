package Server;

import java.util.Map;

public interface Server
{
    void listen(int port,int timeout);
    void stop();
    Thread serverThread();
    Map<String, Double> getData();
}
