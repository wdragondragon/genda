package genda1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gendaClient.battleClient;

import javax.swing.*;
public class winchange extends Thread implements ActionListener {
	private static final int heightTwo = 0;
	JTextArea dazi;
	JTextPane wenben;
	JScrollPane accept;
	JSplitPane jSplitPane1;
//	JSplitPane jSplitPane1;
	Window win;
	JScrollPane tableN;
	JProgressBar gendajindutiao;
	JButton close,max,size,ce;
	public static int shuweizhi,hengweizhi,bottom;
	
	double i=0;
	int j=0;
	int size1=0,size2=1;
	int heightOne=0,heigthTwo=0,n=0;
	int move = 215;
	int jsplitX = 0;

	winchange(JButton ce,JButton size,JButton max,JButton close,JTextPane one,JTextArea two,JScrollPane accept,JSplitPane jSplitPane1,Window win,JScrollPane tableN,JProgressBar gendajindutiao){
		this.wenben = one;
		this.dazi = two;
		this.jSplitPane1 = jSplitPane1;
//		this.jSplitPane1=jSplitPane2;
		this.win = win;
		this.tableN = tableN;
		this.gendajindutiao = gendajindutiao;
		this.accept = accept;
		this.close = close;
		this.max = max;
		this.size = size;
		this.ce = ce;
	}
	public void run(){
		while(true){
			try{
				if(heightOne!=heigthTwo)//防延迟设置
					for(int i= 0 ;i>100;i++){
						win.validate();//刷新布置
					}
				sleep(1);
				shuweizhi = jSplitPane1.getDividerLocation();
				hengweizhi = jSplitPane1.getWidth();
					jsplitX = 5;
				if(n<=0){
					sleep(500);
					heightOne = heigthTwo;
					heigthTwo = win.getHeight();
				}
				n++;
				if(n>0){
					move = win.getWidth()-jsplitX;
					if(!accept.isVisible()){
						heightOne = heigthTwo;
						heigthTwo = win.getHeight();
						jSplitPane1.setBounds(jsplitX,win.suduButton.getY()+win.suduButton.getHeight()+5,move-5,jSplitPane1.getHeight()+(heigthTwo-heightOne));//295
						gendajindutiao.setBounds(jsplitX,jSplitPane1.getY()+jSplitPane1.getHeight()+(heigthTwo-heightOne),move-5,10);//341
						accept.setBounds(jsplitX,accept.getY()+(heigthTwo-heightOne),move-5,200);//460
						size.setBounds(win.getWidth()-10,win.getHeight()-10,10,10);
						ce.setBounds(jSplitPane1.getX()+jSplitPane1.getWidth(),jSplitPane1.getY(),5,jSplitPane1.getHeight());
						
					}
					else{
						jSplitPane1.setBounds(jsplitX,win.suduButton.getY()+win.suduButton.getHeight()+5,move-5-200,jSplitPane1.getHeight());//295
						gendajindutiao.setBounds(jsplitX,jSplitPane1.getY()+jSplitPane1.getHeight(),move-5-200,10);//341
						accept.setBounds(jsplitX,gendajindutiao.getY()+gendajindutiao.getHeight()+45,move-5-200,200);//460
						
						size.setBounds(win.getWidth()-10,win.getHeight()-10,10,10);
						ce.setBounds(jSplitPane1.getX()+jSplitPane1.getWidth(),jSplitPane1.getY(),5,jSplitPane1.getHeight());
						
					}
					close.setBounds(win.getWidth()-20,0,20,10);
					max.setBounds(win.getWidth()-42,0,20,10);
					win.min.setBounds(win.getWidth()-63,0,20,10);
					win.qqName.setBounds(jSplitPane1.getX()-5,jSplitPane1.getY()+jSplitPane1.getHeight()+10,120,40);
					win.zishu.setBounds(jSplitPane1.getX()-5+win.qqName.getWidth()+5,jSplitPane1.getY()+jSplitPane1.getHeight()+10,(win.zishu.getText().length()-5)*7+5*12,40);
					win.allnumber.setBounds(jSplitPane1.getX()-5+(win.qqName.getWidth()+win.zishu.getWidth()+10),jSplitPane1.getY()+jSplitPane1.getHeight()+10,(win.allnumber.getText().length()-4)*7+4*12,40);
					win.tips.setBounds(jSplitPane1.getX()-5+(win.qqName.getWidth()+win.zishu.getWidth()+win.allnumber.getWidth()+15),jSplitPane1.getY()+jSplitPane1.getHeight()+10,win.tips.getText().length()*15,40);
					win.sendwen.setBounds(jSplitPane1.getX()-5+(win.qqName.getWidth()+win.zishu.getWidth()+win.allnumber.getWidth()+win.tips.getWidth()+20),jSplitPane1.getY()+jSplitPane1.getHeight()+10,win.sendwen.getText().length()*8,40);
					win.dqbanben.setBounds(win.getWidth()-100,win.getHeight()-50,100,40);
					win.zxbanben.setBounds(win.getWidth()-100,win.getHeight()-30,100,40);
					win.lookplayfinish.setBounds(win.sendwen.getX()+win.sendwen.getWidth(), win.sendwen.getY()+5, 100, 30);
					win.leftce.setBounds(win.jSplitPane1.getX()-5,win.jSplitPane1.getY(),5,jSplitPane1.getHeight());
					
					int jswei = jSplitPane1.getX()+jSplitPane1.getWidth()+5;
					int chawei = win.one.getWidth()+5;
					win.one.setLocation(jswei, win.one.getY());
					win.two.setLocation(jswei+chawei, win.two.getY());
					win.three.setLocation(jswei, win.three.getY());
					win.four.setLocation(jswei+chawei, win.four.getY());
					win.five.setLocation(jswei, win.five.getY());
					win.six.setLocation(jswei+chawei, win.six.getY());
					win.seven.setLocation(jswei, win.seven.getY());
					win.eight.setLocation(jswei+chawei, win.eight.getY());
					win.link.setLocation(jswei, win.link.getY());
					win.breaklink.setLocation(jswei+chawei,win.breaklink.getY());
					win.ready.setLocation(jswei, win.ready.getY());
					win.score.setLocation(jswei+chawei,win.score.getY());
					win.communion1.setLocation(jswei, win.communion1.getY());
					win.reducesudu.setLocation(jswei,win.reducesudu.getY());
				}
				bottom =gendajindutiao.getY()+gendajindutiao.getHeight();
//				if(win.getWidth()<800){
//					win.setSize(800,win.getHeight());
//					JOptionPane.showMessageDialog(new JTextArea(),"不能再瘦啦");
//				}
			}catch(Exception e){}
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}