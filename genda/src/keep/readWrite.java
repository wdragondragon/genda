package keep;


import genda1.GendaListener;
import genda1.JTextPaneChange;
import genda1.MoreListener;
import genda1.Window;
import genda1.winchange;

import java.lang.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.*;

import javax.swing.JSplitPane;

import Login.*;
import SetWin.SetCharListener;
import SetWin.SetFrame;
import SetWin.SetFrameJinduListener;
import SetWin.SetFrameQianshuiListener;
import SetWin.SetFramechangeListener;
import SetWin.SetspaceListener;
import Tips.ChooseFile;
import Tips.Tips;

public class readWrite
{
	static String rString = "";
	public static void keep (Window win)throws IOException
	{
//		if(win.one.isVisible())
//			win.setSize(win.getWidth(),winchange.bottom+7);
		System.out.println(win.jSplitPane1.getDividerLocation());
		rString = "%"+String.valueOf(win.getWidth())
				+"%"+String.valueOf(win.getHeight())
				+"%"+String.valueOf(win.getX())
				+"%"+String.valueOf(win.getY())
				+"%"+String.valueOf(Window.fontSize)
				+"%"+String.valueOf(SetFrameJinduListener.jindusign)
				+"%"+String.valueOf(MoreListener.MoreSign)
				+"%"+Login.zhanghao.getText()
				+"%"+String.valueOf(win.jSplitPane1.getDividerLocation())
				+"%"+String.valueOf(win.jSplitPane1.getDividerLocation())
				+"%"+String.valueOf(win.jSplitPane1.getWidth())
				+"%"+String.valueOf(win.jSplitPane1.getHeight())
				+"%"+String.valueOf(SetFrameQianshuiListener.qianshui)
				+"%"+String.valueOf(Window.rightcolor.getRed())
				+"%"+String.valueOf(Window.rightcolor.getGreen())
				+"%"+String.valueOf(Window.rightcolor.getBlue())
				+"%"+String.valueOf(Window.mistakecolor.getRed())
				+"%"+String.valueOf(Window.mistakecolor.getGreen())
				+"%"+String.valueOf(Window.mistakecolor.getBlue())
				+"%"+String.valueOf(win.getBackground().getRed())
				+"%"+String.valueOf(win.getBackground().getGreen())
				+"%"+String.valueOf(win.getBackground().getBlue())
				+"%"+String.valueOf(Window.wenben.getBackground().getRed())
				+"%"+String.valueOf(Window.wenben.getBackground().getGreen())
				+"%"+String.valueOf(Window.wenben.getBackground().getBlue())
				+"%"+String.valueOf(Window.dazi.getBackground().getRed())
				+"%"+String.valueOf(Window.dazi.getBackground().getGreen())
				+"%"+String.valueOf(Window.dazi.getBackground().getBlue())
				+"%"+String.valueOf(Window.qmacicolor.getRed())
				+"%"+String.valueOf(Window.qmacicolor.getGreen())
				+"%"+String.valueOf(Window.qmacicolor.getBlue())
				+"%"+String.valueOf(Window.smacicolor.getRed())
				+"%"+String.valueOf(Window.smacicolor.getGreen())
				+"%"+String.valueOf(Window.smacicolor.getBlue())
				+"%"+String.valueOf(Window.emacicolor.getRed())
				+"%"+String.valueOf(Window.emacicolor.getGreen())
				+"%"+String.valueOf(Window.emacicolor.getBlue())
				+"%"+String.valueOf(SetFramechangeListener.tipsign)
				+"%"+String.valueOf(GendaListener.fenye)
				+"%"+ChooseFile.cizufilename
				+"%"+Window.family
				+"%"+SetspaceListener.spacesign
				+"%"+SetCharListener.charsign
				+"%"+String.valueOf(win.jSplitPane1.getWidth())
				+"%"+String.valueOf(win.jSplitPane1.getHeight())
				;
		Writer fWrite=new FileWriter("../set/lx.ini",false);
		BufferedWriter wStream=new BufferedWriter(fWrite);
//		rString=bStream.readLine();
		wStream.write(rString);
		wStream.close();
		fWrite.close();
	}
	public static void read(Window win)throws IOException{
		try{
			Reader rReader = new FileReader("../set/lx.ini");
			BufferedReader rStream=new BufferedReader(rReader);
			rString = rStream.readLine();
			System.out.println(rString);
			rStream.close();
			rReader.close();
			int num[] = {
					0,0,0,0,0,
					0,0,0,0,0,
					0,0,0,0,0,
					0,0,0,0,0,
					0,0,0,0,0,
					0,0,0,0,0,
					0,0,0,0,0,
					0,0,0,0,0,
					0,0,0,0,0
					};
			int pos = 0;
		
			for(int i=0;i<45;i++){
				pos =  rString.indexOf('%',pos)+1;
				if(pos!=-1)
					num[i] = pos;
				else break;
			}
			
			if(num[0]==0)return;
			int width =Integer.parseInt(rString.substring(num[0],num[1]-1));
			int height = Integer.parseInt(rString.substring(num[1],num[2]-1));
			int x = Integer.parseInt(rString.substring(num[2],num[3]-1));
			int y = Integer.parseInt(rString.substring(num[3],num[4]-1));
			int fontSize = Integer.parseInt(rString.substring(num[4],num[5]-1));
			int jindusign = Integer.parseInt(rString.substring(num[5],num[6]-1));
			int  moresign = Integer.parseInt(rString.substring(num[6],num[7]-1));
			String name = rString.substring(num[7],num[8]-1);
			int sp1 = Integer.parseInt(rString.substring(num[8],num[9]-1));
			int sp2 = Integer.parseInt(rString.substring(num[9],num[10]-1));
			int sp2width = Integer.parseInt(rString.substring(num[10],num[11]-1));
			int sp2height = Integer.parseInt(rString.substring(num[11],num[12]-1));
			int qianshui = Integer.parseInt(rString.substring(num[12],num[13]-1));
			
			int rightR = Integer.parseInt(rString.substring(num[13],num[14]-1));
			int rightG = Integer.parseInt(rString.substring(num[14],num[15]-1));
			int rightB = Integer.parseInt(rString.substring(num[15],num[16]-1));
			
			int misR = Integer.parseInt(rString.substring(num[16],num[17]-1));
			int misG = Integer.parseInt(rString.substring(num[17],num[18]-1));
			int misB = Integer.parseInt(rString.substring(num[18],num[19]-1));
			
			int winR = Integer.parseInt(rString.substring(num[19],num[20]-1));
			int winG = Integer.parseInt(rString.substring(num[20],num[21]-1));
			int winB = Integer.parseInt(rString.substring(num[21],num[22]-1));
			
			int wenbenR = Integer.parseInt(rString.substring(num[22],num[23]-1));
			int wenbenG = Integer.parseInt(rString.substring(num[23],num[24]-1));
			int wenbenB = Integer.parseInt(rString.substring(num[24],num[25]-1));
			
			int daziR = Integer.parseInt(rString.substring(num[25],num[26]-1));
			int daziG = Integer.parseInt(rString.substring(num[26],num[27]-1));
			int daziB = Integer.parseInt(rString.substring(num[27],num[28]-1));
			
			int qmcR = Integer.parseInt(rString.substring(num[28],num[29]-1));
			int qmcG = Integer.parseInt(rString.substring(num[29],num[30]-1));
			int qmcB = Integer.parseInt(rString.substring(num[30],num[31]-1));
			
			int smcR = Integer.parseInt(rString.substring(num[31],num[32]-1));
			int smcG = Integer.parseInt(rString.substring(num[32],num[33]-1));
			int smcB = Integer.parseInt(rString.substring(num[33],num[34]-1));
			
			int emcR = Integer.parseInt(rString.substring(num[34],num[35]-1));
			int emcG = Integer.parseInt(rString.substring(num[35],num[36]-1));
			int emcB = Integer.parseInt(rString.substring(num[36],num[37]-1));
			
			int tip = Integer.parseInt(rString.substring(num[37],num[38]-1));
			int fenye = Integer.parseInt(rString.substring(num[38],num[39]-1));
			
			String cizufilename  = rString.substring(num[39],num[40]-1);
			String family = rString.substring(num[40],num[41]-1);
			
			int space = Integer.parseInt(rString.substring(num[41],num[42]-1));
			int Charset = Integer.parseInt(rString.substring(num[42],num[43]-1));
			
			int sp1width = Integer.parseInt(rString.substring(num[43],num[44]-1));
			int sp1height = Integer.parseInt(rString.substring(num[44]));
			
			
			Window.rightcolor = new Color(rightR,rightG,rightB);
			Window.mistakecolor = new Color(misR,misG,misB);
			Window.qmacicolor = new Color(qmcR,qmcG,qmcB);
			Window.smacicolor = new Color(smcR,smcG,smcB);
			Window.emacicolor = new Color(emcR,emcG,emcB);
			
			Window.wenben.setBackground(new Color(wenbenR,wenbenG,wenbenB));
			Window.dazi.setBackground(new Color(daziR,daziG,daziB));
			Window.fontSize = fontSize;
			Window.family = family;
			Window.dazi.setFont(new Font(Window.family,0,fontSize));
			Window.accept.setFont(new Font(Window.family,0,fontSize));
			JTextPaneChange.createStyle("黑",JTextPaneChange.styledDoc,fontSize,0,0,0,Color.BLACK,Window.family,Window.rightcolor);
			JTextPaneChange.createStyle("红",JTextPaneChange.styledDoc,fontSize,0,0,0,Color.BLACK,Window.family,Window.mistakecolor);
			
			JTextPaneChange.createStyle("灰",JTextPaneChange.styledDoc,fontSize,0,0,0,Color.BLACK,Window.family,Window.mistakecolor);//GRAY
			
			JTextPaneChange.createStyle("蓝粗",JTextPaneChange.styledDoc,fontSize,1,0,0,Window.smacicolor,Window.family,Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("蓝",JTextPaneChange.styledDoc,fontSize,0,0,0,Window.smacicolor,Window.family,Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("蓝斜",JTextPaneChange.styledDoc,fontSize,0,1,0,Window.smacicolor,Window.family,Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("蓝粗斜",JTextPaneChange.styledDoc,fontSize,1,1,0,Window.smacicolor,Window.family,Window.rightcolor);//GRAY
			
			JTextPaneChange.createStyle("粉粗",JTextPaneChange.styledDoc,fontSize,1,0,0,Window.emacicolor,Window.family,Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("粉",JTextPaneChange.styledDoc,fontSize,0,0,0,Window.emacicolor,Window.family,Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("粉斜",JTextPaneChange.styledDoc,fontSize,0,1,0,Window.emacicolor,Window.family,Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("粉粗斜",JTextPaneChange.styledDoc,fontSize,1,1,0,Window.emacicolor,Window.family,Window.rightcolor);//GRAY
			
			JTextPaneChange.createStyle("绿粗",JTextPaneChange.styledDoc,fontSize,1,0,0,Window.qmacicolor,Window.family,Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("绿",JTextPaneChange.styledDoc,fontSize,0,0,0,Window.qmacicolor,Window.family,Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("绿斜",JTextPaneChange.styledDoc,fontSize,0,1,0,Window.qmacicolor,Window.family,Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("绿粗斜",JTextPaneChange.styledDoc,fontSize,1,1,0,Window.qmacicolor,Window.family,Window.rightcolor);//GRAY

			MoreListener.MoreSign = moresign;
			GendaListener.fenye = fenye;
			
			SetFrame.Splitenum.setText(String.valueOf(fenye));
			SetFrame.FontSize.setText(String.valueOf(fontSize));
			ChooseFile.cizufilename = cizufilename;
			System.out.println("读配置:"+cizufilename);
			Window.tipschange = new Tips(Window.tips);

			Window.jSplitPane1.setSize(sp1width,sp1height);
		    Window.jSplitPane1.setDividerLocation(sp1);
			
			win.setBounds(x,y,width,height);
	
			SetFrameQianshuiListener.qianshui = qianshui;
			if(qianshui == 1){
				SetFrameQianshuiListener.qianshui1.setText("潜水跟打\"已开\"");
				SetFrameQianshuiListener.qianshui1.setForeground(SetFrame.open);
			}
			else{
				SetFrameQianshuiListener.qianshui1.setText("潜水跟打\"已关\"");
			}
			SetFramechangeListener.tipsign = tip;
			if(tip == 1){
				SetFramechangeListener.change.setForeground(SetFrame.open);
				SetFramechangeListener.change.setText("词语提示\"已开\"");
			}
			else{
				SetFramechangeListener.change.setText("词语提示\"已关\"");
			}
			SetspaceListener.spacesign = space;
			if(space ==1){
				SetspaceListener.space.setForeground(SetFrame.open);
				SetspaceListener.space.setText("去除空格\"已开\"");
			}
			else{
				SetspaceListener.space.setText("去除空格\"已关\"");
			}
			SetCharListener.charsign = Charset;
			if(Charset ==1){
				SetCharListener.charset.setForeground(SetFrame.open);
				SetCharListener.charset.setText("符号替换\"已开\"");
			}
			else{
				SetCharListener.charset.setText("符号替换\"已关\"");
			}
			SetFrameJinduListener.jindusign = jindusign;
			if(jindusign ==1){
				SetFrameJinduListener.jindu.setForeground(SetFrame.open);
				SetFrameJinduListener.jindu.setText("进度条显示\"已开\"");
			}
			else
				SetFrameJinduListener.jindu.setText("进度条显示\"已关\"");
		}catch(Exception ex){System.out.println("文本格式不对");}
	}
	public static void readfontnum() throws IOException{
		Reader rReader = new FileReader("../set/num.ini");
		BufferedReader rStream=new BufferedReader(rReader);
		rString = rStream.readLine();
		try{
			int num[] = {0,0,0,0};
			int pos = 0;
			for(int i=0;i<4;i++){
				pos =  rString.indexOf('%',pos)+1;
				if(pos!=-1)
					num[i] = pos;
				else break;
			}
			int numall = Integer.parseInt(rString.substring(num[0],num[1]-1));
			int right = Integer.parseInt(rString.substring(num[1],num[2]-1));
			int mis = Integer.parseInt(rString.substring(num[2],num[3]-1));
			int date = Integer.parseInt(rString.substring(num[3]));
			Window.fontallnum = numall;
			Window.rightnum = right;
			Window.misnum = mis;
			Window.datenum = date;
			rStream.close();
			rReader.close();
		}catch(Exception e){System.out.println("读取字数文件失败");}
	}
	public static void keepfontnum(int num,int rightnum,int misnum) throws IOException{
		Writer fWrite=new FileWriter("../set/num.ini",false);
		BufferedWriter wStream=new BufferedWriter(fWrite);
		String message = "%"+String.valueOf(num)+"%"+String.valueOf(rightnum)+"%"+String.valueOf(misnum)+"%"+String.valueOf(Window.datenum);
		wStream.write(message);
		wStream.close();
		fWrite.close();
	}
	public static int readsign() throws IOException{
		Reader rReader = new FileReader("readsign.ini");
		BufferedReader rStream=new BufferedReader(rReader);
		rString = rStream.readLine();
		rStream.close();
		rReader.close();
		return Integer.parseInt(rString);
	}
	public static void setreadsign() throws IOException{
		Writer fWrite=new FileWriter("readsign.ini",false);
		BufferedWriter wStream=new BufferedWriter(fWrite);
		wStream.write("1");
		wStream.close();
		fWrite.close();
	}
	public static void readzm() throws IOException{
		int num[] = {0,0};
		Reader rReader = new FileReader("../set/zm.ini");
		BufferedReader rStream=new BufferedReader(rReader);
		rString = rStream.readLine();
		int pos = 0;
		for(int i=0;i<2;i++){
			pos =  rString.indexOf('%',pos)+1;
			if(pos!=-1)
				num[i] = pos;
			else break;
		}
		System.out.println(rString);
		String zhanghao = rString.substring(num[0],num[1]-1);
		String mima = rString.substring(num[1]);
		Login.zhanghao.setText(zhanghao);
		Login.mima.setText(mima);
		rStream.close();
		rReader.close();
	}
	public static void setzm() throws IOException{
		Writer fWrite=new FileWriter("../set/zm.ini",false);
		BufferedWriter wStream=new BufferedWriter(fWrite);
		String message = "%"+Login.zhanghao.getText()+"%"+Login.mima.getText();
		wStream.write(message);
		wStream.close();
		fWrite.close();
	}
	
}