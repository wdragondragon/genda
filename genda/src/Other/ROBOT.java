package Other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ROBOT {
  /**
   * @param args
   * @throws IOException
   * @throws UnsupportedEncodingException
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    try {
      String message = URLEncoder.encode("司法厅", "utf-8");
      URL url = new URL(
          "http://jdragon.club:5700/send_private_msg?message=" + message + "&user_id=1061917196");
      URLConnection urlcon = url.openConnection(); //模拟浏览器发出请求
      //		urlcon.setRequestProperty("User-agent", "Mozilla/5.0 (X11; Linux x86_64)
      //AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
      urlcon.setRequestProperty("User-Agent",
          "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");
      InputStreamReader in = new InputStreamReader(urlcon.getInputStream(), "utf-8");
      BufferedReader bufferRead = new BufferedReader(in);
      String str = "";
      String temp = null;
      while ((temp = bufferRead.readLine()) != null) str += temp;
      System.out.println(str);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
