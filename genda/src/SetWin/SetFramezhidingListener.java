package SetWin;

import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SetFramezhidingListener implements ActionListener {
	public static int zhidingsign = 0;
	public static JButton zhiding;
	public static Window win;
	public SetFramezhidingListener(JButton zhiding,Window win){
		this.zhiding = zhiding;
		this.win = win;
	}
	public SetFramezhidingListener(){}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(zhidingsign == 0){
			zhidingsign = 1;
			zhiding.setText("跟打置顶\"开\"");
			zhiding.setForeground(SetFrame.open);
			win.setAlwaysOnTop(true);
			Window.zhiding.setSelected(true);
		}
		else{
			zhidingsign = 0;
			zhiding.setText("跟打置顶\"关\"");
			zhiding.setForeground(SetFrame.close);
			win.setAlwaysOnTop(false);
			Window.zhiding.setSelected(false);
		}
	}
}
