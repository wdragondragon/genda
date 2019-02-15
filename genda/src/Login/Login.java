package Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import genda1.*;

import javax.swing.*;

public class Login extends JFrame implements ActionListener{
	static JButton confirm;
	JButton resert;
	JButton zhuce;
	JButton wangmi;
	JButton tuichu;
	public static String banben = "版本1.488";
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
	public Login(Window win){
		this.win = win;
		link();
		setTitle("登录");
		setLayout(null);
//		com.sun.awt.AWTUtilities.setWindowOpaque(this,true);
		setBounds(win.getX()+win.getWidth()/4,win.getY()+win.getHeight()/4,300,300);
		
		zhanghao = new JTextField("账号");
		zhanghao.setBounds(20,20,190,30);
		add(zhanghao);
		
		mima = new JPasswordField("密码");
		mima.setBounds(20,70,190,30);
		add(mima);
		
		confirm = new JButton("登录");
		confirm.setBounds(20,110,90,30);
		add(confirm);
		
		resert = new JButton("清空输入");
		resert.setBounds(120,110,90,30);
		add(resert);
		
		zhuce = new JButton("注册");
		zhuce.setBounds(20,150,90,30);
		add(zhuce);
		
		wangmi = new JButton("忘记密码");
		wangmi.setBounds(120,150,90,30);
		add(wangmi);
		
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
	void link(){
		try {
			socket = new Socket(Window.IP,port);
			socket.setSoTimeout(1000);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			out.writeUTF(Login.banben);
			String what = in.readUTF();
			socket.setSoTimeout(0);
			if(!what.equals("版本正确")){
				JOptionPane.showMessageDialog(new JTextArea(),what);
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://x-ws.cn/tljxz");
				System.exit(0);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JTextArea(),"连接异常");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JTextArea(),"连接异常");
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		setBounds(win.getX()+win.getWidth()/2,win.getY()+win.getHeight()/2,300,300);
		this.setVisible(true);
	}
}
