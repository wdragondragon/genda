package genda1;

import java.awt.event.ActionEvent;


import javax.swing.*;

import gendaClient.battleClient;
import gendaClient.battleSend;
import SetWin.SetFrameJinduListener;
public class F3Listener extends AbstractAction{
	JButton F3;
	JLabel zishu;
	JTextArea dazi,chengji;
	JTextPane wenben;
	GendaJindutiao gendajindu;
	GendaListener gendaListener;
	JScrollBar JSB;
	public void setDaziText(JTextArea t){
		dazi = t;
	}
	public void setChengjiText(JTextArea t){
		chengji = t;
	}
	public void setZishu(JLabel zishu2){
		zishu = zishu2;
	}
	public void setWenben(JTextPane t){
		wenben = t;
	}
	public void setGendaListener(GendaListener t){
		gendaListener = t;
	}
	public void setJSB(JScrollBar t){
		JSB = t;
	}
	public void setJProgressBar(GendaJindutiao t){
		gendajindu = t;
	}
	public void actionPerformed(ActionEvent e) {
		F3();
	}
	public void F3(){
		dazi.setText(null);
		dazi.setEditable(true);
		
		gendaListener.setSign(0);
		zishu.setText("字数:"+wenben.getText().length()+"/已打:"+0);
		
		gendaListener.KeyNumber = 0;
		gendaListener.deleteNumber = 0;
		gendaListener.deleteTextNumber = 0;
		gendaListener.left = 0;
		gendaListener.right = 0;
		gendaListener.repeat = 0;
		gendaListener.space = 0;
		gendaListener.suduButton.setText("0.00");
		gendaListener.KeysuduButton.setText("0.00");
		gendaListener.Keylength.setText("0.00");
//		gendaListener.record = "";
		battleSend.Mistake ++;
		//进度条
		if(SetFrameJinduListener.jindusign==1)
			gendajindu.open(QQZaiwenListener.wenbenstr.length());//进度条开启
		//字体载文
		Window.tipschange.changecolortip();
		wenben.setText("");
		Window.gendaListener.ChangeFontColor();
		wenben.setCaretPosition(0);
		//打词重置
		gendaListener.daciall = 0;
		gendaListener.daci = 0;
		//理论码长
		QQZaiwenListener.lilun = 1.0*Window.tipschange.compalllength()/QQZaiwenListener.wenbenstr.length();
		Window.lilunma.setText("理论码长:"+String.format("%.2f", QQZaiwenListener.lilun));
		Window.zishu.setText("字数:"+QQZaiwenListener.wenbenstr.length()+"/已打:"+0+"/错"+0);

		dazi.requestFocusInWindow();
	}
}