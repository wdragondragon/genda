package SetWin;

import genda1.JTextPaneChange;
import genda1.Window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class FontFamilyListener implements ActionListener {
  JComboBox<String> family;
  FontFamilyListener(JComboBox<String> family) {
    this.family = family;
  }
  @Override
  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub

    String familyfont = family.getSelectedItem().toString();
    if (familyfont.substring(0, 2).equals("µ±Ç°"))
      return;
    Window.family = familyfont;
    JTextPaneChange.createStyle("»Ò", JTextPaneChange.styledDoc, Window.fontSize, 0, 0, 0,
        Color.BLACK, Window.family, Window.mistakecolor);
    JTextPaneChange.createStyle("ºÚ", JTextPaneChange.styledDoc, Window.fontSize, 0, 0, 0,
        Color.BLACK, Window.family, Window.rightcolor);
    JTextPaneChange.createStyle("ºì", JTextPaneChange.styledDoc, Window.fontSize, 0, 0, 0,
        Color.BLACK, Window.family, Window.mistakecolor);

    JTextPaneChange.createStyle("À¶´Ö", JTextPaneChange.styledDoc, Window.fontSize, 1, 0, 0,
        Window.smacicolor, Window.family, Window.rightcolor); // GRAY
    JTextPaneChange.createStyle("À¶", JTextPaneChange.styledDoc, Window.fontSize, 0, 0, 0,
        Window.smacicolor, Window.family, Window.rightcolor); // GRAY
    JTextPaneChange.createStyle("À¶Ð±", JTextPaneChange.styledDoc, Window.fontSize, 0, 1, 0,
        Window.smacicolor, Window.family, Window.rightcolor); // GRAY
    JTextPaneChange.createStyle("À¶´ÖÐ±", JTextPaneChange.styledDoc, Window.fontSize, 1, 1, 0,
        Window.smacicolor, Window.family, Window.rightcolor); // GRAY
    JTextPaneChange.createStyle("·Û´Ö", JTextPaneChange.styledDoc, Window.fontSize, 1, 0, 0,
        Window.emacicolor, Window.family, Window.rightcolor); // GRAY
    JTextPaneChange.createStyle("·Û", JTextPaneChange.styledDoc, Window.fontSize, 0, 0, 0,
        Window.emacicolor, Window.family, Window.rightcolor); // GRAY
    JTextPaneChange.createStyle("·ÛÐ±", JTextPaneChange.styledDoc, Window.fontSize, 0, 1, 0,
        Window.emacicolor, Window.family, Window.rightcolor); // GRAY
    JTextPaneChange.createStyle("·Û´ÖÐ±", JTextPaneChange.styledDoc, Window.fontSize, 1, 1, 0,
        Window.emacicolor, Window.family, Window.rightcolor); // GRAY

    JTextPaneChange.createStyle("ÂÌ´Ö", JTextPaneChange.styledDoc, Window.fontSize, 1, 0, 0,
        Window.qmacicolor, Window.family, Window.rightcolor); // GRAY
    JTextPaneChange.createStyle("ÂÌ", JTextPaneChange.styledDoc, Window.fontSize, 0, 0, 0,
        Window.qmacicolor, Window.family, Window.rightcolor); // GRAY
    JTextPaneChange.createStyle("ÂÌ´ÖÐ±", JTextPaneChange.styledDoc, Window.fontSize, 1, 1, 0,
        Window.qmacicolor, Window.family, Window.rightcolor); // GRAY
    JTextPaneChange.createStyle("ÂÌÐ±", JTextPaneChange.styledDoc, Window.fontSize, 0, 1, 0,
        Window.qmacicolor, Window.family, Window.mistakecolor); // GRAY

    Window.dazi.setFont(new Font(Window.family, 0, Window.fontSize));
    Window.accept.setFont(new Font(Window.family, 0, Window.fontSize));
    //		family.getSize();
  }
}
