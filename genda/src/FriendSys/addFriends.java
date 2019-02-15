package FriendSys;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Login.Login;

public class addFriends implements ActionListener{
	JTextField addfriendname;
	public addFriends(JTextField addfriendname) {
		// TODO Auto-generated constructor stub
		this.addfriendname = addfriendname;
	}
	DataOutputStream out;
	DataInputStream in;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try{
			out = new DataOutputStream(Login.socket.getOutputStream());
			in = new DataInputStream(Login.socket.getInputStream());
		}catch (UnknownHostException e) {
			System.out.println("跟打记录网络错误1");
		} catch (IOException e) {
			System.out.println("跟打记录网络错误2");
		}
		try {
			out.writeUTF("添加好友");
			out.writeUTF(addfriendname.getText());
			String message = in.readUTF();
			if(message.equals("不在线"))
				JOptionPane.showMessageDialog(new JTextArea(),"对方不在线");
			else if(message.equals("不存在"))
				JOptionPane.showMessageDialog(new JTextArea(),"没有该用户");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
