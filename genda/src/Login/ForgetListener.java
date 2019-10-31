package Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import EmailCheck.Email;
import EmailCheck.EmailCheckFrame;

public class ForgetListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub

    new EmailCheckFrame().setTitle("修改密码");

    // JOptionPane.showMessageDialog(new JTextArea(),"无自助改密\n点击协助作者来联系作者");
  }
}
