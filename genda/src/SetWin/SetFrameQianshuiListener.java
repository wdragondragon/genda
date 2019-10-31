package SetWin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class SetFrameQianshuiListener implements ActionListener {
  public static JButton qianshui1;
  public static int qianshui = 0;
  public SetFrameQianshuiListener(JButton qianshui1) {
    // TODO Auto-generated constructor stub
    this.qianshui1 = qianshui1;
  }
  @Override
  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    if (qianshui == 0) {
      qianshui = 1;
      qianshui1.setText("潜水跟打\"已开\"");
      qianshui1.setForeground(SetFrame.open);
    } else {
      qianshui = 0;
      qianshui1.setText("潜水跟打\"已关\"");
      qianshui1.setForeground(SetFrame.close);
    }
  }
}
