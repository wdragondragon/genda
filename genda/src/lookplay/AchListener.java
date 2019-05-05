package lookplay;

import genda1.GendaListener;
import genda1.QQZaiwenListener;
import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import History.ShowWen;


public class AchListener implements ActionListener {
	String str1 = "",str2 ="";
	int keynumber = 0,i=0,sign,tempbefore=0,temp1before=0;
	public static int mistake=0,duo=0,lou=0;
	int tempmis=0,tempduo=0,templou=0;
	public static String lookplay;
	ArrayList<String> record;
	
	ShowWen sw =new ShowWen();
	
	ArrayList<Integer> errorArea = new ArrayList<Integer>();
	public static ArrayList<int[]>strmatch;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!(Window.Pattern==1))return;
		String mistakeall="";
		mistake=0;duo=0;lou=0;
		record = new ArrayList<String>();
		str1=QQZaiwenListener.wenbenstr;
		str2=Window.dazi.getText();
		String []act = str1.split("，|。");
		String []dazi = str2.split("，|。");
		System.out.println(act.length);
		if(act.length==dazi.length&&act.length!=0)
			for(int k =0;k<act.length;k++){
				str1=act[k];
				str2=dazi[k];
				if(str1.equals("")&&str2.equals(""))continue;
				System.out.println(str1+":"+str2);
				ArticleMatch();
				if(tempmis!=0||templou!=0||tempduo!=0)
					record.add("原文："+str1+"\n跟打："+str2+"\n错："+tempmis+" 漏："+templou+" 多："+tempduo+"\n");
				tempmis=0;templou=0;tempduo=0;
			}
		else
			ArticleMatch();
//		ArticleMatch();
		for(int i=0;i<record.size();i++)
			mistakeall+=record.get(i);
		lookplay="错:"+mistake+" 漏:"+lou+" 多:"+duo;
		mistakeall+="总情况-----------------\n"+lookplay;
		Window.dazi.setEditable(false);
		GendaListener.gendaSign = true;
		sw.setVisible(true);
		sw.showwen(mistakeall);
	}
	//edit distance
	void ArticleMatch(){
		ArticleMatch act = new ArticleMatch();
		strmatch = act.getMatch(str1, str2);
		for(int i =0;i<strmatch.get(0).length;i++){
			int temp = strmatch.get(0)[i];
			if(temp==1){
				lou++;
				templou++;
			}
			else if(temp==2){
				mistake++;
				tempmis++;
			}
		}
		for(int i =0;i<strmatch.get(1).length;i++){
			int temp = strmatch.get(1)[i];
			if(temp==1){
				duo++;
				tempduo++;
			}
		}
	}
	
	//自己算法
//	void ArticleMatch1(){
//		record = new ArrayList<String>();
//		
//		
//		String []act = str1.split("，|。");
//		String []dazi = str2.split("，|。");
//		
//		mistake=0;duo=0;lou=0;
//		System.out.println();
//		if(act.length==dazi.length)
//			for(int k =0;k<act.length;k++){
//				str1=act[k];
//				str2=dazi[k];
//				compe(str1,str2);
//			}
//		else
//			compe(str1,str2);
//		
//		System.out.println();
//		String mistakeall="";
//		for(int i=0;i<record.size();i++)
//			mistakeall+=record.get(i)+"\n";
//		lookplay="错:"+mistake+" 漏:"+lou+" 多:"+duo;
//		mistakeall+="总情况-----------------\n"+lookplay;
//		Window.dazi.setEditable(false);
//		GendaListener.gendaSign = true;
//		sw.setVisible(true);
//		sw.showwen(mistakeall);
//	}
//	void compe(String str1,String str2){
//		tempbefore=0;temp1before=0;
//		int onlymistake=0;
//		int onlylou=0;
//		int onlyduo=0;
//		while(i<=str2.length()){
//			for(int j=str2.length();j>i;j--){
//				int temp=str1.indexOf(str2.substring(i,j),tempbefore);
//				if(temp!=-1){
//					int temp1=str2.indexOf(str2.substring(i,j),temp1before);
//					int mistaketmp=0;
//					int loutemp=Math.abs(temp-tempbefore);
//					int duotemp=Math.abs(temp1-temp1before);
//					mistaketmp=loutemp<duotemp?loutemp:duotemp;
//					
//					onlymistake+=mistaketmp;
//					onlylou+=loutemp-mistaketmp;
//					onlyduo+=duotemp-mistaketmp;
//					
//					mistake+=mistaketmp;
//					lou+=loutemp-mistaketmp;
//					duo+=duotemp-mistaketmp;
//					
//					tempbefore=temp+j-i;
//					temp1before=temp1+j-i;
//
//					i=j-1;
//					break;
//				}
//			}
//			i++;
//			if(tempbefore<=str1.length()&&str2.length()==temp1before){
//				onlylou+=str1.length()-tempbefore;
//				lou+=str1.length()-tempbefore;;
//
//				break;
//			}
//		}
//		if(tempbefore<str1.length()&&str2.length()>temp1before){	//解决最后连错。
//			int temp1=str1.length()-tempbefore;
//			int temp2=str2.length()-temp1before;
//			onlymistake += Math.min(temp1,temp2);
//			mistake += Math.min(temp1,temp2);
//			int dif = temp1-temp2;
//			if(dif>0){
//				onlylou+=dif;
//				lou+=dif;
//			}
//			else if(dif<0){
//				onlyduo+=-dif;
//				lou+=-dif;
//			}
//		}
//		if(onlymistake!=0||onlyduo!=0||onlylou!=0)
//			record.add("原文："+str1+"\n跟打："+str2+"\n错："+onlymistake+" 漏："+onlylou+" 多："+onlyduo);
//		i=0;
//	}
}