package alon.flightsim.language.command;

import alon.flightsim.Environment;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintCommand implements Command
{
    public static final String CommandName="print";
    private final Environment env;

    public PrintCommand(Environment env) {
        this.env = env;
    }

    @Override
    public int execute(List<String> arguments)
    {
        // validate arguments
        String word = arguments.get(1);
        if (word.startsWith("\"") && word.endsWith("\""))
            word =word.substring(word.indexOf("\"")+1,word.lastIndexOf("\""));
        else {
            word = env.getValue(word)+"";
        }
        System.out.println(word);
        return 3;
    }

    @Override
    public String getName() {
        return CommandName;
    }
}
