package alon.flightsim;

import alon.flightsim.client.Client;
import alon.flightsim.language.interpreter.Parser;
import alon.flightsim.server.Server;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Environment
{
    private Map<String, String> bindTable = new ConcurrentHashMap<>();
    private Map<String, Double> symbolTable = new ConcurrentHashMap<>();
    private Client client;
    private Server server;
    private List<String> remainingScript;
    private Parser parser;


    public Map<String, String> getBindTable()
    {
        return bindTable;
    }

    public Double getBind(String bindTableKey)
    {
        String path = this.bindTable.get(bindTableKey);
        Double value = this.client.getValue(path);
        this.setSymbol(bindTableKey,value);
        return value;
    }
    public void setBind(String bindTableKey,Double value)
    {
        String path = this.bindTable.get(bindTableKey);
        this.client.set(path,value);
        this.setSymbol(bindTableKey,value);
    }

    public Double getSymbol(String symbolTableKey)
    {
        return this.symbolTable.get(symbolTableKey);
    }
    public void setSymbol(String symbolTableKey,Double value)
    {
        this.symbolTable.put(symbolTableKey,value);
    }

    public void addBind(String bindTableKey,String path)
    {
        this.bindTable.put(bindTableKey,path);
    }

    public void addSymbol(String symboTableKey,Double value)
    {
        this.symbolTable.put(symboTableKey,value);
    }
    public Double getValue(String key)
    {
        if (bindTable.containsKey(key))
            return this.getBind(key);
        else
            if (symbolTable.containsKey(key))
                return this.getSymbol(key);
            throw new RuntimeException("key not found in tables for key="+key);
    }
    public void setValue(String key,Double value)
    {
        if (bindTable.containsKey(key))
            this.setBind(key,value);
        else
            if (symbolTable.containsKey(key))
                this.setSymbol(key,value);
            else
                this.addSymbol(key,value);
    }

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
