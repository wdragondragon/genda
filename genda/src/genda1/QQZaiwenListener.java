package genda1;

import gendaClient.*;
import Login.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import QQ.*;
import SetWin.*;
import Tips.Tips;

public class QQZaiwenListener extends AbstractAction{
	public static double lilun;
	JTextArea dazi;
	JTextPane wenben;
	JLabel qqName;
	GendaListener gendalistener;
	GendaJindutiao gendajindu;
	SetFrameJinduListener setframejinduListener;
	int i;
	public static int duan;
	String str,dvbi;
	public static String wenbenstr = "";
	QQ qq = new QQ();
	RegexText regexText = new RegexText();
	private Tips tipschange;
	private JButton lilunma;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			QQ.sendMessage(1,qqName.getText());
			str = AchievementListener.getClipboardString();
			System.out.println(str);
			wenbenstr = regexText.regetText(str);
			dazi.setText(null);
			dazi.setEditable(true);
			
			gendalistener.sign = 0;
			gendalistener.deleteNumber = 0;
			gendalistener.deleteTextNumber = 0;
			gendalistener.KeyNumber = 0;
			gendalistener.left = 0;
			gendalistener.right = 0;
			gendalistener.repeat = 0;
			gendalistener.space = 0;
//			gendalistener.record = "";
			dazi.requestFocusInWindow();
			if(SetFrameJinduListener.jindusign==1)
				gendajindu.open(wenbenstr.length());
			//字体载文
			tipschange.changecolortip();
			wenben.setText("");
			gendalistener.ChangeFontColor();
			wenben.setCaretPosition(0);
			//计算理论码长
			lilun = tipschange.compalllength()/wenbenstr.length();
			lilunma.setText("理论码长:"+String.format("%.2f", lilun));
			
			gendalistener.daciall = 0;
			gendalistener.daci = 0;
			try{
				DataOutputStream out = new DataOutputStream(battleClient.socket.getOutputStream());
				out.writeUTF("%"+ReadyListener.BeganSign+"%"+"%"+wenbenstr+"%0"+"%"+Login.zhanghao.getText());
			}
			catch(Exception ex){
				System.out.println("无法发送文本内容");
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			System.out.println("载文失败");
		}
	}
	public void setDaziText(JTextArea t){
		dazi = t;
	}
	
	public void setWenbenText(JTextPane t){
		wenben = t;
	}
	public void setQQName(JLabel qqName2) {
		qqName = qqName2;
		
	}
	public void setGendaListener(GendaListener t){
		gendalistener = t;
	}
	public void setJProgressBar(GendaJindutiao t){
		gendajindu = t;
	}
	public void setTipschange(Tips tipschange){
		this.tipschange = tipschange;
	}
	public void setLilunma(JButton t){
		lilunma = t;
	}
	
}
