package SetWin;
import genda1.*;
import genda1.JTextPaneChange;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
public class SetBackground implements ActionListener {
	Frame win;
	Window win1;
	Color tempColor;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand()=="´ò×Ö¿ò±³¾°ÑÕÉ«"){
			tempColor = JColorChooser.showDialog(win, "µ÷É«°å", Color.white);
			if(tempColor!=null)
				Window.dazi.setBackground(tempColor);
		}
		else if(e.getActionCommand()=="ÎÄ±¾¿ò±³¾°ÑÕÉ«"){
			tempColor = JColorChooser.showDialog(win, "µ÷É«°å", Color.white);
			if(tempColor!=null)
				Window.wenben.setBackground(tempColor);
		}
		else if(e.getActionCommand()=="´ò¶Ô×ÖÑÕÉ«"){
			Window.rightcolor = JColorChooser.showDialog(win, "µ÷É«°å", Color.gray);
			if(Window.rightcolor!=null)
				JTextPaneChange.createStyle("ºÚ",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Color.BLACK,"Î¢ÈíÑÅºÚ",Window.rightcolor);
		}
		else if(e.getActionCommand()=="´ò´í×ÖÑÕÉ«"){
			Window.mistakecolor = JColorChooser.showDialog(win, "µ÷É«°å", Color.red);
			if(Window.mistakecolor!=null)
				JTextPaneChange.createStyle("ºì",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Window.mistakecolor,"Î¢ÈíÑÅºÚ",Window.mistakecolor);
		}
		else if(e.getActionCommand()=="ÕûÌå½çÃæÑÕÉ«"){
			tempColor = JColorChooser.showDialog(win, "µ÷É«°å", Color.red);
			if(tempColor!=null)
				win1.getContentPane().setBackground(tempColor);
		}
		else if(e.getActionCommand()=="È«Âë´ÊÑÕÉ«"){
			Window.qmacicolor = JColorChooser.showDialog(win, "µ÷É«°å",new Color(128,138,135));
			if(Window.qmacicolor!=null){
				JTextPaneChange.createStyle("ÂÌ´Ö",JTextPaneChange.styledDoc,Window.fontSize,1,0,0,Window.qmacicolor,"Î¢ÈíÑÅºÚ",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("ÂÌ",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Window.qmacicolor,"Î¢ÈíÑÅºÚ",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("ÂÌÐ±",JTextPaneChange.styledDoc,Window.fontSize,0,1,0,Window.qmacicolor,"Î¢ÈíÑÅºÚ",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("ÂÌ´ÖÐ±",JTextPaneChange.styledDoc,Window.fontSize,1,1,0,Window.qmacicolor,"Î¢ÈíÑÅºÚ",Window.mistakecolor);//GRAY
			}	
		}
		else if(e.getActionCommand()=="¶þÂë´ÊÑÕÉ«"){
			Window.emacicolor = JColorChooser.showDialog(win, "µ÷É«°å", Color.ORANGE);
			if(Window.emacicolor!=null){
				JTextPaneChange.createStyle("·Û´Ö",JTextPaneChange.styledDoc,Window.fontSize,1,0,0,Window.emacicolor,"Î¢ÈíÑÅºÚ",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("·Û",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Window.emacicolor,"Î¢ÈíÑÅºÚ",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("·ÛÐ±",JTextPaneChange.styledDoc,Window.fontSize,0,1,0,Window.emacicolor,"Î¢ÈíÑÅºÚ",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("·Û´ÖÐ±",JTextPaneChange.styledDoc,Window.fontSize,1,1,0,Window.emacicolor,"Î¢ÈíÑÅºÚ",Window.mistakecolor);//GRAY
			}
				
		}
		else if(e.getActionCommand()=="ÈýÂë´ÊÑÕÉ«"){
			Window.smacicolor = JColorChooser.showDialog(win, "µ÷É«°å",Color.BLUE);
			if(Window.smacicolor!=null){
				JTextPaneChange.createStyle("À¶´Ö",JTextPaneChange.styledDoc,Window.fontSize,1,0,0,Window.smacicolor,"Î¢ÈíÑÅºÚ",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("À¶",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Window.smacicolor,"Î¢ÈíÑÅºÚ",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("À¶Ð±",JTextPaneChange.styledDoc,Window.fontSize,0,1,0,Window.smacicolor,"Î¢ÈíÑÅºÚ",Window.mistakecolor);//GRAY
				JTextPaneChange.createStyle("À¶´ÖÐ±",JTextPaneChange.styledDoc,Window.fontSize,1,1,0,Window.smacicolor,"Î¢ÈíÑÅºÚ",Window.mistakecolor);//GRAY
			}
		}
	}
	public void setFrame(Frame win){
		this.win = win;
	}
	public void setWin(Window win1){
		this.win1 = win1;
	}
	
}