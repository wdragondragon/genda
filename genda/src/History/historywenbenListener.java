package History;

import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class historywenbenListener implements ActionListener {
	JTextField lookcow;
	String a;
	DataOutputStream out;
	DataInputStream in;
	ShowWen sw = new ShowWen();
	historywenbenListener(JTextField lookcow){
		this.lookcow = lookcow;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int lookrownum = 0;
		try{
		lookrownum = Integer.parseInt(historyFrame.id.get(Integer.parseInt(lookcow.getText())-1));
		System.out.println(lookrownum);
		}catch(Exception e){	JOptionPane.showMessageDialog(new JTextArea(),"输入错误");return;}
		try{
			Socket socket = new Socket(Window.IP,Login.Login.port);
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			out.writeUTF("内容%"+String.valueOf(lookrownum));
			String wen = in.readUTF();
			System.out.println(wen);
			socket = null;
			sw.setVisible(true);
			sw.showwen(wen);
		}
		catch(Exception e){System.out.println("发送获取内容失败");e.printStackTrace();}
	}
}
