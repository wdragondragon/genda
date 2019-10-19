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
			try { // 判断对战中
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
					System.out.println("速度"
							+ String.valueOf(sudu
									- 30
									* battleSend.Mistake
									- Integer.parseInt(Window.reducesudu
											.getText())));
				}
			} catch (Exception ex) {
				System.out.println("无法发送文本内容F1");
			}
			setClipboardString(gendageshi);// 将成绩段放入剪切板

			try { // 发送成绩给QQ窗口
				QQ qq = new QQ();
				if (Example.systemname.substring(0, 7).equals("Windows"))
					qq.sendMessage(2, qqName.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("发送成绩错误");
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
		// +"速度:"+check);

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
			// Window.gendaListener.comp.getSpeed(Window.gendaListener.str1.length(),4*(int)(AchListener.duo+AchListener.lou+AchListener.mistake));//速度显示
			else if (QQZaiwenListener.wenbenstr.length() <= 600
					&& QQZaiwenListener.wenbenstr.length() > 300)
				mistake = (int) 3
						* (AchListener.duo + AchListener.lou + AchListener.mistake);
			// sudu =
			// Window.gendaListener.comp.getSpeed(Window.gendaListener.str1.length(),3*(int)(AchListener.duo+AchListener.lou+AchListener.mistake));
			// //速度显示
			else if (QQZaiwenListener.wenbenstr.length() <= 1000
					&& QQZaiwenListener.wenbenstr.length() > 600)
				mistake = (int) 2
						* (AchListener.duo + AchListener.lou + AchListener.mistake);
			// sudu =
			// Window.gendaListener.comp.getSpeed(Window.gendaListener.str1.length(),2*(int)(AchListener.duo+AchListener.lou+AchListener.mistake));
			// //速度显示
			else if (QQZaiwenListener.wenbenstr.length() > 1000)
				// sudu =
				// Window.gendaListener.comp.getSpeed(Window.gendaListener.str1.length(),(int)(AchListener.duo+AchListener.lou+AchListener.mistake));
				// //速度显示
				mistake = (int) (AchListener.duo + AchListener.lou + AchListener.mistake);
			sudu = Window.gendaListener.comp.getSpeed(
					Window.gendaListener.str1.length(), (int) mistake); // 速度显示
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
			System.out.println("错误" + yingdamistake);
			yingdasudu = Window.gendaListener.comp.getSpeed(temp.length,
					(int) yingdamistake); // 速度显示
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
				* (1.0 * Window.tipschange.alllength / QQZaiwenListener.wenbenstr
						.length()))
				/ KeyNumber;
		dacilv = ((double) (gendaListener.daciall) / (QQZaiwenListener.wenbenstr
				.length() + deleteTextNumber));

		gendageshi = "第" + RegexText.duan1;

		if (mistake == 0)
			gendageshi += "段 速度"
					+ String.format("%.2f", sudu)
					+ " 击键"
					+ String.format("%.2f", KeyNumber / second)
					+ " 码长"
					+ String.format("%.2f", KeyNumber / length)
					+ " 标顶理论"
					+ String.format("%.2f", Tips.dingalllength
							/ QQZaiwenListener.wenbenstr.length()) + " 文章难度"
					+ String.format("%.2f", Tips.dengji) + " 字数"
					+ (int) (length) + " 回改" + (int) (deleteTextNumber) + " 退格"
					+ (int) (deleteNumber) + " 错字" + (int) (mistake) + " 键数"
					+ (int) (KeyNumber) + " 选重" + (int) (repeat) + " 键准"
					+ String.format("%.2f", Keyaccuracy * 100) + "% 键法"
					+ String.format("%.2f", Keymethod * 100) + "%(左"
					+ String.valueOf((int) left) + ":右"
					+ String.valueOf((int) right) + ":空格"
					+ String.valueOf(space) + ")" + " 打词率"
					+ String.format("%.2f", dacilv * 100) + "% 选重率"
					+ String.format("%.2f", repeat / length * 100)
					+ "% 拖拉机跟打器 " + Login.banben + " " + Example.systemname
					+ "版 ";
		else
			gendageshi += "段 速度"
					+ String.format("%.2f", sudu)
					+ "/"
					+ nomistakesudu
					+ " 击键"
					+ String.format("%.2f", KeyNumber / second)
					+ " 码长"
					+ String.format("%.2f", KeyNumber / length)
					+ " 标顶理论"
					+ String.format("%.2f", Tips.dingalllength
							/ QQZaiwenListener.wenbenstr.length()) + " 文章难度"
					+ String.format("%.2f", Tips.dengji) + " 字数"
					+ (int) (length) + " 回改" + (int) (deleteTextNumber) + " 退格"
					+ (int) (deleteNumber) + " 错字" + (int) (mistake) + " 键数"
					+ (int) (KeyNumber) + " 选重" + (int) (repeat) + " 键准"
					+ String.format("%.2f", Keyaccuracy * 100) + "% 键法"
					+ String.format("%.2f", Keymethod * 100) + "%(左"
					+ String.valueOf((int) left) + ":右"
					+ String.valueOf((int) right) + ":空格"
					+ String.valueOf(space) + ")" + " 打词率"
					+ String.format("%.2f", dacilv * 100) + "% 选重率"
					+ String.format("%.2f", repeat / length * 100)
					+ "% 拖拉机跟打器 " + Login.banben + " " + Example.systemname
					+ "版 ";

		if (Window.Pattern == 1)
			gendageshi += " 看打版 " + AchListener.lookplay + " 校验"
					+ DoCheck.buildcheckstr(check, "kanda");
		else if (Window.Pattern == 2)
			if (mistake == 0)
				gendageshi += "英打版 速度:" + String.format("%.2f", yingdasudu)
						+ " 单词" + temp.length + " 错" + (int) yingdamistake
						+ " 校验" + DoCheck.buildcheckstr(check, "yingda");
			else
				gendageshi += "英打版 速度:" + String.format("%.2f", yingdasudu)
						+ "/" + yingdanomistakesudu + " 单词" + temp.length
						+ " 错" + (int) yingdamistake + " 校验"
						+ DoCheck.buildcheckstr(check, "yingda");
		else
			gendageshi += " 校验" + DoCheck.buildcheckstr(check, "genda");
		// ReadyListener.ReadyDuan++;
		table.addRow();
		try {
			sendhistory();
		} catch (Exception e) {
			System.out.println("历史成绩错误");
		}
		try {
			if (Window.everydaySign) {
				sendsaiwen();
				Login.out.writeUTF(KeyPassword.convertMD5("扣扣发送成绩"
						+ Login.zhanghao.getText() + "已打今日赛文\n成绩：速度"
						+ String.format("%.2f", sudu) + " 击键"
						+ String.format("%.2f", KeyNumber / second) + " 码长"
						+ String.format("%.2f", KeyNumber / length)));
				win.setAlwaysOnTop(false);
				Window.everydaySign = false;
			}
		} catch (Exception e) {
			System.out.println("每日赛文成绩错误 ACHI 112");
		}
		try { // 判断对战中
			if (!battleClient.socket.isClosed()) {
				gendageshi = gendageshi
						+ " 让速"
						+ Window.reducesudu.getText()
						+ " 重打"
						+ battleSend.Mistake
						+ " 最终速度(显示速度-重打*30-让速)"
						+ String.format("%.2f", sudu - 30 * battleSend.Mistake
								- Integer.parseInt(Window.reducesudu.getText()));
				gendageshi = gendageshi + " 正在对战中 " + battleReadThread.Whowin;
			}
		} catch (Exception ex) {
			System.out.println("无法设置对战后缀");
		}
		return gendageshi;
	}

	public void sendsaiwen() {
		if (Login.dengluSign == 1) {
			String message = "成绩" + "%" + Login.zhanghao.getText() + "%"
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
					+ String.format("%.2f", Tips.dengji);
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
			String message = "历史" + "%" + Login.zhanghao.getText() + "%"
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
					+ String.format("%.2f", Tips.dengji);// 14
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
		// 获取系统剪贴板
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		// 封装文本内容
		Transferable trans = new StringSelection(text);
		// 把文本内容设置到系统剪贴板
		clipboard.setContents(trans, null);
		clipboard = null;
	}

	public static String getClipboardString() {
		// 获取系统剪贴板
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		// 获取剪贴板中的内容
		Transferable trans = clipboard.getContents(null);

		if (trans != null) {
			// 判断剪贴板中的内容是否支持文本
			if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				try {
					// 获取剪贴板中的文本内容
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
