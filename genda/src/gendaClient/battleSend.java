package gendaClient;

import genda1.QQZaiwenListener;
import genda1.ReadyListener;
import genda1.RegexText;
import genda1.Window;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JTextArea;
import Login.*;
public class battleSend{
	JTextArea accept;
	JTextArea sendText;
	Socket socket;
	String message;
	public static int Mistake;
	public static DataOutputStream out;
	battleSend(JTextArea sendText,JTextArea accept2,Socket socket){
		this.accept = accept2;
		this.socket = socket;
		this.sendText = sendText;
	}
	public void send(){
		try {
			out = new DataOutputStream(socket.getOutputStream());
			if(sendText.getText().length()!=0&&Window.wenben.getText().length()!=0){
				message = "%"+ReadyListener.BeganSign+"%"+sendText.getText()+"%"+RegexText.duan1+"#"+QQZaiwenListener.wenbenstr+"%0"+"%"+Login.zhanghao.getText();
				out.writeUTF(message);//向服务器发送信息
			}
		} catch (IOException e) {
//			e.printStackTrace();
		}
	}
}