package Tips;


import genda1.Window;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

public class LookChange extends JFrame{
	JTextArea look = new JTextArea();
	public static JLabel jindu = new JLabel();
	LookChange(){
		init();
		setVisible(true);
		setTitle("ÕýÔÚ×ª»»");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100,100,200,150);
	}
	void init(){
		setLayout(new FlowLayout());
		jindu.setFont(new Font(Window.family,0,Window.fontSize));
		add(jindu);
	}
}
