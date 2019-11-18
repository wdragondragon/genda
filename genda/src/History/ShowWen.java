package History;


import javax.swing.*;
public class ShowWen extends JFrame{
	JTextArea wenshow = new JTextArea();
	JScrollPane jswenshow = new JScrollPane(wenshow);
//	JTextPane wenshow = new JTextPane();;
	public ShowWen(){
		setTitle("跟打详细");
		setBounds(100,100,550,550);
		
//		wenshow.setLineWrap(true);
		wenshow.setLineWrap(true);
		jswenshow.setBounds(0,0,500,500);
		add(jswenshow);
		setVisible(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public void showwen(String wen){
		wenshow.setText(wen);
	}
}