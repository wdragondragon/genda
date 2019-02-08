package SetWin;
import genda1.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SetFontSize implements ActionListener{
	JTextField FontSize;
	int fontSize ;
	SetFontSize(JTextField FontSize){
		this.FontSize = FontSize;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try{
			Window.fontSize = Integer.parseInt(FontSize.getText());
			fontSize = Window.fontSize;
			JTextPaneChange.createStyle("»Ò",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Color.BLACK,"Î¢ÈíÑÅºÚ",Window.mistakecolor);
			JTextPaneChange.createStyle("ºÚ",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Color.BLACK,"Î¢ÈíÑÅºÚ",Window.rightcolor);
			JTextPaneChange.createStyle("ºì",JTextPaneChange.styledDoc,Window.fontSize,0,0,0,Color.BLACK,"Î¢ÈíÑÅºÚ",Window.mistakecolor);
			
			JTextPaneChange.createStyle("À¶´Ö",JTextPaneChange.styledDoc,fontSize,1,0,0,Window.smacicolor,"Î¢ÈíÑÅºÚ",Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("À¶",JTextPaneChange.styledDoc,fontSize,0,0,0,Window.smacicolor,"Î¢ÈíÑÅºÚ",Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("À¶Ð±",JTextPaneChange.styledDoc,fontSize,0,1,0,Window.smacicolor,"Î¢ÈíÑÅºÚ",Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("À¶´ÖÐ±",JTextPaneChange.styledDoc,fontSize,1,1,0,Window.smacicolor,"Î¢ÈíÑÅºÚ",Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("·Û´Ö",JTextPaneChange.styledDoc,fontSize,1,0,0,Window.emacicolor,"Î¢ÈíÑÅºÚ",Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("·Û",JTextPaneChange.styledDoc,fontSize,0,0,0,Window.emacicolor,"Î¢ÈíÑÅºÚ",Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("·ÛÐ±",JTextPaneChange.styledDoc,fontSize,0,1,0,Window.emacicolor,"Î¢ÈíÑÅºÚ",Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("·Û´ÖÐ±",JTextPaneChange.styledDoc,fontSize,1,1,0,Window.emacicolor,"Î¢ÈíÑÅºÚ",Window.rightcolor);//GRAY
			
			JTextPaneChange.createStyle("ÂÌ´Ö",JTextPaneChange.styledDoc,fontSize,1,0,0,Window.qmacicolor,"Î¢ÈíÑÅºÚ",Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("ÂÌ",JTextPaneChange.styledDoc,fontSize,0,0,0,Window.qmacicolor,"Î¢ÈíÑÅºÚ",Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("ÂÌ´ÖÐ±",JTextPaneChange.styledDoc,fontSize,1,1,0,Window.qmacicolor,"Î¢ÈíÑÅºÚ",Window.rightcolor);//GRAY
			JTextPaneChange.createStyle("ÂÌÐ±",JTextPaneChange.styledDoc,fontSize,0,1,0,Window.qmacicolor,"Î¢ÈíÑÅºÚ",Window.mistakecolor);//GRAY
			
			Window.dazi.setFont(new Font("Î¢ÈíÑÅºÚ",0,fontSize));
			Window.accept.setFont(new Font("Î¢ÈíÑÅºÚ",0,fontSize));
		}catch(Exception e){
			JOptionPane.showMessageDialog(new JTextArea(),"ÉèÖÃ×ÖºÅÖ»ÄÜÎªÊý×Ö");
		}
	}
}