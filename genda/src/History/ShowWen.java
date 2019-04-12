package History;


import javax.swing.*;
public class ShowWen extends JFrame{
	JTextArea wenshow = new JTextArea();
	JScrollPane jswenshow = new JScrollPane(wenshow);
//	JTextPane wenshow = new JTextPane();;
	public ShowWen(){
		setTitle("¸ú´òÏêÏ¸");
		setBounds(100,100,550,550);
		
//		wenshow.setLineWrap(true);
		jswenshow.setBounds(0,0,500,500);
		add(jswenshow);
		setVisible(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public void showwen(String wen){
		wenshow.setText(wen);
	}
}