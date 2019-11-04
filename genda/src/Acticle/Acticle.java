package Acticle;

import genda1.Window;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class Acticle extends JFrame {
	public static ActicleListener treeListener;
	JTree tree;
	JScrollPane tree1;
	DefaultMutableTreeNode root;
	DefaultMutableTreeNode danzilei, wenzhanglei, yingwenlei;
	Window win;
	JTextArea wenben;
	JScrollPane wenben1;
	public static JTextField number, cikuchouqucanshu;
	JButton send, next, mix, chouqu, cikuchouqu, English;
	JPanel p = new JPanel();
	public JSpinner spinnerSpeed,spinnerKey,spinnerKeyLength;
	public JComboBox<String> caozuo;
	int i;

	public Acticle(Window win) {
		this.win = win;
		setTitle("����");
		setBounds(100, 100, 605, 380);
		// setLayout(null);
		p.setLayout(null);
		add(p);
		init();
		setVisible(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	void init() {

		addinArea();
		addnumber();
		Sendwenben();
		Articlelist();
		addNext();
		addluanxu();
		addchouqu();
		addcikuchouqu();
		addEnglish();
		automaticDisruption();
	}

	void addEnglish() {
		English = new JButton("Ӣ����ϰ");
		English.setBounds(490, 270, 90, 30);
		English.addActionListener(treeListener);
		p.add(English);
	}

	void addcikuchouqu() {
		cikuchouqu = new JButton("�ʿ���ϰ");
		cikuchouqucanshu = new JTextField("a:b:c:d");
		cikuchouqu.setBounds(350, 270, 70, 30);
		cikuchouqucanshu.setBounds(425, 270, 60, 30);
		cikuchouqu.addActionListener(treeListener);
		p.add(cikuchouqu);
		p.add(cikuchouqucanshu);
	}

	void addchouqu() {
		chouqu = new JButton("��ȡģʽ����");
		chouqu.setBounds(245, 270, 100, 30);
		p.add(chouqu);
		chouqu.addActionListener(treeListener);
	}

	void addluanxu() {
		mix = new JButton("ȫ������");
		mix.setBounds(170, 270, 70, 30);
		p.add(mix);
		mix.addActionListener(win.mixlistener);
	}

	void addNext() {
		next = new JButton("��һ��");
		next.setBounds(240, 270, 70, 30);
		// add(next);
		next.addActionListener(treeListener);

	}

	void Sendwenben() {
		send = new JButton("˳��ģʽ����");
		send.setBounds(65, 270, 100, 30);
		p.add(send);
		SendWenben sendwenben = new SendWenben(wenben);
		sendwenben.setwin(win, this);
		send.addActionListener(sendwenben);
	}

	void addnumber() {
		number = new JTextField("10");
		number.addKeyListener(new KeyListener() {
					@Override
					public void keyPressed(KeyEvent e) {
						
					}
					@Override
					public void keyReleased(KeyEvent e) {
//						keyTyped(e);
						int keyChar = e.getKeyChar();
						if (keyChar >= KeyEvent.VK_0
								&& keyChar <= KeyEvent.VK_9|| keyChar == '\b') {
							treeListener.getNumber();
							treeListener.showwen();
						} 
						else
							e.consume();
					}
					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
		});
		number.setBounds(5, 270, 50, 30);
		p.add(number);
	}

	void addinArea() {
		wenben = new JTextArea();
		wenben1 = new JScrollPane(wenben);
		wenben.setLineWrap(true);
		wenben1.setBounds(191, 0, 400, 270);
		p.add(wenben1);
	}
	void automaticDisruption() {
		JLabel lable1 = new JLabel("�ٶȡ�");
		JLabel lable2 = new JLabel("������");
		JLabel lable3 = new JLabel("��׼��");
		JLabel lable4 = new JLabel("δ���ʱ");
		lable1.setBounds(5, 310, 40, 30);
	    spinnerSpeed = new JSpinner();
		spinnerSpeed.setModel(new SpinnerNumberModel(0, 0, 999, 0.1));
		spinnerSpeed.setBounds(lable1.getX()+lable1.getWidth()+10, 310, 50, 30);
		
		lable2.setBounds(spinnerSpeed.getX()+spinnerSpeed.getWidth()+10, 310, 40, 30);
		spinnerKey = new JSpinner();
		spinnerKey.setModel(new SpinnerNumberModel(0, 0, 30, 0.1));
		spinnerKey.setBounds(lable2.getX()+lable2.getWidth()+10, 310, 50, 30);
		
		lable3.setBounds(spinnerKey.getX()+spinnerKey.getWidth()+10, 310, 40, 30);
		spinnerKeyLength = new JSpinner();
		spinnerKeyLength.setModel(new SpinnerNumberModel(0, 0, 7, 0.1));
		spinnerKeyLength.setBounds(lable3.getX()+lable3.getWidth()+10, 310, 50, 30);
		
		
		lable4.setBounds(spinnerKeyLength.getX()+spinnerKeyLength.getWidth()+10,310,50,30);
		caozuo = new JComboBox<String>();
		caozuo.addItem("������");
		caozuo.addItem("����");
		caozuo.addItem("�ش�");
		caozuo.setBounds(lable4.getX()+lable4.getWidth()+10, 310, 100, 30);
		
		
		
		p.add(spinnerSpeed);
		p.add(spinnerKey);
		p.add(spinnerKeyLength);
		p.add(caozuo);
		p.add(lable1);
		p.add(lable2);
		p.add(lable3);
		p.add(lable4);
	}
	void Articlelist() {
		root = new DefaultMutableTreeNode("��ϰ");
		danzilei = new DefaultMutableTreeNode("������");
		wenzhanglei = new DefaultMutableTreeNode("������");
		yingwenlei = new DefaultMutableTreeNode("Ӣ����");
		File danziDir = new File("����//������"), wenzhangDir = new File("����//������"), yingwenDir = new File(
				"����//Ӣ����");

		File[] danziFile = danziDir.listFiles(), wenzhangFile = wenzhangDir
				.listFiles(), yingwenFile = yingwenDir.listFiles();
		String[] danziFileName = danziDir.list(), wenzhangFileName = wenzhangDir
				.list(), yingwenFileName = yingwenDir.list();
		for (i = 0; i < danziFileName.length; i++)
			if (danziFile[i].isFile()) {
				danzilei.add(new DefaultMutableTreeNode(danziFileName[i]));
			}
		for (i = 0; i < wenzhangFileName.length; i++)
			if (wenzhangFile[i].isFile())
				wenzhanglei
						.add(new DefaultMutableTreeNode(wenzhangFileName[i]));
		for (i = 0; i < yingwenFileName.length; i++) {
			if (yingwenFile[i].isFile())
				yingwenlei.add(new DefaultMutableTreeNode(yingwenFileName[i]));
		}
		root.add(danzilei);
		root.add(wenzhanglei);
		root.add(yingwenlei);

		tree = new JTree(root);
		tree1 = new JScrollPane(tree);

		treeListener = new ActicleListener();
		treeListener.setActicle(this);
		treeListener.setTree(tree);
		treeListener.setDanziFileName(danziFileName);
		treeListener.setWenzhangFileName(wenzhangFileName);
		treeListener.setWin(win);
		tree.addTreeSelectionListener(treeListener);
		tree1.setBounds(0, 0, 190, 270);
		treeListener.setWenbenText(wenben);

		treeListener.setNumber(number);

		p.add(tree1);
	}
}
