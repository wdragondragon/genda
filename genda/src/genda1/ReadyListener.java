package genda1;
import gendaClient.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import SetWin.*;
import Login.*;
public class ReadyListener extends AbstractAction {
	public static int BeganSign = 0;
	public static int ReadyDuan = 0;
	JTextArea accept,sendText;
	battleClient client;
	DataOutputStream out;
	private GendaListener gendaListener;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(!Window.Linksign){
			if(BeganSign==0){
				BeganSign = 1;
				accept.append("已准备\n");
				Window.f3listener.F3();
				battleSend.Mistake = 0;
			}
			else{
				BeganSign = 0;
				accept.append("取消准备\n");
				sendText.setText("");
				sendText.setEditable(false);
			}
			try {
				out = new DataOutputStream(client.socket.getOutputStream());
				String message = "%"+BeganSign+"%"+sendText.getText()+"%"+Window.wenben.getText()+"%0"+"%"+Login.zhanghao.getText();
				QQZaiwenListener.wenbenstr = Window.wenben.getText();
				out.writeUTF(message);//向服务器发送信息
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			accept.append("请先加入一个房间\n");
		}
	}
	public void setAccept(JTextArea accept) {
		// TODO Auto-generated method stub
		this.accept = accept;
	}
	public void setSocket(battleClient client){
		this.client = client;
	}
	public void setSendText(JTextArea sendText){
		this.sendText = sendText;
	}
	public void setGendaLisener(GendaListener gendaListener) {
		// TODO Auto-generated method stub
		this.gendaListener = gendaListener;
	}
	
}