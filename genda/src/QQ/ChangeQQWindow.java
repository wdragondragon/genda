package QQ;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ChangeQQWindow extends AbstractAction{
	JLabel F5;
	int i = 0;
	GetQQWindow ot;
	public ChangeQQWindow(JLabel qqName){
		this.F5 = qqName;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(i==0){
			ot = new GetQQWindow();
		}
		F5.setText(ot.name.get(i));
		i++;
		i = i%(ot.name.size());
	}
}
