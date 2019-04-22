package alon.flightsim;

import alon.flightsim.client.SimpleClient;
import alon.flightsim.language.interpreter.Lexer;
import alon.flightsim.language.interpreter.Parser;
import alon.flightsim.server.DataReaderServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Main {

    private static final Environment env = new Environment();
    public static void main(String[] args) {
        Parser parser = new Parser(env);
        env.setParser(parser);
        env.setServer(new DataReaderServer(env));
        env.setClient(new SimpleClient());
        File file=new File("./src/alon/flightsim/eli_script.txt");
        try {
            List<String> lexer = Lexer.Lexer(new FileReader(file));
//            lexer.stream().forEach(s -> System.out.print(s+","));
//            System.out.println();
            parser.parse(lexer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
