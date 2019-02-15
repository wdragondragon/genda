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
		Window.f3listener.F3();
	}
	
}
