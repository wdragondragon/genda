package genda1;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import keep.readWrite;
import Login.*;
import Ranking.rankFrame;
public class Example{
	public static void main(String args[]){
		int readsign;
		Window win = new Window();
		win.setTitle("跟打");
		win.setBounds(100,100,710,515);
		
		try {
			readWrite.read(win);//读取保存的设置
		} catch (IOException e1) {System.out.println("读取设置失败");}
		String message = "以后更新时，外部配置jre,set不需要再替换，只需要替换内部的《拖拉机1.xx》文件夹\n登录后会覆盖掉本机原有字数\n如需恢复本机字数，联系作者\n欢迎使用，拖拉机帮助群974172771\n鸡龙";
		try{
			if(readWrite.readsign()==0){
				JOptionPane.showMessageDialog(new JTextArea(),message);
//				JOptionPane.showMessageDialog(new JTextArea(),update);
				readWrite.setreadsign();
			}
		}catch(IOException e2){System.out.println("读取文件提示失败");}
		try{
			readWrite.readzm();
			Login.denglulistener.denglu();
		}
		catch(Exception e){System.out.println("读取账号密码失败");}
	}
}