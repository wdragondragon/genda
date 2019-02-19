package RamdomOne;

import genda1.JTextPaneChange;
import genda1.QQZaiwenListener;
import genda1.RegexText;
import genda1.Window;
import gendaClient.battleSend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Acticle.SendWenben;
import SetWin.SetFrameJinduListener;

public class RamdomListener implements ActionListener{
	Load load = new Load();
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(SendWenben.sendwenSign==1){JOptionPane.showMessageDialog(new JTextArea(),"先结束发文");return;}
		if(Window.everydaySign){JOptionPane.showMessageDialog(new JTextArea(),"请先结束每日赛文");return;}
		String ramdomWen = load.getRamdomWenben();
		ramdomWen = RegexText.qukong(ramdomWen);
		ramdomWen = RegexText.huanfu(ramdomWen);
		QQZaiwenListener.wenbenstr = ramdomWen;
		Window.f3listener.F3();
		RegexText.duan1 = 1;
	}
	
}
