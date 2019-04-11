package lookplay;

import genda1.GendaListener;
import genda1.QQZaiwenListener;
import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import History.ShowWen;

public class AchListener implements ActionListener {
	String str1 = "",str2 ="";
	int keynumber = 0,i=0,sign,tempbefore=0,temp1before=0;
	public static int mistake=0,duo=0,lou=0;
	public static String lookplay;
	ArrayList<String> record;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		record = new ArrayList<String>();
		str1=QQZaiwenListener.wenbenstr;
		str2=Window.dazi.getText();
		
		String []act = str1.split("，");
		String []dazi = str2.split("，");
		mistake=0;duo=0;lou=0;
		System.out.println();
		for(int k =0;k<act.length;k++){
			tempbefore=0;temp1before=0;
			str1=act[k];
			str2=dazi[k];
			int onlymistake=0;
			int onlylou=0;
			int onlyduo=0;
			while(i<=str2.length()){
				for(int j=str2.length();j>i;j--){
					int temp=str1.indexOf(str2.substring(i,j),tempbefore);
					if(temp!=-1){
						int temp1=str2.indexOf(str2.substring(i,j),temp1before);
						int mistaketmp=0;
						int loutemp=Math.abs(temp-tempbefore);
						int duotemp=Math.abs(temp1-temp1before);
						mistaketmp=loutemp<duotemp?loutemp:duotemp;
						
						onlymistake+=mistaketmp;
						onlylou+=loutemp-mistaketmp;
						onlyduo+=duotemp-mistaketmp;
						
						mistake+=mistaketmp;
						lou+=loutemp-mistaketmp;
						duo+=duotemp-mistaketmp;
						
						tempbefore=temp+j-i;
						temp1before=temp1+j-i;
						
//						System.out.println(temp+":"+tempbefore+":"+str1.substring(temp,tempbefore)+":"+temp1+":"+temp1before);
						i=j-1;
						break;
					}
				}
				i++;
				if(tempbefore<=str1.length()&&str2.length()==temp1before){
					onlylou+=str1.length()-tempbefore;
					lou+=onlylou;
//					System.out.println("漏补"+str1.length()+":"+tempbefore);
					break;
				}
			}
			if(onlymistake!=0||onlyduo!=0||onlylou!=0)
				record.add("原文："+str1+"\n跟打："+str2+"\n错："+onlymistake+" 漏："+onlylou+" 多："+onlyduo);
			i=0;
		}
		System.out.println();
		String mistakeall="";
		for(int i=0;i<record.size();i++)
			mistakeall+=record.get(i)+"\n";
		lookplay="错："+mistake+" 漏："+lou+" 多："+duo;
		mistakeall+=lookplay;
		Window.dazi.setEditable(false);
		GendaListener.gendaSign = true;
		ShowWen sw = new ShowWen(mistakeall);
	}
}