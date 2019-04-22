package alon.flightsim.language.command;

import alon.flightsim.Environment;

import java.util.List;

public class SleepCommand implements Command
{
    public static final String CommandName="sleep";
    private final Environment env;

    public SleepCommand(Environment env) {
        this.env = env;
    }

    @Override
    public int execute(List<String> words)
    {
//        System.out.println(words);
        try {
            Thread.sleep(Long.parseLong(words.get(1)));
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
        return 3;
    }

    @Override
    public String getName()
    {
        return CommandName;
    }
}
