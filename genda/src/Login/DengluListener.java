package Login;

import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import keep.readWrite;

public class DengluListener implements ActionListener{
	Login login;
	heartThread heartThread;
	int num[] = {0,0,0,0,0};
	DengluListener(Login login){
		this.login = login;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		denglu();
	}
	public void denglu(){
		if(Login.dengluSign==0){
			try{
				Login.socket.setSoTimeout(500);
				Login.out = new DataOutputStream(Login.socket.getOutputStream());
				Login.in =  new DataInputStream(Login.socket.getInputStream());
				String message = "%1%"+Login.zhanghao.getText()+"%"+Login.mima.getText()+"%-999"+"%无"+"%无"+"%无";
				Login.out.writeUTF(message);
				message = Login.in.readUTF();
				System.out.println(message);
				int pos = 0;
				for(int i=0;i<5;i++){
					pos =  message.indexOf('%',pos)+1;
					if(pos!=-1)
						num[i] = pos;
					else break;
				}
				int i = Integer.parseInt(message.substring(num[0],num[1]-1));  	//接受登录结果
				
				int zishu = Integer.parseInt(message.substring(num[1],num[2]-1));		//接受数据字数
				int rightnum = Integer.parseInt(message.substring(num[2],num[3]-1));
				int misnum = Integer.parseInt(message.substring(num[3],num[4]-1));
				int datenum = Integer.parseInt(message.substring(num[4]));
				
				Login.socket.setSoTimeout(0);
				if(i==1){
					Login.dengluSign = 1;
					Window.fontallnum = zishu;
					Window.rightnum = rightnum;
					Window.misnum = misnum;
					Window.datenum = datenum;
//					JOptionPane.showMessageDialog(new JTextArea(),"登录成功");
					Login.confirm.setText("退出登录");
					Window.denglu.setText("欢迎："+Login.zhanghao.getText());
					Window.wenben.setText("登录成功"+"\n"+"欢迎："+Login.zhanghao.getText());
					Login.zhanghao.setEditable(false);
					Login.mima.setEditable(false);
					readWrite.setzm();
					login.setVisible(false);
					
					heartThread = new heartThread(Login.socket);//心跳包发送
					heartThread.start();
				}
				else if(i==2)
					JOptionPane.showMessageDialog(new JTextArea(),"密码或账号错误");
			}
			catch(Exception e1){JOptionPane.showMessageDialog(new JTextArea(),"请检查网络2");}
		}
		else if(Login.dengluSign==1){
			try {
				Login.out.writeUTF("断开");
				Login.socket = new Socket(Window.IP,Login.port);
				Login.out = new DataOutputStream(Login.socket.getOutputStream());
				Login.out.writeUTF(Login.banben);
				Login.in = new DataInputStream(Login.socket.getInputStream());
				String what = Login.in.readUTF();
				if(!what.equals("版本正确"))System.exit(0);
				JOptionPane.showMessageDialog(new JTextArea(),"退出登录成功");
				Login.zhanghao.setEditable(true);
				Login.mima.setEditable(true);
				Login.confirm.setText("登录");
				Window.denglu.setText("登录");
				Login.dengluSign = 0;
				
				
				heartThread.stop();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(new JTextArea(),"请检查网络1");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(new JTextArea(),"请检查网络1");
			}
		}
		else{
			JOptionPane.showMessageDialog(new JTextArea(),"你已登录");
		}
	}
}
