package genda1;

import gendaClient.*;
import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import Tips.*;
import keep.KeyPassword;
import lookplay.AchListener;
import Check.DoCheck;
import Login.Login;
import QQ.QQ;

public class AchievementListener extends AbstractAction {
	JLabel qqName;
	JButton achievementButton;
	GendaListener gendaListener;
	JTextArea chengji, dazi;
	JTextPane wenben;
	double sudu, second, length, Keymethod, right, left, KeyNumber, mistake,
			deleteNumber, deleteTextNumber, repeat, fengzhi, yingdasudu,
			yingdamistake;
	String yingdanomistakesudu;
	public static double Keyaccuracy;
	public static double dacilv;
	String gendageshi;
	TableAdd table;
	int space;

	private Window win;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		sendchengji();
	}

	public void sendchengji() {
		if (gendaListener.sign == 2) {
			try { // �ж϶�ս��
				if (!battleClient.socket.isClosed()) {
					if (Window.reducesudu.getText() != "")
						battleSend.out.writeUTF("%"
								+ ReadyListener.BeganSign
								+ "%"
								+ "%"
								+ RegexText.duan1
								+ "#"
								+ Window.wenben.getText()
								+ "%"
								+ String.valueOf(sudu
										- 30
										* battleSend.Mistake
										- Integer.parseInt(Window.reducesudu
												.getText())) + "%"
								+ Login.zhanghao.getText());
					else
						battleSend.out
								.writeUTF("%"
										+ ReadyListener.BeganSign
										+ "%"
										+ "%"
										+ RegexText.duan1
										+ "#"
										+ Window.wenben.getText()
										+ "%"
										+ String.valueOf(sudu - 30
												* battleSend.Mistake) + "%"
										+ Login.zhanghao.getText());
					System.out.println("�ٶ�"
							+ String.valueOf(sudu
									- 30
									* battleSend.Mistake
									- Integer.parseInt(Window.reducesudu
											.getText())));
				}
			} catch (Exception ex) {
				System.out.println("�޷������ı�����F1");
			}
			setClipboardString(gendageshi);// ���ɼ��η�����а�

			try { // ���ͳɼ���QQ����
				QQ qq = new QQ();
				if (Example.systemname.substring(0, 7).equals("Windows"))
					qq.sendMessage(2, qqName.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("���ͳɼ�����");
			}
		}
	}

	public String getGeshi() {
		sudu = gendaListener.getSudu();
		KeyNumber = gendaListener.getKeyNumber();
		second = gendaListener.getSecond();
		length = gendaListener.getLength();
		mistake = gendaListener.getMistake();
		deleteNumber = gendaListener.getDeleteNumber();
		deleteTextNumber = gendaListener.getDeleteTextNumber();
		right = gendaListener.getRight();
		left = gendaListener.getLeft();
		repeat = gendaListener.getRepeat();
		space = gendaListener.getSpace();
		fengzhi = gendaListener.getFenzhi();

		String check = String.format(
				"%.2f",
				Double.parseDouble(String.format("%.2f", sudu))
						+ Double.parseDouble(String.format("%.2f", KeyNumber
								/ second))
						+ Double.parseDouble(String.format("%.2f", KeyNumber
								/ length)));
		// System.out.println(Double.parseDouble(String.format("%.2f", sudu))
		// +":"
		// +Double.parseDouble(String.format("%.2f",KeyNumber/second))
		// +":"
		// +Double.parseDouble(String.format("%.2f", KeyNumber/length))
		// +":"
		// +"�ٶ�:"+check);

		if (right != 0)
			Keymethod = left / right;
		else
			Keymethod = 1;
		String nomistakesudu = "";
		String[] temp = QQZaiwenListener.wenbenstr.split(" ");
		int[] EnglishType = new int[temp.length];
		if (Window.Pattern == 1) {
			if (QQZaiwenListener.wenbenstr.length() <= 300)
				mistake = (int) 4
						* (AchListener.duo + AchListener.lou + AchListener.mistake);
			// sudu =
			// Window.gendaListener.comp.getSpeed(Window.gendaListener.str1.length(),4*(int)(AchListener.duo+AchListener.lou+AchListener.mistake));//�ٶ���ʾ
			else if (QQZaiwenListener.wenbenstr.length() <= 600
					&& QQZaiwenListener.wenbenstr.length() > 300)
				mistake = (int) 3
						* (AchListener.duo + AchListener.lou + AchListener.mistake);
			// sudu =
			// Window.gendaListener.comp.getSpeed(Window.gendaListener.str1.length(),3*(int)(AchListener.duo+AchListener.lou+AchListener.mistake));
			// //�ٶ���ʾ
			else if (QQZaiwenListener.wenbenstr.length() <= 1000
					&& QQZaiwenListener.wenbenstr.length() > 600)
				mistake = (int) 2
						* (AchListener.duo + AchListener.lou + AchListener.mistake);
			// sudu =
			// Window.gendaListener.comp.getSpeed(Window.gendaListener.str1.length(),2*(int)(AchListener.duo+AchListener.lou+AchListener.mistake));
			// //�ٶ���ʾ
			else if (QQZaiwenListener.wenbenstr.length() > 1000)
				// sudu =
				// Window.gendaListener.comp.getSpeed(Window.gendaListener.str1.length(),(int)(AchListener.duo+AchListener.lou+AchListener.mistake));
				// //�ٶ���ʾ
				mistake = (int) (AchListener.duo + AchListener.lou + AchListener.mistake);
			sudu = Window.gendaListener.comp.getSpeed(
					Window.gendaListener.str1.length(), (int) mistake); // �ٶ���ʾ
		} else if (Window.Pattern == 2) {
			ArrayList<Integer> misType = new ArrayList<Integer>();
			for (int i = 0; i < temp.length; i++) {
				int sign = QQZaiwenListener.wenbenstr.indexOf(temp[i])
						+ temp[i].length();
				if (i != 0 && EnglishType[i - 1] == sign)
					sign = -1;
				EnglishType[i] = sign;

				// EnglishType.add(sign+"%"+temp[i]+"%"+(sign+temp[i].length()));
			}
			for (int i = 0; i < GendaListener.missign.size(); i++) {
				int missign = GendaListener.missign.get(i);
				System.out.println(i);
				for (int j = 0; j < temp.length; j++) {
					if (EnglishType[j] > missign && EnglishType[i] != -1) {
						if (!misType.contains(j)) {
							misType.add(j);
						}
						break;
					}
				}
			}
			sudu = Window.gendaListener.comp.getSpeed(
					Window.gendaListener.str1.length(),
					(int) (Window.gendaListener.mistake));
			yingdamistake = misType.size();
			System.out.println("����" + yingdamistake);
			yingdasudu = Window.gendaListener.comp.getSpeed(temp.length,
					(int) yingdamistake); // �ٶ���ʾ
			yingdanomistakesudu = String.format("%.2f",
					Window.gendaListener.comp.getSpeed(temp.length, 0));
		} else {
			nomistakesudu = String.format("%.2f", Window.gendaListener.comp
					.getSpeed(Window.dazi.getText().length(), 0));
			sudu = Window.gendaListener.comp.getSpeed(
					Window.gendaListener.str1.length(),
					(int) (Window.gendaListener.mistake));
		}
		nomistakesudu = String.format(
				"%.2f",
				Window.gendaListener.comp.getSpeed(
						Window.gendaListener.str1.length(), 0));

		Keyaccuracy = (KeyNumber - deleteNumber * 2 - deleteTextNumber
				* (1.0 * Window.tipschange.allCodeLength / QQZaiwenListener.wenbenstr
						.length()))
				/ KeyNumber;
		dacilv = ((double) (gendaListener.daciall) / (QQZaiwenListener.wenbenstr
				.length() + deleteTextNumber));

		gendageshi = "��" + RegexText.duan1;

		if (mistake == 0)
			gendageshi += "�� �ٶ�"
					+ String.format("%.2f", sudu)
					+ " ����"
					+ String.format("%.2f", KeyNumber / second)
					+ " �볤"
					+ String.format("%.2f", KeyNumber / length)
					+ " �궥����"
					+ String.format("%.2f", Tips.allCodeLength
							/ QQZaiwenListener.wenbenstr.length()) + " ����"
					+ (int) (length) + " �ظ�" + (int) (deleteTextNumber) + " �˸�"
					+ (int) (deleteNumber) + " ����" + (int) (mistake) + " ����"
					+ (int) (KeyNumber) + " ѡ��" + (int) (repeat) + " ��׼"
					+ String.format("%.2f", Keyaccuracy * 100) + "% ����"
					+ String.format("%.2f", Keymethod * 100) + "%(��"
					+ String.valueOf((int) left) + ":��"
					+ String.valueOf((int) right) + ":�ո�"
					+ String.valueOf(space) + ")" + " �����"
					+ String.format("%.2f", dacilv * 100) + "% ѡ����"
					+ String.format("%.2f", repeat / length * 100)
					+ "% ������������ " + Login.banben + " " + Example.systemname
					+ "�� ";
		else
			gendageshi += "�� �ٶ�"
					+ String.format("%.2f", sudu)
					+ "/"
					+ nomistakesudu
					+ " ����"
					+ String.format("%.2f", KeyNumber / second)
					+ " �볤"
					+ String.format("%.2f", KeyNumber / length)
					+ " �궥����"
					+ String.format("%.2f", Tips.allCodeLength
							/ QQZaiwenListener.wenbenstr.length()) + " ����"
					+ (int) (length) + " �ظ�" + (int) (deleteTextNumber) + " �˸�"
					+ (int) (deleteNumber) + " ����" + (int) (mistake) + " ����"
					+ (int) (KeyNumber) + " ѡ��" + (int) (repeat) + " ��׼"
					+ String.format("%.2f", Keyaccuracy * 100) + "% ����"
					+ String.format("%.2f", Keymethod * 100) + "%(��"
					+ String.valueOf((int) left) + ":��"
					+ String.valueOf((int) right) + ":�ո�"
					+ String.valueOf(space) + ")" + " �����"
					+ String.format("%.2f", dacilv * 100) + "% ѡ����"
					+ String.format("%.2f", repeat / length * 100)
					+ "% ������������ " + Login.banben + " " + Example.systemname
					+ "�� ";

		if (Window.Pattern == 1)
			gendageshi += " ����� " + AchListener.lookplay + " У��"
					+ DoCheck.buildcheckstr(check, "kanda");
		else if (Window.Pattern == 2)
			if (mistake == 0)
				gendageshi += "Ӣ��� �ٶ�:" + String.format("%.2f", yingdasudu)
						+ " ����" + temp.length + " ��" + (int) yingdamistake
						+ " У��" + DoCheck.buildcheckstr(check, "yingda");
			else
				gendageshi += "Ӣ��� �ٶ�:" + String.format("%.2f", yingdasudu)
						+ "/" + yingdanomistakesudu + " ����" + temp.length
						+ " ��" + (int) yingdamistake + " У��"
						+ DoCheck.buildcheckstr(check, "yingda");
		else
			gendageshi += " У��" + DoCheck.buildcheckstr(check, "genda");
		// ReadyListener.ReadyDuan++;
		table.addRow();
		try {
			sendhistory();
		} catch (Exception e) {
			System.out.println("��ʷ�ɼ�����");
		}
		try {
			if (Window.everydaySign) {
				sendsaiwen();
				Login.out.writeUTF(KeyPassword.convertMD5("�ۿ۷��ͳɼ�"
						+ Login.zhanghao.getText() + "�Ѵ��������\n�ɼ����ٶ�"
						+ String.format("%.2f", sudu) + " ����"
						+ String.format("%.2f", KeyNumber / second) + " �볤"
						+ String.format("%.2f", KeyNumber / length)));
				win.setAlwaysOnTop(false);
				Window.everydaySign = false;
			}
		} catch (Exception e) {
			System.out.println("ÿ�����ĳɼ����� ACHI 112");
		}
		try { // �ж϶�ս��
			if (!battleClient.socket.isClosed()) {
				gendageshi = gendageshi
						+ " ����"
						+ Window.reducesudu.getText()
						+ " �ش�"
						+ battleSend.Mistake
						+ " �����ٶ�(��ʾ�ٶ�-�ش�*30-����)"
						+ String.format("%.2f", sudu - 30 * battleSend.Mistake
								- Integer.parseInt(Window.reducesudu.getText()));
				gendageshi = gendageshi + " ���ڶ�ս�� " + battleReadThread.Whowin;
			}
		} catch (Exception ex) {
			System.out.println("�޷����ö�ս��׺");
		}
		return gendageshi;
	}

	public void sendsaiwen() {
		if (Login.dengluSign == 1) {
			String message = "�ɼ�" + "%" + Login.zhanghao.getText() + "%"
					+ String.format("%.2f", sudu) + "%"
					+ String.format("%.2f", KeyNumber / second) + "%"
					+ String.format("%.2f", KeyNumber / length) + "%"
					+ (int) (length) + "%" + (int) (deleteTextNumber) + "%"
					+ (int) (deleteNumber) + "%" + (int) (mistake) + "%"
					+ (int) (repeat) + "%"
					+ String.format("%.2f", Keyaccuracy * 100) + "%"
					+ String.format("%.2f", Keymethod * 100) + "%"
					+ String.format("%.2f", dacilv * 100) + "%"
					+ String.valueOf(GendaListener.comp.getSecond()) + "%"
					+ String.format("%.2f", 0.0);
			message = KeyPassword.convertMD5(message);
			try {
				Login.out.writeUTF(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void sendhistory() {
		if (Login.dengluSign == 1) {
			String message = "��ʷ" + "%" + Login.zhanghao.getText() + "%"
					+ String.format("%.2f", sudu) + "%"
					+ String.format("%.2f", KeyNumber / second) + "%"
					+ String.format("%.2f", KeyNumber / length) + "%"
					+ (int) (length) + "%" + (int) (deleteTextNumber) + "%"
					+ (int) (deleteNumber) + "%" + (int) (mistake) + "%"
					+ (int) (repeat) + "%"
					+ String.format("%.2f", Keyaccuracy * 100) + "%"
					+ String.format("%.2f", Keymethod * 100) + "%"
					+ String.format("%.2f", dacilv * 100) + "%"
					+ String.valueOf(GendaListener.comp.getSecond()) + "%"
					+ QQZaiwenListener.wenbenstr + "%"
					+ String.valueOf(RegexText.duan1) + "%"
					+ String.format("%.2f", 0.0);// 14
			message = KeyPassword.convertMD5(message);
			try {
				Login.out.writeUTF(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setWin(Window win) {
		this.win = win;
	}

	public void setGendaListener(GendaListener t) {
		gendaListener = t;
	}

	public void setChengjiText(JTextArea t) {
		chengji = t;
	}

	public void setDaziText(JTextArea t) {
		dazi = t;
	}

	public void setWenbenText(JTextPane t) {
		wenben = t;
	}

	public void setQQName(JLabel qqName2) {
		qqName = qqName2;
	}

	public void setTable(TableAdd t) {
		table = t;
	}

	public static void setClipboardString(String text) {
		// ��ȡϵͳ������
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		// ��װ�ı�����
		Transferable trans = new StringSelection(text);
		// ���ı��������õ�ϵͳ������
		clipboard.setContents(trans, null);
		clipboard = null;
	}

	public static String getClipboardString() {
		// ��ȡϵͳ������
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		// ��ȡ�������е�����
		Transferable trans = clipboard.getContents(null);

		if (trans != null) {
			// �жϼ������е������Ƿ�֧���ı�
			if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				try {
					// ��ȡ�������е��ı�����
					String text = (String) trans
							.getTransferData(DataFlavor.stringFlavor);
					clipboard = null;
					return text;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		clipboard = null;
		return null;
	}
}
