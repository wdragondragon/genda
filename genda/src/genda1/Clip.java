package genda1;

import gendaClient.battleClient;

import java.io.DataOutputStream;

import Login.Login;


public class Clip extends Thread{

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public void run(){
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(QQZaiwenListener.zaiwenSign){
				System.out.println(AchievementListener.getClipboardString());
				QQZaiwenListener.str = AchievementListener.getClipboardString();
				QQZaiwenListener.wenbenstr = QQZaiwenListener.regexText.regetText(QQZaiwenListener.str);
				Window.f3listener.F3();
				try{
					DataOutputStream out = new DataOutputStream(battleClient.socket.getOutputStream());
					out.writeUTF("%"+ReadyListener.BeganSign+"%"+"%"+RegexText.duan1+"#"+QQZaiwenListener.wenbenstr+"%0"+"%"+Login.zhanghao.getText());
				}
				catch(Exception ex){
					System.out.println("无法发送文本内容");
				}
				QQZaiwenListener.zaiwenSign = false;
			}
//			System.out.println(wenbenstr);
//			AchievementListener.setClipboardString(AchievementListener.getClipboardString());
		}
	}
}