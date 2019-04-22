package alon.flightsim.language.interpreter;


import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import alon.flightsim.Environment;
import alon.flightsim.language.command.*;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class Parser
{
    private final Map<String, Command> commands;
    private final Environment env;

    public Parser(Environment env) {
        this.env = env;

        commands = Stream.of(
                new PrintCommand(env),
                new ConnectToClientCommand(env),
                new OpenDataServerCommand(env),
                new VariableDeclarationCommand(env),
                new SleepCommand(env),
                new WhileCommand(env)
        ).collect(toMap(Command::getName, identity()));
    }

    public void parse(List<String> words)
    {
        while (!words.isEmpty())
        {
            Command command = commands.getOrDefault(words.get(0),commands.get(VariableDeclarationCommand.CommandName));
//            System.out.println(words.get(0));
            int numOfArgs = command.execute(words);
            words = words.subList(numOfArgs, words.size() );
        }
    }

    public void addCommand(Command cmd)
    {
        commands.put(cmd.getName(), cmd);
    }
}