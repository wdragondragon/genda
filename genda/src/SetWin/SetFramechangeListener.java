package SetWin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class SetFramechangeListener implements ActionListener {
	public static int tipsign = 1;
	public static JButton change;
	SetFramechangeListener(JButton change){
		this.change = change;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(tipsign == 0){
			tipsign = 1;
			change.setText("词语提示\"已开\"");
		}
		else{
			tipsign = 0;
			change.setText("词语提示\"已关\"");
		}
	}
}
