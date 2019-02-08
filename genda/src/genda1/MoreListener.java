package genda1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;

public class MoreListener extends AbstractAction{
	Window win;
	public static int MoreSign = 1;
	MoreListener(Window win){
		this.win = win;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		change();
	}
	public void change(){
		if(MoreSign == 0){
		
			win.F3.setSize(0,0); //重打设置按钮大小位置 190
			
			win.suduButton.setBounds(57,win.F3.getY(),200,30);
			
			win.KeySuduButton.setBounds(267,win.F3.getY(),200,30);
			win.Keylength.setBounds(477,win.F3.getY(),200,30);
			
			win.lilunma.setSize(0,0);
			
			win.menubar.setSize(45,32);
			win.zishu.setSize(0,0);
			win.zaiwen.setSize(0,0);//剪切版载文按钮大小位置
			win.more.setSize(0,0);//文本载文按钮大小位置
			win.achievementButton.setSize(0,0);
			win.QQzaiwenButton.setSize( 0, 0);
			win.qqName.setSize(0,0);
			
			win.conection.setBounds(10,85,0,0);//协助作者
			win.set.setSize(0,0);//更多
			win.help.setSize(0,0);
			win.online.setSize(0,0);
			win.acticlebutton.setSize(0,0);
			MoreSign=1;
		}
		else{
			win.F3.setSize(60,30); //重打设置按钮大小位置 190
			win.suduButton.setBounds(122,win.F3.getY(),72,30);
			
			win.KeySuduButton.setBounds(199,win.F3.getY(),72,30);
			win.Keylength.setBounds(276,win.F3.getY(),72,30);
			win.zaiwen.setSize(100,30);//剪切版载文按钮大小位置
			win.more.setSize(100,30);//文本载文按钮大小位置
			win.achievementButton.setSize(100,30);
			win.QQzaiwenButton.setSize(100, 30);
			win.help.setSize(90,35);
			win.online.setSize(90,30);
			win.set.setSize(90,30);//更多
			win.help.setSize(90,30);
			win.menubar.setSize(45,32);
			win.acticlebutton.setSize(90,30);
			MoreSign=0;
		}
	}

}
