package EmailCheck;

import javax.swing.*;
public class EmailCheckFrame extends JFrame {
	JButton con,confire,mimabutton;
	public static JTextField email,yanzhengma,xgmima,zhanghao;
	
	public EmailCheckFrame(){
		
		setLayout(null);
		setBounds(10,10,250,250);
		init();
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭按钮
	}
	void init(){
		con = new JButton("获取验证码");
		confire = new JButton("确定");
		email = new JTextField("绑定邮箱");
		yanzhengma = new JTextField("验证码");
		
		zhanghao = new JTextField("账号");
		xgmima = new JTextField("修改新密码");
		mimabutton = new JButton("修改");
		
		email.setBounds(20,20,120,30);
		con.setBounds(150,20,80,30);
		yanzhengma.setBounds(20,70,120,30);
		confire.setBounds(150,70,80,30);	
		
		zhanghao.setBounds(20, 120, 200, 30);
		xgmima.setBounds(20,170,120,30);
		mimabutton.setBounds(150, 170, 80, 30);
		
		SendEmailListener sendemaillistener = new SendEmailListener();
		con.addActionListener(sendemaillistener);
		confire.addActionListener(sendemaillistener);
		mimabutton.addActionListener(sendemaillistener);
		add(con);
		add(confire);
		add(email);
		add(yanzhengma);
		add(zhanghao);
		add(xgmima);
		add(mimabutton);
	}
}
