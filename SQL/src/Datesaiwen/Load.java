package Datesaiwen;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Load {
  public String getRamdomWenben() {
    String str = getUrlConStr(); //获得网站str
    String regex = "<p>(.*?)</p>"; //正则匹配出<p>与</p>之间
    Pattern pattern = Pattern.compile(regex); //匹配模式
    Matcher m = pattern.matcher(str); //判断是否符合匹配
    String str1 = "";
    while (m.find()) {
      int i = 1;
      str1 += m.group(i);
      i++;
    }
    if (str1.length() > 600)
      str1 = str1.substring(0, 599);
    //			System.out.println(str1);
    return str1;
  }
  String getUrlConStr() {
    try {
      URL url = new URL("https://meiriyiwen.com/random");
      URLConnection urlcon = url.openConnection(); //模拟浏览器发出请求
      //			urlcon.setRequestProperty("User-agent", "Mozilla/5.0 (X11; Linux
      //x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
      urlcon.setRequestProperty("User-Agent",
          "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");
      InputStreamReader in = new InputStreamReader(urlcon.getInputStream(), "utf-8");
      BufferedReader bufferRead = new BufferedReader(in);
      String str = "";
      String temp = null;
      while ((temp = bufferRead.readLine()) != null) str += temp;
      //			System.out.println(str);
      return str;
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(new JTextArea(), "获取失败,请检查网络");
    }
    return "";
  }
}
