package genda1;
import gendaClient.battleClient;

import java.awt.event.ActionEvent;
import java.io.DataOutputStream;

import javax.swing.*;

import Acticle.SendWenben;
import Login.Login;
import SetWin.SetFrameJinduListener;
import Tips.Tips;
public class JianQieListener extends AbstractAction {
	JTextArea dazi;
	JTextPane wenben;
	JLabel zishu;
	GendaJindutiao gendajindu;
	GendaListener gendaListener;
	JScrollBar JSB;
	SetFrameJinduListener setframejinduListener;
	public void setWenbenText(JTextPane t){
		wenben = t;
	}
	public void setDaziText(JTextArea t){
		dazi = t;
	}
	public void setGendaListener(GendaListener t){
		gendaListener = t;
	}
	public void setZishu(JLabel zishu2){
		zishu = zishu2;
	}
	public void setJSB(JScrollBar t){
		JSB = t;
	}
	public void actionPerformed(ActionEvent arg0) {
		if(SendWenben.sendwenSign==1){JOptionPane.showMessageDialog(new JTextArea(),"先结束发文");return;}
		if(Window.everydaySign){JOptionPane.showMessageDialog(new JTextArea(),"请先结束每日赛文");return;}
		RegexText.duan1 = 1;
		if(SetFrameJinduListener.jindusign==1)
			gendajindu.open(wenben.getText().length());
		QQZaiwenListener.wenbenstr = AchievementListener.getClipboardString();
		QQZaiwenListener.wenbenstr = RegexText.qukong(RegexText.huanfu(QQZaiwenListener.wenbenstr));
		try{
			DataOutputStream out = new DataOutputStream(battleClient.socket.getOutputStream());
			out.writeUTF("%"+ReadyListener.BeganSign+"%"+"%"+RegexText.duan1+"#"+Window.wenben.getText()+"%0"+"%"+Login.zhanghao.getText());
		}
		catch(Exception ex){
			System.out.println("无法发送文本内容jianqie,49");
		}
		Window.f3listener.F3();
	}
	public void setJProgressBar(GendaJindutiao t){
		gendajindu = t;
	}
}