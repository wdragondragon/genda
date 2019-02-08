package SetWin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class SetFrameJinduListener implements ActionListener {
	JRadioButton button1;
	public static int jindusign = 1;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(button1.isSelected()){
			jindusign = 1;
		}
		else{
			jindusign = 0;
		}
	}
	public void setButton1(JRadioButton t){
		button1 = t;
	}
}