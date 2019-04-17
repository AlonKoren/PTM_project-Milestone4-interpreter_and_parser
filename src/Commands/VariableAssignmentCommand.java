package Commands;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableAssignmentCommand implements Command
{
    private Map<String, String> symbolTable;

    public VariableAssignmentCommand( Map<String, String> symbolTable)
    {
        this.symbolTable = symbolTable;
    }

    @Override
    public int doCommand(ArrayList<String> arguments)
    {
        if (arguments.get(2).equals("bind")) {
            String word = arguments.get(3);
            Pattern pattern = Pattern.compile("\"*\"");
            Matcher matcher = pattern.matcher(word);
            if (matcher.find())
                word =matcher.group();
            symbolTable.put(arguments.get(0), word);
            return 4;
        }else {
            symbolTable.put(arguments.get(0), symbolTable.get(arguments.get(2)));
            return 4;
        }
    }
}
