package genda1;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class JTextPaneChange {
  public static StyledDocument styledDoc;
  static Style sys;

  static public void creat() {
    styledDoc = new DefaultStyledDocument();
  }

  static public void insertDoc(
      StyledDocument styledDoc, String content, String currentStyle, JTextPane wenben) {
    try {
      // wenben.setText("");
      styledDoc.insertString(styledDoc.getLength(), content, styledDoc.getStyle(currentStyle));
    } catch (BadLocationException e) {
      System.err.println("BadLocationException: " + e);
    }
  }

  static public void createStyle(String style, StyledDocument doc, int size, int bold, int italic,
      int underline, Color color, String fontName, Color backcolor) {
    sys = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
    try {
      doc.removeStyle(style);
    } catch (Exception e) {
    } // 先删除这种Style,假使他存在

    Style s = doc.addStyle(style, sys); // 加入
    StyleConstants.setFontSize(s, size); // 大小
    StyleConstants.setBold(s, (bold == 1) ? true : false); // 粗体
    StyleConstants.setItalic(s, (italic == 1) ? true : false); // 斜体
    StyleConstants.setUnderline(s, (underline == 1) ? true : false); // 下划线
    StyleConstants.setForeground(s, color); // 颜色
    StyleConstants.setFontFamily(s, fontName); // 字体
    if (style.equals("黑"))
      StyleConstants.setBackground(s, backcolor);
    else if (style.equals("红")) {
      StyleConstants.setBackground(s, backcolor);
    }
  }
}
