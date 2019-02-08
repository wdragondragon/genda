package genda1;
import java.awt.event.ActionEvent;

import javax.swing.*;

import SetWin.SetFrameJinduListener;
import Tips.Tips;
public class JianQieListener extends AbstractAction {
	JTextArea dazi;
	JTextPane wenben;
	JLabel zishu;
	GendaJindutiao gendajindu;
	GendaListener gendaListener;
	JScrollBar JSB;
	SetFrameJinduListener setframejinduListener;
	private Tips tipschange;
	private JButton lilunma;
	public void setWenbenText(JTextPane t){
		wenben = t;
	}
	public void setDaziText(JTextArea t){
		dazi = t;
	}
	public void setGendaListener(GendaListener t){
		gendaListener = t;
	}
	public void setZishu(JLabel zishu2){
		zishu = zishu2;
	}
	public void setJSB(JScrollBar t){
		JSB = t;
	}
	public void actionPerformed(ActionEvent arg0) {
		if(Window.one.isVisible()){
//			JOptionPane.showMessageDialog(new JTextArea(),"在线对战不支持剪贴板载文");return;
		}
		wenben.setText(null);
		wenben.setEditable(true);
		wenben.paste();
		dazi.setText(null);
		dazi.setEditable(true);
		gendaListener.setSign(0);
		zishu.setText("字数:"+wenben.getText().length()+"/已打:"+0);
		dazi.requestFocusInWindow();
		JSB.setValue(JSB.getMinimum());
		wenben.setEditable(false);
		wenben.setCaretPosition(0);
		gendaListener.KeyNumber = 0;//键数清零
		gendaListener.deleteNumber = 0;
		gendaListener.deleteTextNumber = 0;
		gendaListener.left = 0;
		gendaListener.right = 0;
		gendaListener.repeat = 0;
		gendaListener.record = "";
		gendaListener.space = 0;
		RegexText.duan1 = 1;
		if(SetFrameJinduListener.jindusign==1)
			gendajindu.open(wenben.getText().length());
		QQZaiwenListener.wenbenstr = AchievementListener.getClipboardString().replaceAll("\\s*", "");
		tipschange.changecolortip();
		wenben.setText("");
		gendaListener.ChangeFontColor();
		wenben.setCaretPosition(0);
		QQZaiwenListener.lilun = 1.0*tipschange.compalllength()/QQZaiwenListener.wenbenstr.length();
		lilunma.setText("理论码长:"+String.format("%.2f", QQZaiwenListener.lilun));
		
		//打词重置
		gendaListener.daciall = 0;
		gendaListener.daci = 0;
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