package genda1;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
public class MousePressedZaiwenText implements MouseListener {
	JTextArea zaiwenText;
	public void setZaiwenText(JTextArea t){
		zaiwenText = t;
	}
	public void mouseClicked(MouseEvent arg0) {
		zaiwenText.setText(null);
	}
	public void mousePressed(MouseEvent arg0) {
		zaiwenText.setText(null);
	}
	public void mouseEntered(MouseEvent arg0) {

	}
	public void mouseExited(MouseEvent arg0) {

	}
	public void mouseReleased(MouseEvent arg0) {

	}
}
