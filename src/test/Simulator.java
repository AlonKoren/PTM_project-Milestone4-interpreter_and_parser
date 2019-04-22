package test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Random;

public class Simulator {
	
	double simX,simY,simZ;
	private int port;
	private volatile boolean stop;
	
	public Simulator(int port) {
		this.port=port;
		Random r=new Random();
		simY=r.nextInt(1000);
		simZ=r.nextInt(1000);
		new Thread(()->runServer()).start();
		new Thread(()->runClient()).start();
	}
	
	private void runClient(){
		while(!stop){
			try {
				Socket interpreter=new Socket("127.0.0.1", port+1);
				PrintWriter out=new PrintWriter(interpreter.getOutputStream());
				while(!stop){
					out.println(simX+","+simY+","+simZ);
					out.flush();
					try {Thread.sleep(100);} catch (InterruptedException e1) {}
				}
				out.close();
				interpreter.close();
			} catch (IOException e) {
				try {Thread.sleep(1000);} catch (InterruptedException e1) {}
			}
		}
	}
	
	private void runServer(){
		try {
			ServerSocket server=new ServerSocket(port);
			server.setSoTimeout(1000);
			while(!stop){
				try{
					Socket client=server.accept();
					BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
//					PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
					String line=null;
					while(!(line=in.readLine()).equals("bye")){
						try{
//							System.out.println("text sim sim ="+line);
							if(line.startsWith("set simX"))
								simX=Double.parseDouble(line.split(" ")[2]);
							if(line.startsWith("set simY"))
								simY=Double.parseDouble(line.split(" ")[2]);
							if(line.startsWith("set simZ"))
								simZ=Double.parseDouble(line.split(" ")[2]);
//							if(line.startsWith("get simX")) {
//								out.println("simX = '"+simX+"' (double)");
//								out.flush();
//							}
//							if(line.startsWith("get simY")){
//								out.println("simY = '"+simY+"' (double)");
//								out.flush();
//							}
//							if(line.startsWith("get simZ")){
//								out.println("simZ = '"+simZ+"' (double)");
//								out.flush();
//							}
						}catch(NumberFormatException e){
							e.printStackTrace();
						}
					}
					in.close();
					client.close();
				}catch(SocketTimeoutException e){}
			}
			server.close();
		} catch (IOException e) {}
	}

	public void close() {
		stop=true;
	}
}
