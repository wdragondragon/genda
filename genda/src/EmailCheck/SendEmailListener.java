package EmailCheck;

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
    if (e.getActionCommand().equals("获取验证码")) {
      try {
        String message =
            "验证邮箱" + EmailCheckFrame.zhanghao.getText() + "%" + EmailCheckFrame.email.getText();
        message = KeyPassword.convertMD5(message);
        Login.out.writeUTF(message);
        if (Login.in.readUTF().equals("验证成功")) {
          if (Window.Email.equals(EmailCheckFrame.email.getText())) {
            emailchecksend d = new emailchecksend();
            d.start();
          }
          JOptionPane.showMessageDialog(null, "已发送");
        } else {
          JOptionPane.showMessageDialog(null, "该邮箱不是账号绑定邮箱");
        }

      } catch (Exception ex) {
        // TODO Auto-generated catch block
        ex.printStackTrace();
      }
    } else if (e.getActionCommand().equals("确定")) {
      if (emailchecksend.str.equals(EmailCheckFrame.yanzhengma.getText())) {
        JOptionPane.showMessageDialog(null, "验证码正确");
      }
    } else if (e.getActionCommand().equals("修改")) {
      //			if(EmailCheckFrame.zhanghao)
      if (emailchecksend.str.equals(EmailCheckFrame.yanzhengma.getText())) {
        try {
          Login.in = new DataInputStream(Login.socket.getInputStream());
          String message = "修改密码" + EmailCheckFrame.zhanghao.getText() + "%"
              + EmailCheckFrame.xgmima.getText();
          message = KeyPassword.convertMD5(message);
          Login.out.writeUTF(message);
          if (Login.in.readUTF().equals("修改成功"))
            JOptionPane.showMessageDialog(null, "修改密码成功");
          else
            JOptionPane.showMessageDialog(null, "修改密码失败");
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    }
  }
}