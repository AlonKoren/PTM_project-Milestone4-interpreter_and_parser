package Commands;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableDeclarationCommand implements Command {

    private Map<String, String> symbolTable;

    public VariableDeclarationCommand(Map<String, String> symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Override
    public int doCommand(ArrayList<String> arguments){
        // validate arguments
        if (arguments.get(3).equals("bind")) {
            String word = arguments.get(4);
            Pattern pattern = Pattern.compile("\"*\"");
            Matcher matcher = pattern.matcher(word);
            if (matcher.find())
                word =matcher.group();
            symbolTable.put(arguments.get(1), word);
            return 5;
        }else {
            symbolTable.put(arguments.get(1), symbolTable.get(arguments.get(3)));
            return 4;
        }
    }
}
