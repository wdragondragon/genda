package SetWin;

import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class SetFrameQianshuiListener implements ActionListener {
	public static JButton qianshui1;
	public static int qianshui = 0;
	public SetFrameQianshuiListener(JButton qianshui1) {
		// TODO Auto-generated constructor stub
		this.qianshui1 = qianshui1;
	}
	public SetFrameQianshuiListener(){}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(qianshui==0){
			qianshui = 1;
			qianshui1.setText("Ǳˮ����\"�ѿ�\"");
			qianshui1.setForeground(SetFrame.open);
			Window.qianshui.setSelected(true);
		}
		else{
			qianshui = 0;
			qianshui1.setText("Ǳˮ����\"�ѹ�\"");
			qianshui1.setForeground(SetFrame.close);
			Window.qianshui.setSelected(false);
		}
	}
}
