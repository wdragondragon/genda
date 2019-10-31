package gendaClient;
import genda1.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.swing.JTextArea;

public class LinkListener implements ActionListener {
  battleClient client;
  JTextArea communion;
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    try {
      if (Window.Linksign) {
        if (Window.state.equals("对战")) {
          client.StratClient();
          client.readThread.start();
          DataOutputStream out = new DataOutputStream(client.socket.getOutputStream());
          out.writeUTF(Window.state);
          communion.append("已连接到" + Window.RoomNum + "号房\n等待对方连接\n");
          Window.Linksign = battleClient.socket.isClosed();
          //					ReadyListener.ReadyDuan = 1;
        } else if (Window.state.equals("观战")) {
        }
      } else {
        communion.append("已连接" + Window.state + ",请勿重复连接\n");
        Window.Linksign = client.socket.isClosed();
      }

    } catch (Exception ex) {
      communion.setText("请先选择房间，再进行连接\n");
    }
  }
  public void setClient(battleClient client) {
    this.client = client;
    //		this.client.StratClient();
  }
  public void setCommunion(JTextArea t) {
    communion = t;
  }
}