package Example13_4;
import java.io.*;
import java.net.*;
import java.util.*;
public class ServerThread extends Thread {
	Socket socket ;
	DataOutputStream out =null;
	DataInputStream in =null;
	String s = null;
	ServerThread(Socket t){
		socket = t;
		try{
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
		}
		catch(IOException e){}
		
	}
	public void run(){
		while(true){
			try{
				double r = in.readDouble();
				double area = Math.PI*r*r;
				out.writeDouble(area);
			}
			catch(IOException e){
				System.out.println("客户离开");
				return;
			}
		}
	}
}
