package alon.flightsim;

import alon.flightsim.client.Client;
import alon.flightsim.language.interpreter.Parser;
import alon.flightsim.server.Server;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Stream;

public class Environment
{
    private Map<String, String> bindTable = new ConcurrentHashMap<>();
    private Map<String, Double> symbolTable = new ConcurrentHashMap<>();
    private Client client;
    private Server server;
    private List<String> remainingScript;
    private Parser parser;
    private int returnValue = 0;
    private Queue<Double> defultValues=new ConcurrentLinkedQueue<>();

    public Queue<Double> getDefultValues() {
        return defultValues;
    }

    public int getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(int returnValue) {
        this.returnValue = returnValue;
    }

    public Map<String, String> getBindTable()
    {
        return bindTable;
    }

    public Double getBind(String bindTableKey)
    {
        String path = this.bindTable.get(bindTableKey);
        Double value = this.client.getValue(path);
        updateAllBinds(path,value);
        return value;
    }
    public void setBind(String bindTableKey,Double value)
    {
        String path = this.bindTable.get(bindTableKey);
        this.client.set(path,value);
        updateAllBinds(path,value);
    }

    private void updateAllBinds(String path,Double value){
        Stream<Map.Entry<String, String>> entryStream = this.bindTable.entrySet().stream()
                .filter(stringStringEntry -> stringStringEntry.getValue().equals(path));
        entryStream.forEach(stringStringEntry -> {
                    setSymbol(stringStringEntry.getKey(), value);
//                    System.out.println("change for "+stringStringEntry.getKey()+"="+value);
                });
//        this.symbolTable.entrySet().forEach(new Consumer<Map.Entry<String, Double>>() {
//            @Override
//            public void accept(Map.Entry<String, Double> stringDoubleEntry) {
//                System.out.println(stringDoubleEntry.getKey()+"|"+stringDoubleEntry.getValue());
//            }
//        });

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
        try {
            setValue(bindTableKey, this.getDefultValues().remove());
        }catch (NoSuchElementException e){

        }
//        getBind(bindTableKey);
    }

    public void addSymbol(String symboTableKey,Double value)
    {
        this.symbolTable.put(symboTableKey,value);
    }
    public Double getValue(String key)
    {
        if (bindTable.containsKey(key) && !client.isClose())
            return this.getBind(key);
        else if (symbolTable.containsKey(key))
            return this.getSymbol(key);
        throw new RuntimeException("key not found in tables for key="+key);
    }
    public void setValue(String key,Double value)
    {
        if (bindTable.containsKey(key) && !client.isClose())
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
