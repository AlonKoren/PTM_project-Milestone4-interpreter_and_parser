package alon.flightsim.language.command;

import alon.flightsim.Environment;
import alon.flightsim.language.interpreter.Lexer;
import alon.flightsim.language.interpreter.calculator.ShuntingYard;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class VariableDeclarationCommand implements Command {

    public static final String CommandName="var";
    private final Environment env;

    public VariableDeclarationCommand(Environment env) {
        this.env = env;
    }


    @Override
    public int execute(List<String> arguments){
        // validate arguments
//        System.out.println("----------------------------------");
//        env.getSymbolTable().entrySet().stream().forEach(new Consumer<Map.Entry<String, Double>>() {
//            @Override
//            public void accept(Map.Entry<String, Double> stringDoubleEntry) {
//                System.out.println(stringDoubleEntry.getKey()+"\t:\t"+stringDoubleEntry.getValue());
//            }
//        });
//        System.out.println("----------------------------------");
//        System.out.println("key word "+arguments.get(0));
        int n=0;
        if(arguments.get(0).equals("var"))
        {
            n++;
        }
        if (arguments.get(2+n).equals("bind")) {
            String word = arguments.get(3+n);

            if (word.startsWith("\"") && word.endsWith("\""))
                word =word.substring(word.indexOf("\"")+1,word.lastIndexOf("\""));
//            System.out.println("text="+word);
            env.getSymbolTable().put(arguments.get(0+n), env.getClient().getValue(word));
            return 5+n;
        }else {
            String key =arguments.get(0+n);
            List<String> stringList = arguments.subList(2 + n, arguments.indexOf(Lexer.EOL) );
            String collect = String.join("", stringList);
            Double value = ShuntingYard.calc(collect, env);
//            System.out.println(key+" now = "+value);
//            System.out.println("'"+key+"'='"+valuetext+"'");
            env.getSymbolTable().put(key,value);
            env.getClient().set(key,value);
            return 3+n+stringList.size();
        }
    }

    @Override
    public String getName() {
        return CommandName;
    }
}
