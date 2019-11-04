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
	public static JTextField Splitenum,FontSize,readyFont;
	public CirecordListener cirecordlistener;
	JRadioButton qianshuiON;
	JRadioButton qianshuiOFF;
	JRadioButton changetxtON;
	JRadioButton changetxtOFF;
	
	ChooseFile chosefilelistener = new ChooseFile();
	
	ButtonGroup setqianshui ;
	ButtonGroup setchange;
	
	Window win;
	JLabel setjindutiao = new JLabel("���ö�̬������",JLabel.LEFT);
	JLabel setchangetxt = new JLabel("������ʾ",JLabel.LEFT);
	public static Color close,open = new Color(255,117,90);
	public static JPanel p = new JPanel();
	public static JComboBox<String> family ;
	public SetCharListener setcharlistener;
	public SetspaceListener setspacelistener;
	public SetFrameJinduListener setframeJindulistener;
	public SetFrameQianshuiListener setFrameQianshuiListener;
	public SetFramechangeListener tiplistener;
	public SetFramezhidingListener setframezhidinglistener;
	public void SetFrame1(){
		setTitle("����");
//		setLayout(null);
		setVisible(false);	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		p.setLayout(null);
//		com.sun.awt.AWTUtilities.setWindowOpaque(this,true);//ȡ��͸��
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
		JButton RightcolorSet = new JButton("�������ɫ");
		RightcolorSet.setBounds(10,180,100,30);
		p.add(RightcolorSet);
		
		JButton MistakecolorSet = new JButton("�������ɫ");
		MistakecolorSet.setBounds(RightcolorSet.getX()+RightcolorSet.getWidth()+10,RightcolorSet.getY(),100,30);
		p.add(MistakecolorSet);
		
		JButton WenbenBackgroundSet = new JButton("�ı��򱳾���ɫ");
		WenbenBackgroundSet.setBounds(MistakecolorSet.getX()+MistakecolorSet.getWidth()+10,RightcolorSet.getY(),140,30);
		p.add(WenbenBackgroundSet);
		
		JButton qmccolorset = new JButton("ȫ�����ɫ");
		qmccolorset.setBounds(WenbenBackgroundSet.getX()+WenbenBackgroundSet.getWidth()+10,RightcolorSet.getY(),140,30);
		p.add(qmccolorset);
		
		JButton BackgroundSet = new JButton("���������ɫ");
		BackgroundSet.setBounds(RightcolorSet.getX(),RightcolorSet.getY()+RightcolorSet.getHeight()+10,140,30);
		p.add(BackgroundSet);
		
		JButton DaziBackgroundSet = new JButton("���ֿ򱳾���ɫ");
		DaziBackgroundSet.setBounds(BackgroundSet.getX()+BackgroundSet.getWidth()+10,RightcolorSet.getHeight()+RightcolorSet.getY()+10,140,30);
		p.add(DaziBackgroundSet);
		
		JButton emccolorset = new JButton("�������ɫ");
		emccolorset.setBounds(DaziBackgroundSet.getX()+DaziBackgroundSet.getWidth()+10,RightcolorSet.getHeight()+RightcolorSet.getY()+10,100,30);
		p.add(emccolorset);
		
		JButton smccolorset = new JButton("�������ɫ");
		smccolorset.setBounds(emccolorset.getX()+emccolorset.getWidth()+10,RightcolorSet.getHeight()+RightcolorSet.getY()+10,100,30);
		p.add(smccolorset);
		
		FontSize = new JTextField("�����ֺ�:����");
		FontSize.setBounds(BackgroundSet.getX(),BackgroundSet.getY()+BackgroundSet.getHeight()+10,70,30);
		FontSize.addKeyListener(		//ֻ����������
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
		
		JButton changeFontSize = new JButton("����");
		changeFontSize.setBounds(FontSize.getX()+FontSize.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,60,30);
		p.add(changeFontSize);
		
		Splitenum = new JTextField("��ҳ����:����");
		Splitenum.setBounds(changeFontSize.getX()+changeFontSize.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,70,30);
		Splitenum.addKeyListener(		//ֻ����������
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
		
		JButton splitebutton = new JButton("����");
		splitebutton.setBounds(Splitenum.getX()+Splitenum.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,60,30);
		p.add(splitebutton);

		JButton mabiao = new JButton("ȫ���ѡ��");
		mabiao.setBounds(splitebutton.getX()+splitebutton.getWidth()+10,BackgroundSet.getY()+BackgroundSet.getHeight()+10,100,30);
		p.add(mabiao);
		
		JButton zhiding = new JButton("�ö�����\"��\"");
		p.add(zhiding);
		setframezhidinglistener = new SetFramezhidingListener(zhiding,win);
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
		
		JButton familyque = new JButton("�޸�����");
		familyque.setBounds(family.getX()+family.getWidth()+10,FontSize.getY()+FontSize.getHeight()+10,90,30);
		p.add(familyque);
		
		readyFont = new JTextField("0");
		readyFont.setBounds(familyque.getX()+familyque.getWidth()+10,FontSize.getY()+FontSize.getHeight()+10,90,30);
		readyFont.addKeyListener(		//ֻ����������
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
		p.add(readyFont);
		
		
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
		JButton charchange = new JButton("�����滻\"�ѹ�\"");
		charchange.setBounds(460,10,110,20);
		p.add(charchange);
		setcharlistener = new SetCharListener(charchange);
		charchange.addActionListener(setcharlistener);
		close = charchange.getForeground();
	}
	void addspaceON_Off(){
		JButton space = new JButton("ȥ���ո�\"�ѹ�\"");
		space.setBounds(350,10,110,20);
		p.add(space);
		setspacelistener = new SetspaceListener(space);
		space.addActionListener(setspacelistener);
	}
	void addJinduON_Off(){
		JButton jindu = new JButton("��ʾ������\"�ѿ�\"");
		jindu.setBounds(10,10,120,20);
		p.add(jindu);
		setframeJindulistener = new SetFrameJinduListener(jindu);
		jindu.addActionListener(setframeJindulistener);
	}
	void addChangetxt(){	
		JButton changetxt = new JButton("������ʾ\"�ѿ�\"");
		changetxt.setBounds(240,10,110,20);
		p.add(changetxt);
		tiplistener = new SetFramechangeListener(changetxt);
		changetxt.addActionListener(tiplistener);
	}
	void addQianshu(){
		JButton qianshui = new JButton("Ǳˮ����\"�ѹ�\"");
		qianshui.setBounds(130,10,110,20);
		p.add(qianshui);
		setFrameQianshuiListener = new SetFrameQianshuiListener(qianshui);
		qianshui.addActionListener(setFrameQianshuiListener);
	}
	
	void addKeyboardRecord(){
		JButton KeyboardRecord = new JButton("��ȡ���θ��������¼");
		JTextArea Keyboarddisplay = new JTextArea("��ȡ�ı��ڴ���ʾ");
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
		JButton KeymistakeRecord = new JButton("��ȡ���θ�����ּ�¼");
		KeymistakeRecord.addActionListener(keyRecordListener);
		KeymistakeRecord.setBounds(220,40,200,20);
		p.add(KeymistakeRecord);;
	}
	
	void addCiRecord(){
		JButton Cirecord = new JButton("��������");
		cirecordlistener = new CirecordListener();
		Cirecord.addActionListener(cirecordlistener);
		Cirecord.setBounds(430,40,120,20);
		p.add(Cirecord);
	}
}
