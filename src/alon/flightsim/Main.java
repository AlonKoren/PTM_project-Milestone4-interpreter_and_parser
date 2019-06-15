package alon.flightsim;

import alon.flightsim.client.SimpleClient;
import alon.flightsim.language.interpreter.Lexer;
import alon.flightsim.language.interpreter.Parser;
import alon.flightsim.server.DataReaderServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Main
{
//    private static final Environment env = new Environment();

    public static void main(String[] args)
    {
        File file=new File("../PTM2 Project/scripts/script.txt");
        runSimulator(file);
    }



    public static void runSimulator(File fileScript){
        final Environment env=new Environment();
        Parser parser = new Parser(env);
        env.setParser(parser);
        env.setServer(new DataReaderServer(env));
        env.setClient(new SimpleClient());
        try
        {
            List<String> lexer = Lexer.Lexer(new FileReader(fileScript));
//            lexer.stream().forEach(s -> System.out.print(s+","));
//            System.out.println();
            parser.parse(lexer);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }



}
