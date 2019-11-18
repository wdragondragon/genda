package History;

import genda1.Window;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class historythis extends JFrame{
	historythis(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(10,10,850,screenSize.height*3/4);
		init();
		setVisible(true);
//		setTitle("跟打记录");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭按钮
	}
	void init (){
		add(Window.tableN);
	}
}
