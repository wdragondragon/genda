package genda1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class returnJspListener implements ActionListener {
	Window win;
	returnJspListener(Window win){
		this.win = win;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		win.jSplitPane2.setBounds(10,win.F3.getY()+win.F3.getHeight()+5,win.getWidth()-10,win.getHeight()-95);
	}

}
