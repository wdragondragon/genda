package Login;

import genda1.Window;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class heartThread extends Thread{
	private DataOutputStream outputStream;
	Socket socket;
	Date date1 = getdate(),date2 = getdate();
	public heartThread(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}
	public void run(){
		try {
			outputStream = new DataOutputStream(socket.getOutputStream());
			while(true){
                try {
					Thread.sleep(5*1000);
					outputStream.writeUTF("心跳");
		            outputStream.flush(); 
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//1s发送一次心跳
                datenum();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			 JOptionPane.showMessageDialog(new JTextArea(),"未知原因，账号下线");
			 try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 Login.dengluSign = 0;
			 Login.confirm.setText("登录");
			 Window.denglu.setText("登录");
		}
		
	}	
	void datenum(){
		date2 = date1;
		date1 = getdate();
		if(date1.toString().equals(date2.toString())){}
		else 
			Window.datenum = 0;
	}
	Date getdate(){
		int y,m,d;
		Calendar cal;
		cal=Calendar.getInstance(); 
		y=cal.get(Calendar.YEAR); 
		m=cal.get(Calendar.MONTH); 
		d=cal.get(Calendar.DATE);
		return new Date(y-1900,m,d);
	}
}
