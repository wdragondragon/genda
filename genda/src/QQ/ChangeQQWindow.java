package QQ;

import genda1.Example;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ChangeQQWindow extends AbstractAction{
	JLabel F5;
	int i = 0;
	GetQQWindow ot;
	public ChangeQQWindow(JLabel qqName){
		this.F5 = qqName;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try{
			if(!Example.systemname.substring(0,7).equals("Windows"))return;
			
			if(i==0){
				ot = new GetQQWindow();
			}
			F5.setText(ot.name.get(i));
			i++;
			i = i%(ot.name.size());
		}catch(Exception e){System.out.println("获取QQ窗口错误");}
	}
}