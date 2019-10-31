package History;

import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class WritehistoryToText extends Thread {
  public void run() {
    String name[] = {"序号", "日期", "速度", "击键", "码长", "字数", "回改", "退格", "错字", "选重",
        "键准", "键法", "打词率", "时间(秒)", "段数", "内容"};
    FileOutputStream fos;
    StringBuilder all = new StringBuilder();
    int n = 0;
    try {
      Socket socket = new Socket(Window.IP, Login.Login.port);
      DataOutputStream out = new DataOutputStream(socket.getOutputStream());
      DataInputStream in = new DataInputStream(socket.getInputStream());

      out.writeUTF("全部文本记录");
      ObjectOutputStream outputToServer = new ObjectOutputStream(out);
      ObjectInputStream inputByServer = new ObjectInputStream(in);
      outputToServer.writeObject(historyFrame.id);
      @SuppressWarnings("unchecked")
      List<String> wenbenlist = (List<String>) inputByServer.readObject();
      //			System.out.println(wenbenlist);
      inputByServer.close();
      outputToServer.close();
      out.close();
      in.close();
      socket.close();

      fos = new FileOutputStream("跟打记录.txt");
      OutputStreamWriter writer;
      writer = new OutputStreamWriter(fos, "UTF-8");
      BufferedWriter bufferWriter = new BufferedWriter(writer);
      for (int i = 0; i < 16; i++) {
        all.append(name[i] + "\t");
      }
      all.append("\r\n");
      for (@SuppressWarnings("rawtypes") Vector v : historyFrame.allhistory) {
        for (int i = 0; i < 15; i++) {
          all.append(v.get(i) + "\t");
        }
        all.append(wenbenlist.get(n++) + "\r\n");
      }
      all.append("\r\n");
      bufferWriter.write(all.toString());
      bufferWriter.close();
      writer.close();
      fos.close();
      JOptionPane.showMessageDialog(new JTextArea(), "保存成功");
    } catch (IOException | ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
