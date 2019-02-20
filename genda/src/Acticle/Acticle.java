package Acticle;

import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class Acticle extends JFrame{
	public static ActicleListener treeListener;
	JTree tree;
	JScrollPane tree1;
	DefaultMutableTreeNode root;
	DefaultMutableTreeNode danzilei,wenzhanglei;
	Window win;
	JTextPane wenben;
	JScrollPane wenben1;
	JTextField number;
	JButton send,next,mix;
	int i;
	public Acticle(Window win){
		this.win = win;
		setTitle("发文");
		setBounds(100,100,650,380);
		setLayout(null);
		init();
		setVisible(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
	}
	void init(){
		addinArea();
		addnumber();
		Sendwenben();
		Articlelist();
		addNext();
		addluanxu();
	}
	void addluanxu(){
		mix = new JButton("全局乱序");
		mix.setBounds(170, 270, 70, 30);
		add(mix);
		mix.addActionListener(win.mixlistener);
	}
	void addNext(){
		next = new JButton("下一段");
		next.setBounds(170, 270, 70, 30);
//		add(next);
		next.addActionListener(treeListener);
		
	}
	void Sendwenben(){
		send = new JButton("确定");
		send.setBounds(105, 270, 50, 30);
		add(send);
		SendWenben sendwenben = new SendWenben(wenben);
		sendwenben.setwin(win,this);
		send.addActionListener(sendwenben);
	}
	void addnumber(){
		number = new JTextField("100");
		number.setBounds(5,270,90,30);
		add(number);
	}
	void addinArea(){
		wenben = new JTextPane();
		wenben1 = new JScrollPane(wenben);
		wenben1.setBounds(191,0,400,270);
		add(wenben1);
	}
	void Articlelist(){
		root = new DefaultMutableTreeNode("练习");
		danzilei = new DefaultMutableTreeNode("单字类");
		wenzhanglei = new DefaultMutableTreeNode("文章类");
		File danziDir = new File("文章//单字类"),
				wenzhangDir = new File("文章//文章类");
		File []danziFile = danziDir.listFiles(),
				wenzhangFile = wenzhangDir.listFiles();
		String[]danziFileName = danziDir.list(),
				wenzhangFileName = wenzhangDir.list();
		for(i=0;i<danziFileName.length;i++)
			 if(danziFile[i].isFile()) {
				 danzilei.add(new DefaultMutableTreeNode(danziFileName[i]));
			 }
		for(i=0;i<wenzhangFileName.length;i++)
			if(wenzhangFile[i].isFile())
				wenzhanglei.add(new DefaultMutableTreeNode(wenzhangFileName[i]));
		root.add(danzilei);
		root.add(wenzhanglei);

		tree = new JTree(root);
		tree1 = new JScrollPane(tree);
		 	
		treeListener = new ActicleListener();
		treeListener.setTree(tree);
		treeListener.setDanziFileName(danziFileName);
		treeListener.setWenzhangFileName(wenzhangFileName);
		treeListener.setWin(win);
		tree.addTreeSelectionListener(treeListener);
		tree1.setBounds(0,0,190,270);
		treeListener.setWenbenText(wenben);

		treeListener.setNumber(number);
		
		add(tree1);
	}
}
