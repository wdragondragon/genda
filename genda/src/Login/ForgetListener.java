package Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ForgetListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(new JTextArea(),"无自助改密\n点击协助作者来联系作者");
	}

}
