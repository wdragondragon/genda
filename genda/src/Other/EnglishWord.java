package Other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class EnglishWord {
  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub
    HashMap<String, String> bianma = new HashMap<String, String>();
    File open = new File("文章//英打类", "cet4.txt");
    FileInputStream fis = new FileInputStream(open);
    InputStreamReader read = new InputStreamReader(fis, "UTF-8");
    BufferedReader bufferRead = new BufferedReader(read);

    File bao = new File("文章//英打类", "cet41.txt");
    FileOutputStream fos = new FileOutputStream(bao);
    OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
    BufferedWriter bufferWriter = new BufferedWriter(writer);
    String str;
    int i;
    while ((str = bufferRead.readLine()) != null) {
      char a[] = str.toCharArray();
      str = "";
      for (i = 0; i < a.length; i++) {
        if (a[i] != ' ')
          break;
      }
      for (; i < a.length; i++) {
        str += String.valueOf((a[i]));
      }
      try {
        String[] arr = str.split("\\s+");
        bianma.put(arr[0], arr[1]);
        str = arr[0] + "\t" + arr[1];
        //				System.out.println(str);
        bufferWriter.write(str + "\r\n");
      } catch (Exception e) {
      }
    }
    bufferWriter.close();
    writer.close();
    fos.close();
    printhash(bianma);
  }
  public static void printhash(HashMap<String, String> bianma) {
    for (String key : bianma.keySet()) {
      System.out.println(key + ":" + bianma.get(key));
    }
  }
}
