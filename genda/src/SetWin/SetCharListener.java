package SetWin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SetCharListener implements ActionListener {
  public static int charsign = 0;
  public static JButton charset;
  SetCharListener(JButton space) {
    this.charset = space;
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    if (charsign == 0) {
      charsign = 1;
      charset.setText("·ûºÅÌæ»»\"ÒÑ¿ª\"");
      charset.setForeground(SetFrame.open);
    } else {
      charsign = 0;
      charset.setText("·ûºÅÌæ»»\"ÒÑ¹Ø\"");
      charset.setForeground(SetFrame.close);
    }
  }
}
