package Login;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class FriendThread extends Thread {
  public void run() {
    while (true) {
      try {
        String message = Login.in.readUTF();
        if (message.substring(0, 4).equals("ÃÌº”∫√”—")) {
          JOptionPane.showMessageDialog(new JTextArea(), message.substring(4));
        }
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
}
