package Login;

import genda1.Window;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import keep.KeyPassword;
import keep.readWrite;

public class heartThread extends Thread{
	DengluListener denglu;
	Date date1 = getdate(),date2 = getdate();
	public heartThread(DengluListener denglu){
		this.denglu = denglu;
	}
	public void run(){
		try {
			while(true){
            	Thread.sleep(5*1000);
            	datenum();
            	String message = KeyPassword.convertMD5("心跳");
            	System.out.println("心跳");
				Login.out.writeUTF(message); 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(Login.dengluSign==1){
				 try {
				 	Login.socket.close();
				 } catch (IOException e1) {
				 	// TODO Auto-generated catch block
				 	e1.printStackTrace();
				 }
				 Login.dengluSign = 0;
				 Login.confirm.setText("登录");
				 Window.denglu.setText("登录");
				 int n = JOptionPane.showConfirmDialog(null, "未知原因，账号下线，是否重新登录", "掉线提示", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						denglu.denglu();
					}
			 }
		}//1s发送一次心跳
	}
	void datenum(){
		date2 = date1;
		date1 = getdate();
		if(date1.toString().equals(date2.toString())){}
		else{
			Window.datenum = 0;
			try {
				RecordChange.recordChange();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
