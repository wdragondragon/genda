package GendaServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class LinkMyself extends Thread {
  Socket socket;
  int[] port;
  LinkMyself(int[] port) {
    this.port = port;
  }
  public void run() {
    try {
      while (true) {
        try {
          sleep(3600 * 1000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        for (int i = 0; i < port.length; i++) {
          socket = new Socket("127.0.0.1", port[i]);
          DataOutputStream out = new DataOutputStream(socket.getOutputStream());
          //					out.writeUTF("вта╛");
          socket.close();
          socket = null;
        }
      }
    } catch (UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
