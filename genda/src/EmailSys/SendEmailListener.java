package EmailSys;

import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import keep.KeyPassword;

import Login.Login;

public class SendEmailListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("获取验证码")&&Window.Email.equals("0")){
			try {
				emailsend d = new emailsend();
				d.start();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("确定")){
			if(emailsend.str.equals(EmailFrame.yanzhengma.getText())){
				try {
					Login.in =  new DataInputStream(Login.socket.getInputStream());
					String message = "绑定邮箱"+EmailFrame.email.getText()+"%"+Login.zhanghao.getText();
					message = KeyPassword.convertMD5(message);
					Login.out.writeUTF(message);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,"绑定成功");
			}
		}
		else if(!Window.Email.equals("0")){
			JOptionPane.showMessageDialog(null,"你已绑定邮箱");
		}
	}
}