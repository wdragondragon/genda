package Robots;

import java.awt.AWTException;
import java.awt.Robot;


import Tips.Tips;

public class robotopen extends Thread {

	public void run(){
		char[]c = Tips.allCode.toString().toCharArray();
		int num = 0;
//		char a = 'A';
		String regex = "234567890";
		int machang = 0;
		try {
			sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Robot robot = new Robot();
			robot.delay(10);
			for(int i = 0;i<c.length;i++){
				num++;
				machang++;
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				if(machang<4&&num>10&&i>0&&c[i-1]!=32&&c[i]!=32&&c[i-1]!='.'&&c[i-1]!=','&&regex.indexOf(String.valueOf(c[i]))==-1&&regex.indexOf(String.valueOf(c[i-1]))==-1){
//					num=0;
//					i-=2;
//					robot.keyPress(0x08);
//	    			robot.keyRelease(0x08);
//	    			machang--;
//	    			continue;
//				}
				if(c[i]>='a'&&c[i]<='z')
					c[i]-=32;
				if(c[i]=='_'||c[i]==','||c[i]=='.'||regex.indexOf(String.valueOf(c[i]))!=-1){
					machang = 0;
				}
				if(c[i]=='_')
					c[i]=32;
				robot.keyPress(c[i]);
    			robot.keyRelease(c[i]);
			}
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
