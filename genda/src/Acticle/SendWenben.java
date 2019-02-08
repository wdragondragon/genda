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
public class SendWenben implements ActionListener{
	static public int sendwenSign = 0;
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
		win.dazi.setText(null);
		win.dazi.setEditable(true);
		win.gendaListener.sign = 0;
		win.gendaListener.deleteNumber = 0;
		win.gendaListener.deleteTextNumber = 0;
		win.gendaListener.KeyNumber = 0;
		win.gendaListener.space = 0;
		win.gendaListener.left = 0;
		win.gendaListener.right = 0;
		win.wenben.setText("");
		
		QQZaiwenListener.wenbenstr = wenben.getText();
		win.tipschange.changecolortip();
		win.wenben.setText("");
		win.gendaListener.ChangeFontColor();//字体描色
		
		QQZaiwenListener.lilun = 1.0*win.tipschange.compalllength()/QQZaiwenListener.wenbenstr.length();
		win.lilunma.setText("理论码长:"+String.format("%.2f", QQZaiwenListener.lilun));
		
		win.wenben.setCaretPosition(0);		//移动到最顶行
		Window.dazi.requestFocusInWindow();	//固定焦点
		win.setGendajindu.open(wenben.getText().length());	//进度条开启
		sendwenSign = 1;	//发文标志
		QQZaiwenListener.wenbenstr = wenben.getText();//固定文本框
		RegexText.duan1 = 1;//设置段数
		
		win.sendwen.setVisible(true);
		win.sendwen.setText(String.valueOf(0)+"%");
		
		//打词重置
		win.gendaListener.daciall = 0;
		win.gendaListener.daci = 0;
		try{
			DataOutputStream out = new DataOutputStream(battleClient.socket.getOutputStream());
			out.writeUTF("%"+ReadyListener.BeganSign+"%"+"%"+wenben.getText()+"%0"+"%"+Login.zhanghao.getText());
		}
		catch(Exception ex){
			System.out.println("无法发送文本内容");
		}
		acticle.setVisible(false);
	}
	public void setwin(Window t,Acticle acticle){
		win = t;
		this.acticle = acticle;
	}
}
