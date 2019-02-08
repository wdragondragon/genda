package genda1;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;

public class Mix extends AbstractAction{
	Window win;
	Mix(Window win){
		this.win = win;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
//		Window.wenben.setText(mix(Window.wenben.getText()));
		String wenstr = mix(QQZaiwenListener.wenbenstr);
		win.gendaListener.sign = 0;
		win.gendaListener.deleteNumber = 0;
		win.gendaListener.deleteTextNumber = 0;
		win.gendaListener.KeyNumber = 0;
		Window.dazi.setText(null);
		Window.dazi.setEditable(true);
		QQZaiwenListener.wenbenstr = wenstr;
		
		win.wenben.setText("");
		Window.tipschange.changecolortip();
		Window.wenben.setText("");
		Window.gendaListener.ChangeFontColor();
		Window.dazi.requestFocusInWindow();
		win.wenben.setCaretPosition(0);
	}
	String mix(String str){
		String mix = "";
		List list = new ArrayList<>();
		char [] a = str.toCharArray();
		for (int i = 0;i<str.length();i++){
			list.add(a[i]);
		}
		Collections.shuffle(list);
		for(int i=0;i<str.length();i++)
			mix = mix +(list.get(i));
		return mix;
	}
}
