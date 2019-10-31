package genda1;

import gendaClient.*;
import Acticle.SendWenben;
import Login.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import QQ.*;
import SetWin.*;

public class QQZaiwenListener extends AbstractAction {
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  JTextArea dazi;
  JTextPane wenben;
  JLabel qqName;
  GendaListener gendalistener;
  GendaJindutiao gendajindu;
  SetFrameJinduListener setframejinduListener;
  int i;
  public static int duan;
  public static String str, dvbi;
  public static String wenbenstr = "";
  public static boolean zaiwenSign = false;
  QQ qq = new QQ();
  static RegexText regexText = new RegexText();

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    if (SendWenben.sendwenSign == 1) {
      JOptionPane.showMessageDialog(new JTextArea(), "先结束发文");
      return;
    }
    if (Window.everydaySign) {
      JOptionPane.showMessageDialog(new JTextArea(), "请先结束每日赛文");
      return;
    }
    try {
      if (Example.systemname.substring(0, 7).equals("Windows"))
        QQ.sendMessage(1, qqName.getText());
    } catch (Exception ex) {
      // TODO Auto-generated catch block
      System.out.println("载文失败");
      // ex.printStackTrace();
    }
    zaiwenSign = true;
  }

  public void setDaziText(JTextArea t) {
    dazi = t;
  }

  public void setWenbenText(JTextPane t) {
    wenben = t;
  }

  public void setQQName(JLabel qqName2) {
    qqName = qqName2;
  }

  public void setGendaListener(GendaListener t) {
    gendalistener = t;
  }

  public void setJProgressBar(GendaJindutiao t) {
    gendajindu = t;
  }
}
