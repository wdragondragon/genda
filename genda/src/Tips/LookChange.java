package Tips;

import javax.swing.*;

public class LookChange extends JFrame{
	JTextArea look = new JTextArea();
	LookChange(){
		init();
		setVisible(true);
		setTitle("ÕýÔÚ×ª»»");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100,100,370,370);
	}
	void init(){
		setLayout(null);
		JScrollPane lookp = new JScrollPane(look);
		lookp.setBounds(0,0,300,300);
		add(lookp);
	}
}
