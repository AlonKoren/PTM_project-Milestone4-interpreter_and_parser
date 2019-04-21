package alon.flightsim;

import alon.flightsim.client.Client;
import alon.flightsim.language.interpreter.Parser;
import alon.flightsim.server.Server;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Environment
{
    private Map<String, Double> symbolTable = new ConcurrentHashMap<>();
    private Client client;
    private Server server;
    private List<String> remainingScript;
    private Parser parser;

    public Map<String, Double> getSymbolTable()
    {
        return symbolTable;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public Server getServer()
    {
        return server;
    }

    public void setServer(Server server)
    {
        this.server = server;
    }

    public List<String> getRemainingScript()
    {
        return remainingScript;
    }

    public void setRemainingScript(List<String> remainingScript)
    {
        this.remainingScript = remainingScript;
    }

    public Parser getParser()
    {
        return parser;
    }
    public void setParser(Parser parser)
    {
        this.parser = parser;
    }



}
