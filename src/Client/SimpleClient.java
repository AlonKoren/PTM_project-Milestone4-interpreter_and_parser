package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleClient implements Client
{
    private Socket socket;

    @Override
    public void connect(String ip, int port)
    {
        try
        {
            socket = new Socket(ip, port);
            Thread.sleep(1000);
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void set(String path, Double value)
    {
        try
        {
            PrintWriter toClient = new PrintWriter(socket.getOutputStream());
            toClient.println("set " + path + " " + value);
            toClient.flush();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void close()
    {
        try
        {
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
