package genda1;

import java.awt.event.ActionEvent;

import javax.swing.*;

import gendaClient.battleClient;
import gendaClient.battleSend;
import Acticle.SendWenben;
import SetWin.SetFrameJinduListener;
import Tips.Tips;
import Tips.TipsFrame;

public class F3Listener extends AbstractAction {
	JButton F3;
	JLabel zishu;
	JTextArea dazi, chengji;
	JTextPane wenben;
	GendaJindutiao gendajindu;
	GendaListener gendaListener;
	JScrollBar JSB;
	public static int[] EnglishType;
	public static String[] Englishword;

	public void setDaziText(JTextArea t) {
		dazi = t;
	}

	public void setChengjiText(JTextArea t) {
		chengji = t;
	}

	public void setZishu(JLabel zishu2) {
		zishu = zishu2;
	}

	public void setWenben(JTextPane t) {
		wenben = t;
	}

	public void setGendaListener(GendaListener t) {
		gendaListener = t;
	}

	public void setJSB(JScrollBar t) {
		JSB = t;
	}

	public void setJProgressBar(GendaJindutiao t) {
		gendajindu = t;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			F3();
		} catch (Exception ex) {
		}
	}

	public void F3() {
		if (Window.everydaySign)
			return;
		f3caozuo();
	}

	public void f3caozuo() {
		dazi.setText(null);
		dazi.setEditable(true);

		gendaListener.setSign(0);
		zishu.setText("����:" + wenben.getText().length() + "/�Ѵ�:" + 0);

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
		// gendaListener.record = "";
		battleSend.Mistake++;
		// ������
		if (SetFrameJinduListener.jindusign == 1)
			gendajindu.open(QQZaiwenListener.wenbenstr.length());// ����������
		// ��������
		Window.tipschange.changecolortip();
		wenben.setText("");
		Window.gendaListener.ChangeFontColor();
		wenben.setCaretPosition(0);
		// �������
		gendaListener.daciall = 0;
		gendaListener.daci = 0;
		// �����볤
		Window.tipschange.compalllength();

//		Window.lilunma.setText("�����볤:"
//				+ String.format("%.2f", 1.0 * Window.tipschange.alllength
//						/ QQZaiwenListener.wenbenstr.length()));
		Window.dinglilunma.setText("�궥����:"
				+ String.format("%.2f", 1.0 * Window.tipschange.allCodeLength
						/ QQZaiwenListener.wenbenstr.length()));
		Window.zishu.setText("����:" + QQZaiwenListener.wenbenstr.length()
				+ "/�Ѵ�:" + 0 + "/��" + 0);
		Window.tipschange
				.changeTips(QQZaiwenListener.wenbenstr.substring(0, 1));// ���ֱ�����ʾ����

		if (Window.Pattern == 2) {
			Englishword = QQZaiwenListener.wenbenstr.split(" ");
			EnglishType = new int[Englishword.length];
			for (int i = 0; i < Englishword.length; i++) {
				int sign = QQZaiwenListener.wenbenstr.indexOf(Englishword[i]);
				if (i > 0)
					while (sign < EnglishType[i - 1]) {
						sign = QQZaiwenListener.wenbenstr.indexOf(
								Englishword[i], sign + 1);
						if (sign == -1)
							break;
					}
				EnglishType[i] = sign;
			}
			Window.tips.setText(TipsFrame.bianma.get(Englishword[0]));
			TipsFrame.show.setText(Englishword[0] + "��"
					+ TipsFrame.bianma.get(Englishword[0]));
		}
		dazi.requestFocusInWindow();
	}
}