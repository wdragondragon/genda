package denglu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SendQQMessage {
  public static void sendmessage(String str) {
    try {
      //		String message = new String (str.getBytes("ISO-8859-1"),"utf8");
      String message = URLEncoder.encode(str, "utf8");

      System.out.println(message);

      String URL =
          "http://127.0.0.1:5700/send_group_msg?message=" + message + "&group_id=974172771";
      System.out.println(URL);
      java.net.URL url = new URL(URL);
      URLConnection urlcon = url.openConnection();
      InputStreamReader in = new InputStreamReader(urlcon.getInputStream(), "utf-8");
      BufferedReader bufferRead = new BufferedReader(in);
      System.out.println(bufferRead.readLine());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
