package SetWin;
import genda1.*;

import java.awt.event.*;
import javax.swing.*;

public class SetFrameKeyboardRecordListener implements ActionListener {
  JTextArea Keyboarddisplay;
  JTextArea keymistakedisplay;
  GendaListener gendalistener;
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    if (e.getActionCommand() == "提取本次跟打击键记录")
      Keyboarddisplay.setText(gendalistener.record);
    else if (e.getActionCommand() == "提取本次跟打错字记录") {
      Keyboarddisplay.setText("");
      for (int i = 0; i < gendalistener.mistakelist.size(); i++)
        Keyboarddisplay.append(gendalistener.mistakelist.get(i));
    }
  }
  public void setKeyboarddisplay(JTextArea t) {
    Keyboarddisplay = t;
  }
  public void setGendaListener(GendaListener t) {
    gendalistener = t;
  }
  public void setKeymistakedisplay(JTextArea t) {
    // TODO Auto-generated method stub
    keymistakedisplay = t;
  }
}
