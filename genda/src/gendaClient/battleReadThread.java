package gendaClient;

import genda1.GendaJindutiao;
import genda1.GendaListener;
import genda1.QQZaiwenListener;
import genda1.ReadyListener;
import genda1.RegexText;
import genda1.Window;

import java.io.DataInputStream;
import java.io.IOException;

import java.net.Socket;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import SetWin.SetFrameJinduListener;

public class battleReadThread extends Thread{//读取服务器发来信息的线程
	public  Socket socket;
	public static int otherready = 0;
	public static String Whowin = ""; 
	int num[] = {0,0,0,0,0,0,0};
	JTextArea accept;
	GendaJindutiao gendajindu;
	public battleReadThread(Socket socket,JTextArea accept2,GendaJindutiao gendajindu){
		this.socket = socket;
		this.accept = accept2;
		this.gendajindu= gendajindu;
	}
	public void run() {
		super.run();
		try {
//			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			DataInputStream in = null;
			while(true){
				in = new DataInputStream(socket.getInputStream());
				String message = in.readUTF();
				int pos = 0;
				for(int i=0;i<7;i++){
					pos =  message.indexOf('%',pos)+1;
					if(pos!=-1)
						num[i] = pos;
					else break;
				}
				if(num[0]==0)continue;
				int sign =Integer.parseInt(message.substring(num[0],num[1]-1));
				String jindu = message.substring(num[1],num[2]-1);
				String wenbentemp = message.substring(num[2],num[3]-1);
				String othName = message.substring(num[3],num[4]-1);
				String win = message.substring(num[4],num[5]-1);
				String system = message.substring(num[5],num[6]-1);
				String began = message.substring(num[6]);
				Whowin = othName+win;
				String wenben ="无";
				System.out.println(wenbentemp);
				
				if(!wenbentemp.equals("无")&&!wenbentemp.equals("")&&wenbentemp!=null){
					String wenben1[] = wenbentemp.split("#");
					wenben = wenben1[1];
					RegexText.duan1 = Integer.parseInt(wenben1[0]);
					if(sign!=1&&!QQZaiwenListener.wenbenstr.equals(wenben)){
						if(SetFrameJinduListener.jindusign==1)
							gendajindu.open(wenben.length());
						System.out.println(wenben);
						QQZaiwenListener.wenbenstr = wenben;
						QQZaiwenListener.wenbenstr = RegexText.qukong(RegexText.huanfu(QQZaiwenListener.wenbenstr));
						if(GendaListener.sign!=1&&ReadyListener.BeganSign==0){
							Window.f3listener.F3();
						}
					}
				}
				if(!win.equals("无"))
					Window.score.setText(win);
				if(!system.equals("无")){
					if(system.equals("对方已准备\n"))
						otherready = 1;
					else if (system.equals("对方取消准备\n"))
						otherready = 0;
//					else if(system.equals("另一玩家断开\n"))

					Window.communion.append(system);
				}
				if(!began.equals("无")){
					Window.communion.append(began);
				}
//				System.out.println(otherready);
				if(!jindu.equals("无")){
					accept.setText(jindu);
				}
				if(wenben.equals(jindu)&&!wenben.equals("无")){
					System.out.println(jindu+"  "+wenben);
					Window.communion.append("对方已打完\n");
				}
//				System.out.println(message);
				accept.setCaretPosition(accept.getText().length());
				Window.communion.setCaretPosition(Window.communion.getText().length());
			}
		} catch (IOException e) {
//			e.printStackTrace();
//			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		}
	}
}