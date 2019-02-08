package SetWin;

import genda1.*;
import genda1.Window;
import java.awt.Font;
import java.awt.Label;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.awt.*;

import javax.swing.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import Tips.ChooseFile;
public class SetFrame extends JFrame {
	JRadioButton setjindutiaoON,setjindutiaoOFF;
	ButtonGroup setjindutiaogroup;
	SetFrameJinduListener setframeJindulistener = new SetFrameJinduListener();
	SetFrameKeyboardRecordListener keyRecordListener = new SetFrameKeyboardRecordListener();
	SetFramechangeListener tiplistener = new SetFramechangeListener();
	SetFrameSplitnum setframesplitenum;
	GendaListener gendalistener;
	JLabel qianshui;
	public static JTextField Splitenum,FontSize;
	JRadioButton qianshuiON;
	JRadioButton qianshuiOFF;
	JRadioButton changetxtON;
	JRadioButton changetxtOFF;
	
	ChooseFile chosefilelistener = new ChooseFile();
	
	ButtonGroup setqianshui ;
	ButtonGroup setchange;
	SetFrameQianshuiListener setframeQianshuilistener = new SetFrameQianshuiListener();
	Window win;
	JLabel setjindutiao = new JLabel("设置动态进度条",JLabel.LEFT);
	JLabel setchangetxt = new JLabel("词语提示",JLabel.LEFT);
	public void SetFrame1(){
		setTitle("设置");
		setBounds(100,100,700,400);
		setLayout(null);

		setVisible(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		com.sun.awt.AWTUtilities.setWindowOpaque(this,true);//取消透明
		
		addJinduON_Off();
		addKeyboardRecord();
		addKeymistakeRecord();
		addQianshu();
		addChangetxt();
		SetBackground setbackgroundListener = new SetBackground();
		setbackgroundListener.setFrame(this);
		setbackgroundListener.setWin(win);
		JButton RightcolorSet = new JButton("打对字颜色");
		RightcolorSet.setBounds(10,180,100,30);
		add(RightcolorSet);
		
		JButton MistakecolorSet = new JButton("打错字颜色");
		MistakecolorSet.setBounds(RightcolorSet.getX()+RightcolorSet.getWidth()+10,RightcolorSet.getY(),100,30);
		add(MistakecolorSet);
		
		JButton WenbenBackgroundSet = new JButton("文本框背景颜色");
		WenbenBackgroundSet.setBounds(MistakecolorSet.getX()+MistakecolorSet.getWidth()+10,RightcolorSet.getY(),140,30);
		add(WenbenBackgroundSet);
		
		JButton qmccolorset = new JButton("全码词颜色");
		qmccolorset.setBounds(WenbenBackgroundSet.getX()+WenbenBackgroundSet.getWidth()+10,RightcolorSet.getY(),140,30);
		add(qmccolorset);
		
		JButton BackgroundSet = new JButton("整体界面颜色");
		BackgroundSet.setBounds(RightcolorSet.getX(),RightcolorSet.getY()+RightcolorSet.getHeight()+10,140,30);
		add(BackgroundSet);
		
		JButton DaziBackgroundSet = new JButton("打字框背景颜色");
		DaziBackgroundSet.setBounds(BackgroundSet.getX()+BackgroundSet.getWidth()+10,RightcolorSet.getHeight()+RightcolorSet.getY()+10,140,30);
		add(DaziBackgroundSet);
		
		JButton emccolorset = new JButton("二码词颜色");
		emccolorset.setBounds(DaziBackgroundSet.getX()+DaziBackgroundSet.getWidth()+10,RightcolorSet.getHeight()+RightcolorSet.getY()+10,100,30);
		add(emccolorset);
		
		JButton smccolorset = new JButton("三码词颜色");
		smccolorset.setBounds(emccolorset.getX()+emccolorset.getWidth()+10,RightcolorSet.getHeight()+RightcolorSet.getY()+10,100,30);
		add(smccolorset);
		
		FontSize = new JTextField("填入字号:保存");
		FontSize.setBounds(BackgroundSet.getX(),BackgroundSet.getY()+BackgroundSet.getHeight()+10,90,30);
		add(FontSize);
		
		JButton changeFontSize = new JButton("保存");
		changeFontSize.setBounds(FontSize.getX()+FontSize.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,60,30);
		add(changeFontSize);
		
		Splitenum = new JTextField("分页字数:保存");
		Splitenum.setBounds(changeFontSize.getX()+changeFontSize.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,90,30);
		add(Splitenum);
		
		JButton splitebutton = new JButton("保存");
		splitebutton.setBounds(Splitenum.getX()+Splitenum.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,60,30);
		add(splitebutton);
		
		JButton mabiao = new JButton("全码表选择");
		mabiao.setBounds(splitebutton.getX()+splitebutton.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,120,30);
		add(mabiao);
		
		SetFontSize setfontlistener = new SetFontSize(FontSize);
		setframesplitenum = new SetFrameSplitnum(Splitenum);
		
		WenbenBackgroundSet.addActionListener(setbackgroundListener);
		RightcolorSet.addActionListener(setbackgroundListener);
		MistakecolorSet.addActionListener(setbackgroundListener);
		DaziBackgroundSet.addActionListener(setbackgroundListener);
		BackgroundSet.addActionListener(setbackgroundListener);
		qmccolorset.addActionListener(setbackgroundListener);
		emccolorset.addActionListener(setbackgroundListener);
		smccolorset.addActionListener(setbackgroundListener);
		changeFontSize.addActionListener(setfontlistener);
		splitebutton.addActionListener(setframesplitenum);
		mabiao.addActionListener(chosefilelistener);
		
	}
	public void setGendaListener(GendaListener t){
		gendalistener = t;
	}
	public void setWin(Window win){
		this.win = win;
	}
	void addJinduON_Off(){
		setjindutiaoON = new JRadioButton("开");
		setjindutiaoOFF = new JRadioButton("关");
		setjindutiaogroup = new ButtonGroup();
		setjindutiaogroup.add(setjindutiaoON);
		setjindutiaogroup.add(setjindutiaoOFF);
		setjindutiaoON.setBounds(110,10,50,20);
		setjindutiaoOFF.setBounds(160,10,50,20);
		setjindutiao.setBounds(10,10,90,20);
		
		add(setjindutiao);
		add(setjindutiaoON);
		add(setjindutiaoOFF);
		setframeJindulistener.setButton1(setjindutiaoON);
		setjindutiaoON.addActionListener(setframeJindulistener);
		setjindutiaoOFF.addActionListener(setframeJindulistener);
	}
	void addChangetxt(){
		
		changetxtON = new JRadioButton("开");
		changetxtOFF = new JRadioButton("关");
		setchange = new ButtonGroup();
		setchange.add(changetxtON);
		setchange.add(changetxtOFF);
		setchangetxt.setBounds(410,10,50,20);
		changetxtON.setBounds(470,10,50,20);
		changetxtOFF.setBounds(520,10,50,20);
		add(setchangetxt);
		add(changetxtON);
		add(changetxtOFF);
		tiplistener.setButton1(changetxtON);
		changetxtON.addActionListener(tiplistener);
		changetxtOFF.addActionListener(tiplistener);
	}
	void addQianshu(){
		qianshui = new JLabel("潜水跟打",JLabel.LEFT);
		qianshuiON = new JRadioButton("开");
		qianshuiOFF = new JRadioButton("关");
		setqianshui = new ButtonGroup();
		setqianshui.add(qianshuiON);
		setqianshui.add(qianshuiOFF);
		qianshui.setBounds(220,10,50,20);
		qianshuiON.setBounds(280,10,50,20);
		qianshuiOFF.setBounds(330,10,50,20);
		add(qianshui);
		add(qianshuiON);
		add(qianshuiOFF);
		setframeQianshuilistener.setButton1(qianshuiON);
		qianshuiON.addActionListener(setframeQianshuilistener);
		qianshuiOFF.addActionListener(setframeQianshuilistener);
	}
	void addKeyboardRecord(){
		JButton KeyboardRecord = new JButton("提取本次跟打击键记录");
		JTextArea Keyboarddisplay = new JTextArea("提取文本在此显示");
		JScrollPane Keyboarddisplay1 = new JScrollPane(Keyboarddisplay);
		
		
		keyRecordListener.setKeyboarddisplay(Keyboarddisplay);
		
		keyRecordListener.setGendaListener(gendalistener);
		
		KeyboardRecord.addActionListener(keyRecordListener);
		Keyboarddisplay.setLineWrap(true);
		Keyboarddisplay.setFont(new Font("微软雅黑",0,20));
		KeyboardRecord.setBounds(10,40,250,20);
		Keyboarddisplay1.setBounds(10,70,510,100);
		add(KeyboardRecord);
		add(Keyboarddisplay1);
	}
	void addKeymistakeRecord(){
		JButton KeymistakeRecord = new JButton("提取本次跟打错字记录");
//		JTextArea Keymistakedisplay = new JTextArea("提取文本在此显示");
//		JScrollPane Keymistakedisplay1 = new JScrollPane(Keymistakedisplay);
//		keyRecordListener.setKeymistakedisplay( Keymistakedisplay);
		
		KeymistakeRecord.addActionListener(keyRecordListener);
//		Keymistakedisplay.setLineWrap(true);
//		Keymistakedisplay.setFont(new Font("微软雅黑",0,20));
		KeymistakeRecord.setBounds(270,40,250,20);
//		Keymistakedisplay1.setBounds(10,210,500,100);
		add(KeymistakeRecord);
//		add(Keymistakedisplay1);
	}
}
