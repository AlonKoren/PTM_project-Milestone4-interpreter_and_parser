package alon.flightsim.language.command;


import alon.flightsim.Environment;
import alon.flightsim.language.Condition;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WhileCommand implements Command
{
    public static final String CommandName="while";
    private final Environment env;

    public WhileCommand(Environment env) {
        this.env = env;
    }

    @Override
    public int execute(List<String> args)
    {
        Condition condition = new Condition(env.getSymbolTable(), args);

        args = args.subList(condition.getParsedWords(), args.size() - 1);

//        if (!args.get(0).equals("{")) {
//            throw new RuntimeException("expected { and get "+args.get(0));
//        }

        int numOfWordsInTheBlock = findEndOfBlock(args);
        List<String> block = args.subList(1, numOfWordsInTheBlock);

        while (condition.evaluate()) {
            env.getParser().parse(block);
        }

        return 1 + condition.getParsedWords() + 1 + block.size() + 1+1;// while cond { \n block } \n
    }

    private int findEndOfBlock(List<String> args) {
        return args.indexOf("}")-1; //TODO: check inner loop in loop
    }

    @Override
    public String getName() {
        return CommandName;
    }


}