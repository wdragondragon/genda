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
//			JOptionPane.showMessageDialog(new JTextArea(),"���Ĺ���ֻ֧�ַ���״̬��ʹ��");return;}
		String share = "";
		if(SendWenben.sendwenSign==1)
			share = SendWenben.title+"\n"+QQZaiwenListener.wenbenstr+"\n-----"+"��"+String.valueOf(RegexText.duan1)+"��-��"+(ActicleListener.length-ActicleListener.fontweizhi-ActicleListener.fontnum)+"��";
		else if(SendWenben.sendwenSign2==1)
			share = SendWenben.title+"�������ȡ����ģʽ"+"\n"+QQZaiwenListener.wenbenstr+"\n-----"+"��"+String.valueOf(RegexText.duan1)+"��";
		else
			share = SendWenben.title+"\n"+QQZaiwenListener.wenbenstr+"\n-----"+"��"+String.valueOf(RegexText.duan1)+"��";
		AchievementListener.setClipboardString(share); //���ɼ����������
		System.out.println(AchievementListener.getClipboardString());
		try {
			if(Example.systemname.substring(0,7).equals("Windows"))
				QQ.sendMessage(2,qqName.getText());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("������ʧ��");
		}
	}
}
