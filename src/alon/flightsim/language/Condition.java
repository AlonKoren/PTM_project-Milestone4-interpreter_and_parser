package alon.flightsim.language;


import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Condition {

    private final int parsedWords;

    public Condition(Map<String, Double> symbolTable, List<String> args) {
        parsedWords = 5;
    }

    public int getParsedWords() {
        return parsedWords;
    }

    public boolean evaluate() {
        // TODO: impl
        return false;
    }
/*
    private boolean condition(String variable, String operator, String value)
    {
        switch (operator)
        {
            case "<":
                return env.getSymbolTable().get(variable) < Double.valueOf(value);
            case ">":
                return server.getData().get(variable) > Double.valueOf(value);
            case "<=":
                return server.getData().get(variable) <= Double.valueOf(value);
            case ">=":
                return server.getData().get(variable) >= Double.valueOf(value);
            case "==":
                return server.getData().get(variable).equals(Double.valueOf(value));
            case "!=":
                return !server.getData().get(variable).equals(Double.valueOf(value));
            default:
                return false;
        }
    }
*/
}
