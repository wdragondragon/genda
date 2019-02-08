package SetWin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class SetFrameQianshuiListener implements ActionListener {
	JRadioButton button1;
	public static int qianshui = 0;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(button1.isSelected()){
			qianshui = 1;
		}
		else{
			qianshui = 0;
		}
	}
	public void setButton1(JRadioButton t){
		button1 = t;
	}
}
