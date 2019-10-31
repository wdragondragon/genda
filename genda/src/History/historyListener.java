package History;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class historyListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    if (e.getActionCommand().equals("跟打记录"))
      if (Login.Login.dengluSign == 1)
        new historyFrame().setTitle("总跟打记录");
      else
        JOptionPane.showMessageDialog(new JTextArea(), "请先登录");
    else
      new historythis().setTitle("本次跟打记录");
  }
}
