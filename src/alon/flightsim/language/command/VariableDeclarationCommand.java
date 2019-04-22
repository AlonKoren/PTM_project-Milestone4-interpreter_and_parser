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
    public int execute(List<String> arguments)
    {
        // validate arguments
//        System.out.println("----------------------------------");
//        env.getSymbolTable().entrySet().stream().forEach(new Consumer<Map.Entry<String, Double>>() {
//            @Override
//            public void accept(Map.Entry<String, Double> stringDoubleEntry) {
//                System.out.println(stringDoubleEntry.getKey()+"\t:\t"+stringDoubleEntry.getValue());
//            }
//        });
//        System.out.println("----------------------------------");
//        System.out.println("**********************************");
//        env.getBindTable().entrySet().stream().forEach(new Consumer<Map.Entry<String, String>>() {
//            @Override
//            public void accept(Map.Entry<String, String> stringDoubleEntry) {
//                System.out.println(stringDoubleEntry.getKey()+"\t:\t"+stringDoubleEntry.getValue());
//            }
//        });
//        System.out.println("**********************************");
//        System.out.println("key word "+arguments.get(0));
        int n=0;
        if(arguments.get(0).equals("var"))
        {
            n++;
        }
        if (arguments.get(2+n).equals("bind"))
        {
            String key = arguments.get(0 + n);
            String path = arguments.get(3+n);

            if (path.startsWith("\"") && path.endsWith("\""))
                path =path.substring(path.indexOf("\"")+1,path.lastIndexOf("\""));
//            System.out.println("text="+path);
            env.addBind(key,path);
            return 5+n;
        }
        else
            {
                String key =arguments.get(0+n);
                List<String> stringList = arguments.subList(2 + n, arguments.indexOf(Lexer.EOL) );
                String collect = String.join("", stringList);
                Double value = ShuntingYard.calc(collect, env);
//              System.out.println(key+" now = "+value);
//              System.out.println("'"+key+"'='"+valuetext+"'");

                env.setValue(key,value);

//                env.getSymbolTable().put(key,value);
//                if (env.getBindTable().containsKey(key))
//                {
//                    env.getClient().set(env.getBindTable().get(key),value);
//                }
                return 3+n+stringList.size();
            }
    }

    @Override
    public String getName() {
        return CommandName;
    }
}
