package SetWin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SetspaceListener implements ActionListener {
  public static int spacesign = 0;
  public static JButton space;

  SetspaceListener(JButton charchange) {
    this.space = charchange;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    if (spacesign == 0) {
      spacesign = 1;
      space.setText("去除空格\"已开\"");
      space.setForeground(SetFrame.open);
    } else {
      spacesign = 0;
      space.setText("去除空格\"已关\"");
      space.setForeground(SetFrame.close);
    }
  }
}
