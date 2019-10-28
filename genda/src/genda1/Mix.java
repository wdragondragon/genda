package genda1;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractAction;

import Acticle.ActicleListener;
import Acticle.EnlishRamdom;
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
		mixButton(e.getActionCommand());
	}
	public void mixButton(String model){
		if(model.equals("¸Ã¶ÎÂÒÐò")){
			if(SendWenben.sendwenSign3==1)
				QQZaiwenListener.wenbenstr = mixstr(ActicleListener.chouqubufenlist,ActicleListener.y);
			else if(SendWenben.sendwenSign4)
				QQZaiwenListener.wenbenstr = EnglishMix();
			else
				QQZaiwenListener.wenbenstr = mix(QQZaiwenListener.wenbenstr);
			Window.f3listener.F3();
		}
		else if(model.equals("È«¾ÖÂÒÐò")){
			ActicleListener.all = mix(ActicleListener.all);
			if(ActicleListener.all==null)return;
			ActicleListener.showwen();
		}
	}
	public static String mix(String str){
		if(str==null)return null;
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
	public static String EnglishMix(){
		String str = "";
		Collections.shuffle(EnlishRamdom.wordlist);
		for(int i = 0;i<EnlishRamdom.wordlist.size();i++){
			str+=EnlishRamdom.wordlist.get(i);
			str+=" ";
		}
		System.out.println("wfvh:"+str);
		return str.substring(0,str.length()-1);
	}
}
