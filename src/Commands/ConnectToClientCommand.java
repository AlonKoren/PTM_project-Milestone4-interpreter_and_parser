package Commands;

import Client.Client;

import java.util.ArrayList;

public class ConnectToClientCommand implements Command
{
    private Client client;
    public ConnectToClientCommand(Client client)
    {
        this.client = client;
    }

    @Override
    public int doCommand(ArrayList<String> arguments)
    {
        client.connect(arguments.get(1),Integer.parseInt(arguments.get(2)));
        return 3;
    }


}
