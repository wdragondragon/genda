package Login;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class heartThread extends Thread{
	private DataOutputStream outputStream;
	Socket socket;
	public heartThread(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}
	public void run(){
		try {
			outputStream = new DataOutputStream(socket.getOutputStream());
			while(true){
				try {
                    Thread.sleep(60*1000);//60s发送一次心跳
                    outputStream.writeUTF("心跳");
                    outputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
