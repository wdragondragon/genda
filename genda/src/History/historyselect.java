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
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.TableColumn;

import Login.Login;

public class historyselect implements ActionListener {
	Socket socket;
	DataOutputStream out;
	DataInputStream in;
	String id;
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
			socket = new Socket(Window.IP,Login.port);
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
		historyFrame.clearall();
	}
	void requestsaiwen(){
		try {
			while(true){
				historyFrame.vRow1 = new Vector();
				historyFrame.vRow1.add(++historyFrame.n);
				for(int i=0;i<13;i++){
					historyFrame.vRow1.add(in.readUTF());	
				}
				id = in.readUTF();
				historyFrame.id.add(id);
				int duan = Integer.valueOf(historyFrame.vRow1.get(13).toString()); 
				if(duan==999){
					getvROW();
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
			historyFrame.aver();
			historyFrame.showtable();
			System.out.println("排名结束");
		}
	}
	void requestsudu(){
		try {
			while(true){
				historyFrame.vRow1 = new Vector();
				historyFrame.vRow1.add(++historyFrame.n);
				for(int i=0;i<13;i++){
					historyFrame.vRow1.add(in.readUTF());	
				}
				id = in.readUTF();
				historyFrame.id.add(id);
				double sudu = Double.valueOf(historyFrame.vRow1.get(2).toString());
				double suduget;
				try{
					suduget = Integer.valueOf(historyFrame.suduf.getText());
				}catch(Exception e){JOptionPane.showMessageDialog(new JTextArea(),"输入错误");return;}
				if(sudu>=Integer.valueOf(historyFrame.suduf.getText())){
					getvROW();
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
			if(historyFrame.num==0)return;
			historyFrame.aver();
			historyFrame.showtable();
			System.out.println("排名结束");
		}
	}
	void requestdate(){
		try {
			while(true){
				historyFrame.vRow1 = new Vector();
				historyFrame.vRow1.add(++historyFrame.n);
				for(int i=0;i<13;i++){
					historyFrame.vRow1.add(in.readUTF());	
				}
				id = in.readUTF();
				historyFrame.id.add(id);
				String date1 = historyFrame.vRow1.get(1).toString();
				String []a= date1.split("-");  
				System.out.println(date1);
				String date = a[0]+a[1]+a[2];
				if(date.equals(historyFrame.datef.getText())){
					getvROW();
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
			if(historyFrame.num==0){JOptionPane.showMessageDialog(new JTextArea(),"无记录");return;}
			historyFrame.aver();
			historyFrame.showtable();
			System.out.println("排名结束");
		}
	}
	void getvROW(){
		historyFrame.allhistory.add(historyFrame.vRow1);
		historyFrame.yenum++;
		historyFrame.num++;
		historyFrame.aversudu += Double.parseDouble((String) historyFrame.vRow1.get(2));
		historyFrame.averkey += Double.parseDouble((String)historyFrame.vRow1.get(3));
		historyFrame.averkeylength += Double.parseDouble((String)historyFrame.vRow1.get(4));
		historyFrame.avernumber += Double.parseDouble((String)historyFrame.vRow1.get(5));
		historyFrame.averdeletetext += Double.parseDouble((String)historyFrame.vRow1.get(6));
		historyFrame.averdelete += Double.parseDouble((String)historyFrame.vRow1.get(7));
		historyFrame.avermistake += Double.parseDouble((String)historyFrame.vRow1.get(8));
		historyFrame.averrepeat += Double.parseDouble((String)historyFrame.vRow1.get(9));
		historyFrame.averkeyaccur += Double.parseDouble((String)historyFrame.vRow1.get(10));
		historyFrame.averdacilv += Double.parseDouble((String)historyFrame.vRow1.get(11));
		historyFrame.avertime += Double.parseDouble((String)historyFrame.vRow1.get(12));
	}
}
