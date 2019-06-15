package alon.flightsim;

import alon.flightsim.client.SimpleClient;
import alon.flightsim.language.interpreter.Lexer;
import alon.flightsim.language.interpreter.Parser;
import alon.flightsim.server.DataReaderServer;

import java.io.*;
import java.util.List;

public class FlyMain
{
    private final Environment env = new Environment();

    public FlyMain(){
        Parser parser = new Parser(env);
        env.setParser(parser);
        env.setServer(new DataReaderServer(env));
        env.setClient(new SimpleClient());
    }

    public static void main(String[] args)
    {
        FlyMain flyMain=new FlyMain();
        File file=new File("../PTM2 Project/scripts/script.txt");
        flyMain.runSimulator(file);
        flyMain.env.getParser().Resume();

    }


    public void runSimulator(String... lines){
        for (String line : lines) {
            InputStream is = new ByteArrayInputStream(line.getBytes());
            // read it with BufferedReader
            List<String> lexer = Lexer.Lexer(new InputStreamReader(is));
            this.env.getParser().threadparse(lexer);
        }

    }

    public void runSimulator(File fileScript){
//        final Environment env=new Environment();
//        Parser parser = new Parser(env);
//        env.setParser(parser);
//        env.setServer(new DataReaderServer(env));
//        env.setClient(new SimpleClient());
        try
        {
            List<String> lexer = Lexer.Lexer(new FileReader(fileScript));
//            lexer.stream().forEach(s -> System.out.print(s+","));
//            System.out.println();
            this.env.getParser().threadparse(lexer);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    public void setAutoPilot(boolean autoPilot){
        if (autoPilot){
            this.env.getParser().Resume();
        }else if (!autoPilot){
            this.env.getParser().stop();
        }
    }

}
