package genda1;

import gendaClient.battleClient;

import java.io.DataOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import lookplay.AchListener;

import Acticle.SendWenben;
import Login.Login;

import SetWin.SetFrameJinduListener;
import SetWin.SetFrameQianshuiListener;
import Tips.*;

public class Clip extends Thread {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				sleep(10);
				if (QQZaiwenListener.zaiwenSign)
					setZaiwenSign();
				if (GendaListener.gendaSign)
					setgendaSign();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Clip����");
			}
			// System.out.println(wenbenstr);
			// AchievementListener.setClipboardString(AchievementListener.getClipboardString());
		}
	}

	void setZaiwenSign() {
		try {
			QQZaiwenListener.zaiwenSign = false;
			QQZaiwenListener.str = AchievementListener.getClipboardString();
			// System.out.println(QQZaiwenListener.str);
			QQZaiwenListener.wenbenstr = QQZaiwenListener.regexText
					.regetText(QQZaiwenListener.str);
			Window.f3listener.F3();
			try {
				DataOutputStream out = new DataOutputStream(
						battleClient.socket.getOutputStream());
				out.writeUTF("%" + ReadyListener.BeganSign + "%" + "%"
						+ RegexText.duan1 + "#" + QQZaiwenListener.wenbenstr
						+ "%0" + "%" + Login.zhanghao.getText());
			} catch (Exception ex) {
				System.out.println("�޷������ı�����");
			}

			// System.out.println(QQZaiwenListener.zaiwenSign);
		} catch (Exception ex) {
		}

	}

	void setgendaSign() throws InterruptedException {
		GendaListener.gendaSign = false;
		sleep(100);
		GendaListener.sign = 2;
		if (Window.Pattern == 2) {
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < F3Listener.Englishword.length; i++) {
				str.append(F3Listener.Englishword[i] + "��"
						+ TipsFrame.bianma.get(F3Listener.Englishword[i])
						+ "\n");
			}
			TipsFrame.show.setText(str.toString());
		}
		Window.gendaListener.zishu.setText("�����������:"
				+ Window.gendaListener.str2.length() + "/��"
				+ Window.gendaListener.mistake); // ������ʾ
		Window.gendaListener.Keylength.setText(String.format(
				"%.2f",
				Window.gendaListener.KeyNumber
						/ Window.gendaListener.str2.length()));// �볤��ʾ
		Window.gendaListener.deleteNumber = Window.gendaListener.deleteNumber
				- Window.gendaListener.deleteTextNumber; // �˸���ʵ����Ҫ��ȥ�ظ�����
		if (Window.gendaListener.deleteNumber < 0)
			Window.gendaListener.deleteNumber = 0; // ��֤�˸�С����
		ReadyListener.BeganSign = 0; // ׼����־
		Window.suduButton.setText(String.format("%.2f",
				Window.gendaListener.sudu));
		AchievementListener
				.setClipboardString(Window.gendaListener.achievementlistener
						.getGeshi()); // ���ɼ����������
		if (SetFrameQianshuiListener.qianshui == 0) // ��ΪǱˮ����Ļ����ͳɼ�
			Window.gendaListener.achievementlistener.sendchengji();
		if (SetFrameJinduListener.jindusign == 1)// �ж��Ƿ��˽�����
			Window.gendaListener.gendajindu.jindu(Window.gendaListener.dazi
					.getText().length() + 1);
		Window.gendaListener.ChangeAllColor();
		//�Զ���һ���ж�
		double nextSpeed = Double.valueOf(String.valueOf(Window.acticle.spinnerSpeed.getValue()));
		double nextKey = Double.valueOf(String.valueOf(Window.acticle.spinnerKey.getValue()));
		double nextKeyLength = Double.valueOf(String.valueOf(Window.acticle.spinnerKeyLength.getValue()));
		double speed = Window.gendaListener.getSudu();
		double keySpeed = Window.gendaListener.KeyNumber
				/ GendaListener.comp.getSecond();
		double keyLength = Window.gendaListener.KeyNumber
				/ GendaListener.str1.length();
		if(!(nextSpeed==0&&nextKey==0&&nextKeyLength==0)
				&&(nextSpeed==0||speed>=nextSpeed)
				&&(nextKey==0||keySpeed>=nextKey)
				&&(nextKeyLength==0||keyLength<=nextKeyLength)
				){
			if(SendWenben.sendwenSign2==1){
				Window.acticle.treeListener.chouqu("��ȡ��һ��");
			}else if(SendWenben.sendwenSign==1){
				Window.acticle.treeListener.nextOrder();
			}else if(SendWenben.sendwenSign3==1){
				Window.acticle.treeListener.ciku();
			}else if(SendWenben.sendwenSign4){
				Window.acticle.treeListener.enlighWord("Ӣ����һ��");
			}
		}else if(!(nextSpeed==0&&nextKey==0&&nextKeyLength==0)&&
				Window.acticle.luanxu.isSelected()){
			Example.getWindow().mixlistener.mixButton("�ö�����");
		}

	}
}