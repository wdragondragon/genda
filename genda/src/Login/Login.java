package Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import genda1.*;

import javax.swing.*;

import Acticle.SendWenben;
import EmailCheck.Email;

public class Login extends JFrame implements ActionListener{
	static JButton confirm;
	JButton resert;
	JButton zhuce;
	JButton wangmi;
	JButton tuichu;
	public static String banben = "°æ±¾1.706";
	public static String zxbanben = "";
	public static int port = 1230;
	public static JTextField zhanghao;
	public static JPasswordField mima;
	public static DengluListener denglulistener;
	public static ZhuceListener zhucelistener;
	Window win;
	public static Socket socket ;
	public static DataOutputStream out;
	public static DataInputStream in;
	public static int dengluSign = 0;
	JPanel p = new JPanel();
	public Login(Window win){
		this.win = win;
//		link();
		setTitle("µÇÂ¼");
		Window.dqbanben.setText(Window.dqbanben.getText()+banben);
//		com.sun.awt.AWTUtilities.setWindowOpaque(this,true);
		setBounds(win.getX()+win.getWidth()/4,win.getY()+win.getHeight()/4,255,255);
		add(p);
		p.setLayout(null);
		zhanghao = new JTextField("ÕËºÅ");
		zhanghao.setBounds(20,20,190,30);
		p.add(zhanghao);
		
		mima = new JPasswordField("ÃÜÂë");
		mima.setBounds(20,70,190,30);
		p.add(mima);
		
		
		confirm = new JButton("µÇÂ¼");
		confirm.setBounds(20,110,90,30);
		p.add(confirm);
		
		resert = new JButton("Çå¿ÕÊäÈë");
		resert.setBounds(120,110,90,30);
		p.add(resert);
		
		zhuce = new JButton("×¢²á");
		zhuce.setBounds(20,150,90,30);
		p.add(zhuce);
		
		wangmi = new JButton("Íü¼ÇÃÜÂë");
		wangmi.setBounds(120,150,90,30);
		p.add(wangmi);
		
		denglulistener = new DengluListener(this);
		confirm.addActionListener(denglulistener);
		
		zhucelistener = new ZhuceListener(this);
		zhuce.addActionListener(zhucelistener);
		
		QingkongListener qingkonglistener = new QingkongListener(mima,zhanghao);
		resert.addActionListener(qingkonglistener);
		
		ForgetListener forgetlistener = new ForgetListener();
		wangmi.addActionListener(forgetlistener);
		
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		setBounds(win.getX()+win.getWidth()/2,win.getY()+win.getHeight()/2,245,245);
		this.setVisible(true);
	}
}
