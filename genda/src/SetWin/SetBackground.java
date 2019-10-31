package SetWin;
import genda1.*;

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

    if (e.getActionCommand() == "打字框背景颜色") {
      tempColor = JColorChooser.showDialog(win, "调色板", Color.white);
      if (tempColor != null)
        Window.dazi.setBackground(tempColor);
    } else if (e.getActionCommand() == "文本框背景颜色") {
      tempColor = JColorChooser.showDialog(win, "调色板", Color.white);
      if (tempColor != null)
        Window.wenben.setBackground(tempColor);
    } else if (e.getActionCommand() == "打对字颜色") {
      Window.rightcolor = JColorChooser.showDialog(win, "调色板", Color.gray);
      if (Window.rightcolor != null)
        JTextPaneChange.createStyle("黑", JTextPaneChange.styledDoc, Window.fontSize, 0, 0, 0,
            Color.BLACK, Window.family, Window.rightcolor);
    } else if (e.getActionCommand() == "打错字颜色") {
      Window.mistakecolor = JColorChooser.showDialog(win, "调色板", Color.red);
      if (Window.mistakecolor != null)
        JTextPaneChange.createStyle("红", JTextPaneChange.styledDoc, Window.fontSize, 0, 0, 0,
            Color.BLACK, Window.family, Window.mistakecolor);
    } else if (e.getActionCommand() == "整体界面颜色") {
      tempColor = JColorChooser.showDialog(win, "调色板", Color.red);
      if (tempColor != null)
        win1.getContentPane().setBackground(tempColor);
    } else if (e.getActionCommand() == "全码词颜色") {
      Window.qmacicolor = JColorChooser.showDialog(win, "调色板", new Color(128, 138, 135));
      if (Window.qmacicolor != null) {
        JTextPaneChange.createStyle("绿粗", JTextPaneChange.styledDoc, Window.fontSize, 1, 0, 0,
            Window.qmacicolor, Window.family, Window.mistakecolor); // GRAY
        JTextPaneChange.createStyle("绿", JTextPaneChange.styledDoc, Window.fontSize, 0, 0, 0,
            Window.qmacicolor, Window.family, Window.mistakecolor); // GRAY
        JTextPaneChange.createStyle("绿斜", JTextPaneChange.styledDoc, Window.fontSize, 0, 1, 0,
            Window.qmacicolor, Window.family, Window.mistakecolor); // GRAY
        JTextPaneChange.createStyle("绿粗斜", JTextPaneChange.styledDoc, Window.fontSize, 1, 1, 0,
            Window.qmacicolor, Window.family, Window.mistakecolor); // GRAY
      }
    } else if (e.getActionCommand() == "二码词颜色") {
      Window.emacicolor = JColorChooser.showDialog(win, "调色板", Color.ORANGE);
      if (Window.emacicolor != null) {
        JTextPaneChange.createStyle("粉粗", JTextPaneChange.styledDoc, Window.fontSize, 1, 0, 0,
            Window.emacicolor, Window.family, Window.mistakecolor); // GRAY
        JTextPaneChange.createStyle("粉", JTextPaneChange.styledDoc, Window.fontSize, 0, 0, 0,
            Window.emacicolor, Window.family, Window.mistakecolor); // GRAY
        JTextPaneChange.createStyle("粉斜", JTextPaneChange.styledDoc, Window.fontSize, 0, 1, 0,
            Window.emacicolor, Window.family, Window.mistakecolor); // GRAY
        JTextPaneChange.createStyle("粉粗斜", JTextPaneChange.styledDoc, Window.fontSize, 1, 1, 0,
            Window.emacicolor, Window.family, Window.mistakecolor); // GRAY
      }

    } else if (e.getActionCommand() == "三码词颜色") {
      Window.smacicolor = JColorChooser.showDialog(win, "调色板", Color.BLUE);
      if (Window.smacicolor != null) {
        JTextPaneChange.createStyle("蓝粗", JTextPaneChange.styledDoc, Window.fontSize, 1, 0, 0,
            Window.smacicolor, Window.family, Window.mistakecolor); // GRAY
        JTextPaneChange.createStyle("蓝", JTextPaneChange.styledDoc, Window.fontSize, 0, 0, 0,
            Window.smacicolor, Window.family, Window.mistakecolor); // GRAY
        JTextPaneChange.createStyle("蓝斜", JTextPaneChange.styledDoc, Window.fontSize, 0, 1, 0,
            Window.smacicolor, Window.family, Window.mistakecolor); // GRAY
        JTextPaneChange.createStyle("蓝粗斜", JTextPaneChange.styledDoc, Window.fontSize, 1, 1, 0,
            Window.smacicolor, Window.family, Window.mistakecolor); // GRAY
      }
    }
  }
  public void setFrame(Frame win) {
    this.win = win;
  }
  public void setWin(Window win1) {
    this.win1 = win1;
  }
}