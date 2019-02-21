package genda1;

import javax.swing.*;

import java.awt.event.*;
public class HelpDialog implements ActionListener{
	JTextArea a = new JTextArea();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(a,
				"F1对文本框内的群发送成绩(在线对战时向服务其提交成绩)\n"
				+"F2选择本地发文\n"
				+"F3重打\n"
				+"F4载文\n"
				+"F5换群\n"
				+"F7在线对战准备\n"
				+"F9分享发文\n"
				+"alt+L乱序功能\n"
				+"alt+P发文中发送下一段\n"
				+"alt+O随机抽取发文下一段\n"
				+"alt+S保存跟打进度\n"
				+"alt+X在线跟打准备\n"
				+"alt+Z简洁模式下进入设置\n"
				+"\n颜色提示\n"
				+"黄色二简，橘色三简，灰色全四码,斜体多选\n"
				+"\n在线跟打步骤：\n"
				+"打开在线跟打之后，选择一个房间进入，等待对方进入，提示谁为房主\n"
				+"房主载文之后，双方准备，系统框中有提示对方是否准备,\n"
				+"在对方与你都已经准备，进入倒数，\n"
				+"倒数结束之后就可以开始跟打\n"
				+"跟打结束之后，必须F1提交成绩，否则无法计分\n"
				+"注，在线对战时跟打段数并不与载文段数一致，但双方的段数是一致的\n段数代表已对战的局数\n"
				+"\n\t鸡龙出品"
				,"使用帮助",JOptionPane.PLAIN_MESSAGE);
		
	}
}