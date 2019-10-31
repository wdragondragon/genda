package gendaClient;
import genda1.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

public class BreakLinkListener implements ActionListener {
  battleClient client;
  JTextArea accept;

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    try {
      if (!Window.Linksign) {
        try {
          client.socket.close();
          Window.Linksign = client.socket.isClosed();
        } catch (Exception ex) {
        } finally {
          accept.append("已断开\n");
          //					ReadyListener.ReadyDuan = 1;
        }
      } else {
        accept.append("已断开,请勿重复断开\n");
        Window.Linksign = client.socket.isClosed();
      }
    } catch (Exception ex) {
      accept.setText("请先连接任意房间再选择断开\n");
    }
  }
  public void setClient(battleClient client) {
    this.client = client;
    //		this.client.StratClient();
  }
  public void setAccept(JTextArea t) {
    accept = t;
  }
}
