package genda1;

import gendaClient.*;
import saiwenSys.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import Acticle.Acticle;
import EmailSys.Email;
import FriendSys.FriendsSysListener;
import History.historyListener;
import Login.Login;
import Login.UpdateListener;
import QQ.ChangeQQWindow;
import QQ.ForegroundWinName;
import RamdomOne.RamdomListener;
import Ranking.RankListener;
import Robots.RobotListener;
import SetWin.*;
import Tips.BuildChooseFile;
import Tips.Noexit;
import Tips.TheoryListener;
import Tips.Tips;
import Tips.TipsFrame;

import java.awt.event.*;
import java.io.IOException;
import Acticle.*;
import BuildSai.BuildSaiListener;
import Check.Check;
import Communication.CommunicationListener;
import keep.readWrite;
import lookplay.AchListener;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public static String IP = "39.96.83.89";
	public static String state = "��ս";// ����״̬
	public static Color rightcolor = Color.gray;
	public static Color mistakecolor = Color.red;
	public static Color smacicolor = Color.BLUE;
	public static Color emacicolor = Color.ORANGE;
	public static Color qmacicolor = new Color(128, 138, 135);
	public static JTextArea dazi, chengji, zaiwenText;
	public static JTextPane wenben;
	public static int fontallnum;
	public static int rightnum;
	public static int misnum;
	public static int datenum;
	public static orActicle orActicle;
	public static GendaListener gendaListener;
	public static JProgressBar gendajindutiao;
	public static GendaJindutiao setGendajindu;
	public static boolean everydaySign = false;
	public static Tips tipschange;
	public static JLabel tips;
	public static String family = "΢���ź�";
//	 public static String IP = "127.0.0.1";
	public static String Email = "";
	public static int Pattern = 0;
	Font ziti, zititip;
	JLabel qqName;
	static JLabel zishu;
	JLabel allnumber;
	public JLabel sendwen;
	public static JLabel dqbanben, zxbanben;
	JScrollPane wenben1, dazi1, chengji1, zaiwenText1;
	public static JScrollPane tableN;

	JButton F3;
	JButton zaiwen, more;
	static JButton suduButton;
	JButton KeySuduButton;
	JButton achievementButton;
	JButton QQzaiwenButton;
	JButton help;
	JButton conection;
	JButton set;
	JButton online;
	JButton Keylength;
	JButton close, max, size, min;
	JButton changeQQButton;
	JButton rightce;
	JButton leftce;
	JButton acticlebutton;
	JButton mix;
	JButton next;
	JButton save;
	JButton share;
	JButton chouqu;
	JButton Englishnew;
	//������ð�ť��
	public static JButton charchange;
	public static JButton space;
	public static JButton jindu;
	public static JButton wordTips;
	public static JButton qianshui;
	public static JButton zhiding;
	
	JButton lookplayfinish;

//	public static JButton lilunma;
	public static JButton dinglilunma;
	JTable table;

	TableColumn column = null;
	DefaultTableModel tableM;

	// public MoreListener morelistener;
	public static F3Listener f3listener;
	JianQieListener zaiwenlistener;
	MousePressedZaiwenText zaiwenMouselistener;
	AchievementListener achievementListener;
	QQZaiwenListener qqzaiwenListener;
	ConectionListener conectionListener;
	Set setlistener;
	public Mix mixlistener;
	ShareListener sharelistener;
	qqNameButtonAction qqNamebuttonlistener;
	HelpDialog helpListener;
	ChangeQQWindow changeQQwindow;
	TheoryListener theorylistener;
	AchListener lookplaylistener;
	public static TipsFrame tipsframe;

	JScrollBar JSBwenben;
	InputMap F3Key, zaiwenKey, qqzaiwenKey, sendChengji, readyKey, shareKey,
			changeQQButtonKey, setKey, fawenKey;

	public static Acticle acticle;
	Login login;

	TableAdd tableAdd;
	SetFrame setframe;
	ForegroundWinName setwinName;
	winchange Winchg;

	// ����Ϊ����ϵͳ����

	JButton link, breaklink, ready;
	public static JButton score;
	public static JButton one;
	JButton two;
	JButton three;
	JButton four;
	JButton five;
	JButton six;
	JButton seven;
	JButton eight;
	static JTextField reducesudu;
	public static JTextArea accept = new JTextArea();
	public static JTextArea communion = new JTextArea();
	JScrollPane accept1 = new JScrollPane(accept);
	JScrollPane communion1 = new JScrollPane(communion);
	LinkListener linkListener;// ���Ӽ���
	BreakLinkListener breakLinkListener;
	RoomListener roomListener;
	ReadyListener readyListener;
	SystemListener systemlistener;

	battleClient client;

	// ȫ�ֶ���
	public static JSplitPane jSplitPane1;
	// public JSplitPane jSplitPane2;
	public static int RoomNum = 0;
	public static boolean Linksign = true;
	static public int fontSize = 30;

	OnlineOorF onlineListener;
	int i;
	Point pressedPoint;

	Window() {
		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// UIManager.put("ToolBar.isPaintPlainBackground",true);
		// } catch (ClassNotFoundException | InstantiationException
		// | IllegalAccessException | UnsupportedLookAndFeelException e1) {
		// e1.printStackTrace();
		// }
		try {
			// JFrame.setDefaultLookAndFeelDecorated(true);
			// JDialog.setDefaultLookAndFeelDecorated(true);
			// BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
			// UIManager
			// .setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");
			// SubstanceLookAndFeel
			// .setCurrentTheme("org.jvnet.substance.theme.SubstanceAquaTheme");

			// UIManager.setLookAndFeel(LittleLuckLookAndFeel.class.getName());

			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		addWindowFocusListener(new StopListener());
		// �����ޱ߿��������ƴ����ƶ�
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { // ��갴���¼�
				pressedPoint = e.getPoint(); // ��¼�������
			}

			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() >= 2) // ˫�����
					if (systemlistener.MaxSign == 0)
						SystemListener.max();
					else
						SystemListener.min();
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) { // �����ק�¼�
				Point point = e.getPoint();// ��ȡ��ǰ����
				Point locationPoint = getLocation();// ��ȡ��������
				int x = locationPoint.x + point.x - pressedPoint.x;// �����ƶ����������
				int y = locationPoint.y + point.y - pressedPoint.y;
				setLocation(x, y);// �ı䴰��λ��
			}
		});
		try {// ��ȡ������
			readWrite.readfontnum();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("��ȡ�ܸ�������ʧ��");
		}
		setVisible(true);// ���ÿɼ�
		setMinimumSize(new Dimension(100, 100));
		init();// ��ʼ��
//		setOpacity(0.95f);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ùرհ�ť
		dazi.setBackground(new Color(238, 238, 238));// ���ô��ֿ���ɫ
		wenben.setBackground(new Color(238, 238, 238));// �����ı�����ɫ
	}

	// window��ʼ��
	void init() {
		setNew();// �����¶���
		setAllziti();// ������������
		TrueOrFalse();// ����True False����
		// Listener Set
		gendaListenerset();// ��������������
		f3Listenerset();// �ش�����
		zaiwenlistenerset();// ���а���������
		zaiwenMouselistenerset();// ���Ŀ�����¼�����������
		achievementlistenerset();
		qqzaiwenlistenerset();
		addAllListener();// ����Ҫ��Ӽ����������Ӽ�����
		setAllBounds();// ����Bounds��������
		qqsendChengjiset();// ���ͳɼ���ť����
		gendajinduset();// �������������
		setlistenerset();// ������������
		setwinNameset();// ����ȡqq�������ַ���
		qqNamebuttonlistenerset();// qq��ť������
		// setKey
		setF3Key();// ��F3���ÿ�ݼ�
		setQQzaiwenKey();// QQ���Ŀ�ݼ�
		setMoreKey();// �����ݼ�
		setChangeQQKey();// ��Ⱥ��ݼ�
		setSetKey();// �������ÿ�ݼ�
		setMixKey();// �����ݼ�
		setFawenKey();// ���Ŀ�ݼ�
		TableRowAddset();
		initText();// ��ʼ���ı���

		// �������ͳɼ����
		Tableset();

		addAll();// ����������
		jsplit();// ����
		Client();// �ͻ�������
		menu();// �˵�
		FlowLayout();// ����������

		Count backConut = new Count(communion, dazi);
		backConut.start();
	}

	// ������ʵ����
	void setNew() {
		setLayout(null);
		Object name[] = { "", "�ٶ�", "����", "�볤", "����", "�ظ�", "�˸�", "����", "ѡ��",
				"��׼", "�����", "ʱ��(��)" }, a[][] = null;
		// �����С
		ziti = new Font(family, 0, fontSize);
		zititip = new Font(family, 0, 12);
		// ������ɫ
		JTextPaneChange.creat();
		JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc, fontSize,
				0, 0, 0, Color.BLACK, family, rightcolor);
		JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc, fontSize,
				0, 0, 0, Color.BLACK, family, mistakecolor);
		JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc, fontSize,
				0, 0, 0, Color.BLACK, family, mistakecolor);// GRAY

		JTextPaneChange.createStyle("����", JTextPaneChange.styledDoc, fontSize,
				1, 0, 0, smacicolor, family, mistakecolor);// GRAY
		JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc, fontSize,
				0, 0, 0, smacicolor, family, mistakecolor);// GRAY
		JTextPaneChange.createStyle("��б", JTextPaneChange.styledDoc, fontSize,
				0, 1, 0, smacicolor, family, mistakecolor);// GRAY
		JTextPaneChange.createStyle("����б", JTextPaneChange.styledDoc, fontSize,
				1, 1, 0, smacicolor, family, mistakecolor);// GRAY
		JTextPaneChange.createStyle("�۴�", JTextPaneChange.styledDoc, fontSize,
				1, 0, 0, emacicolor, family, mistakecolor);// GRAY
		JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc, fontSize,
				0, 0, 0, emacicolor, family, mistakecolor);// GRAY
		JTextPaneChange.createStyle("��б", JTextPaneChange.styledDoc, fontSize,
				0, 1, 0, emacicolor, family, mistakecolor);// GRAY
		JTextPaneChange.createStyle("�۴�б", JTextPaneChange.styledDoc, fontSize,
				1, 1, 0, emacicolor, family, mistakecolor);// GRAY

		JTextPaneChange.createStyle("�̴�", JTextPaneChange.styledDoc, fontSize,
				1, 0, 0, qmacicolor, family, mistakecolor);// GRAY
		JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc, fontSize,
				0, 0, 0, qmacicolor, family, mistakecolor);// GRAY
		JTextPaneChange.createStyle("��б", JTextPaneChange.styledDoc, fontSize,
				0, 1, 0, qmacicolor, family, mistakecolor);// GRAY
		JTextPaneChange.createStyle("�̴�б", JTextPaneChange.styledDoc, fontSize,
				1, 1, 0, qmacicolor, family, mistakecolor);// GRAY
		gendajindutiao = new JProgressBar(0, 0);

		tableM = new DefaultTableModel(a, name);
		table = new JTable(tableM);
		tableAdd = new TableAdd();

		wenben = new JTextPane(JTextPaneChange.styledDoc);

		dazi = new JTextArea() {
			@Override
			public void copy() {
				if (Window.everydaySign)
					JOptionPane.showMessageDialog(new JTextArea(), "�����в�������");
			}

			@Override
			public void paste() {
				JOptionPane.showMessageDialog(new JTextArea(), "������ճ��");
			}
		};
		// dazi = new JTextArea();
		chengji = new JTextArea();
		zaiwenText = new JTextArea();
		reducesudu = new JTextField("0");
		reducesudu.addKeyListener( // ֻ����������
				new KeyListener() {
					@Override
					public void keyPressed(KeyEvent arg0) {
					}

					@Override
					public void keyReleased(KeyEvent arg0) {
					}

					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						int keyChar = e.getKeyChar();
						if (keyChar >= KeyEvent.VK_0
								&& keyChar <= KeyEvent.VK_9) {
						} else
							e.consume();
					}
				});
		wenben1 = new JScrollPane(wenben);
		dazi1 = new JScrollPane(dazi);
		chengji1 = new JScrollPane(chengji);
		tableN = new JScrollPane(table);

		qqName = new JLabel("F5��������Ⱥ");
		qqName.setBorder(BorderFactory.createTitledBorder("����Ⱥ"));
		zishu = new JLabel("����:0/�Ѵ�:0/��:0");
		zishu.setBorder(BorderFactory.createTitledBorder("����"));
		allnumber = new JLabel("��:" + String.valueOf(Window.fontallnum) + " ��:"
				+ String.valueOf(Window.rightnum) + " ��:"
				+ String.valueOf(Window.misnum) + " ��:"
				+ String.valueOf(Window.datenum));
		allnumber.setBorder(BorderFactory.createTitledBorder("�����¼"));
		sendwen = new JLabel("���Ľ���");
		sendwen.setBorder(BorderFactory.createTitledBorder("���Ľ���"));
		tips = new JLabel("������ʾ");
		tips.setBorder(BorderFactory.createTitledBorder("������ʾ"));

		dqbanben = new JLabel("��ǰ");
		zxbanben = new JLabel("����");

		charchange = new JButton("");
		space = new JButton("");
		jindu = new JButton("");
		wordTips = new JButton("");
		qianshui = new JButton("");
		zhiding = new JButton("");
		
		F3 = new JButton("�ش�");
		zaiwen = new JButton("���а�����");
		more = new JButton("�˵�ģʽ");

		suduButton = new JButton("�ٶ�");
		KeySuduButton = new JButton("����");
		Keylength = new JButton("�볤");
//		lilunma = new JButton("�����볤");
		dinglilunma = new JButton("�궥����");
		achievementButton = new JButton("���ɳɼ�");
		QQzaiwenButton = new JButton("QQȺ����");
		help = new JButton("����");
		conection = new JButton("Э������");
		set = new JButton("��������");
		online = new JButton("���߶�ս");
		close = new JButton("��");
		max = new JButton("���");
		min = new JButton("��С��");
		size = new JButton("��С");
		changeQQButton = new JButton();
		rightce = new JButton();
		leftce = new JButton();
		acticlebutton = new JButton("����");
		mix = new JButton("�ö�����");
		next = new JButton("��һ��");
		save = new JButton("����������");
		share = new JButton("������");
		chouqu = new JButton("��ȡ��һ��");
		Englishnew = new JButton("Ӣ����һ��");
		lookplayfinish = new JButton("����");
		lookplayfinish.setToolTipText("�����㷨��Ӣ��ȺȺ������硱�����Ż�");

		
		
		gendaListener = new GendaListener();// ���ֿ������
		f3listener = new F3Listener();// F3��ť������
		zaiwenlistener = new JianQieListener();
		zaiwenMouselistener = new MousePressedZaiwenText();
		achievementListener = new AchievementListener();
		qqzaiwenListener = new QQZaiwenListener();
		helpListener = new HelpDialog();
		conectionListener = new ConectionListener();
		setlistener = new Set(this);
		setwinName = new ForegroundWinName();
		qqNamebuttonlistener = new qqNameButtonAction();
		systemlistener = new SystemListener(this);
		// morelistener = new MoreListener(this);
		changeQQwindow = new ChangeQQWindow(qqName);
		mixlistener = new Mix(this);
		sharelistener = new ShareListener(qqName);
		tipschange = new Tips(tips);
		theorylistener = new TheoryListener();
		lookplaylistener = new AchListener();
		tipsframe = new TipsFrame();

		setGendajindu = new GendaJindutiao();
		JSBwenben = wenben1.getVerticalScrollBar();

		setframe = new SetFrame();

		acticle = new Acticle(this);
		login = new Login(this);

		orActicle = new orActicle(acticle, this);
		next.addActionListener(Acticle.treeListener);
		save.addActionListener(Acticle.treeListener);
		chouqu.addActionListener(Acticle.treeListener);
		Englishnew.addActionListener(Acticle.treeListener);

		AcitiyComp acitiycomp = new AcitiyComp();
		acitiycomp.start();// �ٶȻ����볤��̬�仯�߳�

		Clip clip = new Clip();
		clip.start();

		ImageIcon icon = new ImageIcon("images//installer_repair_1.png"); // xxx����ͼƬ���·����2.pngͼƬ���Ƽ���ʽ
		this.setIconImage(icon.getImage());

		Onsystem();
	}

	// ����������
	void addAll() {
		add(wenben1);
		// add(dazi1);
		add(chengji1);
		add(F3);
		add(zaiwen);
		add(more);
		add(zishu);
		add(suduButton);
		add(KeySuduButton);
		add(achievementButton);
		add(QQzaiwenButton);
		add(qqName);

		add(tableN);
		add(conection);
		add(gendajindutiao);
		add(set);
		add(online);
		add(help);

		add(close);
		add(max);
		add(min);
		add(size);
		add(changeQQButton);
		add(Keylength);
		add(rightce);
		add(leftce);
		add(acticlebutton);
		add(allnumber);
		add(mix);
		add(next);
		add(save);
		add(share);
		add(sendwen);
		add(tips);
//		add(lilunma);
		add(dinglilunma);
		add(reducesudu);
		add(chouqu);
		add(Englishnew);
		add(zxbanben);
		add(dqbanben);
		add(lookplayfinish);
		add(charchange);
		add(space);
		add(jindu);
		add(wordTips);
		add(qianshui);
		add(zhiding);
	}

	// ������������ü�����
	void addAllListener() {
		(dazi.getDocument()).addDocumentListener(gendaListener);// �������Ӽ�����
		dazi.addKeyListener(gendaListener);
		F3.addActionListener(f3listener);// �ش������
		zaiwen.addActionListener(zaiwenlistener);// ���а����ļӼ�����
		zaiwenText.addMouseListener(zaiwenMouselistener);

		achievementButton.addActionListener(achievementListener);
		QQzaiwenButton.addActionListener(qqzaiwenListener);
		help.addActionListener(helpListener);
		conection.addActionListener(conectionListener);
		set.addActionListener(setlistener);
		qqName.addMouseListener(qqNamebuttonlistener);
		close.addActionListener(systemlistener);
		max.addActionListener(systemlistener);
		size.addMouseListener(systemlistener);
		min.addActionListener(systemlistener);
		size.addMouseMotionListener(systemlistener);
		// more.addActionListener(morelistener);
		// online.addActionListener()
		changeQQButton.addActionListener(changeQQwindow);
		acticlebutton.addActionListener(orActicle);
		mix.addActionListener(mixlistener);
		share.addActionListener(sharelistener);
//		lilunma.addActionListener(theorylistener);
		dinglilunma.addActionListener(theorylistener);
		leftce.addActionListener(historylistener);
		tips.addMouseListener(tipsframe);
		
		SetCharListener setcharlistener = new SetCharListener();
		SetspaceListener setspacelistener = new SetspaceListener();
		SetFrameJinduListener setframeJindulistener = new SetFrameJinduListener();
		SetFrameQianshuiListener setFrameQianshuiListener = new SetFrameQianshuiListener();
		SetFramechangeListener tiplistener = new SetFramechangeListener();
		SetFramezhidingListener setframezhidinglistener = new SetFramezhidingListener();
		
		zhiding.addActionListener(setframezhidinglistener);
		charchange.addActionListener(setcharlistener);
		space.addActionListener(setspacelistener);
		jindu.addActionListener(setframeJindulistener);
		wordTips.addActionListener(tiplistener);
		qianshui.addActionListener(setFrameQianshuiListener);
	}

	// ���м��������á�����������
	void gendaListenerset() {
		gendaListener.setDaziText(dazi);
		gendaListener.setChengjiText(chengji);
		gendaListener.setWenben1Text(wenben1);// ���ø�������������
		gendaListener.setWenbenText(wenben);
		gendaListener.setJSB(JSBwenben);
		gendaListener.setZishu(zishu);
		gendaListener.setSuduButton(suduButton);
		gendaListener.setKeySuduButton(KeySuduButton);
		gendaListener.setAchievementButton(achievementButton);
		gendaListener.setJProgressBar(setGendajindu);
		gendaListener.setKeylength(Keylength);
		gendaListener.setAllnumber(allnumber);
		gendaListener.setAchievementListener(achievementListener);
		gendaListener.setTipschange(tipschange);
	}

	void f3Listenerset() {
		f3listener.setDaziText(dazi);// ����F3���ֿ�
		f3listener.setChengjiText(chengji);
		f3listener.setZishu(zishu);
		f3listener.setWenben(wenben);
		f3listener.setGendaListener(gendaListener);
		f3listener.setJSB(JSBwenben);
		f3listener.setJProgressBar(setGendajindu);
	}

	void zaiwenlistenerset() {
		zaiwenlistener.setWenbenText(wenben);// ���а������ı���
		zaiwenlistener.setDaziText(dazi);// ���а����ô��ֿ�
		zaiwenlistener.setGendaListener(gendaListener);
		zaiwenlistener.setZishu(zishu);
		zaiwenlistener.setJSB(JSBwenben);
		zaiwenlistener.setJProgressBar(setGendajindu);
	}

	void zaiwenMouselistenerset() {
		zaiwenMouselistener.setZaiwenText(zaiwenText);
	}

	void achievementlistenerset() {
		achievementListener.setGendaListener(gendaListener);
		achievementListener.setChengjiText(chengji);
		achievementListener.setDaziText(dazi);
		achievementListener.setQQName(qqName);
		achievementListener.setTable(tableAdd);
		achievementListener.setWenbenText(wenben);
		achievementListener.setWin(this);
	}

	void qqzaiwenlistenerset() {
		qqzaiwenListener.setDaziText(dazi);
		qqzaiwenListener.setWenbenText(wenben);
		qqzaiwenListener.setQQName(qqName);
		qqzaiwenListener.setGendaListener(gendaListener);
		qqzaiwenListener.setJProgressBar(setGendajindu);
	}

	void qqNamebuttonlistenerset() {
		qqNamebuttonlistener.setQQnameButton(qqName);
	}

	// ������������������
	void setlistenerset() {
		setlistener.setFrame(setframe);
		setframe.setGendaListener(gendaListener);
		setframe.setWin(this);
		setframe.SetFrame1();
	}

	// �������������С
	void setAllBounds() {
		// TODO
		// ���Ű�ť
		jindu.setBounds(50,0,20,10);
		qianshui.setBounds(jindu.getX()+jindu.getWidth()+10,0,20,10);
		wordTips.setBounds(qianshui.getX()+qianshui.getWidth()+10,0,20,10);
		space.setBounds(wordTips.getX()+wordTips.getWidth()+10,0,20,10);
		charchange.setBounds(space.getX()+space.getWidth()+10,0,20,10);
		zhiding.setBounds(charchange.getX()+charchange.getWidth()+10,0,20,10);
		
		F3.setBounds(57, 10, 0, 0); // �ش����ð�ť��Сλ�� 190
		suduButton.setBounds(57, F3.getY(), 150, 30);
		KeySuduButton.setBounds(217, F3.getY(), 150, 30);
		Keylength.setBounds(377, F3.getY(), 150, 30);
		dinglilunma.setBounds(537, F3.getY(), 150, 30);
//		dinglilunma.setBounds(697, F3.getY(), 150, 30);

		zaiwen.setBounds(353, F3.getY(), 0, 0);// ���а����İ�ť��Сλ��
		more.setBounds(458, F3.getY(), 0, 0);// �ı����İ�ť��Сλ��
		achievementButton.setBounds(563, F3.getY(), 0, 0);
		QQzaiwenButton.setBounds(668, F3.getY(), 0, 0);

		set.setBounds(963, F3.getY(), 0, 0);// ����

		help.setBounds(868, F3.getY(), 0, 0);
		acticlebutton.setBounds(1058, F3.getY(), 0, 0);
		online.setBounds(773, 10, 0, 0);
		// mix.setBounds(400,400,300,300);
		
		
	}

	// �����ı�������
	void setAllziti() {
		wenben.setFont(ziti);
		dazi.setFont(ziti);
		chengji.setFont(new Font("΢���ź�", 0, 20));
		min.setFont(new Font(family, 0, 0));
		max.setFont(new Font(family, 0, 0));
		close.setFont(new Font(family, 0, 0));
		zaiwenText.setFont(ziti);

		qqName.setFont(zititip);

		zishu.setFont(zititip);
		allnumber.setFont(zititip);
		sendwen.setFont(zititip);
		;
		tips.setFont(zititip);
		online.setBackground(Color.white);
	}

	void TrueOrFalse() {
		// wenben.setLineWrap(true);//�ı����Զ�����
		dazi.setLineWrap(true);// ���ֿ��Զ�����
		chengji.setLineWrap(true);// ���ֿ��Զ�����
		zaiwenText.setLineWrap(true);// ���Ŀ��Զ�����
		wenben.setEditable(false);
		chengji.setEditable(false);// �����ı���ͳɼ��򲻿ɱ༭
		sendwen.setVisible(false);
		// this.setResizable(false);
	}

	// �������ı����ʼ��
	void initText() {
		wenben.setText("F5��Ⱥ��F4���ģ�F3�ش�F2���ģ�F1���ͳɼ���Ĭ���Զ����ͳɼ���");
		dazi.setText("");
		chengji.setText("");
		zaiwenText.setText("");
	}

	// ���ÿ�ݼ�����������
	void setMixKey() {
		mix.setMnemonic(KeyEvent.VK_L);
		next.setMnemonic(KeyEvent.VK_P);
		save.setMnemonic(KeyEvent.VK_S);
		chouqu.setMnemonic(KeyEvent.VK_O);
	}

	void setFawenKey() {
		acticlebutton.setMnemonic(KeyEvent.VK_2);
		fawenKey = acticlebutton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		fawenKey.put(KeyStroke.getKeyStroke("F2"), "send");
		ActionMap fawenAction = acticlebutton.getActionMap();
		fawenAction.put("send", orActicle);
	}

	void setF3Key() {
		F3.setMnemonic(KeyEvent.VK_3);// F3��ť��ݼ�
		F3Key = F3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		F3Key.put(KeyStroke.getKeyStroke("F3"), "dog");
		ActionMap F3Action = F3.getActionMap();
		F3Action.put("dog", f3listener);
	}

	void setQQzaiwenKey() {
		QQzaiwenButton.setMnemonic(KeyEvent.VK_4);
		qqzaiwenKey = QQzaiwenButton
				.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		qqzaiwenKey.put(KeyStroke.getKeyStroke("F4"), "qqzaiwen");
		ActionMap qqzaiwenAction = QQzaiwenButton.getActionMap();
		qqzaiwenAction.put("qqzaiwen", qqzaiwenListener);
	}

	// qq���ͳɼ����ÿ�ݼ�
	void qqsendChengjiset() {
		achievementButton.setMnemonic(KeyEvent.VK_1);
		sendChengji = achievementButton
				.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		sendChengji.put(KeyStroke.getKeyStroke("F1"), "qqzaiwenSend");
		ActionMap qqsendChengjiAction = achievementButton.getActionMap();
		qqsendChengjiAction.put("qqzaiwenSend", achievementListener);
	}

	void setReadyKey() {
		ready.setMnemonic(KeyEvent.VK_X);
		readyKey = ready.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		readyKey.put(KeyStroke.getKeyStroke("F7"), "ready");
		ActionMap readyAction = ready.getActionMap();
		readyAction.put("ready", readyListener);
	}

	void setMoreKey() {
		share.setMnemonic(KeyEvent.VK_9);
		shareKey = share.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		shareKey.put(KeyStroke.getKeyStroke("F9"), "share");
		ActionMap shareAction = share.getActionMap();
		shareAction.put("share", sharelistener);
	}

	void setChangeQQKey() {
		changeQQButton.setMnemonic(KeyEvent.VK_5);
		changeQQButtonKey = changeQQButton
				.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		changeQQButtonKey.put(KeyStroke.getKeyStroke("F5"), "change");
		ActionMap changeQQButtonAction = changeQQButton.getActionMap();
		changeQQButtonAction.put("change", changeQQwindow);
	}

	void setSetKey() {
		set.setMnemonic(KeyEvent.VK_Z);
		setKey = set.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		setKey.put(KeyStroke.getKeyStroke("F10"), "��������");
		ActionMap setAction = set.getActionMap();
		setAction.put("��������", setlistener);
	}

	// ����������������
	// ����������
	void gendajinduset() {
		setGendajindu.setJProgressBar(gendajindutiao);
	}

	// �����������
	void TableRowAddset() {
		tableAdd.init(tableM, gendaListener, table);
	}

	// �ɼ��������
	void Tableset() {
		table.setRowHeight(25);
		table.setBackground(Color.white);
		table.setEnabled(false);
		column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(1);
		JTableHeader head = table.getTableHeader();
		head.setFont(new Font("΢���ź�", 1, 15));
		head.setBackground(Color.white);

	}

	// ���ô������ֵ�����
	void setwinNameset() {
		setwinName.setQQname(qqName);
	}

	JMenuBar menubar;
	JMenu menu, base, linkbase, other, paiming, fawenmenu;
	public static JMenuItem denglu;
	JMenuItem fasongchengji;
	JMenuItem fawen;
	JMenuItem chongda;
	JMenuItem QQQzaiwen;
	JMenuItem huanqun;
	JMenuItem jqbzaiwen;
	// JMenuItem jjmu;
	JMenuItem gengduo;
	JMenuItem zxdv;
	JMenuItem bang;
	JMenuItem xiezhu;
	JMenuItem ramdom;
	JMenuItem paimingall, paimingday, paiming999;
	JMenuItem txt;
	JMenuItem historyall;
	JMenuItem friendSys;
	JMenuItem everydaysaiwen;
	JMenuItem everydaypaiming;
	JMenuItem fuwei;
	JMenuItem buildsaiwen;
	JMenuItem com;

	JMenuItem luanxu;
	JMenuItem xiayiduan;
	JMenuItem baocun;
	JMenuItem chouquxiayiduan;
	JMenuItem email;
	JMenuItem chexit;
	JMenuItem kaiG;
	JMenuItem resert;

	JMenuItem lookplay;
	JMenuItem check;
	JMenuItem EnglishNext;
	JMenuItem update;
	RankListener ranklistener = new RankListener();
	BuildChooseFile changetxt = new BuildChooseFile();
	historyListener historylistener = new historyListener();
	FriendsSysListener fsyslistenre = new FriendsSysListener();
	BuildSaiListener buildsailistener = new BuildSaiListener();
	CommunicationListener comlistener = new CommunicationListener();
	Check checklistener = new Check();

	void jsplit() {
		dazi.setDragEnabled(true);
		jSplitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, wenben1,
				dazi1);
		jSplitPane1.setBounds(10, F3.getY() + F3.getHeight() + 5, 700, 420);
		jSplitPane1.setDividerSize(5);

		// jSplitPane2 = new
		// JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jSplitPane1,tableN);
		add(jSplitPane1);
		// jSplitPane2.setBounds(10,F3.getY()+F3.getHeight()+5,700,420);
		// jSplitPane2.setDividerSize(5);
		// jSplitPane2.setDividerLocation(0.7);
		jSplitPane1.setDividerLocation(400);
		System.out.println(jSplitPane1.getDividerLocation());
		// jSplitPane1.setDividerLocation(150);
		// ce.setBounds(jSplitPane2.getX()-5,jSplitPane2.getY(),5,jSplitPane2.getHeight());

	}

	void menu() {
		menubar = new JMenuBar();
		menu = new JMenu("�˵�");
		base = new JMenu("��������");
		linkbase = new JMenu("��������");
		other = new JMenu("����");
		paiming = new JMenu("����");
		fawenmenu = new JMenu("���Ĳ���");

		paimingall = new JMenuItem("�ܸ�������");
		paimingday = new JMenuItem("�ո�������");
		paiming999 = new JMenuItem("����ƽ���ɼ�����");
		everydaypaiming = new JMenuItem("ÿ����������");

		denglu = new JMenuItem("��¼");
		fasongchengji = new JMenuItem("���ͳɼ� F1");
		fawen = new JMenuItem("����F2");
		chongda = new JMenuItem("�ش�F3");
		QQQzaiwen = new JMenuItem("���� F4");
		huanqun = new JMenuItem("��Ⱥ F5");

		xiayiduan = new JMenuItem("˳����һ��");
		baocun = new JMenuItem("����������");
		chouquxiayiduan = new JMenuItem("��ȡ��һ��");
		luanxu = new JMenuItem("�ö�����");

		jqbzaiwen = new JMenuItem("����������");
		// jjmu = new JMenuItem("��ťģʽ");
		ramdom = new JMenuItem("���һ��");

		txt = new JMenuItem("�������");
		gengduo = new JMenuItem("�������� alt+Z");
		zxdv = new JMenuItem("���߶�ս");
		bang = new JMenuItem("ʹ�ð���");
		xiezhu = new JMenuItem("Э������");
		historyall = new JMenuItem("�����¼");
		friendSys = new JMenuItem("����ϵͳ");
		everydaysaiwen = new JMenuItem("ÿ������");
		buildsaiwen = new JMenuItem("��������");
		com = new JMenuItem("����һ��");
		fuwei = new JMenuItem("��λ");
		email = new JMenuItem("������");
		chexit = new JMenuItem("������");
		kaiG = new JMenuItem("���ع���");
		resert = new JMenuItem("��λ��λ");
		update = new JMenuItem("����");
		
		lookplay = new JMenuItem("�ɼ��ύ");
		check = new JMenuItem("�������");

		EnglishNext = new JMenuItem("Ӣ����һ��");

		RamdomListener ramdomlistener = new RamdomListener();
		getDatesaiwen getsaiwen = new getDatesaiwen(this);
		Email emaillistener = new Email();
		Noexit noexit = new Noexit();
		RobotListener robotls = new RobotListener();
		returnJspListener rnjsp = new returnJspListener(this);

		fasongchengji.addActionListener(achievementListener);
		chongda.addActionListener(f3listener);
		QQQzaiwen.addActionListener(qqzaiwenListener);
		huanqun.addActionListener(changeQQwindow);
		jqbzaiwen.addActionListener(zaiwenlistener);
		// jjmu.addActionListener(morelistener);
		gengduo.addActionListener(setlistener);
		bang.addActionListener(helpListener);
		fawen.addActionListener(orActicle);
		denglu.addActionListener(login);
		zxdv.addActionListener(onlineListener);
		ramdom.addActionListener(ramdomlistener);
		paimingall.addActionListener(ranklistener);
		paimingday.addActionListener(ranklistener);
		paiming999.addActionListener(ranklistener);
		txt.addActionListener(changetxt);
		historyall.addActionListener(historylistener);
		friendSys.addActionListener(fsyslistenre);
		everydaysaiwen.addActionListener(getsaiwen);
		everydaypaiming.addActionListener(ranklistener);
		buildsaiwen.addActionListener(buildsailistener);
		com.addActionListener(comlistener);

		xiayiduan.addActionListener(Acticle.treeListener);
		baocun.addActionListener(Acticle.treeListener);
		chouquxiayiduan.addActionListener(Acticle.treeListener);
		luanxu.addActionListener(mixlistener);
		email.addActionListener(emaillistener);
		chexit.addActionListener(noexit);
		kaiG.addActionListener(robotls);
		resert.addActionListener(rnjsp);
		lookplayfinish.addActionListener(lookda);
		lookplay.addActionListener(lookplaylistener);

		resert.addActionListener(rnjsp);
		check.addActionListener(checklistener);

		update.addActionListener(new UpdateListener());
		
		EnglishNext.addActionListener(Acticle.treeListener);

		luanxu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				KeyEvent.CTRL_MASK));
		chouquxiayiduan.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				KeyEvent.CTRL_MASK));
		xiayiduan.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				KeyEvent.CTRL_MASK));
		baocun.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				KeyEvent.CTRL_MASK));
		kaiG.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
				KeyEvent.CTRL_MASK));
		lookplay.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,
				KeyEvent.CTRL_MASK));
		EnglishNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				KeyEvent.CTRL_MASK));

		jqbzaiwen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				KeyEvent.CTRL_MASK));
		fawenmenu.add(luanxu);
		fawenmenu.add(xiayiduan);
		fawenmenu.add(chouquxiayiduan);
		fawenmenu.add(EnglishNext);
		fawenmenu.add(baocun);

		paiming.add(paimingall);
		paiming.add(paimingday);
		paiming.add(paiming999);
		paiming.add(everydaypaiming);

		base.add(fasongchengji);
		base.add(fawen);
		base.add(chongda);
		base.add(QQQzaiwen);
		base.add(huanqun);
		base.add(jqbzaiwen);
		base.add(chexit);
		base.add(fawenmenu);
		base.add(lookplay);
		base.add(check);

		linkbase.add(paiming);
		linkbase.add(zxdv);
		linkbase.add(historyall);
		linkbase.add(everydaysaiwen);
		linkbase.add(email);
		linkbase.add(friendSys);
		linkbase.add(buildsaiwen);
		linkbase.add(com);

		// other.add(jjmu);
		other.add(txt);
		other.add(ramdom);

		menu.add(denglu);
		menu.add(base);
		menu.add(linkbase);
		menu.add(other);

		menu.add(bang);
		menu.add(xiezhu);
		menu.add(gengduo);
		menu.add(kaiG);
		menu.add(resert);
		menu.add(update);
		// menu.setFont(); //��������
		menubar.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menu);
		menubar.setBounds(5, 10, 45, 32);
		add(menubar);
	}

	void FlowLayout() {

		Winchg = new winchange(rightce, size, max, close, wenben, dazi,
				accept1, jSplitPane1, this, tableN, gendajindutiao);
		Winchg.start();
	}

	// ����Ϊ����ϵͳ
	void Client() {
		clientNew();
		setReadyKey();
		onlineListener.setRoomNum(reducesudu, one, two, three, four, five, six,
				seven, eight, link, breaklink, accept1, ready, score,
				communion1, this);
		online.addActionListener(onlineListener);
		rightce.addActionListener(onlineListener);
		// LINK����������
		linkListener.setClient(client);
		linkListener.setCommunion(communion);
		// LINK��ť��Ӽ�����
		link.addActionListener(linkListener);
		// breaklinklistener����������
		breakLinkListener.setClient(client);
		breakLinkListener.setAccept(communion);
		breaklink.addActionListener(breakLinkListener);
		// ѡ�񷿼䰴ť������
		roomListener.setAccept(communion);
		// Ready��ť����������
		readyListener.setAccept(communion);
		readyListener.setSocket(client);
		readyListener.setSendText(dazi);
		readyListener.setGendaLisener(gendaListener);
		// client����
		client.setDazi(dazi);
		client.setAccept(accept);
		client.setGendajindu(setGendajindu);

		clientButtonSetListener();// ��ť��Ӽ�����
		clientSetBounds();// �������
		clientAdd();// ������
	}

	void clientNew() {
		one = new JButton("һ��");
		two = new JButton("����");
		three = new JButton("����");
		four = new JButton("�ķ�");
		five = new JButton("�巿");
		six = new JButton("����");
		seven = new JButton("�߷�");
		eight = new JButton("�˷�");
		link = new JButton("����");
		breaklink = new JButton("�Ͽ�����");

		ready = new JButton("׼��");
		score = new JButton("�ȷ�");

		accept.setLineWrap(true);// �Զ�����
		accept.setFont(new Font(family, 0, 30));

		onlineListener = new OnlineOorF();
		linkListener = new LinkListener();
		breakLinkListener = new BreakLinkListener();
		roomListener = new RoomListener();
		readyListener = new ReadyListener();

		client = new battleClient();
	}

	void clientSetBounds() {
		int x = jSplitPane1.getX() + jSplitPane1.getWidth() + 5;
		accept1.setBounds(210, 460, 820, 200);
		one.setBounds(x, jSplitPane1.getY() + 30, 90, 30);
		two.setBounds(x + one.getWidth() + 5, jSplitPane1.getY() + 30, 90, 30);

		three.setBounds(x, one.getY() + one.getHeight() + 10, 90, 30);
		four.setBounds(x + three.getWidth() + 5, one.getY() + one.getHeight()
				+ 10, 90, 30);

		five.setBounds(x, three.getY() + three.getHeight() + 10, 90, 30);
		six.setBounds(x + five.getWidth() + 5, three.getY() + three.getHeight()
				+ 10, 90, 30);

		seven.setBounds(x, five.getY() + five.getHeight() + 10, 90, 30);
		eight.setBounds(x + seven.getWidth() + 5,
				five.getY() + five.getHeight() + 10, 90, 30);

		link.setBounds(x, seven.getY() + seven.getHeight() + 10, 90, 30);
		breaklink.setBounds(x + link.getWidth() + 5,
				seven.getY() + seven.getHeight() + 10, 90, 30);

		ready.setBounds(x, link.getY() + link.getHeight() + 10, 90, 30);
		score.setBounds(x + ready.getWidth() + 5,
				link.getY() + link.getHeight() + 10, 90, 30);

		communion1
				.setBounds(x, ready.getY() + ready.getHeight() + 10, 190, 200);

		reducesudu.setBounds(x,
				communion1.getY() + communion1.getHeight() + 10, 90, 30);

		one.setVisible(false);
		two.setVisible(false);
		three.setVisible(false);
		four.setVisible(false);
		five.setVisible(false);
		six.setVisible(false);
		seven.setVisible(false);
		eight.setVisible(false);
		link.setVisible(false);
		breaklink.setVisible(false);
		accept1.setVisible(false);
		ready.setVisible(false);
		score.setVisible(false);
		communion1.setVisible(false);
		accept.setEditable(false);
		communion.setEditable(false);

		communion.setLineWrap(true);
	}

	void clientButtonSetListener() {
		one.addActionListener(roomListener);
		two.addActionListener(roomListener);
		three.addActionListener(roomListener);
		four.addActionListener(roomListener);
		five.addActionListener(roomListener);
		six.addActionListener(roomListener);
		seven.addActionListener(roomListener);
		eight.addActionListener(roomListener);
		ready.addActionListener(readyListener);
		suduButton.addActionListener(setframe.cirecordlistener);
	}

	void clientAdd() {
		add(one);
		add(two);
		add(three);
		add(four);
		add(five);
		add(six);
		add(seven);
		add(eight);
		add(link);
		add(breaklink);
		add(accept1);
		add(score);
		add(ready);
		add(communion1);
	}

	ActionListener lookda = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (Pattern == 1) {
				Pattern = 2;
				System.out.println("Ӣ��");
				lookplayfinish.setText("Ӣ��");
				JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc,
						Window.fontSize, 0, 0, 0, Color.BLACK, Window.family,
						Window.rightcolor);
				JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc,
						Window.fontSize, 0, 0, 0, Color.BLACK, Window.family,
						Window.mistakecolor);
			} else if (Pattern == 0) {
				Pattern = 1;
				lookplayfinish.setText("����");
				System.out.println("����");
				JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc,
						Window.fontSize, 0, 0, 0, Color.BLACK, Window.family,
						new Color(238, 238, 238));
				JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc,
						Window.fontSize, 0, 0, 0, Color.BLACK, Window.family,
						new Color(238, 238, 238));
			} else if (Pattern == 2) {
				Pattern = 0;
				System.out.println("����");
				lookplayfinish.setText("����");
				JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc,
						Window.fontSize, 0, 0, 0, Color.BLACK, Window.family,
						Window.rightcolor);
				JTextPaneChange.createStyle("��", JTextPaneChange.styledDoc,
						Window.fontSize, 0, 0, 0, Color.BLACK, Window.family,
						Window.mistakecolor);
			}

		}
	};

	void Onsystem() {
		TrayIcon trayIcon = null;
		if (SystemTray.isSupported()) // �ж�ϵͳ�Ƿ�֧��ϵͳ����
		{
			SystemTray tray = SystemTray.getSystemTray(); // ����ϵͳ����

			Image image = Toolkit.getDefaultToolkit().getImage(
					"images\\config_3.png");// ����ͼƬ ͼƬλ���ǳ������ڵ�Ŀ¼
			ActionListener listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// ����һ������
					setVisible(true);

				}
			};
			// ���������˵�
			PopupMenu popup = new PopupMenu();// ������Ҽ����ܴ����Ĳ˵�
			MenuItem defaultItem = new MenuItem("��");
			defaultItem.addActionListener(listener);
			MenuItem change = new MenuItem("��/��");
			change.addActionListener(lookda);
			MenuItem exitItem = new MenuItem("�˳�");
			exitItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			popup.add(defaultItem);
			popup.add(change);
			popup.add(exitItem);
			trayIcon = new TrayIcon(image, "������������", popup);// ����trayIcon
			trayIcon.addActionListener(listener);// ��Сͼ����ϼ�������Ĭ�ϵľ��Ǽ���˫����
			// ���ż���������ɶ�� �ͼ�mouselistener
			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}
	}
}