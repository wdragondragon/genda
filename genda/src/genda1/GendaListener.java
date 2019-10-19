package genda1;

import gendaClient.battleClient;
import gendaClient.battleSend;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.*;
import javax.swing.*;

import keep.readWrite;

import com.sun.jna.platform.win32.WinDef.CHAR;

import Acticle.SendWenben;
import Login.RecordChange;
import SetWin.SetFrame;
import SetWin.SetFrameJinduListener;
import SetWin.SetFrameQianshuiListener;
import SetWin.SetFramechangeListener;
import Tips.Tips;
import Tips.TipsFrame;

public class GendaListener implements DocumentListener, KeyListener {
	JTextArea dazi, chengji;
	JScrollPane wenben1;
	JTextPane wenben;
	static String str1 = "";
	static String str2 = "";
	public static String record;
	public static ComputeSpeed comp = new ComputeSpeed();
	ComputeSpeed compdazi = new ComputeSpeed();
	ComputeSpeed comphvgd = new ComputeSpeed();
	ComputeSpeed fengzhi = new ComputeSpeed();
	public static int fenye = 500;
	public static int fengzhicomp = 10;
	JScrollPane JSP;
	JScrollBar JSB;
	GendaJindutiao gendajindu;
	JLabel zishu;
	JButton suduButton, KeysuduButton, achievement, Keylength;
	AchievementListener achievementlistener;
	public double sudu;
	double length = 0;
	double mistake = 0;
	public double KeyNumber = 0;
	public double left = 0;
	public double right = 0;
	public double deleteNumber = 0;
	public double deleteTextNumber = 0;
	public double repeat = 0;
	double citime;
	public int space = 0;
	String a, b, leftstr = "qazwsxedcrfvtgb", rightstr = ";/.,。，；、plokmijnuhy";
	String dazidangyestr, wenbendangyestr;
	char[] c1, c2;
	public static int sign = 0;
	int i = 160;
	int number = 116;
	int n;
	int lianhvgdsign = 0;
	int fenglengthOne, fenglengthTwo;
	double fengzhinum = 0, fengkey = 0, fengkeytemp = 0, machang;
	public int daci = 0;
	public int daciall = 0;
	public List<String> mistakelist = new ArrayList<String>();
	public static List<Integer> missign = new ArrayList<Integer>();
	public static List<String> dacilist = new ArrayList<String>();
	public static List<String> shushisudu = new ArrayList<String>();
	private JLabel allnumber;
	private Tips tipschange;
	public static boolean gendaSign = false;

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		try {
			if (str1.length() > 0 && sign == 1) {
				if (e.getKeyChar() == '\b') {
					deleteNumber++;
					// System.out.println("退格+");
					record = record + "←";
				} else if (e.getKeyChar() == ' ') {
					record = record + "_";
					space++;
				} else if (leftstr.indexOf(String.valueOf(e.getKeyChar())) >= 0) {
					record = record + String.valueOf(e.getKeyChar());
					left++;
				} else if (rightstr.indexOf(String.valueOf(e.getKeyChar())) >= 0) {
					record = record + String.valueOf(e.getKeyChar());
					right++;
					if (e.getKeyChar() == ';')
						repeat++;
				}
				KeyNumber++;
				fengkeytemp++;
				KeysuduButton.setText(String.format("%.2f",
						KeyNumber / comp.getSecond()));
			}
			if (str1.length() == 0 && e.getKeyChar() == '\b') {
				deleteNumber++;
				// System.out.println("退格+");
				record = record + "←";
			}
		} catch (Exception ex) {
			System.out.println("跟打框无字1");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		try {
			if (str1.length() > 0 && str1.length() <= length
					&& e.getKeyChar() == '\b') {// 触发按键时如果打字框长度减小并且按键为BackSpace，即为回改
				deleteTextNumber++;
				// System.out.println("回改+");
				comphvgd.time2 = comphvgd.time1;
				comphvgd.setTimeOne();
				if (comphvgd.time1 - comphvgd.time2 < 100) {
					deleteNumber++;
					// System.out.println("退格+");
					lianhvgdsign++;
				} else if (lianhvgdsign != 0) {
					// System.out.println("退格+2");
					deleteNumber += 2;
					lianhvgdsign = 0;
				}
			} else if (lianhvgdsign != 0) {
				System.out.println("退格+2");
				deleteNumber += 2;
				lianhvgdsign = 0;
			}
		} catch (Exception ex) {
			System.out.println("跟打框无字2");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		try {
			mistakelist.clear();
			missign.clear();
			if (e.getKeyChar() != '\b')
				str1 = dazi.getText() + e.getKeyChar();
			else
				str1 = dazi.getText();
			str2 = QQZaiwenListener.wenbenstr;
			c1 = str1.toCharArray();
			c2 = str2.toCharArray();
			if (str1.length() > length) {
				if (c2[str1.length() - 1] == e.getKeyChar())
					Window.rightnum++;
				else
					Window.misnum++;
				Window.fontallnum++;
				Window.datenum++;
			}
			// 计算打词率
			try {
				compdaci(e.getKeyChar());// 计算打词
			} catch (Exception ex) {
				System.out.println("打词计算失败180genda");
			}
			mistake = 0; // 错误字数清零
			length = str1.length();// 计算当前打字框长度
			for (n = 0; n < str1.length(); n++) { // 统计错误字数，向文本框添加字体
				if (c1[n] != c2[n]) {
					mistake++;
					String mistakeStr = "\"" + String.valueOf(c2[n]) + "\"在第"
							+ String.valueOf(n + 1) + "个字\n";
					mistakelist.add(mistakeStr);
					missign.add(n);
				}
			}
			if (sign == 1)
				ChangeFontColor(); // 改变字体颜色
			tipschange.changeTips(String.valueOf(c2[str1.length()]));// 单字编码提示更改

			zishu.setText("字数:" + str2.length() + "/已打:" + str1.length() + "/错"
					+ (int) (mistake));
			allnumber.setText("总:" + String.valueOf(Window.fontallnum) + " 对:"
					+ String.valueOf(Window.rightnum) + " 错:"
					+ String.valueOf(Window.misnum) + " 今:"
					+ String.valueOf(Window.datenum));
			readWrite.keepfontnum(Window.fontallnum, Window.rightnum,
					Window.misnum);
			try {
				RecordChange.recordChange();
			} catch (Exception ex) {
				System.out.println("发送跟打字数失败196genda");
			}
			if (SetFrameJinduListener.jindusign == 1)// 判断是否开了进度条
				gendajindu.jindu(dazi.getText().length() + 1 - (int) (mistake));
			ChangePosition();// 文本自动翻页

		} catch (Exception exp) {
		}
		if (Window.Pattern == 2) {
			try {
				if (dazi.getText().length() == QQZaiwenListener.wenbenstr
						.length())
					return;
				for (int y = F3Listener.EnglishType.length - 1; y >= 0; y--) {
					if (dazi.getText().length() >= F3Listener.EnglishType[y]) {
						String englishtemp = TipsFrame.bianma
								.get(F3Listener.Englishword[y]);
						// System.out.println(dazi.getText().length()+":"+F3Listener.EnglishType[y]);
						// System.out.println(F3Listener.Englishword[y]+":"+englishtemp);
						Window.tips.setText(englishtemp);
						TipsFrame.show.setText(F3Listener.Englishword[y] + "\n"
								+ englishtemp);
						break;
					}
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "载文必须在切换英打前进行");
				// ex.printStackTrace();
				System.out.println("mistake!!! GendaListener 207");
			}
		}
	}

	public void changedUpdate(DocumentEvent e) {
		try {
			str1 = dazi.getText();
			str2 = QQZaiwenListener.wenbenstr;
			if (sign == 0 && str1.length() > 0) {
				comp.setTimeOne(); // 计算第一键时间
				record = ""; // 击键清空
				sign = 1; // 设置标记后不再计算，标记已开始跟打
				dacilist.clear(); // 清除上次跟打留下的daci链表
				shushisudu.clear();
				length = 0; // 清除上次跟打留下的Length
			} else
				comp.setTimeTwo(); // 计算最后一键时间
			a = String.valueOf(str1.charAt(str1.length() - 1));
			b = String.valueOf(str2.charAt(str2.length() - 1)); // 取两文本最后一个字
			try {
				if (Window.one.isVisible())
					battleClient.send.send(); // 发送服务器信息
			} catch (Exception ex) {
				System.out.println("发送对战消息失败gen227");
			}
			if (str1.length() == str2.length() && a.equals(b)
					&& !(Window.Pattern == 1)) // 两文本长度相等且最后一字相同时执行
			{
				dazi.setEditable(false); // 设置不可打字状态
				gendaSign = true;
			}
		} catch (Exception exp) {
		}
	}

	public double compshushi() {
		String temp;
		int i = dacilist.size();
		if (i > 5) {
			temp = "";
			String[] first = dacilist.get(i - 6).split(":");
			String[] last = dacilist.get(i - 1).split(":");
			for (int j = i - 5; j < i; j++) {
				String[] a = dacilist.get(j).split(":");
				temp += a[0];
			}
			int length1 = temp.length();
			double shushitime = Double.parseDouble(last[1])
					- Double.parseDouble(first[1]);
			double shushisudu = length1 / shushitime;
			return shushisudu * 60;
		}
		return 0;
	}

	public void compdaci(char c) {
		if (str1 != "" && str1.length() >= length) {
			compdazi.time2 = compdazi.time1;
			compdazi.setTimeOne();
			if (compdazi.time1 - compdazi.time2 < 50) {
				daci++;
				citime = comp.getSecond();
			} else if (daci != 0) {
				String temp = "";
				daciall += daci + 1;
				for (int k = str1.length() - daci - 2; k <= str1.length() - 2; k++) {
					temp += String.valueOf(c2[k]);
				}
				daci = 0; // 当前词长度清零
				String s[] = dacilist.get(dacilist.size() - 1).split(":");
				if (s[0].equals(temp.substring(0, 1))) // 单字对比
					dacilist.remove(dacilist.get(dacilist.size() - 1));
				dacilist.add(temp + ":" + citime + ":"
						+ String.format("%.2f", sudu) + ":"
						+ String.format("%.2f", KeyNumber / getSecond()) + ":"
						+ String.format("%.2f", compshushi()));
			} else if (daci == 0) {
				dacilist.add(String.valueOf(c) + ":" + comp.getSecond() + ":"
						+ String.format("%.2f", sudu) + ":"
						+ String.format("%.2f", KeyNumber / getSecond()) + ":"
						+ String.format("%.2f", compshushi()));
			}
		}
	}

	public void ChangeAllColor() {
		try {
			wenben.setText(""); // 清空文本框
			try {
				for (n = 0; n < str2.length(); n++) { // 统计错误字数，向文本框添加字体
					if (c1[n] != c2[n])
						JTextPaneChange.insertDoc(JTextPaneChange.styledDoc,
								String.valueOf(c2[n]), "红", wenben);
					else
						JTextPaneChange.insertDoc(JTextPaneChange.styledDoc,
								String.valueOf(c2[n]), "黑", wenben);
				}
			} catch (Exception e) {
				n = 0;
				System.out.println("wussssss");
			}
		} catch (Exception ex) {
			System.out.println("跟打框无字3");
			ex.printStackTrace();
		}
	}

	int dangyenum;

	public void ChangeFontColor() {
		try {
			str2 = QQZaiwenListener.wenbenstr;
			c2 = str2.toCharArray();
			dangyenum = str1.length() / fenye;
			int last;
			if (str2.length() - fenye * dangyenum > fenye) {
				wenbendangyestr = str2.substring(fenye * dangyenum,
						(dangyenum + 1) * fenye);
				last = (dangyenum + 1) * fenye + (heng + 1) / 3;
			} else {
				wenbendangyestr = str2.substring(fenye * dangyenum);
				last = str2.length();
			}
			dazidangyestr = str1.substring(fenye * dangyenum);
			// char [] b1 = dazidangyestr.toCharArray();
			// char [] b2 = wenbendangyestr.toCharArray();
			wenben.setText(""); // 清空文本框
			// n = 0;
			try {
				if (dangyenum > 0)
					n = dangyenum * fenye - (heng + 1) / 3;
				else
					n = dangyenum * fenye;
				for (; n < str1.length(); n++) { // 统计错误字数，向文本框添加字体
					if (c1[n] != c2[n] && sign == 1) {
						JTextPaneChange.insertDoc(JTextPaneChange.styledDoc,
								String.valueOf(c2[n]), "红", wenben);
					} else if (sign == 1)
						JTextPaneChange.insertDoc(JTextPaneChange.styledDoc,
								String.valueOf(c2[n]), "黑", wenben);
				}
			} catch (Exception e) {
				n = 0;
				System.out.println("wussssss");
			}
			if (sign == 0)
				n = 0;
			if (n > dangyenum * (fenye - 1)
					+ Integer.valueOf(SetFrame.readyFont.getText())) {
				int tempready = n
						+ Integer.valueOf(SetFrame.readyFont.getText());
				if (tempready > last)
					tempready = last;
				for (; n < tempready; n++) {
					System.out.println("预读");
					JTextPaneChange.createStyle("预读",
							JTextPaneChange.styledDoc, Window.fontSize, 0, 0,
							0, wenben.getBackground(), Window.family,
							wenben.getBackground());
					JTextPaneChange.insertDoc(JTextPaneChange.styledDoc,
							String.valueOf(c2[n]), "预读", wenben);
				}
			}
			for (; n < last; n++) { // 添加剩下字体
			// System.out.print(n);
				if (SetFramechangeListener.tipsign == 0 || Window.everydaySign
						|| (Window.Pattern == 1)) {
					JTextPaneChange.insertDoc(JTextPaneChange.styledDoc,
							String.valueOf(c2[n]), "灰", wenben);
				} else {
					int quanmanum = Tips.quanmacione.indexOf(n);
					int ciquanmanum = Tips.ciquanmacione.indexOf(n);
					int ejianmanum = Tips.ejianmacione.indexOf(n);
					int ciejianmanum = Tips.ciejianmacione.indexOf(n);
					int sjianmanum = Tips.sjianmacione.indexOf(n);
					int cisjianmanum = Tips.cisjianmacione.indexOf(n);
					if (quanmanum != -1) {
						if (quanmanum % 2 == 0)
							for (int k = Tips.quanmacione.get(quanmanum); k <= Tips.quanmacitwo
									.get(quanmanum); k++) {
								JTextPaneChange.insertDoc(
										JTextPaneChange.styledDoc,
										String.valueOf(c2[k]), "绿粗", wenben);
							}
						else {
							for (int k = Tips.quanmacione.get(quanmanum); k <= Tips.quanmacitwo
									.get(quanmanum); k++) {
								JTextPaneChange.insertDoc(
										JTextPaneChange.styledDoc,
										String.valueOf(c2[k]), "绿", wenben);
							}
						}
						n = Tips.quanmacitwo.get(quanmanum);
					} else if (ejianmanum != -1) {
						if (ejianmanum % 2 == 0)
							for (int k = Tips.ejianmacione.get(ejianmanum); k <= Tips.ejianmacitwo
									.get(ejianmanum); k++) {
								JTextPaneChange.insertDoc(
										JTextPaneChange.styledDoc,
										String.valueOf(c2[k]), "粉粗", wenben);
							}
						else {
							for (int k = Tips.ejianmacione.get(ejianmanum); k <= Tips.ejianmacitwo
									.get(ejianmanum); k++) {
								JTextPaneChange.insertDoc(
										JTextPaneChange.styledDoc,
										String.valueOf(c2[k]), "粉", wenben);
							}
						}
						n = Tips.ejianmacitwo.get(ejianmanum);
					} else if (sjianmanum != -1) {
						if (sjianmanum % 2 == 0)
							for (int k = Tips.sjianmacione.get(sjianmanum); k <= Tips.sjianmacitwo
									.get(sjianmanum); k++) {
								JTextPaneChange.insertDoc(
										JTextPaneChange.styledDoc,
										String.valueOf(c2[k]), "蓝粗", wenben);
							}
						else {
							for (int k = Tips.sjianmacione.get(sjianmanum); k <= Tips.sjianmacitwo
									.get(sjianmanum); k++) {
								JTextPaneChange.insertDoc(
										JTextPaneChange.styledDoc,
										String.valueOf(c2[k]), "蓝", wenben);
							}
						}
						n = Tips.sjianmacitwo.get(sjianmanum);
					} else if (ciquanmanum != -1) {
						if (ciquanmanum % 2 == 0)
							for (int k = Tips.ciquanmacione.get(ciquanmanum); k <= Tips.ciquanmacitwo
									.get(ciquanmanum); k++) {
								JTextPaneChange.insertDoc(
										JTextPaneChange.styledDoc,
										String.valueOf(c2[k]), "绿粗斜", wenben);
							}
						else {
							for (int k = Tips.ciquanmacione.get(ciquanmanum); k <= Tips.ciquanmacitwo
									.get(ciquanmanum); k++) {
								JTextPaneChange.insertDoc(
										JTextPaneChange.styledDoc,
										String.valueOf(c2[k]), "绿斜", wenben);
							}
						}
						n = Tips.ciquanmacitwo.get(ciquanmanum);
					} else if (ciejianmanum != -1) {
						if (ciejianmanum % 2 == 0)
							for (int k = Tips.ciejianmacione.get(ciejianmanum); k <= Tips.ciejianmacitwo
									.get(ciejianmanum); k++) {
								JTextPaneChange.insertDoc(
										JTextPaneChange.styledDoc,
										String.valueOf(c2[k]), "粉粗斜", wenben);
							}
						else {
							for (int k = Tips.ciejianmacione.get(ciejianmanum); k <= Tips.ciejianmacitwo
									.get(ciejianmanum); k++) {
								JTextPaneChange.insertDoc(
										JTextPaneChange.styledDoc,
										String.valueOf(c2[k]), "粉斜", wenben);
							}
						}
						n = Tips.ciejianmacitwo.get(ciejianmanum);
					} else if (cisjianmanum != -1) {
						if (cisjianmanum % 2 == 0)
							for (int k = Tips.cisjianmacione.get(cisjianmanum); k <= Tips.cisjianmacitwo
									.get(cisjianmanum); k++) {
								JTextPaneChange.insertDoc(
										JTextPaneChange.styledDoc,
										String.valueOf(c2[k]), "蓝粗斜", wenben);
							}
						else {
							for (int k = Tips.cisjianmacione.get(cisjianmanum); k <= Tips.cisjianmacitwo
									.get(cisjianmanum); k++) {
								JTextPaneChange.insertDoc(
										JTextPaneChange.styledDoc,
										String.valueOf(c2[k]), "蓝斜", wenben);
							}
						}
						n = Tips.cisjianmacitwo.get(cisjianmanum);
					} else {
						JTextPaneChange.insertDoc(JTextPaneChange.styledDoc,
								String.valueOf(c2[n]), "灰", wenben);
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("跟打框无字3");
			ex.printStackTrace();
		}
	}

	int heng;

	void ChangePosition() {// 自动滚动条翻页方法
		int hengsize = Window.fontSize + 59; // 一个字横分辨率
		int shusize = Window.fontSize + 14;// 一个字竖分辨率
		int shu = winchange.shuweizhi / shusize; // 行数
		int tem = 0;
		heng = (winchange.hengweizhi - hengsize) / Window.fontSize; // 一行字数
		number = number % (fenye + (heng + 1) / 3);
		if (dangyenum == 0)
			while (dazidangyestr.length() > number) {
				if (shu > 2)
					tem = (shu - 1) * (heng + 1);
				else
					tem = 1 * (heng + 1);
				if (number + tem > fenye)
					number = fenye - 1;
				else
					number = number + tem;
				System.out.println("文章光标:" + number);
			}
		else
			while (dazidangyestr.length() + (heng + 1) / 3 > number) {
				if (shu > 2)
					tem = (shu - 1) * (heng + 1);
				else
					tem = 1 * (heng + 1);
				if (number + tem > fenye)
					number = fenye + (heng + 1) / 3 - 1;
				else
					number = number + tem;
				System.out.println("文章光标:" + number);
			}
		if (dazidangyestr.length() == 1) {
			if (shu > 1) {
				number = (shu - 1) * (heng + 1) + (heng + 1) / 3;
			} else if (shu <= 1)
				number = 1 * (heng + 1) - (heng + 1) / 3;
		} else if (dazidangyestr.length() == 0) {
			if (shu > 1) {
				number = (shu - 1) * (heng + 1) + (heng + 1) / 3;
			} else if (shu <= 1)
				number = 1 * (heng + 1) - (heng + 1) / 3;
			JSB.setValue(0);
		}
		wenben.setCaretPosition(number);
	}

	public void setDaziText(JTextArea t) {
		dazi = t;
	}

	public void setWenbenText(JTextPane t) {
		wenben = t;
	}

	public void setWenben1Text(JScrollPane t) {
		wenben1 = t;
	}

	public void setChengjiText(JTextArea t) {
		chengji = t;
	}

	public void setSign(int t) {
		sign = t;
	}

	public void setJSB(JScrollBar t) {
		JSB = t;
	}

	public void setZishu(JLabel zishu2) {
		zishu = zishu2;
	}

	public void setSuduButton(JButton t) {
		suduButton = t;
	}

	public void setKeySuduButton(JButton t) {
		KeysuduButton = t;
	}

	public void setAchievementButton(JButton t) {
		achievement = t;
	}

	public void setJProgressBar(GendaJindutiao t) {
		gendajindu = t;
	}

	public void setKeylength(JButton t) {
		Keylength = t;
	}

	public void setAchievementListener(AchievementListener t) {
		achievementlistener = t;
	}

	public void setTipschange(Tips tipschange) {
		this.tipschange = tipschange;
	}

	public double getRight() {
		return right;
	}

	public double getLeft() {
		return left;
	}

	public double getRepeat() {
		return repeat;
	}

	public double getSudu() {
		return sudu;
	}

	public double getKeyNumber() {
		return KeyNumber;
	}

	public double getSecond() {
		return comp.getSecond();
	}

	public double getLength() {
		return length;
	}

	public double getMistake() {
		return mistake;
	}

	public double getDeleteNumber() {
		return deleteNumber;
	}

	public double getDeleteTextNumber() {
		return deleteTextNumber;
	}

	public void insertUpdate(DocumentEvent e) {
		changedUpdate(e);
	}

	public void removeUpdate(DocumentEvent e) {
		changedUpdate(e);
	}

	public void setAllnumber(JLabel allnumber) {
		// TODO Auto-generated method stub
		this.allnumber = allnumber;
	}

	public int getSpace() {
		// TODO Auto-generated method stub
		return space;
	}

	public double getFenzhi() {
		// TODO Auto-generated method stub
		return fengzhinum;
	}
}
