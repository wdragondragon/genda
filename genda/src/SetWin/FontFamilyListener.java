package SetWin;

import genda1.JTextPaneChange;
import genda1.Window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class FontFamilyListener implements ActionListener{
	JComboBox<String> family;
	FontFamilyListener(JComboBox<String> family){
		this.family = family;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		String familyfont = family.getSelectedItem().toString();
		if(familyfont.substring(0,2).equals("当前"))return;
		Window.family = familyfont;
		JTextPaneChange.createStyle("灰",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Color.BLACK,Window.family,Window.mistakecolor);
		JTextPaneChange.createStyle("黑",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Color.BLACK,Window.family,Window.rightcolor);
		JTextPaneChange.createStyle("红",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Color.BLACK,Window.family,Window.mistakecolor);
		
		JTextPaneChange.createStyle("蓝粗",JTextPaneChange.styledDoc,Window.fontSize,1,0,0,Window.smacicolor,Window.family,Window.rightcolor);//GRAY
		JTextPaneChange.createStyle("蓝",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Window.smacicolor,Window.family,Window.rightcolor);//GRAY
		JTextPaneChange.createStyle("蓝斜",JTextPaneChange.styledDoc,Window.fontSize,0,1,0,Window.smacicolor,Window.family,Window.rightcolor);//GRAY
		JTextPaneChange.createStyle("蓝粗斜",JTextPaneChange.styledDoc,Window.fontSize,1,1,0,Window.smacicolor,Window.family,Window.rightcolor);//GRAY
		JTextPaneChange.createStyle("粉粗",JTextPaneChange.styledDoc,Window.fontSize,1,0,0,Window.emacicolor,Window.family,Window.rightcolor);//GRAY
		JTextPaneChange.createStyle("粉",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Window.emacicolor,Window.family,Window.rightcolor);//GRAY
		JTextPaneChange.createStyle("粉斜",JTextPaneChange.styledDoc,Window.fontSize,0,1,0,Window.emacicolor,Window.family,Window.rightcolor);//GRAY
		JTextPaneChange.createStyle("粉粗斜",JTextPaneChange.styledDoc,Window.fontSize,1,1,0,Window.emacicolor,Window.family,Window.rightcolor);//GRAY
		
		JTextPaneChange.createStyle("绿粗",JTextPaneChange.styledDoc,Window.fontSize,1,0,0,Window.qmacicolor,Window.family,Window.rightcolor);//GRAY
		JTextPaneChange.createStyle("绿",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Window.qmacicolor,Window.family,Window.rightcolor);//GRAY
		JTextPaneChange.createStyle("绿粗斜",JTextPaneChange.styledDoc,Window.fontSize,1,1,0,Window.qmacicolor,Window.family,Window.rightcolor);//GRAY
		JTextPaneChange.createStyle("绿斜",JTextPaneChange.styledDoc,Window.fontSize,0,1,0,Window.qmacicolor,Window.family,Window.mistakecolor);//GRAY
		
		Window.dazi.setFont(new Font(Window.family,0,Window.fontSize));
		Window.accept.setFont(new Font(Window.family,0,Window.fontSize));
//		family.getSize();
	}

}
