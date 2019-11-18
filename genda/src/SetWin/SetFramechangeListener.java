package SetWin;

import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class SetFramechangeListener implements ActionListener {
	public static int tipsign = 1;
	public static JButton change;
	public SetFramechangeListener(JButton change){
		this.change = change;
	}
	public SetFramechangeListener(){}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(tipsign == 0){
			tipsign = 1;
			change.setText("词语提示\"已开\"");
			change.setForeground(SetFrame.open);
			Window.wordTips.setSelected(true);
		}
		else{
			tipsign = 0;
			change.setText("词语提示\"已关\"");
			change.setForeground(SetFrame.close);
			Window.wordTips.setSelected(false);
		}
	}
}
