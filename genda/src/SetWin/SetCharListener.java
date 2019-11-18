package SetWin;

import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SetCharListener implements ActionListener{
	public static int charsign = 0;
	public static JButton charset;
	public SetCharListener(JButton space){
		charset = space;
	}
	public SetCharListener(){}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(charsign == 0){
			charsign = 1;
			charset.setText("符号替换\"已开\"");
			charset.setForeground(SetFrame.open);
			Window.charchange.setSelected(true);
		}
		else{
			charsign = 0;
			charset.setText("符号替换\"已关\"");
			charset.setForeground(SetFrame.close);
			Window.charchange.setSelected(false);
		}
	}
}