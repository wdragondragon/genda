package RamdomOne;

import genda1.JTextPaneChange;
import genda1.QQZaiwenListener;
import genda1.Window;
import gendaClient.battleSend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import SetWin.SetFrameJinduListener;

public class RamdomListener implements ActionListener{
	Load load = new Load();
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String ramdomWen = load.getRamdomWenben();
		QQZaiwenListener.wenbenstr = ramdomWen;
		Window.wenben.setText(ramdomWen);
		Window.gendaListener.KeyNumber = 0;
		Window.gendaListener.deleteNumber = 0;
		Window.gendaListener.deleteTextNumber = 0;
		Window.gendaListener.left = 0;
		Window.gendaListener.right = 0;
		Window.gendaListener.repeat = 0;
		
		Window.gendaListener.space = 0;
		
//		gendaListener.record = "";
		battleSend.Mistake ++;
		if(SetFrameJinduListener.jindusign==1)
			Window.setGendajindu.open(Window.wenben.getText().length());
		//字体变化
		Window.tipschange.changecolortip();
		Window.wenben.setText("");
		Window.gendaListener.ChangeFontColor();
		
		Window.wenben.setCaretPosition(0);//字体描色
		////////////////////
		QQZaiwenListener.lilun = 1.0*Window.tipschange.compalllength()/QQZaiwenListener.wenbenstr.length();
		Window.lilunma.setText("理论码长:"+String.format("%.2f", QQZaiwenListener.lilun));
		
		//打词重置
		Window.gendaListener.daciall = 0;
		Window.gendaListener.daci = 0;
	}
	
}
