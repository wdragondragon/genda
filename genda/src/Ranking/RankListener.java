package Ranking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Login.Login;

public class RankListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    if (Login.dengluSign == 1) {
      if (e.getActionCommand() == "总跟打排名")
        new rankFrame(1).setTitle("总跟打排名");
      else if (e.getActionCommand() == "日跟打排名")
        new rankFrame(2).setTitle("日跟打排名");
      else if (e.getActionCommand() == "赛文平均成绩排名")
        new rankFrame(3).setTitle("赛文平均成绩排名");
      else if (e.getActionCommand() == "每日赛文排名") {
        new EveryDayRank();
      }
    } else {
      JOptionPane.showMessageDialog(new JTextArea(), "请先登录");
    }
  }
}