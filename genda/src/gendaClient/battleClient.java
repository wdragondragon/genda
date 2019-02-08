package gendaClient;
import genda1.*;

import java.io.*;
import java.net.*;
import javax.swing.*;
/*客户端类*/
public class battleClient{
	JTextArea dazi;
	JTextArea accept;
	JTextField name;
	public static Socket socket;
	public static battleSend send;
	Thread readThread,heartThread;
	
	int portNum[] = {1111,2222,3333,4444,5555,6666,7777,8888};
	GendaJindutiao gendajindu;
	public void StratClient(){
		try {
			socket = new Socket(Window.IP,portNum[Window.RoomNum-1]);//创建客户端
			send = new battleSend(dazi,accept,socket);
//			((dazi.getDocument())).addDocumentListener(sendlistener);
			readThread = new battleReadThread(socket,accept,gendajindu);//开启读取从服务器端发来的信息
			//			readThread.start();
		}
		catch (UnknownHostException e) {
			System.out.println("对方连接断开");
		}
		catch (IOException e) { 
			System.out.println("错误：服务器未开启！！！");
		}
	}
	public void setDazi(JTextArea dazi){
		this.dazi = dazi;
	}
	public void setAccept(JTextArea accept2){
		this.accept = accept2;
	}
	public void setGendajindu(GendaJindutiao setGendajindu) {
		// TODO Auto-generated method stub
		this.gendajindu = setGendajindu;
	}
	public void setName(JTextField name){
		this.name = name;
	}
}