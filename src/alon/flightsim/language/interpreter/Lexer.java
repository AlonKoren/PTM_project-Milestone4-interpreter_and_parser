package alon.flightsim.language.interpreter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lexer
{
    public static final String EOL ="eol";

    public static List<String> Lexer(InputStreamReader streamReader)
    {
        List<String> words =new ArrayList<>();
        BufferedReader reader = new BufferedReader(streamReader);
        String line;
        try {
            while((line = reader.readLine())!=null){
                Scanner scanner=new Scanner(line);
                while(scanner.hasNext())
                {
                    String token = scanner.next();
                    if (!token.startsWith("\""))
                    {
//                String[] splittedByMathChars = token.split("((?<=[^A-Za-z0-9])|(?=[^A-Za-z0-9]))");
                        String[] splittedByMathChars = token.split("(?<=[-+*/()])|(?=[-+*/()])");

                        for (String word : splittedByMathChars)
                        {
                            words.add(word);
                        }
                    }
                    else
                    {
                        words.add(token);
                    }
                }
                words.add(EOL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public static List<String> Lexer(String script) {
        return Lexer(new InputStreamReader(new ByteArrayInputStream(script.getBytes())));
    }
}
