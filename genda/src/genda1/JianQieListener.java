package genda1;
import java.awt.event.ActionEvent;

import javax.swing.*;

import Acticle.SendWenben;
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
		Window.f3listener.F3();
	}
	public void setJProgressBar(GendaJindutiao t){
		gendajindu = t;
	}
}