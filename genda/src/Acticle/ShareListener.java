package Acticle;

import genda1.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import QQ.QQ;

public class ShareListener extends AbstractAction {
	static JLabel qqName;
	public ShareListener(JLabel qqName){
		ShareListener.qqName = qqName;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		send();
	}
	public static  void send(){
//		if(SendWenben.sendwenSign==0&&SendWenben.sendwenSign2==0){
//			JOptionPane.showMessageDialog(new JTextArea(),"发文功能只支持发文状态下使用");return;}
		String share = "";
		if(SendWenben.sendwenSign==1)
			share = SendWenben.title+"\n"+QQZaiwenListener.wenbenstr+"\n-----"+"第"+String.valueOf(RegexText.duan1)+"段-余"+String.valueOf(ActicleListener.length-ActicleListener.fontweizhi-ActicleListener.fontnum)+"字";
		else if(SendWenben.sendwenSign2==1)
			share = SendWenben.title+"：随机抽取发文模式"+"\n"+QQZaiwenListener.wenbenstr+"\n-----"+"第"+String.valueOf(RegexText.duan1)+"段";
		else
			share = SendWenben.title+"\n"+QQZaiwenListener.wenbenstr+"\n-----"+"第"+String.valueOf(RegexText.duan1)+"段";
		AchievementListener.setClipboardString(share); //将成绩放入剪贴板
		System.out.println(AchievementListener.getClipboardString());
		try {
			QQ.sendMessage(2,qqName.getText());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("分享发文失败");
		}
	}
}
