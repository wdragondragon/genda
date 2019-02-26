package genda1;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;

import Acticle.ActicleListener;
import Acticle.SendWenben;

public class Mix extends AbstractAction{
	Window win;
	Mix(Window win){
		this.win = win;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		Window.wenben.setText(mix(Window.wenben.getText()));
		if(e.getActionCommand().equals("¸Ã¶ÎÂÒÐò")){
			if(SendWenben.sendwenSign3==1)
				QQZaiwenListener.wenbenstr = mixstr(ActicleListener.chouqubufenlist,ActicleListener.y);
			else
				QQZaiwenListener.wenbenstr = mix(QQZaiwenListener.wenbenstr);
			Window.f3listener.F3();
		}
		else if(e.getActionCommand().equals("È«¾ÖÂÒÐò")){
			ActicleListener.all = mix(ActicleListener.all);
			ActicleListener.showwen();
		}
		
	}
	public static String mix(String str){
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
	public static String mixstr(List chouqulist,int y){
		Collections.shuffle(chouqulist);
		String str = "";
		for(int i=0;i<(chouqulist.size()<y?chouqulist.size():y);i++)
			str += chouqulist.get(i);
		return str;
	}
}
