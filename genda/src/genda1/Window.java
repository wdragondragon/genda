package genda1;
import gendaClient.*;
import saiwenSys.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;


import Acticle.Acticle;
import FriendSys.FriendsSysListener;
import History.historyListener;
import Login.Login;
import QQ.ChangeQQWindow;
import RamdomOne.RamdomListener;
import Ranking.RankListener;
import Ranking.rankFrame;
import SetWin.*;
import Tips.BuildChooseFile;
import Tips.TheoryListener;
import Tips.Tips;

import java.awt.event.*;
import java.io.IOException;
import Acticle.*;
import keep.readWrite;
@SuppressWarnings("serial")
public class Window extends JFrame{
	public static String IP = "39.96.83.89";
	public static String state = "对战";//连接状态
	public static Color rightcolor = Color.gray;
	public static Color mistakecolor = Color.red;
	public static Color smacicolor  = Color.BLUE;
	public static Color emacicolor = Color.ORANGE;
	public static Color qmacicolor = new Color(128,138,135);
	public static JTextArea dazi,chengji,zaiwenText;
	public static JTextPane wenben;
	public static int fontallnum ;
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
	public static String family = "微软雅黑";
//	public static String IP = "127.0.0.1";
	Font ziti,zititip;
	JLabel qqName;
	static JLabel zishu;
	JLabel allnumber;
	public JLabel sendwen;
	JScrollPane wenben1,dazi1,chengji1,zaiwenText1,tableN;
	
	JButton F3;
	JButton zaiwen,more;
	static JButton suduButton;
	JButton KeySuduButton;
	JButton achievementButton;
	JButton QQzaiwenButton;
	JButton help;
	JButton conection;
	JButton set;
	JButton online;
	JButton Keylength;
	JButton close,max,size;
	JButton changeQQButton;
	JButton ce;
	JButton acticlebutton;
	JButton mix;
	JButton next;
	JButton save;
	JButton share;
	JButton chouqu;
	public static JButton lilunma;
	public static JButton dinglilunma;
	JTable table;
	
	
	TableColumn column = null;
	DefaultTableModel tableM;
	

	public MoreListener morelistener;
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
	
	JScrollBar JSBwenben;
	InputMap F3Key,zaiwenKey,qqzaiwenKey,sendChengji,readyKey,shareKey,changeQQButtonKey,setKey,fawenKey;

	Acticle acticle;
	Login login;
	
	TableAdd tableAdd;
	SetFrame setframe;
	ForegroundWinName setwinName;
	winchange Winchg;
	

	//以下为连接系统定义
	
	JButton link,breaklink,ready;
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
	LinkListener linkListener;//连接监视
	BreakLinkListener breakLinkListener;
	RoomListener roomListener;
	ReadyListener readyListener;
	SystemListener systemlistener;
	
	battleClient client;
	
	//全局定义
	public JSplitPane jSplitPane1;
	public JSplitPane jSplitPane2;
	public static int RoomNum = 0;
	public static boolean Linksign = true;
	static public int fontSize = 30;

	OnlineOorF onlineListener;
	int i;
	Point pressedPoint;
	Window(){
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			UIManager.put("ToolBar.isPaintPlainBackground",true);
//		} catch (ClassNotFoundException | InstantiationException
//				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
//			e1.printStackTrace();
//		}
		try {
//			JFrame.setDefaultLookAndFeelDecorated(true);
//			JDialog.setDefaultLookAndFeelDecorated(true);
//			BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
//			UIManager
//			.setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");
//			SubstanceLookAndFeel
//			.setCurrentTheme("org.jvnet.substance.theme.SubstanceAquaTheme");
			
//			UIManager.setLookAndFeel(LittleLuckLookAndFeel.class.getName());
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF(); 
			UIManager.put("RootPane.setupButtonVisible",false);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        addWindowFocusListener(new WindowFocusListener() {  
        	  
            @Override  
            public void windowGainedFocus(WindowEvent e) {}
            @Override  
            public void windowLostFocus(WindowEvent e) {  
                if(qqNamebuttonlistener.getQQGroupSign==1)
                	setwinName.setQQGroupName();
            }
        });
        //设置无边框，用鼠标控制窗体移动
    	getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
        this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { //鼠标按下事件
				pressedPoint = e.getPoint(); //记录鼠标坐标
			}
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()>=2){			//双击最大化
					if(systemlistener.MaxSign == 0){
						systemlistener.x = systemlistener.win.getX();
						systemlistener.y = systemlistener.win.getY();
						systemlistener.width = systemlistener.win.getWidth();
						systemlistener.height = systemlistener.win.getHeight();
						Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕分辨率
						System.out.println(screenSize.width+"  "+screenSize.height);
						systemlistener.win.setLocation(0, 0); 
						systemlistener.win.setSize(screenSize.width,screenSize.height); 
						systemlistener.MaxSign = 1;
					}
					else{
						systemlistener.win.setLocation(systemlistener.x, systemlistener.y); 
						systemlistener.win.setSize(systemlistener.width,systemlistener.height);
						systemlistener.x=0;systemlistener.y=0;systemlistener.width=0;systemlistener.height=0;
						systemlistener.MaxSign = 0;	
					}
				}
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) { // 鼠标拖拽事件
				Point point = e.getPoint();// 获取当前坐标
				Point locationPoint = getLocation();// 获取窗体坐标
				int x = locationPoint.x + point.x - pressedPoint.x;// 计算移动后的新坐标
				int y = locationPoint.y + point.y - pressedPoint.y;
				setLocation(x, y);// 改变窗体位置
			}
		});
		try {//读取总字数
			readWrite.readfontnum();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("读取总跟打字数失败");
		}
		setVisible(true);//设置可见
		init();//初始化
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭按钮
		dazi.setBackground(new Color(238,238,238));//设置打字框颜色
		wenben.setBackground(new Color(238,238,238));//设置文本框颜色
	}
	//window初始化
	void init(){
		setNew();//创建新对象
		setAllziti();//设置所有字体
		TrueOrFalse();//所有True False设置
		//Listener Set
		gendaListenerset();//跟打框监视器设置
		f3Listenerset();//重打设置
		zaiwenlistenerset();//剪切板载文设置
		zaiwenMouselistenerset();//载文框鼠标事件监视器设置
		achievementlistenerset();
		qqzaiwenlistenerset();
		addAllListener();//给需要添加监视器组件添加监视器
		setAllBounds();//所有Bounds属性设置
		qqsendChengjiset();//发送成绩按钮设置
		gendajinduset();//跟打进度条设置
		setlistenerset();//设置面板的设置
		setwinNameset();//设置取qq窗口名字方法
		qqNamebuttonlistenerset();//qq按钮监视器
		//setKey
		setF3Key();//给F3设置快捷键
		setQQzaiwenKey();//QQ载文快捷键
		setMoreKey();//更多快捷键
		setChangeQQKey();//换群快捷键
		setSetKey();//更多设置快捷键
		setMixKey();//乱序快捷键
		setFawenKey();//发文快捷键
		TableRowAddset();
		initText();//初始化文本框
		
		
		//文章树和成绩表格
		Tableset();
		
		addAll();//添加所有组件
		jsplit();//三栏
		Client();//客户端连接
		menu();//菜单
		FlowLayout();//布置流布局
		
		Count backConut = new Count(communion,dazi);
		backConut.start();
	}
	//给对象实例化
	void setNew(){
		setLayout(null);
		Object name[]={"","速度","击键","码长","字数","回改","退格","错字","选重","键准","打词率","时间(秒)"},a[][] = null;
		//字体大小
		ziti = new Font(family,0,fontSize);
		zititip = new Font(family,0,12);
		//字体颜色
		JTextPaneChange.creat();
		JTextPaneChange.createStyle("黑",JTextPaneChange.styledDoc,fontSize,0,0,0,Color.BLACK,family,rightcolor);
		JTextPaneChange.createStyle("红",JTextPaneChange.styledDoc,fontSize,0,0,0,Color.BLACK,family,mistakecolor);
		JTextPaneChange.createStyle("灰",JTextPaneChange.styledDoc,fontSize,0,0,0,Color.BLACK,family,mistakecolor);//GRAY
		
		JTextPaneChange.createStyle("蓝粗",JTextPaneChange.styledDoc,fontSize,1,0,0,smacicolor,family,mistakecolor);//GRAY
		JTextPaneChange.createStyle("蓝",JTextPaneChange.styledDoc,fontSize,0,0,0,smacicolor,family,mistakecolor);//GRAY
		JTextPaneChange.createStyle("蓝斜",JTextPaneChange.styledDoc,fontSize,0,1,0,smacicolor,family,mistakecolor);//GRAY
		JTextPaneChange.createStyle("蓝粗斜",JTextPaneChange.styledDoc,fontSize,1,1,0,smacicolor,family,mistakecolor);//GRAY
		JTextPaneChange.createStyle("粉粗",JTextPaneChange.styledDoc,fontSize,1,0,0,emacicolor,family,mistakecolor);//GRAY
		JTextPaneChange.createStyle("粉",JTextPaneChange.styledDoc,fontSize,0,0,0,emacicolor,family,mistakecolor);//GRAY
		JTextPaneChange.createStyle("粉斜",JTextPaneChange.styledDoc,fontSize,0,1,0,emacicolor,family,mistakecolor);//GRAY
		JTextPaneChange.createStyle("粉粗斜",JTextPaneChange.styledDoc,fontSize,1,1,0,emacicolor,family,mistakecolor);//GRAY
		
		JTextPaneChange.createStyle("绿粗",JTextPaneChange.styledDoc,fontSize,1,0,0,qmacicolor,family,mistakecolor);//GRAY
		JTextPaneChange.createStyle("绿",JTextPaneChange.styledDoc,fontSize,0,0,0,qmacicolor,family,mistakecolor);//GRAY
		JTextPaneChange.createStyle("绿斜",JTextPaneChange.styledDoc,fontSize,0,1,0,qmacicolor,family,mistakecolor);//GRAY
		JTextPaneChange.createStyle("绿粗斜",JTextPaneChange.styledDoc,fontSize,1,1,0,qmacicolor,family,mistakecolor);//GRAY
		gendajindutiao = new JProgressBar(0,0);
		
		tableM = new DefaultTableModel(a,name);
		table = new JTable(tableM);
		tableAdd = new TableAdd();
		
		wenben = new JTextPane(JTextPaneChange.styledDoc);
		
		dazi = new JTextArea()
		{
			@Override public void copy(){
				if(Window.everydaySign)
					JOptionPane.showMessageDialog(new JTextArea(),"日赛中不允许复制");}
			@Override public void paste(){
				JOptionPane.showMessageDialog(new JTextArea(),"不允许粘贴");}
		};
//		dazi = new JTextArea();
		chengji = new JTextArea();
		zaiwenText = new JTextArea();
		reducesudu = new JTextField("0");
		reducesudu.addKeyListener(		//只能输入数字
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
		
		wenben1 = new JScrollPane(wenben);
		dazi1 = new JScrollPane(dazi);
		chengji1 = new JScrollPane(chengji);
		tableN = new JScrollPane(table);
		
		qqName =  new JLabel("点击选择跟打群");
		qqName.setBorder(BorderFactory.createTitledBorder("跟打群"));
		zishu = new JLabel("字数:0/已打:0/错:0");
		zishu.setBorder(BorderFactory.createTitledBorder("字数"));
		allnumber = new JLabel("总:"+String.valueOf(Window.fontallnum)+" 对:"+String.valueOf(Window.rightnum)+" 错:"+String.valueOf(Window.misnum)+" 今:"+String.valueOf(Window.datenum));
		allnumber.setBorder(BorderFactory.createTitledBorder("跟打记录"));
		sendwen = new JLabel("发文进度");
		sendwen.setBorder(BorderFactory.createTitledBorder("发文进度"));
		tips = new JLabel("编码提示");
		tips.setBorder(BorderFactory.createTitledBorder("编码提示"));
		
		F3 = new JButton("重打");
		zaiwen = new JButton("剪切板载文");
		more = new JButton("菜单模式");
		
		suduButton = new JButton("速度");
		KeySuduButton = new JButton("击键");
		Keylength = new JButton("码长");
		lilunma = new JButton("理论码长");
		dinglilunma = new JButton("标顶理论");
		achievementButton = new JButton("生成成绩");
		QQzaiwenButton = new JButton("QQ群载文");
		help = new JButton("帮助");
		conection = new JButton("协助作者");
		set = new JButton("更多设置");
		online = new JButton("在线对战");
		close = new JButton("关");
		max = new JButton("最大化");
		size = new JButton("大小");
		changeQQButton = new JButton();
		ce = new JButton();
		acticlebutton = new JButton("发文");
		mix = new JButton("该段乱序");
		next = new JButton("下一段");
		save = new JButton("保存跟打进度");
		share = new JButton("分享发文");
		chouqu = new JButton("抽取模式发文");
		
		gendaListener = new GendaListener();//打字框监视器
		f3listener = new F3Listener();//F3按钮监视器
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
		morelistener = new MoreListener(this);
		changeQQwindow = new ChangeQQWindow(qqName);
		mixlistener = new Mix(this);
		sharelistener = new ShareListener(qqName);
		tipschange = new Tips(tips);
		theorylistener = new TheoryListener();
		
		setGendajindu = new GendaJindutiao();
		JSBwenben = wenben1.getVerticalScrollBar();
		
		setframe = new SetFrame();
		
		acticle = new Acticle(this);
		login = new Login(this);
		
		orActicle = new orActicle(acticle,this);
		next.addActionListener(Acticle.treeListener);
		save.addActionListener(Acticle.treeListener);
		chouqu.addActionListener(Acticle.treeListener);
		
		AcitiyComp acitiycomp = new AcitiyComp();
		acitiycomp.start();//速度击键码长动态变化线程
		
		ImageIcon icon=new ImageIcon("images//installer_repair_1.png");  //xxx代表图片存放路径，2.png图片名称及格式
		this.setIconImage(icon.getImage());
	}
	//添加所有组件
	void addAll(){
		add(wenben1);
//		add(dazi1);
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
		add(size);
		add(changeQQButton);
		add(Keylength);
		add(ce);
		add(acticlebutton);
		add(allnumber);
		add(mix);
		add(next);
		add(save);
		add(share);
		add(sendwen);
		add(tips);
		add(lilunma);
		add(dinglilunma);
		add(reducesudu);
		add(chouqu);
	}
	//给所有组件设置监视器
	void addAllListener(){
		(dazi.getDocument()).addDocumentListener(gendaListener);//给跟打框加监视器
		dazi.addKeyListener(gendaListener);
		F3.addActionListener(f3listener);//重打监视器
		zaiwen.addActionListener(zaiwenlistener);//剪切板载文加监视器
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
		size.addMouseMotionListener(systemlistener);
		more.addActionListener(morelistener);
//		online.addActionListener()
		changeQQButton.addActionListener(changeQQwindow);
		acticlebutton.addActionListener(orActicle);
		mix.addActionListener(mixlistener);
		share.addActionListener(sharelistener);
		lilunma.addActionListener(theorylistener);
		dinglilunma.addActionListener(theorylistener);
	}
	//所有监视器设置。。。。。。
	void gendaListenerset(){
		gendaListener.setDaziText(dazi);
		gendaListener.setChengjiText(chengji);
		gendaListener.setWenben1Text(wenben1);//设置跟打框监视器对象
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
	void f3Listenerset(){
		f3listener.setDaziText(dazi);//设置F3打字框
		f3listener.setChengjiText(chengji);
		f3listener.setZishu(zishu);
		f3listener.setWenben(wenben);
		f3listener.setGendaListener(gendaListener);
		f3listener.setJSB(JSBwenben);
		f3listener.setJProgressBar(setGendajindu);
	}

	void zaiwenlistenerset(){
		zaiwenlistener.setWenbenText(wenben);//剪切板设置文本框
		zaiwenlistener.setDaziText(dazi);//剪切板设置打字框
		zaiwenlistener.setGendaListener(gendaListener);
		zaiwenlistener.setZishu(zishu);
		zaiwenlistener.setJSB(JSBwenben);
		zaiwenlistener.setJProgressBar(setGendajindu);
	}
	void zaiwenMouselistenerset(){
		zaiwenMouselistener.setZaiwenText(zaiwenText);
	}
	void achievementlistenerset(){
		achievementListener.setGendaListener(gendaListener);
		achievementListener.setChengjiText(chengji);
		achievementListener.setDaziText(dazi);
		achievementListener.setQQName(qqName);
		achievementListener.setTable(tableAdd);
		achievementListener.setWenbenText(wenben);
	}
	void qqzaiwenlistenerset(){
		qqzaiwenListener.setDaziText(dazi);
		qqzaiwenListener.setWenbenText(wenben);
		qqzaiwenListener.setQQName(qqName);
		qqzaiwenListener.setGendaListener(gendaListener);
		qqzaiwenListener.setJProgressBar(setGendajindu);
	}
	void qqNamebuttonlistenerset(){
		qqNamebuttonlistener.setQQnameButton(qqName);
	}

	//。。。。。。。。。
	void setlistenerset(){
		setlistener.setFrame(setframe);
		setframe.setGendaListener(gendaListener);
		setframe.setWin(this);
		setframe.SetFrame1();
	}
	//设置所有组件大小
	void setAllBounds(){
		//TODO 
		//上排按钮
		F3.setBounds(57,10,0,0); //重打设置按钮大小位置 190
		suduButton.setBounds(57,F3.getY(),150,30);
		KeySuduButton.setBounds(217,F3.getY(),150,30);
		Keylength.setBounds(377,F3.getY(),150,30);
		lilunma.setBounds(537,F3.getY(),150,30);
		dinglilunma.setBounds(697,F3.getY(),150,30);
		
		zaiwen.setBounds(353,F3.getY(),0,0);//剪切版载文按钮大小位置
		more.setBounds(458,F3.getY(),0,0);//文本载文按钮大小位置
		achievementButton.setBounds(563,F3.getY(),0,0);
		QQzaiwenButton.setBounds(668, F3.getY(), 0, 0);

		set.setBounds(963,F3.getY(),0,0);//更多
		
		help.setBounds(868,F3.getY(),0,0);
		acticlebutton.setBounds(1058,F3.getY(),0,0);
		online.setBounds(773,10,0,0);
//		mix.setBounds(400,400,300,300);
	}
	//设置文本框字体
	void setAllziti(){
		wenben.setFont(ziti);
		dazi.setFont(ziti);
		chengji.setFont(new Font("微软雅黑",0,20));
		zaiwenText.setFont(ziti);
		
		qqName.setFont(zititip);
		
		zishu.setFont(zititip);
		allnumber.setFont(zititip);
		sendwen.setFont(zititip);;
		tips.setFont(zititip);
		online.setBackground(Color.white);
	}

	void TrueOrFalse(){
//		wenben.setLineWrap(true);//文本框自动换行
		dazi.setLineWrap(true);//打字框自动换行
		chengji.setLineWrap(true);//打字框自动换行
		zaiwenText.setLineWrap(true);//载文框自动换行
		wenben.setEditable(false);
		chengji.setEditable(false);//锁定文本框和成绩框不可编辑
		sendwen.setVisible(false);
//		this.setResizable(false);
	}
	//将所有文本框初始化
	void initText(){
		wenben.setText("F5换群，F4载文，F3重打，F2发文，F1发送成绩，默认自动发送成绩。");
		dazi.setText("");
		chengji.setText("");
		zaiwenText.setText("");
	}
	//设置快捷键。。。。。
	void setMixKey(){
		mix.setMnemonic(KeyEvent.VK_L);
		next.setMnemonic(KeyEvent.VK_P);
		save.setMnemonic(KeyEvent.VK_S);
		chouqu.setMnemonic(KeyEvent.VK_O);
	}
	void setFawenKey(){
		acticlebutton.setMnemonic(KeyEvent.VK_2);
		fawenKey = acticlebutton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		fawenKey.put(KeyStroke.getKeyStroke("F2"),"send");
		ActionMap fawenAction = acticlebutton.getActionMap();
		fawenAction.put("send", orActicle);
	}
	void setF3Key(){
		F3.setMnemonic(KeyEvent.VK_3);//F3按钮快捷键
		F3Key = F3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		F3Key.put(KeyStroke.getKeyStroke("F3"),"dog");
		ActionMap F3Action = F3.getActionMap();
		F3Action.put("dog", f3listener);
	}
	void setQQzaiwenKey(){
		QQzaiwenButton.setMnemonic(KeyEvent.VK_4);
		qqzaiwenKey = QQzaiwenButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		qqzaiwenKey.put(KeyStroke.getKeyStroke("F4"),"qqzaiwen");
		ActionMap qqzaiwenAction = QQzaiwenButton.getActionMap();
		qqzaiwenAction.put("qqzaiwen", qqzaiwenListener);
	}
	//qq发送成绩设置快捷键
	void qqsendChengjiset(){
		achievementButton.setMnemonic(KeyEvent.VK_1);
		sendChengji = achievementButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		sendChengji.put(KeyStroke.getKeyStroke("F1"),"qqzaiwenSend");
		ActionMap qqsendChengjiAction = achievementButton.getActionMap();
		qqsendChengjiAction.put("qqzaiwenSend",achievementListener);
	}
	void setReadyKey(){
		ready.setMnemonic(KeyEvent.VK_X);
		readyKey = ready.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		readyKey.put(KeyStroke.getKeyStroke("F7"),"ready");
		ActionMap readyAction = ready.getActionMap();
		readyAction.put("ready",readyListener);
	}
	void setMoreKey(){
		share.setMnemonic(KeyEvent.VK_9);
		shareKey = share.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		shareKey.put(KeyStroke.getKeyStroke("F9"),"share");
		ActionMap shareAction = share.getActionMap();
		shareAction.put("share",sharelistener);
	}
	void setChangeQQKey(){
		changeQQButton.setMnemonic(KeyEvent.VK_5);
		changeQQButtonKey = changeQQButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		changeQQButtonKey.put(KeyStroke.getKeyStroke("F5"),"change");
		ActionMap changeQQButtonAction = changeQQButton.getActionMap();
		changeQQButtonAction.put("change",changeQQwindow);
	}
	void setSetKey(){
		set.setMnemonic(KeyEvent.VK_Z);
		setKey = set.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		setKey.put(KeyStroke.getKeyStroke("F10"),"更多设置");
		ActionMap setAction = set.getActionMap();
		setAction.put("更多设置",setlistener);
	}
	//。。。。。。。。
	//进度条设置
	void gendajinduset(){
		setGendajindu.setJProgressBar(gendajindutiao);
	}
	//表添加行设置
	void TableRowAddset(){
		tableAdd.init(tableM,gendaListener,table);
	}
	//成绩表格设置
	void Tableset(){
		table.setRowHeight(25);
		table.setBackground(Color.white);
		table.setEnabled(false);
		column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(1);
		JTableHeader head = table.getTableHeader();
		head.setFont(new Font("微软雅黑", 1, 15));
		head.setBackground(Color.white);
		
	}
	//设置窗口名字的设置
	void setwinNameset(){
		setwinName.setQQname(qqName);
	}
	
	JMenuBar menubar;
	JMenu menu,base,linkbase,other,paiming;
	public static JMenuItem denglu;
	JMenuItem fasongchengji;
	JMenuItem fawen;
	JMenuItem chongda;
	JMenuItem QQQzaiwen;
	JMenuItem huanqun;
	JMenuItem jqbzaiwen;
	JMenuItem jjmu;
	JMenuItem gengduo;
	JMenuItem zxdv;
	JMenuItem bang;
	JMenuItem xiezhu;	
	JMenuItem ramdom;
	JMenuItem paimingall,paimingday,paiming999;
	JMenuItem txt;
	JMenuItem historyall;
	JMenuItem friendSys;
	JMenuItem everydaysaiwen;
	JMenuItem everydaypaiming;
	
	RankListener ranklistener = new RankListener();
	BuildChooseFile changetxt = new BuildChooseFile();
	historyListener historylistener = new historyListener();
	FriendsSysListener fsyslistenre = new FriendsSysListener();
	void jsplit(){
		dazi.setDragEnabled(true);
		jSplitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,wenben1,dazi1);
		jSplitPane1.setBounds(205,F3.getY()+F3.getHeight()+5,900,300);
		jSplitPane1.setDividerSize(5);
		
		
		jSplitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,jSplitPane1,tableN);
		add(jSplitPane2);
		jSplitPane2.setBounds(10,F3.getY()+F3.getHeight()+5,700,420);
		jSplitPane2.setDividerSize(5);
		jSplitPane2.setDividerLocation(0.7);
		jSplitPane1.setDividerLocation(150);
//		jSplitPane1.setDividerLocation(150);
		ce.setBounds(jSplitPane2.getX()-5,jSplitPane2.getY(),5,jSplitPane2.getHeight());
	}
	void menu(){
		menubar = new JMenuBar();
		menu = new JMenu("菜单");
		base = new JMenu("基本操作");
		linkbase = new JMenu("联网操作");
		other = new JMenu("其他");
		paiming = new JMenu("排名");
		paimingall = new JMenuItem("总跟打排名");
		paimingday = new JMenuItem("日跟打排名");
		paiming999 = new JMenuItem("赛文平均成绩排名");
		everydaypaiming = new JMenuItem("每日赛文排名");
		
		denglu = new JMenuItem("登录");
		fasongchengji = new JMenuItem("发送成绩 F1");
		fawen = new JMenuItem("发文F2");
		chongda = new JMenuItem("重打F3");
		QQQzaiwen = new JMenuItem("载文 F4");
		huanqun = new JMenuItem("换群 F5");
		jqbzaiwen = new JMenuItem("剪贴板载文");
		jjmu = new JMenuItem("按钮模式");
		ramdom = new JMenuItem("随机一文");
		
		txt = new JMenuItem("生成码表");
		gengduo = new JMenuItem("更多设置 alt+Z");
		zxdv = new JMenuItem("在线对战");
		bang = new JMenuItem("使用帮助");
		xiezhu = new JMenuItem("协助作者");
		historyall = new JMenuItem("跟打记录");
		friendSys = new JMenuItem("好友系统");
		everydaysaiwen = new JMenuItem("每日赛文");
		
		RamdomListener ramdomlistener = new RamdomListener();
		getDatesaiwen getsaiwen = new getDatesaiwen();
		
		fasongchengji.addActionListener(achievementListener);
		chongda.addActionListener(f3listener);
		QQQzaiwen.addActionListener(qqzaiwenListener);
		huanqun.addActionListener(changeQQwindow);
		jqbzaiwen.addActionListener(zaiwenlistener);
		jjmu.addActionListener(morelistener);
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
		
		linkbase.add(paiming);
		linkbase.add(zxdv);
		linkbase.add(historyall);
		linkbase.add(friendSys);
		linkbase.add(everydaysaiwen);
		
		other.add(jjmu);
		other.add(txt);
		other.add(ramdom);
		
		menu.add(denglu);
		menu.add(base);
		menu.add(linkbase);
		menu.add(other);
		
		menu.add(bang);
		menu.add(xiezhu);
		menu.add(gengduo);
//		menu.setFont(); //设置字体
		menubar.setBorder(BorderFactory.createEtchedBorder());
		menubar.add(menu);
		menubar.setBounds(10,10,45,32);
		add(menubar);
	}
	void FlowLayout(){
		
		Winchg = new winchange(ce,size,max,close,wenben,dazi,accept1,jSplitPane1,jSplitPane2,this,tableN,gendajindutiao);
		Winchg.start();
	}
	
	//以下为连接系统
	void Client(){
		clientNew();
		setReadyKey();
		onlineListener.setRoomNum(reducesudu,one,two,three,four,five,six,seven,eight,link,breaklink,accept1,ready,score,communion1,this);
		online.addActionListener(onlineListener);
		ce.addActionListener(onlineListener);
		//LINK监视器设置
		linkListener.setClient(client);
		linkListener.setCommunion(communion);
		//LINK按钮添加监视器
		link.addActionListener(linkListener);
		//breaklinklistener监视器设置
		breakLinkListener.setClient(client);
		breakLinkListener.setAccept(communion);
		breaklink.addActionListener(breakLinkListener);
		//选择房间按钮监视器
		roomListener.setAccept(communion);
		//Ready按钮监视器设置
		readyListener.setAccept(communion);
		readyListener.setSocket(client);
		readyListener.setSendText(dazi);
		readyListener.setGendaLisener(gendaListener);
		//client设置
		client.setDazi(dazi);
		client.setAccept(accept);
		client.setGendajindu(setGendajindu);
		
		clientButtonSetListener();//按钮添加监视器
		clientSetBounds();//组件设置
		clientAdd();//添加组件
	}
	void clientNew(){
		one = new JButton("一房");
		two = new JButton("二房");
		three = new JButton("三房");
		four = new JButton("四房");
		five = new JButton("五房");
		six = new JButton("六房");
		seven = new JButton("七房");
		eight = new JButton("八房");
		link = new JButton("连接");
		breaklink = new JButton("断开连接");
		
		ready = new JButton("准备");
		score = new JButton("比分");
		
		accept.setLineWrap(true);//自动换行
		accept.setFont(new Font(family,0,30));

		onlineListener = new OnlineOorF();
		linkListener = new LinkListener();
		breakLinkListener = new BreakLinkListener();
		roomListener = new RoomListener();
		readyListener = new ReadyListener();
		
		client = new battleClient();
	}
	void clientSetBounds(){
		int x = jSplitPane2.getX()+jSplitPane2.getWidth()+5;
		accept1.setBounds(210,460,820,200);
		one.setBounds(x,jSplitPane2.getY()+30,90,30);
		two.setBounds(x+one.getWidth()+5,jSplitPane2.getY()+30,90,30);
		
		three.setBounds(x,one.getY()+one.getHeight()+10,90,30);
		four.setBounds(x+three.getWidth()+5,one.getY()+one.getHeight()+10,90,30);
		
		five.setBounds(x,three.getY()+three.getHeight()+10,90,30);
		six.setBounds(x+five.getWidth()+5,three.getY()+three.getHeight()+10,90,30);
		
		seven.setBounds(x,five.getY()+five.getHeight()+10,90,30);
		eight.setBounds(x+seven.getWidth()+5,five.getY()+five.getHeight()+10,90,30);
		
		link.setBounds(x,seven.getY()+seven.getHeight()+10,90,30);
		breaklink.setBounds(x+link.getWidth()+5,seven.getY()+seven.getHeight()+10,90,30);
		
		ready.setBounds(x,link.getY()+link.getHeight()+10,90,30);
		score.setBounds(x+ready.getWidth()+5,link.getY()+link.getHeight()+10,90,30);
		
		communion1.setBounds(x,ready.getY()+ready.getHeight()+10,190,200);
		
		reducesudu.setBounds(x,communion1.getY()+communion1.getHeight()+10,90,30);
		
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
	void clientButtonSetListener(){
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
	void clientAdd(){
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
}