package Check;

import javax.swing.*;

public class CheckFrame extends JFrame {
	JTextArea checktext;
	JScrollPane checktextjs;
	JButton confirelook,confiregen,confireying;
	CheckFrame(){
		init();
		setBounds(10,10,330,200);
		setVisible(true);
		setResizable(false);
		setTitle("校验看打成绩");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭按钮
	}
	void init(){
		setLayout(null);
		checktext = new JTextArea("");
		confiregen = new JButton("跟打校验");
		confirelook = new JButton("看打校验");
		confireying = new JButton("英打校验");
		
		checktextjs = new JScrollPane(checktext);
		checktext.setLineWrap(true);//载文框自动换行
		checktextjs.setBounds(10,10,300,100);
		confiregen.setBounds(10,120,70,30);
		confirelook.setBounds(90,120,70,30);
		confireying.setBounds(170,120,70,30);
		
		CheckListener checklistener = new CheckListener(checktext);
		
		confiregen.addActionListener(checklistener);
		confirelook.addActionListener(checklistener);
		confireying.addActionListener(checklistener);
		add(checktextjs);
		add(confiregen);
		add(confirelook);
		add(confireying);
	}
}
