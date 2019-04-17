package Commands;

import Server.Server;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrintCommand implements Command
{
    private Map<String, String> symbolTable;

    public PrintCommand(Map<String, String> symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public int doCommand(ArrayList<String> arguments)
    {
        // validate arguments
        String word = arguments.get(1);
        Pattern pattern = Pattern.compile("\"*\"");
        Matcher matcher = pattern.matcher(word);
        if (matcher.find())
            System.out.println(matcher.group());
        else
            System.out.println(symbolTable.get(word));
        return 2;
    }
}
