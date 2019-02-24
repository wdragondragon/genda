package Acticle;

import genda1.GendaJindutiao;
import genda1.GendaListener;
import genda1.JTextPaneChange;
import genda1.QQZaiwenListener;
import genda1.ReadyListener;
import genda1.RegexText;
import genda1.Window;
import gendaClient.battleClient;
import gendaClient.battleSend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import Login.*;
import SetWin.SetFrameQianshuiListener;
public class SendWenben implements ActionListener{
	static public int sendwenSign = 0;
	static public int sendwenSign2 = 0;//抽取模式发文
	static public String title = "";
	JTextPane wenben;
	Window win;
	private Acticle acticle;
	SendWenben(JTextPane wenben){
		this.wenben = wenben;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		QQZaiwenListener.wenbenstr = wenben.getText();//固定文本框
		QQZaiwenListener.wenbenstr = RegexText.qukong(RegexText.huanfu(QQZaiwenListener.wenbenstr));
		Window.f3listener.F3();
		sendwenSign = 1;	//发文标志
		RegexText.duan1 = 1;//设置段数
		ActicleListener.fontweizhi += ActicleListener.fontnum;
		win.sendwen.setVisible(true);
		try{
			DataOutputStream out = new DataOutputStream(battleClient.socket.getOutputStream());
			out.writeUTF("%"+ReadyListener.BeganSign+"%"+"%"+RegexText.duan1+"#"+wenben.getText()+"%0"+"%"+Login.zhanghao.getText());
		}
		catch(Exception ex){
			System.out.println("无法发送文本内容");
		}
		acticle.setVisible(false);
		if(SetFrameQianshuiListener.qianshui==0)ShareListener.send();
	}
	public void setwin(Window t,Acticle acticle){
		win = t;
		this.acticle = acticle;
	}
}
