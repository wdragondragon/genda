package genda1;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Login.*;
public class OnlineOorF implements ActionListener{
	Frame frame,win;
	JButton link,breaklink,ready,score;
	JButton one,two,three,four,five,six,seven,eight;
	JScrollPane accept;
	JScrollPane communion;
	int tableweizhi1,tableweizhi2;
	private JTextField reducesudu;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
//		frame.setVisible(true);
		if(Login.dengluSign == 0){JOptionPane.showMessageDialog(new JTextArea(),"请先登录");return;}
		if(Window.everydaySign){JOptionPane.showMessageDialog(new JTextArea(),"请先结束每日赛文");return;}
		if(one.isVisible()){
			one.setVisible(false);
			two.setVisible(false);
			three.setVisible(false);
			four.setVisible(false);
			five.setVisible(false);
			six.setVisible(false);
			seven.setVisible(false);
			eight.setVisible(false);
			link.setVisible(false);
			breaklink.setVisible(false);
			accept.setVisible(false);
			ready.setVisible(false);
			score.setVisible(false);
			communion.setVisible(false);
			reducesudu.setVisible(false);
			win.setSize(win.getWidth(),tableweizhi1+40);

		}
		else{
			one.setVisible(true);
			two.setVisible(true);
			three.setVisible(true);
			four.setVisible(true);
			five.setVisible(true);
			six.setVisible(true);
			seven.setVisible(true);
			eight.setVisible(true);
			link.setVisible(true);
			breaklink.setVisible(true);
			accept.setVisible(true);
			ready.setVisible(true);
			score.setVisible(true);
			communion.setVisible(true);
			reducesudu.setVisible(true);
			tableweizhi1 = winchange.bottom;
			if(win.getHeight()<(tableweizhi1+accept.getHeight()+45))
				win.setSize(win.getWidth(),win.getHeight()+accept.getHeight()-((win.getHeight()-(tableweizhi1+5)))+45);
			else
				win.setSize(win.getWidth(),win.getHeight()+accept.getHeight()+45);
			winchange.bottom = tableweizhi1;
		}
		
		
	}
	public void setFrame(Frame t){
		frame = t;
	}
	public void setRoomNum(JTextField reducesudu,JButton one,JButton two,JButton three,JButton four,JButton five ,JButton six ,JButton seven ,JButton eight,JButton link,JButton breaklink,JScrollPane accept,JButton ready,JButton score,JScrollPane communion,Window win){
		this.one = one;
		this.two = two;
		this.three = three;
		this.four = four;
		this.five = five;
		this.six = six;
		this.seven = seven;
		this.eight = eight;
		this.win = win;
		this.link = link;
		this.breaklink = breaklink;
		this.accept = accept;
		this.ready = ready;
		this.score = score;
		this.communion = communion;
		this.reducesudu = reducesudu;
	}
}
