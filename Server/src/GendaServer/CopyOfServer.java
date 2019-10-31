package GendaServer;

import java.io.*;
import java.net.*;
import java.util.*;

/*服务器类*/
public class CopyOfServer {
  public List<Socket> socketList = new ArrayList<Socket>(); //存放所有连接的客户端的集合
  public ServerSocket server; //服务器
  public int portNum; //端口号
  public static void main(String[] args) {
    int portNum = 8888; //创建服务器的端口号
    CopyOfServer server = new CopyOfServer(portNum);
    server.innit();
  }
  public CopyOfServer(int portNum) {
    this.portNum = portNum;
  }
  public void innit() {
    try {
      server = new ServerSocket(portNum);
      System.out.println("服务器开启成功！");
      int socketNum = 0;
      while (true) {
        Socket socket = server.accept(); //被动等待客户端的连接
        socketNum++;
        System.out.println("第" + socketNum + "个客户端连接成功！！");
        socketList.add(socket); //连接的客户端存放到集合里面
        new RWThread(socket, socketNum - 1).start();
      }

    } catch (IOException e) {
      //		   e.printStackTrace();
      System.out.println("客户断开");
    }
  }
  class RWThread extends Thread { //接收和发送消息的线程
    public Socket socket;
    public int socketNum;
    PrintWriter pw;
    public RWThread(Socket socket, int sockerNum) {
      this.socket = socket;
      this.socketNum = sockerNum;
    }
    public void run() {
      super.run();
      try {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
        while (true) {
          String message = br.readLine(); //接收客户端发来的消息
          for (int i = 0; i < socketList.size(); i++) { //发送给所有连接的客户端
            if (i != socketNum) {
              pw = new PrintWriter(new OutputStreamWriter(socketList.get(i).getOutputStream()));
              pw.println(message);
              pw.flush();
            }
          }
        }
      } catch (IOException e) {
        for (int i = 0; i < socketList.size(); i++) { //发送给所有连接的客户端
          if (i != socketNum) {
            try {
              pw = new PrintWriter(
                  new OutputStreamWriter(socketList.get(i).getOutputStream(), "utf-8"));
              pw.println("第" + (socketNum + 1) + "客户"
                  + "断开");
              pw.flush();
            } catch (IOException e1) {
              System.out.println("无法向客户" + i + "发送客户" + socketNum + "断开信息");
            }
          }
        }
        System.out.println("第" + (socketNum + 1) + "客户"
            + "断开");
      }
    }
  }
}