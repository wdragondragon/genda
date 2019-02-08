package History;

import genda1.Window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JColorChooser;
import javax.swing.table.TableColumn;

import Login.Login;

public class historyselect implements ActionListener {
	Socket socket;
	DataOutputStream out;
	DataInputStream in;
	Vector vRow1;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		lianjie();
		if(e.getActionCommand()=="筛选日期"){
			requestdate();
		}
		else if(e.getActionCommand()=="筛选速度"){
			requestsudu();
		}
		else if(e.getActionCommand()=="筛选赛文成绩"){
			requestsaiwen();
		}
		historyFrame.yenum = historyFrame.yenum++/historyFrame.fenye;
		historyFrame.yemashow.setText(String.valueOf(historyFrame.dangqian+1)+"/"+String.valueOf(historyFrame.yenum+1));
	}
	void lianjie(){
		try{
			socket = new Socket(Window.IP,1232);
			socket.setSoTimeout(100);
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
		}catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println("跟打记录网络错误1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("跟打记录网络错误2");
		}
		try {
			out.writeUTF("跟打记录");
			out.writeUTF(Login.zhanghao.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		historyFrame.id.clear();
		historyFrame.allhistory.clear();
		historyFrame.clear();
		historyFrame.yenum = 0;
		historyFrame.dangqian = 0;
	}
	void requestsaiwen(){
		try {
			int n =0;
			while(true){
				vRow1 = new Vector();
				vRow1.add(++n);
				for(int i=0;i<13;i++){
					vRow1.add(in.readUTF());	
				}
				String id = in.readUTF();
				int duan = Integer.valueOf(vRow1.get(13).toString()); 
				if(duan==999){
					historyFrame.id.add(id);
					historyFrame.allhistory.add(vRow1);
					historyFrame.yenum++;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			historyFrame.showtable();
			System.out.println("排名结束");
		}
	}
	void requestsudu(){
		try {
			int n =0;
			while(true){
				vRow1 = new Vector();
				vRow1.add(++n);
				for(int i=0;i<13;i++){
					vRow1.add(in.readUTF());	
				}
				String id = in.readUTF();
				double sudu = Double.valueOf(vRow1.get(2).toString()); 
				if(sudu>=Integer.valueOf(historyFrame.suduf.getText())){
					historyFrame.id.add(id);
					historyFrame.allhistory.add(vRow1);
					historyFrame.yenum++;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			historyFrame.showtable();
			System.out.println("排名结束");
		}
	}
	void requestdate(){
		try {
			int n =0;
			while(true){
				vRow1 = new Vector();
				vRow1.add(++n);
				for(int i=0;i<13;i++){
					vRow1.add(in.readUTF());	
				}
				String id = in.readUTF();
				String date1 = vRow1.get(1).toString();
				String []a= date1.split("-");  
				System.out.println(date1);
				String date = a[0]+a[1]+a[2];
				if(date.equals(historyFrame.datef.getText())){
					historyFrame.id.add(id);
					historyFrame.allhistory.add(vRow1);
					historyFrame.yenum++;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			historyFrame.showtable();
			System.out.println("排名结束");
		}
	}
	
}
