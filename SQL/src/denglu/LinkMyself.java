package denglu;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LinkMyself extends Thread{
	Socket socket;
	public void run(){
		try {
			while(true){
				try {
					sleep(3600*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Recordnum.con.close();
					System.out.println("数据库重开");
					Recordnum.con = DriverManager.getConnection(Recordnum.url,Recordnum.user,Recordnum.pass);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				socket = new Socket("127.0.0.1",Recordnum.port);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//				out.writeUTF("自连");
				socket.close();
				socket = null;
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