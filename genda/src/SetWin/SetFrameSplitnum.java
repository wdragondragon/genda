package SetWin;

import genda1.GendaListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SetFrameSplitnum implements ActionListener{

	JTextField splitenum;
	public SetFrameSplitnum(JTextField splitenum) {
		// TODO Auto-generated constructor stub
		this.splitenum = splitenum;
	}
	public SetFrameSplitnum(){}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try{
			GendaListener.fenye = Integer.parseInt(splitenum.getText());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(new JTextArea(),"设置分页字数只能为数字");
		}
	}
}
