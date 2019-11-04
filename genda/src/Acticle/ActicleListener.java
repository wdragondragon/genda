package Acticle;

import genda1.*;
import gendaClient.battleClient;

import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Collections;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import Login.Login;
import SetWin.SetFrameQianshuiListener;
import Tips.*;

public class ActicleListener implements TreeSelectionListener, ActionListener {
	JTextArea dazi;
	static JTextArea wenben;
	JTree tree;
	GendaListener gendalistener;
	File open;
	RandomAccessFile in = null, out = null;
	String[] danziFileName, wenzhangFileName;
	byte s[];
	public static String all, wen;
	int i;
	public static long length = 0;
	JTextField number;
	private Acticle acticle;
	public static int fontnum = 0, fontweizhi = 0;
	public static ArrayList<String> chouqulist = new ArrayList<String>();
	public static ArrayList<String> chouqubufenlist = new ArrayList<String>();
	public static int y;
	static Window win;

	// String s;
	public void setWenbenText(JTextArea wenben2) {
		wenben = wenben2;
	}

	public void setTree(JTree t) {
		tree = t;
	}

	public void setDanziFileName(String[] t) {
		danziFileName = t;
	}

	public void setWenzhangFileName(String[] t) {
		wenzhangFileName = t;
	}

	public void setNumber(JTextField number) {
		this.number = number;
	}

	public void setWin(Window win) {
		this.win = win;
	}

	void getNumber() {
		try {
			fontnum = Integer.parseInt(number.getText());
		} catch (Exception e) {
//			JOptionPane.showMessageDialog(new JTextArea(), "��������������");
		}
	}

	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
					.getLastSelectedPathComponent();
			if (node.isLeaf()) {

				fontweizhi = 0;
				SendWenben.title = node.toString();
				if (SendWenben.title.substring(0, 4).equals("�������")) {
					readjindu();
				}
				open = new File("����//" + node.getParent(), SendWenben.title);
				in = new RandomAccessFile(open, "r");
				getNumber();
				length = in.length();
				s = new byte[(int) length];
				in.readFully(s);
				all = new String(s);
				all = RegexText.qukong(all);
				length = all.length();
				acticle.setTitle("�����ܳ���:"+length);
				showwen();
			}
		} catch (Exception e) {
		}
	}

	public static void showwen() {
		if (fontnum > all.length())
			wen = all.substring(fontweizhi, all.length());
		else
			wen = all.substring(fontweizhi, fontweizhi + fontnum);
		if(wen.length()>500)
			wenben.setText(wen.substring(0,500));
		else
			wenben.setText(wen);
		// fontweizhi += fontnum;
		win.sendwen.setText(String.valueOf(fontweizhi)
				+ "/"
				+ String.valueOf(all.length())
				+ ":"
				+ String.format("%.2f",
						(double) fontweizhi * 100 / all.length()) + "%");
	}

	void readjindu() throws IOException {
		try {
			open = new File("����//������", SendWenben.title);
			Reader read = new FileReader(open);
			BufferedReader br = new BufferedReader(read);
			SendWenben.title = br.readLine();
			fontweizhi = Integer.valueOf(br.readLine());
			br.close();
			read.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void nextOrder(){
		try {
			if (fontweizhi >= all.length()) {
				JOptionPane.showMessageDialog(new JTextArea(), "���Ľ���");
				win.sendwen.setVisible(false);
				SendWenben.sendwenSign = 0;
				return;
			}
			if (fontweizhi + fontnum >= all.length()) {
				wen = all.substring(fontweizhi, all.length());
				fontweizhi = all.length();
			} else {
				wen = all.substring(fontweizhi, fontweizhi + fontnum);
				fontweizhi += fontnum;
			}
			QQZaiwenListener.wenbenstr = wen;
			QQZaiwenListener.wenbenstr = RegexText.qukong(RegexText
					.huanfu(QQZaiwenListener.wenbenstr));
			Window.f3listener.F3();

			RegexText.duan1++; // ��������
			win.sendwen.setText(String.valueOf(fontweizhi)
					+ "/"
					+ String.valueOf(all.length())
					+ ":"
					+ String.format("%.2f", (double) fontweizhi * 100
							/ all.length()) + "%");
			try {
				DataOutputStream out = new DataOutputStream(
						battleClient.socket.getOutputStream());
				out.writeUTF("%" + ReadyListener.BeganSign + "%" + "%"
						+ RegexText.duan1 + "#"
						+ Window.wenben.getText() + "%0" + "%"
						+ Login.zhanghao.getText());
			} catch (Exception ex) {
				System.out.println("�޷������ı�����acticlelistner,121");
			}
			if (SetFrameQianshuiListener.qianshui == 0)
				ShareListener.send();
		} catch (Exception ex) {
			System.out.println("���Ĵ�ʧ��");
		}
	}
	public void save(){
		try {
			String jindufile = "�������" + SendWenben.title + ".txt";
			open = new File("����//������", jindufile);
			FileOutputStream testfile = new FileOutputStream(open);
			testfile.write(new String("").getBytes());
			byte baocun[] = (SendWenben.title + "\r\n" + String
					.valueOf(fontweizhi - fontnum)).getBytes();
			testfile.write(baocun);
			testfile.close();
			JOptionPane.showMessageDialog(new JTextArea(), "�ѱ��浱ǰ�������");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(new JTextArea(), "�������ʧ��");
		}
	}
	public void chouqu(String model){
		if (SendWenben.sendwenSign3 == 1) {
			chouqubufenlist.clear();
			Collections.shuffle(chouqulist);
			String str = "";
			for (int i = 0; i < (chouqulist.size() < y ? chouqulist.size()
					: y); i++) {
				str += chouqulist.get(i);
				chouqubufenlist.add(chouqulist.get(i));
			}
			QQZaiwenListener.wenbenstr = str;
			Window.f3listener.F3();
			RegexText.duan1++;
			if (SetFrameQianshuiListener.qianshui == 0)
				ShareListener.send();
		} else if (model.equals("��ȡģʽ����")
				|| SendWenben.sendwenSign2 == 1) {
			getNumber();
			QQZaiwenListener.wenbenstr = randomCommon(all, fontnum);
			if (QQZaiwenListener.wenbenstr == null)
				return;
			Window.f3listener.F3();
			if (SendWenben.sendwenSign2 == 0) {
				acticle.setVisible(false);
				RegexText.duan1 = 1;
				SendWenben.sendwenSign2 = 1;
			} else
				RegexText.duan1++; // ��������
			if (SetFrameQianshuiListener.qianshui == 0)
				ShareListener.send();
		}
	}
	public void enlighWord(String model){
		SendWenben.title = "Ӣ�ĵ��ʳ�ȡ��ϰ";
		if (!SendWenben.sendwenSign4) {
			acticle.setVisible(false);
			RegexText.duan1 = 1;
		} else
			RegexText.duan1++;
		if (model.equals("Ӣ����ϰ"))
			QQZaiwenListener.wenbenstr = EnlishRamdom.readtext(open);
		else
			QQZaiwenListener.wenbenstr = EnlishRamdom.RamdomWord();
		if (QQZaiwenListener.wenbenstr.equals(""))
			return;
		Window.f3listener.F3();
		SendWenben.sendwenSign4 = true;
		if (SetFrameQianshuiListener.qianshui == 0)
			ShareListener.send();
	}
	public void ciku(){
		String temp = "";
		chouqulist.clear();
		chouqubufenlist.clear();
		SendWenben.title = "�ʿ���ϰ";
		try {
			String num[] = win.acticle.cikuchouqucanshu.getText()
					.split(":");
			int x = Integer.parseInt(num[0]);
			y = Integer.parseInt(num[1]);
			if (num[3].equals("0")) {
				if (num[2].length() == 1) {
					int cinum = Integer.parseInt(num[2]);
					if (cinum == 0) {
						for (String str : Tips.alltable.keySet())
							if (Tips.alltable.get(str) == x)
								chouqulist.add(str);
					} else
						for (String str : Tips.alltable.keySet())
							if (str.length() == cinum)
								if (Tips.alltable.get(str) == x)
									chouqulist.add(str);
				} else if (num[2].length() == 2) {
					int cinum = Integer.parseInt(num[2].substring(1));
					String chfuhao = num[2].substring(0, 1);
					if (chfuhao.equals(">")) {
						for (String str : Tips.alltable.keySet())
							if (str.length() > cinum)
								if (Tips.alltable.get(str) == x)
									chouqulist.add(str);
					} else if (chfuhao.equals("<")) {
						for (String str : Tips.alltable.keySet())
							if (str.length() < cinum)
								if (Tips.alltable.get(str) == x)
									chouqulist.add(str);
					} else if (chfuhao.equals("-")) {
						for (String str : Tips.alltable.keySet())
							if (str.length() != cinum)
								if (Tips.alltable.get(str) == x)
									chouqulist.add(str);
					}
				} else if (num[2].length() == 3
						&& num[2].substring(1, 2).equals("<")) {
					int cimin = Integer.parseInt(num[2].substring(0, 1));
					int cimax = Integer.parseInt(num[2].substring(2));
					for (String str : Tips.alltable.keySet())
						if (str.length() > cimin && str.length() < cimax)
							if (Tips.alltable.get(str) == x)
								chouqulist.add(str);
				}
			} else if (num[3].equals("s")) {
				if (num[2].length() == 1) {
					int cinum = Integer.parseInt(num[2]);
					if (cinum == 0) {
						for (String str : Tips.shoutable.keySet())
							if (Tips.shoutable.get(str) == x)
								chouqulist.add(str);
					} else
						for (String str : Tips.shoutable.keySet())
							if (str.length() == cinum)
								if (Tips.shoutable.get(str) == x)
									chouqulist.add(str);
				} else if (num[2].length() == 2) {
					int cinum = Integer.parseInt(num[2].substring(1));
					String chfuhao = num[2].substring(0, 1);
					if (chfuhao.equals(">")) {
						for (String str : Tips.shoutable.keySet())
							if (str.length() > cinum)
								if (Tips.shoutable.get(str) == x)
									chouqulist.add(str);
					} else if (chfuhao.equals("<")) {
						for (String str : Tips.shoutable.keySet())
							if (str.length() < cinum)
								if (Tips.shoutable.get(str) == x)
									chouqulist.add(str);
					} else if (chfuhao.equals("-")) {
						for (String str : Tips.shoutable.keySet())
							if (str.length() != cinum)
								if (Tips.shoutable.get(str) == x)
									chouqulist.add(str);
					}
				} else if (num[2].length() == 3
						&& num[2].substring(1, 2).equals("<")) {
					int cimin = Integer.parseInt(num[2].substring(0, 1));
					int cimax = Integer.parseInt(num[2].substring(2));
					for (String str : Tips.shoutable.keySet())
						if (str.length() > cimin && str.length() < cimax)
							if (Tips.shoutable.get(str) == x)
								chouqulist.add(str);
				}
			} else if (num[3].equals("c")) {
				if (num[2].length() == 1) {
					int cinum = Integer.parseInt(num[2]);
					if (cinum == 0) {
						for (String str : Tips.citable.keySet())
							if (Tips.citable.get(str) == x)
								chouqulist.add(str);
					} else
						for (String str : Tips.citable.keySet())
							if (str.length() == cinum)
								if (Tips.citable.get(str) == x)
									chouqulist.add(str);
				} else if (num[2].length() == 2) {
					int cinum = Integer.parseInt(num[2].substring(1));
					String chfuhao = num[2].substring(0, 1);
					if (chfuhao.equals(">")) {
						for (String str : Tips.citable.keySet())
							if (str.length() > cinum)
								if (Tips.citable.get(str) == x)
									chouqulist.add(str);
					} else if (chfuhao.equals("<")) {
						for (String str : Tips.citable.keySet())
							if (str.length() < cinum)
								if (Tips.citable.get(str) == x)
									chouqulist.add(str);
					} else if (chfuhao.equals("-")) {
						for (String str : Tips.citable.keySet())
							if (str.length() != cinum)
								if (Tips.citable.get(str) == x)
									chouqulist.add(str);
					}
				} else if (num[2].length() == 3
						&& num[2].substring(1, 2).equals("<")) {
					int cimin = Integer.parseInt(num[2].substring(0, 1));
					int cimax = Integer.parseInt(num[2].substring(2));
					for (String str : Tips.citable.keySet())
						if (str.length() > cimin && str.length() < cimax)
							if (Tips.citable.get(str) == x)
								chouqulist.add(str);
				}
			}
			Collections.shuffle(chouqulist);
			// System.out.println(chouqulist.size()+" "+y);
			for (int i = 0; i < (chouqulist.size() < y ? chouqulist.size()
					: y); i++) {
				temp += chouqulist.get(i);
				chouqubufenlist.add(chouqulist.get(i));
			}
			QQZaiwenListener.wenbenstr = temp;
			win.f3listener.F3();
			RegexText.duan1 = 1;
			SendWenben.sendwenSign3 = 1;
			acticle.setVisible(false);
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (SendWenben.sendwenSign == 1) {
			if (e.getActionCommand() == "��һ��"
					|| e.getActionCommand() == "˳����һ��") {
				nextOrder();
			} else if (e.getActionCommand() == "����������") {
				save();
			}
		} else if (e.getActionCommand().equals("��ȡģʽ����")
				|| e.getActionCommand().equals("��ȡ��һ��")) {
			chouqu(e.getActionCommand());
		} else if (e.getActionCommand().equals("Ӣ����ϰ")
				|| e.getActionCommand().endsWith("Ӣ����һ��")) {
			enlighWord(e.getActionCommand());
		} else if (e.getActionCommand().equals("�ʿ���ϰ")) {
			ciku();
		} else
			win.dazi.requestFocusInWindow();

	}

	public static String randomCommon(String wen, int n) {
		if (wen == null)
			return null;
		int min = 0;
		int max = wen.length();
		char c[] = wen.toCharArray();
		StringBuilder resultstr = new StringBuilder();
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		int count = 0;
		while (count < n) {
			int num = (int) (Math.random() * (max - min)) + min;
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if (num == result[j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				result[count] = num;
				count++;
			}
		}
		for (int i = 0; i < result.length; i++)
			resultstr.append(String.valueOf(c[result[i]]));
		return resultstr.toString();
	}

	public void setActicle(Acticle acticle) {
		this.acticle = acticle;
	}
}
