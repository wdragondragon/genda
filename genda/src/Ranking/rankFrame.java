package Ranking;

import genda1.Window;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class rankFrame extends JFrame {
	JTable table;
	DefaultTableModel tableM;
	JScrollPane tableN;
	Socket socket;
//	JPanel p = new JPanel();
	int i=0;
//	public static void main(String args[]){
//		new rankFrame();
//	}
	public rankFrame(int i){
		this.i = i;
		init();
//		p.setLayout(null);
//		add(p);
//		setLayout(new FlowLayout());
		setBounds(10,10,700,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭按钮
		
//		setResizable(false);
	}
	void init(){
		
//		add(p);
		Object name[]={"","名字","总字数","正确字数","错误字数","今日跟打","赛文等级","在线情况","最后登录","注册日期"},a[][] = null;
		tableM = new DefaultTableModel(a,name){
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
		};
		table = new JTable(tableM);
		tableN = new JScrollPane(table);
//		table.setEnabled(false);
		tableN.setBounds(0,0,695,470);
		add(tableN);
		request();
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
	}
	void request(){
		try {
			socket = new Socket(Window.IP,Login.Login.port);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			socket.setSoTimeout(2000);
			if(i==1)
				out.writeUTF("排名1");
			else if(i==2)
				out.writeUTF("排名2");
			else if(i==3);
				out.writeUTF("排名3");
			int n =0;
			while(true){
				Vector vRow1 = new Vector();
				vRow1.add(++n);
				for(int i=0;i<5;i++){
					vRow1.add(in.readUTF());
				}
				Double aversudu = Double.parseDouble(in.readUTF());
				String duanwei = "";
				if(i!=3){
					switch ((int)(aversudu/10))
					{
					    case 0:
					    case 1:
					    case 2:duanwei = "一级";break;
					    case 3:duanwei = "二级";break;
					    case 4:duanwei = "三级";break;
					    case 5:duanwei = "四级";break;
					    case 6:
					    case 7:duanwei = "五级";break;
					    case 8:duanwei = "六级";break;
					    case 9:duanwei = "七级";break;
					    case 10:duanwei = "八级";break;
					    case 11:duanwei = "九级";break;
					    case 12:duanwei = "一段";break;
					    case 13:duanwei = "二段";break;
					    case 14:duanwei = "三段";break;
					    case 15:duanwei = "四段";break;
					    case 16:duanwei = "五段";break;
					    case 17:duanwei = "六段";break;
					    case 18:duanwei = "七段";break;
					    case 19:duanwei = "八段";break;
					    case 20:duanwei = "九段";break;
					    case 21:duanwei = "一星";break;
					    case 22:duanwei = "二星";break;
					    case 23:duanwei = "三星";break;
					    case 24:duanwei = "四星";break;
					    case 25:duanwei = "五星";break;
					    case 26:duanwei = "六星";break;
					    case 27:duanwei = "七星";break;
					    case 28:duanwei = "八星";break;
					    case 29:duanwei = "九星";break;
					    case 30:duanwei = "一重";break;
					    case 31:duanwei = "二重";break;
					    case 32:duanwei = "三重";break;
					    case 33:duanwei = "四重";break;
					    case 34:duanwei = "五重";break;
					    case 35:duanwei = "六重";break;
					    case 36:duanwei = "七重";break;
					    case 37:duanwei = "八重";break;
					    case 38:duanwei = "九重";break;
					    case 39:duanwei = "十重";break;
					    case 40:
					    case 41:
					    case 42:
					    case 43:
					    case 44:duanwei = "地手";break;
					    case 45:
					    case 46:
					    case 47:
					    case 48:
					    case 49:duanwei = "天手";break;
					    default:duanwei = "极手";break;
					}
					vRow1.add(duanwei);
				}
				else
					vRow1.add(aversudu);
				if(in.readUTF().equals("1"))
					vRow1.add("在线");
				else
					vRow1.add("不在线");
				vRow1.add(in.readUTF());
				vRow1.add(in.readUTF());
				tableM.addRow(vRow1);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("排名结束");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("排名结束");
		}
	}
}
