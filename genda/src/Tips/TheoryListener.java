package Tips;

import genda1.QQZaiwenListener;
import Tips.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TheoryListener extends JFrame implements ActionListener {
	JScrollPane show1;
	JTextArea show;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!this.isVisible())setVisible(true);
//		System.out.println(e.getActionCommand());
//		System.out.println(e.getActionCommand().substring(0, 4));
		if(e.getActionCommand().substring(0,4).equals("�����볤"))
			show.setText(Tips.allCode.toString());
		else if(e.getActionCommand().substring(0,4).equals("�궥����"))
			show.setText(Tips.allCode.toString());
		
//		Tips.bianma.size();
	}
	public TheoryListener(){
		init();
		setVisible(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//���ùرհ�ť
		setBounds(10,10,500,300);
	}
	void init(){
		setTitle("�������۱���");
		show = new JTextArea();
		show1 = new JScrollPane(show);
		show.setLineWrap(true);
		show.setFont(new Font("΢���ź�",0,20));
		add(show1);
	}
}
