package Example13_4;
import java.io.*;
import java.net.*;
import java.util.*;
public class Server {
	public static void main(String args[]){
		ServerSocket server = null;
		ServerThread thread;
		Socket you = null;
		while(true){
			try{
				server = new ServerSocket(2010);
			}
			catch(IOException el){
				System.out.println("正在监听");
			}
			try{
				System.out.println(" 等待客户呼叫:");
				you = server.accept();
				System.out.println("客户的地址:"+you.getInetAddress());
			}catch(IOException e){
				System.out.println("正在等待客户");
			}
			if(you!=null){
				new ServerThread(you).start();
			}
		}
	}
}