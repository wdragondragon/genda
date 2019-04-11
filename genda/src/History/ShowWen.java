package History;
import javax.swing.*;
public class ShowWen extends JFrame{
	public ShowWen(String wen){
		setTitle("¸ú´òÏêÏ¸");
		setBounds(100,100,550,550);
		JTextArea wenshow = new JTextArea();
		JScrollPane jswenshow = new JScrollPane(wenshow);
		wenshow.setText(wen);
		wenshow.setLineWrap(true);
		jswenshow.setBounds(0,0,500,500);
		add(jswenshow);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}