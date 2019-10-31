package genda1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class returnJspListener implements ActionListener {
  Window win;
  returnJspListener(Window win) {
    this.win = win;
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    win.jSplitPane1.setBounds(5, win.suduButton.getY() + win.suduButton.getHeight() + 5,
        win.getWidth() - 5, win.getHeight() - 95);
  }
}
