package Commands;

import Server.Server;

import java.net.ServerSocket;
import java.util.ArrayList;

public class OpenDataServerCommand implements Command
{
    private Server server;

    public OpenDataServerCommand(Server server)
    {
        this.server = server;
    }

    public Server getServer()
    {
        return server;
    }

    public void setServer(Server server)
    {
        this.server = server;
    }

    @Override
    public int doCommand(ArrayList<String> arguments)
    {
        // open server on ip and port
        ServerSocket serverSocket;
        return 3;
    }
}
