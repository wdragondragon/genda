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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import Tips.ChooseFile;
public class SetFrame extends JFrame {
	JRadioButton setjindutiaoON,setjindutiaoOFF;
	ButtonGroup setjindutiaogroup;
	
	SetFrameKeyboardRecordListener keyRecordListener = new SetFrameKeyboardRecordListener();

	SetFrameSplitnum setframesplitenum;
	GendaListener gendalistener;
	JLabel qianshui;
	public static JTextField Splitenum,FontSize;
	public CirecordListener cirecordlistener;
	JRadioButton qianshuiON;
	JRadioButton qianshuiOFF;
	JRadioButton changetxtON;
	JRadioButton changetxtOFF;
	
	ChooseFile chosefilelistener = new ChooseFile();
	
	ButtonGroup setqianshui ;
	ButtonGroup setchange;
	JButton zhiding;
	
	Window win;
	JLabel setjindutiao = new JLabel("设置动态进度条",JLabel.LEFT);
	JLabel setchangetxt = new JLabel("词语提示",JLabel.LEFT);
	public static Color close,open = new Color(255,117,90);
	public static JPanel p = new JPanel();
	public static JComboBox<String> family ;
	public void SetFrame1(){
		setTitle("设置");
//		setLayout(null);
		setVisible(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		p.setLayout(null);
//		com.sun.awt.AWTUtilities.setWindowOpaque(this,true);//取消透明
		add(p);
		
		addJinduON_Off();
		addKeyboardRecord();
		addKeymistakeRecord();
		addQianshu();
		addspaceON_Off();
		addcharON_Off();
		addChangetxt();
		addCiRecord();
		
		SetBackground setbackgroundListener = new SetBackground();
		setbackgroundListener.setFrame(this);
		setbackgroundListener.setWin(win);
		JButton RightcolorSet = new JButton("打对字颜色");
		RightcolorSet.setBounds(10,180,100,30);
		p.add(RightcolorSet);
		
		JButton MistakecolorSet = new JButton("打错字颜色");
		MistakecolorSet.setBounds(RightcolorSet.getX()+RightcolorSet.getWidth()+10,RightcolorSet.getY(),100,30);
		p.add(MistakecolorSet);
		
		JButton WenbenBackgroundSet = new JButton("文本框背景颜色");
		WenbenBackgroundSet.setBounds(MistakecolorSet.getX()+MistakecolorSet.getWidth()+10,RightcolorSet.getY(),140,30);
		p.add(WenbenBackgroundSet);
		
		JButton qmccolorset = new JButton("全码词颜色");
		qmccolorset.setBounds(WenbenBackgroundSet.getX()+WenbenBackgroundSet.getWidth()+10,RightcolorSet.getY(),140,30);
		p.add(qmccolorset);
		
		JButton BackgroundSet = new JButton("整体界面颜色");
		BackgroundSet.setBounds(RightcolorSet.getX(),RightcolorSet.getY()+RightcolorSet.getHeight()+10,140,30);
		p.add(BackgroundSet);
		
		JButton DaziBackgroundSet = new JButton("打字框背景颜色");
		DaziBackgroundSet.setBounds(BackgroundSet.getX()+BackgroundSet.getWidth()+10,RightcolorSet.getHeight()+RightcolorSet.getY()+10,140,30);
		p.add(DaziBackgroundSet);
		
		JButton emccolorset = new JButton("二码词颜色");
		emccolorset.setBounds(DaziBackgroundSet.getX()+DaziBackgroundSet.getWidth()+10,RightcolorSet.getHeight()+RightcolorSet.getY()+10,100,30);
		p.add(emccolorset);
		
		JButton smccolorset = new JButton("三码词颜色");
		smccolorset.setBounds(emccolorset.getX()+emccolorset.getWidth()+10,RightcolorSet.getHeight()+RightcolorSet.getY()+10,100,30);
		p.add(smccolorset);
		
		FontSize = new JTextField("填入字号:保存");
		FontSize.setBounds(BackgroundSet.getX(),BackgroundSet.getY()+BackgroundSet.getHeight()+10,70,30);
		FontSize.addKeyListener(		//只能输入数字
				new KeyListener(){
					@Override
					public void keyPressed(KeyEvent arg0) {}
					@Override
					public void keyReleased(KeyEvent arg0) {}
					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						int keyChar = e.getKeyChar();
						if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9){}
						else e.consume();
					}
				}
			);
		p.add(FontSize);
		
		JButton changeFontSize = new JButton("保存");
		changeFontSize.setBounds(FontSize.getX()+FontSize.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,60,30);
		p.add(changeFontSize);
		
		Splitenum = new JTextField("分页字数:保存");
		Splitenum.setBounds(changeFontSize.getX()+changeFontSize.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,70,30);
		Splitenum.addKeyListener(		//只能输入数字
				new KeyListener(){
					@Override
					public void keyPressed(KeyEvent arg0) {}
					@Override
					public void keyReleased(KeyEvent arg0) {}
					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						int keyChar = e.getKeyChar();
						if(keyChar>=KeyEvent.VK_0&&keyChar<=KeyEvent.VK_9){}
						else e.consume();
					}
				}
			);
		p.add(Splitenum);
		
		JButton splitebutton = new JButton("保存");
		splitebutton.setBounds(Splitenum.getX()+Splitenum.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,60,30);
		p.add(splitebutton);
		
		JButton mabiao = new JButton("全码表选择");
		mabiao.setBounds(splitebutton.getX()+splitebutton.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,100,30);
		p.add(mabiao);
		
		JButton zhiding = new JButton("置顶跟打\"关\"");
		p.add(zhiding);
		SetFramezhidingListener setframezhidinglistener = new SetFramezhidingListener(zhiding,win);
		zhiding.addActionListener(setframezhidinglistener);
		zhiding.setBounds(mabiao.getX()+mabiao.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,100,30);
		
		family = new JComboBox<String>();
		GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        String [] fontName=ge.getAvailableFontFamilyNames();
        for(int i=0;i<fontName.length;i++){
            family.addItem(fontName[i]);
        }
		family.setBounds(RightcolorSet.getX(),FontSize.getY()+FontSize.getHeight()+10,120,30);
		p.add(family);
		
		JButton familyque = new JButton("修改字型");
		familyque.setBounds(family.getX()+family.getWidth()+10,FontSize.getY()+FontSize.getHeight()+10,90,30);
		p.add(familyque);
		
		
		
		
		
		FontFamilyListener fontfamilylistener = new FontFamilyListener(family);
		familyque.addActionListener(fontfamilylistener);
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
	void addcharON_Off(){
		JButton charchange = new JButton("符号替换\"已关\"");
		charchange.setBounds(460,10,110,20);
		p.add(charchange);
		SetCharListener setcharlistener = new SetCharListener(charchange);
		charchange.addActionListener(setcharlistener);
		close = charchange.getForeground();
	}
	void addspaceON_Off(){
		JButton space = new JButton("去除空格\"已关\"");
		space.setBounds(350,10,110,20);
		p.add(space);
		SetspaceListener setspacelistener = new SetspaceListener(space);
		space.addActionListener(setspacelistener);
	}
	void addJinduON_Off(){
		JButton jindu = new JButton("显示进度条\"已开\"");
		jindu.setBounds(10,10,120,20);
		p.add(jindu);
		SetFrameJinduListener setframeJindulistener = new SetFrameJinduListener(jindu);
		jindu.addActionListener(setframeJindulistener);
	}
	void addChangetxt(){	
		JButton changetxt = new JButton("词语提示\"已开\"");
		changetxt.setBounds(240,10,110,20);
		p.add(changetxt);
		SetFramechangeListener tiplistener = new SetFramechangeListener(changetxt);
		changetxt.addActionListener(tiplistener);
	}
	void addQianshu(){
		JButton qianshui = new JButton("潜水跟打\"已关\"");
		qianshui.setBounds(130,10,110,20);
		p.add(qianshui);
		SetFrameQianshuiListener setframeQianshuilistener = new SetFrameQianshuiListener(qianshui);
		qianshui.addActionListener(setframeQianshuilistener);
	}
	void addKeyboardRecord(){
		JButton KeyboardRecord = new JButton("提取本次跟打击键记录");
		JTextArea Keyboarddisplay = new JTextArea("提取文本在此显示");
		JScrollPane Keyboarddisplay1 = new JScrollPane(Keyboarddisplay);
		
		
		keyRecordListener.setKeyboarddisplay(Keyboarddisplay);
		
		keyRecordListener.setGendaListener(gendalistener);
		
		KeyboardRecord.addActionListener(keyRecordListener);
		Keyboarddisplay.setLineWrap(true);
		Keyboarddisplay.setFont(new Font(Window.family,0,20));
		KeyboardRecord.setBounds(10,40,200,20);
		Keyboarddisplay1.setBounds(10,70,540,100);
		p.add(KeyboardRecord);
		p.add(Keyboarddisplay1);
	}
	void addKeymistakeRecord(){
		JButton KeymistakeRecord = new JButton("提取本次跟打错字记录");
		KeymistakeRecord.addActionListener(keyRecordListener);
		KeymistakeRecord.setBounds(220,40,200,20);
		p.add(KeymistakeRecord);;
	}
	
	void addCiRecord(){
		JButton Cirecord = new JButton("跟打详情");
		cirecordlistener = new CirecordListener();
		Cirecord.addActionListener(cirecordlistener);
		Cirecord.setBounds(430,40,120,20);
		p.add(Cirecord);
	}
}
