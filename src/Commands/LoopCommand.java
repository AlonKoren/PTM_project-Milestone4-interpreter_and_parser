package Commands;


import Server.Server;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LoopCommand implements Command
{
    private CommandHandler CommandHandler;
    private Server server;

    public LoopCommand(Server server, CommandHandler CommandHandler)
    {
        this.server = server;
        this.CommandHandler = CommandHandler;
    }

    @Override
    public int doCommand(ArrayList<String> arguments)
    {
        // validate arguments
        String variable = arguments.get(1);
        String operator = arguments.get(2);
        String value = arguments.get(3);
        List<ArrayList<String>> listOfCommands = convertArgumentsToListOfCommands(arguments.toString());
        while (condition(variable, operator, value))
        {
            listOfCommands.forEach(command -> CommandHandler.getCommand(command.get(0)).doCommand(command));
        }
    }

    private boolean condition(String variable, String operator, String value)
    {
        switch (operator)
        {
            case "<":
                return server.getData().get(variable) < Double.valueOf(value);
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

    private List<ArrayList<String>> convertArgumentsToListOfCommands(String argumentsAsString)
    {
        // first line is while condition, last is line "}" - therefore we remove them
        List<String> listOfLines = Arrays.asList(argumentsAsString.split("\n"));
        listOfLines.remove(0);
        listOfLines.remove(listOfLines.size());
        return listOfLines.stream().map(line -> new ArrayList(Arrays.asList(line.split(" ")))).collect(Collectors.toList());
    }
}