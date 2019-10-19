package genda1;

import javax.swing.JProgressBar;

public class GendaJindutiao {
	JProgressBar gendajindu;

	public void open(int m) {
		gendajindu.setMinimum(0);
		gendajindu.setMaximum(m);
		gendajindu.setValue(0);
		// setString(String s);
	}

	public void jindu(int m) {
		gendajindu.setValue(m);
	}

	public void setJProgressBar(JProgressBar t) {
		gendajindu = t;
	}
}
