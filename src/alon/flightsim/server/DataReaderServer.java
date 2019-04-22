package alon.flightsim.server;


import alon.flightsim.Environment;
import alon.flightsim.client.SimpleClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

public class DataReaderServer implements Server
{
    private HashMap<String, Double> serverData = new HashMap<>(); ///  symbol table of the simulator
    private boolean stop = false;
    private Thread serverThread;
    private final Environment env;

    public DataReaderServer(Environment env)
    {
        this.env = env;
    }


    @Override
    public void listen(int port,int timeout)
    {
//        System.out.printf("listen server on port %d\n",port);
        serverThread = new Thread(() -> {
            try
            {
                ServerSocket server = new ServerSocket(port);
                server.setSoTimeout(5000);
                while (!stop)
                {
                    try
                    {
                        Socket aClient = server.accept(); // blocking call
//                        System.out.println("-> client connected");
                        try
                        {
                            InputStream clientInputStream = aClient.getInputStream();
                            handleClient(clientInputStream,timeout);
                            clientInputStream.close();
                            aClient.close();
                        }
                        catch (IOException e)
                        {
//                            System.out.println("-> Received an IOException: " + e.getMessage());
                        }
                    }
                    catch (SocketTimeoutException e)
                    {
//                        System.out.println("-> No client connected within the set timeout, trying again...");
                    }
                }
//                System.out.println("-> Closing server");
                server.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });
        serverThread.start();
    }

    private void handleClient(InputStream inputStream,int timeout)
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try
        {
            while ((line = in.readLine()) != null)
            {
//                System.out.println("Received from simulator: " + line);
                // TODO implement map population
                String[] split = line.split(",");
                for (String s : split) {
                    Double d =Double.parseDouble(s);
                    env.getDefultValues().add(d);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void stop()
    {
        stop = true;
    }

    @Override
    public Thread serverThread()
    {
        return serverThread;
    }

    @Override
    public Double getValue(String key) {
        return getData().get(key);
    }

    @Override
    public Map<String, Double> getData()
    {
        return serverData;
    }
}
