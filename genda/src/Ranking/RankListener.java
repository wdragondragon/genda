package Ranking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Login.Login;

public class RankListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(Login.dengluSign==1)
			new rankFrame();
		else{
			JOptionPane.showMessageDialog(new JTextArea(),"ÇëÏÈµÇÂ¼");
		}
	}
}
