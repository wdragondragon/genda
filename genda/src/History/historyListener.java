package History;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class historyListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(Login.Login.dengluSign==1)
			new historyFrame();
		else
			JOptionPane.showMessageDialog(new JTextArea(),"ÇëÏÈµÇÂ¼");
	}

}
