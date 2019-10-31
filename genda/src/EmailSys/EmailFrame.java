package EmailSys;

import javax.swing.*;

public class EmailFrame extends JFrame {
  JButton con, confire;
  public static JTextField email, yanzhengma;

  EmailFrame() {
    setLayout(null);
    setBounds(10, 10, 250, 150);
    init();
    setVisible(true);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //设置关闭按钮
  }
  void init() {
    con = new JButton("获取验证码");
    confire = new JButton("确定");
    email = new JTextField("邮箱号码");
    yanzhengma = new JTextField("验证码");

    email.setBounds(20, 20, 120, 30);
    con.setBounds(150, 20, 80, 30);
    yanzhengma.setBounds(20, 70, 120, 30);
    confire.setBounds(150, 70, 80, 30);

    SendEmailListener sendemaillistener = new SendEmailListener();
    con.addActionListener(sendemaillistener);
    confire.addActionListener(sendemaillistener);
    add(con);
    add(confire);
    add(email);
    add(yanzhengma);
  }
}
