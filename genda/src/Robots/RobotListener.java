package Robots;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Tips.Tips;

//import java.awt.Robot;
public class RobotListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent ex) {
		// TODO Auto-generated method stub
		if (Login.Login.zhanghao.getText().equals("Ã∑”Ó")
				&& Login.Login.dengluSign == 1) {
			robotopen rbop = new robotopen();
			rbop.start();
		}
	}
}
