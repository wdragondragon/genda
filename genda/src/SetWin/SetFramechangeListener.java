package SetWin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class SetFramechangeListener implements ActionListener {
	public static int tipsign = 1;
	JRadioButton button1;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(button1.isSelected()){
			tipsign = 1;
		}
		else{
			tipsign = 0;
		}
	}
	public void setButton1(JRadioButton t){
		button1 = t;
	}
}
