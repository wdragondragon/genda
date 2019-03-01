package SetWin;


import genda1.Window;

import java.awt.event.*;

import javax.swing.*;
public class Set extends AbstractAction {
	SetFrame frame;
	Window win;
	public Set(Window win){
		this.win = win;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		frame.setBounds(win.getX()+win.getWidth()/4,win.getY()+win.getHeight()/4,585,385);
//		SetFrame.p.setBounds(0,0,630,430);
		frame.setVisible(true);
	}
	public void setFrame(SetFrame t){
		frame = t;
	}
}