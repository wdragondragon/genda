package genda1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConectionListener implements ActionListener {
	String uriString = null;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {  
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://wpa.qq.com/msgrd?v=3&uin=1061917196&site=qq&menu=yes");   
       } catch (Exception e) {  
           e.printStackTrace() ;  
       }  
	}

}
