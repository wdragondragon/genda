package Login;


import genda1.Example;
import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.net.Socket;


import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import keep.KeyPassword;
import keep.readWrite;


public class DengluListener implements ActionListener{
	Login login;
	heartThread heartThread;
	int num[] = {0,0,0,0,0,0};
	DengluListener(Login login){
		this.login = login;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		denglu();
	}
	public void denglu() {
		if (Login.dengluSign == 0) {
			try {
				Login.socket = new Socket(Window.IP, Login.port);
				
				Login.socket.setSoTimeout(500);
				Login.out = new DataOutputStream(Login.socket.getOutputStream());
				Login.in = new DataInputStream(Login.socket.getInputStream());
//				ObjectOutputStream outputToServer = new ObjectOutputStream(Login.out);
//				ObjectInputStream inputByServer = new ObjectInputStream(Login.in);
				Login.out.writeUTF(Login.banben);
				String what = Login.in.readUTF();
				Login.socket.setSoTimeout(0);
				if(!what.substring(0,4).equals("版本正确")){
					UIManager.put("OptionPane.yesButtonText", "自动更新");
					UIManager.put("OptionPane.noButtonText", "手动下载");
					int n = JOptionPane.showConfirmDialog(null, what, "更新提示", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						// ......
						if(Example.systemname.length()>=7&&Example.systemname.substring(0,7).equals("Windows"))
							Runtime.getRuntime().exec("更新.exe");
						else
							Runtime.getRuntime().exec("java -jar update.jar");
						System.exit(0);
					} else if (n == JOptionPane.NO_OPTION) {
						// ......
						Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://39.96.83.89/new.zip");
						return;
					}
				}
				Login.zxbanben = what.substring(what.indexOf("最新版本")+4);
				Window.zxbanben.setText(Window.zxbanben.getText()+Login.zxbanben);
				
				String message = "%1%" + Login.zhanghao.getText() + "%" + Login.mima.getText() + "%-999" + "%无" + "%无" + "%无";
				message = KeyPassword.convertMD5(message);
				Login.out.writeUTF(message);
				message = Login.in.readUTF();
				System.out.println(message);
				int pos = 0;
				for (int i = 0; i < 6; i++) {
					pos = message.indexOf('%', pos) + 1;
					if (pos != -1)
						num[i] = pos;
					else break;
				}
				int i = Integer.parseInt(message.substring(num[0], num[1] - 1));    //接受登录结果

				int zishu = Integer.parseInt(message.substring(num[1], num[2] - 1));        //接受数据字数
				int rightnum = Integer.parseInt(message.substring(num[2], num[3] - 1));
				int misnum = Integer.parseInt(message.substring(num[3], num[4] - 1));
				int datenum = Integer.parseInt(message.substring(num[4], num[5] - 1));
				Window.Email = message.substring(num[5]);
				Login.socket.setSoTimeout(0);
				if (i == 1) {
					Login.dengluSign = 1;
					Window.fontallnum = zishu;
					Window.rightnum = rightnum;
					Window.misnum = misnum;
					Window.datenum = datenum;
//					JOptionPane.showMessageDialog(new JTextArea(),"登录成功");
					Login.confirm.setText("退出登录");
					Window.denglu.setText("欢迎：" + Login.zhanghao.getText());
					Window.wenben.setText("登录成功" + "\n" + "欢迎：" + Login.zhanghao.getText() + "\n拖拉机交流群:974172771");
//					SendQQMessage.sendmessage(Login.zhanghao.getText() + "已上线，（上线消息只会发送到拖拉机交流群）");
					Login.zhanghao.setEditable(false);
					Login.mima.setEditable(false);
					readWrite.setzm();
					login.setVisible(false);

					Window.everydaySign = false;

					heartThread = new heartThread(this);//心跳包发送
					heartThread.start();
				} else if (i == 2)
					JOptionPane.showMessageDialog(new JTextArea(), "密码错误");
				else if (i == 3)
					JOptionPane.showMessageDialog(new JTextArea(), "账户不存在");
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(new JTextArea(), "请检查网络2");
			}
		} else if (Login.dengluSign == 1) {
			try {
				Login.out.writeUTF("断开");
				Login.zhanghao.setEditable(true);
				Login.mima.setEditable(true);
				Login.confirm.setText("登录");
				Window.denglu.setText("登录");
				Login.dengluSign = 0;
				Login.in.close();
				Login.out.close();
				Login.socket.close();
				heartThread.stop();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(new JTextArea(), "请检查网络1");
			}
		} else {
			JOptionPane.showMessageDialog(new JTextArea(), "你已登录");
		}
	}
}
