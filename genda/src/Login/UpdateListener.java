package Login;

import genda1.Example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try{
			if(Example.systemname.length()>=7&&Example.systemname.substring(0,7).equals("Windows"))
				Runtime.getRuntime().exec("¸üÐÂ.exe");
			else
				Runtime.getRuntime().exec("java -jar update.jar");
			System.exit(0);
		}catch(Exception e){}
	}
}