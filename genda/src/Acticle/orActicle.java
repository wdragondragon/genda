package Acticle;

import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class orActicle extends AbstractAction{
	Acticle a;
	Window win;
	int n;
	public orActicle(Acticle a,Window win){
		this.a = a;
		this.win = win;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(Window.everydaySign){JOptionPane.showMessageDialog(new JTextArea(),"请先结束每日赛文");return;}
		if (SendWenben.sendwenSign==1||SendWenben.sendwenSign2==1||SendWenben.sendwenSign3==1||SendWenben.sendwenSign4){
			n = JOptionPane.showConfirmDialog(null, "正在发文，要取消发文吗?", "正在发文提示", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				// ......
//				a.setVisible(true);
				ActicleListener.fontweizhi = 0;
				win.sendwen.setVisible(false);
				SendWenben.sendwenSign = 0;
				SendWenben.sendwenSign2 = 0;
				SendWenben.sendwenSign3 = 0;
				SendWenben.sendwenSign4 = false;
				} else if (n == JOptionPane.NO_OPTION) {
					// ......
				}
		}
		else
			a.setVisible(true);
	}
}
