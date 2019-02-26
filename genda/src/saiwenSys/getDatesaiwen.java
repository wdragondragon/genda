package saiwenSys;
import genda1.GendaListener;
import genda1.QQZaiwenListener;
import genda1.RegexText;
import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Acticle.SendWenben;
import Login.*;
public class getDatesaiwen implements ActionListener {
	Window win;
	public getDatesaiwen(Window win){
		this.win = win;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(SendWenben.sendwenSign==1){JOptionPane.showMessageDialog(new JTextArea(),"先结束发文");return;}
		if(Window.everydaySign){JOptionPane.showMessageDialog(new JTextArea(),"正在进行赛文");return;}
		int n = JOptionPane.showConfirmDialog(null, "在跟打每日赛文时，重打系统移除，过程中不可载入别的文章，不可退出登录，不可退出跟打器等等一切尝试二次跟打的行为，否则今日成绩归零\n请检查自身输入法调配，做好防止任何弹窗的准备，准备好了吗?点击“是”后将立即开始计时", "赛文提示", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			// ......
			try {
				Login.socket.setSoTimeout(1000);
				DataOutputStream out = new DataOutputStream(Login.socket.getOutputStream());
				DataInputStream in = new DataInputStream(Login.socket.getInputStream());
				out.writeUTF("获取今日赛文");
				String message = in.readUTF();
				if(message.equals("已打过")){
					JOptionPane.showMessageDialog(new JTextArea(),"你今天已打过赛文");return;
				}
				SendWenben.title = "拖拉机每日赛文-作者：随机生成";
				QQZaiwenListener.wenbenstr = message;
				Window.everydaySign = true;
				RegexText.duan1 = 0;
//				Window.f3listener.f3caozuo();
				Window.dazi.setEditable(false);
				CountSaiwen cs = new CountSaiwen();
				cs.start();
				win.setAlwaysOnTop(true);
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		} else if (n == JOptionPane.NO_OPTION) {
			// ......
			
		}
	}
}