package SetWin;

import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class SetFrameJinduListener implements ActionListener {
	public static JButton jindu;
	public static int jindusign = 1;
	public SetFrameJinduListener(JButton jindu){
		this.jindu = jindu;
	}
	public SetFrameJinduListener(){}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(jindusign == 0){
			jindusign = 1;
			jindu.setText("��ʾ������\"�ѿ�\"");
			jindu.setForeground(SetFrame.open);
			Window.jindu.setSelected(true);
		}
		else{
			jindusign = 0;
			jindu.setForeground(SetFrame.close);
			jindu.setText("��ʾ������\"�ѹ�\"");
			Window.jindu.setSelected(false);
		}
	}
}