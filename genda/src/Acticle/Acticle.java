package Acticle;

import genda1.Window;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
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
  public JSpinner spinnerSpeed, spinnerKey, spinnerKeyLength;
  public JRadioButton luanxu;
  int i;

  public Acticle(Window win) {
    this.win = win;
    setTitle("发文");
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
    English = new JButton("英词练习");
    English.setBounds(490, 270, 90, 30);
    English.addActionListener(treeListener);
    p.add(English);
  }

  void addcikuchouqu() {
    cikuchouqu = new JButton("词库练习");
    cikuchouqucanshu = new JTextField("a:b:c:d");
    cikuchouqu.setBounds(350, 270, 70, 30);
    cikuchouqucanshu.setBounds(425, 270, 60, 30);
    cikuchouqu.addActionListener(treeListener);
    p.add(cikuchouqu);
    p.add(cikuchouqucanshu);
  }

  void addchouqu() {
    chouqu = new JButton("抽取模式发文");
    chouqu.setBounds(245, 270, 100, 30);
    p.add(chouqu);
    chouqu.addActionListener(treeListener);
  }

  void addluanxu() {
    mix = new JButton("全局乱序");
    mix.setBounds(170, 270, 70, 30);
    p.add(mix);
    mix.addActionListener(win.mixlistener);
  }

  void addNext() {
    next = new JButton("下一段");
    next.setBounds(240, 270, 70, 30);
    // add(next);
    next.addActionListener(treeListener);
  }

  void Sendwenben() {
    send = new JButton("顺序模式发文");
    send.setBounds(65, 270, 100, 30);
    p.add(send);
    SendWenben sendwenben = new SendWenben(wenben);
    sendwenben.setwin(win, this);
    send.addActionListener(sendwenben);
  }

  void addnumber() {
    number = new JTextField("100");
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
    JLabel lable1 = new JLabel("速度");
    JLabel lable2 = new JLabel("击键");
    JLabel lable3 = new JLabel("码长");

    lable1.setBounds(5, 310, 30, 30);
    spinnerSpeed = new JSpinner();
    spinnerSpeed.setModel(new SpinnerNumberModel(0, 0, 999, 0.1));
    spinnerSpeed.setBounds(45, 310, 50, 30);

    lable2.setBounds(105, 310, 30, 30);
    spinnerKey = new JSpinner();
    spinnerKey.setModel(new SpinnerNumberModel(0, 0, 30, 0.1));
    spinnerKey.setBounds(145, 310, 50, 30);

    lable3.setBounds(205, 310, 30, 30);
    spinnerKeyLength = new JSpinner();
    spinnerKeyLength.setModel(new SpinnerNumberModel(0, 0, 7, 0.1));
    spinnerKeyLength.setBounds(245, 310, 50, 30);

    luanxu = new JRadioButton("未达到条件是否乱序");
    luanxu.setBounds(305, 310, 150, 30);

    p.add(spinnerSpeed);
    p.add(spinnerKey);
    p.add(spinnerKeyLength);
    p.add(luanxu);
    p.add(lable1);
    p.add(lable2);
    p.add(lable3);
  }
  void Articlelist() {
    root = new DefaultMutableTreeNode("练习");
    danzilei = new DefaultMutableTreeNode("单字类");
    wenzhanglei = new DefaultMutableTreeNode("文章类");
    yingwenlei = new DefaultMutableTreeNode("英打类");
    File danziDir = new File("文章//单字类"), wenzhangDir = new File("文章//文章类"),
         yingwenDir = new File("文章//英打类");

    File[] danziFile = danziDir.listFiles(), wenzhangFile = wenzhangDir.listFiles(),
           yingwenFile = yingwenDir.listFiles();
    String[] danziFileName = danziDir.list(), wenzhangFileName = wenzhangDir.list(),
             yingwenFileName = yingwenDir.list();
    for (i = 0; i < danziFileName.length; i++)
      if (danziFile[i].isFile()) {
        danzilei.add(new DefaultMutableTreeNode(danziFileName[i]));
      }
    for (i = 0; i < wenzhangFileName.length; i++)
      if (wenzhangFile[i].isFile())
        wenzhanglei.add(new DefaultMutableTreeNode(wenzhangFileName[i]));
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
