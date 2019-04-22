package alon.flightsim.language.command;

import alon.flightsim.Environment;
import alon.flightsim.language.Condition;
import alon.flightsim.language.interpreter.Lexer;

import java.util.ArrayList;
import java.util.List;

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
        Condition condition = new Condition(env, args);

        int condsize =args.indexOf("{");
        List<String> block = args.subList(args.indexOf(Lexer.EOL)+1, args.indexOf("}"));
//        System.out.println("condition="+args.subList(0,args.indexOf("{")));
//        System.out.println("condsize="+condsize);
//        System.out.println("block="+block);
//        System.out.println(block.size());
//        if (!args.get(0).equals("{")) {
//            throw new RuntimeException("expected { and get "+args.get(0));
//        }
//        System.out.println(block);
        while (condition.evaluate()) {
            env.getParser().parse(block);
        }

        return 1 + condsize + 1 + block.size() + 1+1;// while cond { \n block } \n
    }

    private int findEndOfBlock(List<String> args) {
        return args.indexOf("}"); //TODO: check inner loop in loop
    }

    @Override
    public String getName() {
        return CommandName;
    }


}