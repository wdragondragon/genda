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
				System.out.println("Clip错误");
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
				System.out.println("无法发送文本内容");
			}

			// System.out.println(QQZaiwenListener.zaiwenSign);
		} catch (Exception ex) {
		}

	}

	void setgendaSign() throws InterruptedException {
		GendaListener.gendaSign = false;
		sleep(100);
		GendaListener.sign = 2;

		// if(Window.Pattern==1){
		// if(QQZaiwenListener.wenbenstr.length()<=300)
		// Window.gendaListener.sudu =
		// Window.gendaListener.comp.getSpeed(Window.gendaListener.str1.length(),4*(int)(AchListener.duo+AchListener.lou+AchListener.mistake));//速度显示
		// else
		// if(QQZaiwenListener.wenbenstr.length()<=600&&QQZaiwenListener.wenbenstr.length()>300)
		// Window.gendaListener.sudu =
		// Window.gendaListener.comp.getSpeed(Window.gendaListener.str1.length(),3*(int)(AchListener.duo+AchListener.lou+AchListener.mistake));
		// //速度显示
		// else
		// if(QQZaiwenListener.wenbenstr.length()<=1000&&QQZaiwenListener.wenbenstr.length()>600)
		// Window.gendaListener.sudu =
		// Window.gendaListener.comp.getSpeed(Window.gendaListener.str1.length(),2*(int)(AchListener.duo+AchListener.lou+AchListener.mistake));
		// //速度显示
		// else if(QQZaiwenListener.wenbenstr.length()>1000)
		// Window.gendaListener.sudu =
		// Window.gendaListener.comp.getSpeed(Window.gendaListener.str1.length(),(int)(AchListener.duo+AchListener.lou+AchListener.mistake));
		// //速度显示
		// }
		// else if(Window.Pattern==2){
		// Window.gendaListener.sudu =
		// Window.gendaListener.comp.getSpeed(Window.gendaListener.str1.length(),(int)(Window.gendaListener.mistake));
		// //速度显示
		//
		// }
		// else
		// Window.gendaListener.sudu =
		// Window.gendaListener.comp.getSpeed(Window.gendaListener.str1.length(),(int)(Window.gendaListener.mistake));
		// //速度显示
		if (Window.Pattern == 2) {
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < F3Listener.Englishword.length; i++) {
				str.append(F3Listener.Englishword[i] + "："
						+ TipsFrame.bianma.get(F3Listener.Englishword[i])
						+ "\n");
			}
			TipsFrame.show.setText(str.toString());
		}
		Window.gendaListener.zishu.setText("跟打完毕字数:"
				+ Window.gendaListener.str2.length() + "/错"
				+ Window.gendaListener.mistake); // 字数显示
		Window.gendaListener.Keylength.setText(String.format(
				"%.2f",
				Window.gendaListener.KeyNumber
						/ Window.gendaListener.str2.length()));// 码长显示
		Window.gendaListener.deleteNumber = Window.gendaListener.deleteNumber
				- Window.gendaListener.deleteTextNumber; // 退格真实数量要减去回改数量
		if (Window.gendaListener.deleteNumber < 0)
			Window.gendaListener.deleteNumber = 0; // 保证退格小于零
		ReadyListener.BeganSign = 0; // 准备标志
		Window.suduButton.setText(String.format("%.2f",
				Window.gendaListener.sudu));
		AchievementListener
				.setClipboardString(Window.gendaListener.achievementlistener
						.getGeshi()); // 将成绩放入剪贴板
		if (SetFrameQianshuiListener.qianshui == 0) // 不为潜水跟打的话发送成绩
			Window.gendaListener.achievementlistener.sendchengji();
		if (SetFrameJinduListener.jindusign == 1)// 判断是否开了进度条
			Window.gendaListener.gendajindu.jindu(Window.gendaListener.dazi
					.getText().length() + 1);
		Window.gendaListener.ChangeAllColor();

	}
}