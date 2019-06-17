package alon.flightsim.language.interpreter;


import alon.flightsim.Environment;
import alon.flightsim.language.command.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class Parser
{
    private final Map<String, Command> commands;
    private final Environment env;
    private volatile boolean isStop;
    Thread parserthread;
    volatile List<String> words;
    public Parser(Environment env)
    {
        isStop=true;
        this.env = env;

        commands = Stream.of(
                new PrintCommand(env),
                new ConnectToClientCommand(env),
                new OpenDataServerCommand(env),
                new VariableDeclarationCommand(env),
                new SleepCommand(env),
                new WhileCommand(env),
                new ReturnCommand(env),
                new DisconnectCommand(env)
        ).collect(toMap(Command::getName, identity()));
    }

    public void threadparse(final List<String> wordsfinal){
        List<String> words=wordsfinal;
        parserthread=new Thread(() -> parse(words));
        if (!isStop)
            parserthread.start();
    }

    public void parse(List<String> words)
    {
        while (!words.isEmpty() && !isStop) {
            Command command = commands.getOrDefault(words.get(0), commands.get(VariableDeclarationCommand.CommandName));
//            System.out.println(words.get(0));
            int numOfArgs = command.execute(words);
            words = words.subList(numOfArgs, words.size());
        }
    }

    public void addCommand(Command cmd)
    {
        commands.put(cmd.getName(), cmd);
    }

    public void stop(){
        isStop=true;
    }

    public void Resume(){
        isStop=false;
        if (parserthread!=null && !parserthread.isAlive())
            parserthread.start();
    }
    public boolean isStop(){
        return isStop;
    }
}