package BuildSai;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class BuildSaiListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(new JTextArea(),"敬请期待");
//		FriendSys fs = new FriendSys();
	}
}