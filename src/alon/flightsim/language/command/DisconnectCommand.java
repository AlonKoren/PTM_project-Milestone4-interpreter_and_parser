package alon.flightsim.language.command;

import alon.flightsim.Environment;
import alon.flightsim.language.interpreter.Lexer;
import alon.flightsim.language.interpreter.calculator.ShuntingYard;

import java.util.List;

public class DisconnectCommand implements Command
{
    public static final String CommandName="disconnect";
    private final Environment env;

    public DisconnectCommand(Environment env)
    {
        this.env = env;
    }

    @Override
    public int execute(List<String> arguments)
    {
        // validate arguments
        env.getClient().sendLine("bye");
        env.getClient().close();
        return 2;
    }

    @Override
    public String getName() {
        return CommandName;
    }
}
