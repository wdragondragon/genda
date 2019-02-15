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
		QQZaiwenListener.wenbenstr = mix(QQZaiwenListener.wenbenstr);
		Window.f3listener.F3();
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
