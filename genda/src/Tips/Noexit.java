package Tips;

import genda1.AchievementListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Noexit implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(Tips.weizhi!=0){
			AchievementListener.setClipboardString(Tips.weizhistr);
			JOptionPane.showMessageDialog(new JTextArea(),"载文中有"+Tips.weizhi+"个未收录字符:\n"+Tips.weizhistr+"\n已放入剪贴板");
		}
		else if(Tips.weizhi==0){
			JOptionPane.showMessageDialog(new JTextArea(),"文章中没有未收录的字符");
		}
	}
}
