package genda1;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class StopListener implements WindowFocusListener{

	@Override
	public void windowGainedFocus(WindowEvent arg0) {
		// TODO Auto-generated method stub
		GendaListener.stop = false;
		GendaListener.comp.setStopTimeTwo();
	}
	@Override
	public void windowLostFocus(WindowEvent arg0) {
		// TODO Auto-generated method stub
		GendaListener.stop = true;
		GendaListener.comp.setStopTimeOne();
		
	}
}