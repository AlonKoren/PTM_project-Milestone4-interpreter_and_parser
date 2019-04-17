package Parser;

import Commands.Command;
import Commands.CommandHandler;

import java.util.ArrayList;

public class Parser
{
    public static void parse(ArrayList<String> words,CommandHandler commandHandler)
    {
        while (!words.isEmpty())
        {
            Command command = commandHandler.getCommand(words.get(0));
            int numOfArgs = command.doCommand(words);
            words = new ArrayList<>(words.subList(numOfArgs,words.size()));
        }
    }
}