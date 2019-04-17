package Lexer;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Lexer
{
    public static ArrayList<String> Lexer(InputStreamReader streamReader)
    {
        Scanner scanner=new Scanner(streamReader);
        ArrayList<String> stringArrayList=new ArrayList<>();
        while(scanner.hasNext())
        {
            String word =scanner.next();
            stringArrayList.add(word);
        }
        return stringArrayList;
    }
}
