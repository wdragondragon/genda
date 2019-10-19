package Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class QingkongListener implements ActionListener {

	JPasswordField mima;
	JTextField zhanghao;

	QingkongListener(JPasswordField mima, JTextField zhanghao) {
		this.mima = mima;
		this.zhanghao = zhanghao;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (Login.dengluSign == 0) {
			mima.setText("");
			zhanghao.setText("");
		} else
			JOptionPane.showMessageDialog(new JTextArea(), "ÄãÒÑµÇÂ¼");
	}
}
