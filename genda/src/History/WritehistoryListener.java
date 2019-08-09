package History;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class WritehistoryListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		WritehistoryToText whtt = new WritehistoryToText();
		whtt.start();
	}
}
