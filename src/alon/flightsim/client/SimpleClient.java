package alon.flightsim.client;

import java.io.*;
import java.net.Socket;

public class SimpleClient implements Client
{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter printWriter;

    @Override
    public void connect(String ip, int port)
    {
        try
        {
            System.out.printf("connect client to server on %s::%d\n",ip,port);
            socket = new Socket(ip, port);
            Thread.sleep(1000);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            in = new BufferedReader(new InputStreamReader(inputStream));
            printWriter = new PrintWriter(new OutputStreamWriter(outputStream));
//            System.out.println("send request");
//            Double value = this.getValue("/instrumentation/altimeter/indicated-altitude-ft");
//            System.out.println(value);

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
    public Double getValue(String path) {
//        System.out.println("path="+path);
        printWriter.println("get "+path);
        printWriter.flush();
        try {
            String s = in.readLine();
//            System.out.println("text from simulator:"+s);
            String substring = s.substring(s.indexOf("'")+1, s.lastIndexOf("'"));
//            System.out.println("word="+substring);
            return Double.parseDouble(!substring.isEmpty()?substring:"0");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
