package SetWin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class SetFrameJinduListener implements ActionListener {
	public static JButton jindu;
	public static int jindusign = 1;
	SetFrameJinduListener(JButton jindu){
		this.jindu = jindu;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(jindusign == 0){
			jindusign = 1;
			jindu.setText("显示进度条\"已开\"");
			jindu.setForeground(SetFrame.open);;
		}
		else{
			jindusign = 0;
			jindu.setForeground(SetFrame.close);
			jindu.setText("显示进度条\"已关\"");
		}
	}
}