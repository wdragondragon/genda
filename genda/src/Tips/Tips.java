package Tips;

import genda1.QQZaiwenListener;
import genda1.RegexText;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.util.Scanner;

import javax.swing.JLabel;

public class Tips {
  int i = 0;
  JLabel showText;
  public static double alllength = 0.0, dingalllength = 0.0;

  public static int qmc = 0, cqmc = 0, smc = 0, csmc = 0, emc = 0, cemc = 0, qdz = 0, sdz = 0,
                    edz = 0, cqdz = 0, fh = 0, weizhi = 0, szfh = 0;
  public static double dengji = 0.0;
  public static HashMap<String, Integer> alltable;
  public static HashMap<String, Integer> citable;
  public static HashMap<String, Integer> shoutable;

  public static HashMap<String, String> hashtable;
  public static HashMap<String, String> moretiphash = null;
  public static HashMap<String, String> fuhao = new HashMap<String, String>();

  public static ArrayList<HashMap<String, String>> hashlist;

  public static Integer[] codeWordHeadAndTail;
  public static Integer[] type; // 0单 1全 2次全 3三简 4 次三简 5二简  6次二简
  public static boolean[] userSign;
  public static String[] code;

  public static ArrayList<String> suluList = new ArrayList<String>();
  public static HashMap<Integer, Integer> suluciOneAndTwo = new HashMap<Integer, Integer>();

  public static ArrayList<Integer> danzi = new ArrayList<Integer>();
  public static String dingshowstr;
  public static StringBuilder showstr = new StringBuilder();

  String regex = "234567890";
  File more = new File(ChooseFile.cizufilename);
  File FuhaoFile = new File("编码文件/符号文件/符号文件.txt");
  Scanner sc = null;
  String str = null;
  boolean sulu = false; //速录标志
  public void Fuhao() throws IOException {
    FileInputStream fis = new FileInputStream(FuhaoFile);
    InputStreamReader read = new InputStreamReader(fis, "UTF-8");
    BufferedReader bufferRead = new BufferedReader(read);
    while ((str = bufferRead.readLine()) != null) {
      String[] splited = str.split("\\s+");
      String ch = splited[0];
      String bm = splited[1];
      fuhao.put(ch, bm);
    }
    bufferRead.close();
    read.close();
    fis.close();
  }
  public Tips(JLabel showText) {
    try {
      Fuhao();
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    this.showText = showText;
    hashlist = new ArrayList<HashMap<String, String>>();
    hashtable = new HashMap<String, String>();
    alltable = new HashMap<String, Integer>();
    citable = new HashMap<String, Integer>();
    shoutable = new HashMap<String, Integer>();
    try {
      for (i = 0; i < 10; i++) {
        moretiphash = new HashMap<String, String>();
        hashlist.add(moretiphash);
      }
      FileInputStream fis = new FileInputStream(more);
      InputStreamReader read = new InputStreamReader(fis, "UTF-8");
      BufferedReader bufferRead = new BufferedReader(read);
      while ((str = bufferRead.readLine()) != null) {
        boolean cixuanSign = false;
        if (str.equals("速录编码")) {
          sulu = true;
          break;
        }
        String[] splited = str.split("\\s+");
        String ch = splited[0];
        String bm = splited[1];
        String temp;
        int chlength = splited[0].length();
        int length = splited[1].length();
        temp = bm.substring(bm.length() - 1);
        if (temp.equals("_"))
          length -= 1;
        else if (regex.indexOf(temp) != -1) {
          cixuanSign = true;
          length -= 1;
        }
        i = -1;
        if (chlength == 1) {
          if (hashtable.containsKey(splited[0])) {
            if (hashtable.get(splited[0]).length() > length) {
              hashtable.put(ch, bm);
              alltable.put(ch, length);
              i++;
            } else if (hashtable.get(splited[0]).length() == length) {
              if (temp.equals("2")) {
                hashtable.put(ch, bm);
                alltable.put(ch, length);
                i++;
              }
            }
          } else {
            hashtable.put(ch, bm);
            i++;
          }
        } else if (chlength >= 2 && chlength <= 11) {
          i = chlength - 2;
        }
        if (i != -1) {
          if (hashlist.get(i).containsKey(splited[0])) {
            if (hashlist.get(i).get(splited[0]).length() > length) {
              hashlist.get(i).put(ch, bm);
              alltable.put(ch, length);
              if (cixuanSign)
                citable.put(ch, length);
              else
                shoutable.put(ch, length);
            }
          } else {
            hashlist.get(i).put(ch, bm);
            alltable.put(ch, length);
            if (cixuanSign)
              citable.put(ch, length);
            else
              shoutable.put(ch, length);
          }
        }
      }
      if (sulu) { //速录码表读取
        while ((str = bufferRead.readLine()) != null) {
          suluList.add(str);
        }
      }
      bufferRead.close();
      read.close();
      fis.close();
    } catch (Exception e) {
      System.out.println("打开失败2");
      e.printStackTrace();
    }
  }
  public void changeTips(String ch) {
    // TODO Auto-generated method stub
    if (hashtable.containsKey(ch)) {
      String bm = hashtable.get(ch);
      showText.setText(ch + ":" + bm);
    } else {
      showText.setText("没有该字");
    }
  }
  public void changecolortip() {
    String str = QQZaiwenListener.wenbenstr;
    String regex = "1234567890";
    alllength = 0;
    dingalllength = 0;

    String str1, str2;
    int cixuansign = 0;
    int length;
    String bianmatemp;

    codeWordHeadAndTail = new Integer[str.length()];
    type = new Integer[str.length()];
    userSign = new boolean[str.length()];
    code = new String[str.length()];

    emc = 0;
    cemc = 0;
    smc = 0;
    csmc = 0;
    qmc = 0;
    cqmc = 0;
    qdz = 0;
    sdz = 0;
    edz = 0;
    cqdz = 0;
    fh = 0;
    weizhi = 0;
    szfh = 0;
    try {
      for (int i = 9; i >= 0; i--) {
        if (sulu)
          break; //判断是否为速录码表
        if (str.length() < i + 2)
          continue;
        int best = -1; //跳出分词步骤
        int back = -1; //跳出分隔单字步骤
        for (int j = 0; j < str.length() - (i + 1); j++) {
          cixuansign = 0;
          str1 = str.substring(j, j + i + 2); //获取判断是否为词的str
          if (hashlist.get(i).containsKey(str1)) {
            if (!userSign[j] && userSign[j + i + 1]) {
              continue;
            }
            //解决两码词最简 上身体 uhufti_ uh_ut_的冲突，
            if (j > best - (i + 1) && j < best && best != -1)
              continue;
            if (j > best && j > back && i == 0) {
              //动态叠词奇数安排分隔单字
              HashMap<Integer, String> twoWordsContinuity = new HashMap<>();
              HashMap<Integer, String> oneWord = new HashMap<>();
              for (int k = j; k < str.length() - (i + 1); k++) {
                String continuityTemp = str.substring(k, k + i + 2);
                String oneWordTemp = str.substring(k, k + 1);
                if (hashlist.get(i).containsKey(continuityTemp)) {
                  if (hashtable.containsKey(oneWordTemp))
                    oneWord.put(k, oneWordTemp);
                  twoWordsContinuity.put(k, continuityTemp);
                } else
                  break;
              }
              //如果出现了词的话，将最后一个单字添加进动态单字链表中
              if (twoWordsContinuity.size() > 0) {
                int lastOneWordIndex = j + twoWordsContinuity.size();
                String lastOneWord = str.substring(lastOneWordIndex, lastOneWordIndex + 1);
                oneWord.put(lastOneWordIndex, lastOneWord);
              }
              System.out.println(twoWordsContinuity + ":" + oneWord);
              //如果组成词的字为两个，则可以直接组满词，如果为奇数，则需要拆字
              if (twoWordsContinuity.size() >= 2 && oneWord.size() % 2 != 0) {
                int minlength = 0;
                for (int conti = j; conti < j + oneWord.size(); conti += 2) {
                  String twoWords = "";
                  for (int contj = j; contj < j + oneWord.size();) {
                    if (contj == conti) {
                      twoWords += hashtable.get(oneWord.get(contj));
                      contj += 1;
                    } else {
                      twoWords += hashlist.get(i).get(twoWordsContinuity.get(contj));
                      contj += 2;
                    }
                  }
                  if (minlength == 0 || minlength >= twoWords.length()) {
                    best = conti;
                    minlength = twoWords.length();
                  }
                  System.out.println(twoWords);
                }
                System.out.println(best);
              } else if (twoWordsContinuity.size() >= 2)
                back = j + oneWord.size();
            }
            if (best == j)
              continue;
            //
            bianmatemp = hashlist.get(i).get(str1); //临时放入编码，往后加 _
            length = bianmatemp.length();
            if (bianmatemp.substring(length - 1, length).equals("_"))
              length -= 1;
            str2 = hashlist.get(i).get(str1).substring(length - 1, length); //获取编码最后一个字符
            if (codeWordHeadAndTail[j] == null) {
              if (regex.indexOf(str2) != -1) { //判断最后一字符是否为多重
                length = length - 1;
                cixuansign = 1;
              }
              if (!userSign[j]) {
                if (length < 3) {
                  if (cixuansign == 0) {
                    for (int k = j; k < j + i + 2; k++) userSign[k] = true;
                    emc++;
                  } else {
                    for (int k = j; k < j + i + 2; k++) userSign[k] = true;
                    cemc++;
                  }
                } else if (length < 4) {
                  if (cixuansign == 0) {
                    for (int k = j; k < j + i + 2; k++) userSign[k] = true;
                    smc++;
                  } else {
                    for (int k = j; k < j + i + 2; k++) userSign[k] = true;
                    csmc++;
                  }
                } else {
                  if (cixuansign == 0) {
                    for (int k = j; k < j + i + 2; k++) userSign[k] = true;
                    qmc++;
                  } else {
                    for (int k = j; k < j + i + 2; k++) userSign[k] = true;
                    cqmc++;
                  }
                }
                code[j] = bianmatemp;
              }
              codeWordHeadAndTail[j] = j + i + 1;
              if (length < 3) { // 0单 1全 2次全 3三简 4 次三简 5二简  6次二简
                if (cixuansign == 0) {
                  type[j] = 5;
                } else {
                  type[j] = 6;
                }
              } else if (length < 4) {
                if (cixuansign == 0) {
                  type[j] = 3;
                } else {
                  type[j] = 4;
                }
              } else {
                if (cixuansign == 0) {
                  type[j] = 1;
                } else {
                  type[j] = 2;
                }
              }
            }
          }
        }
      }
      //速录码表情况下的词提
      if (sulu) {
        for (int i = 4; i > 0; i--) {
          if (str.length() < i)
            continue;
          for (int j = 0; j <= str.length() - i; j++) {
            str1 = str.substring(j, j + i);
            if (suluList.contains(str1) && !suluciOneAndTwo.containsKey(j)) {
              suluciOneAndTwo.put(j, j + i - 1);
            }
          }
        }
      }
    } catch (Exception e) {
      System.out.print("000");
    }
  }
  public static String weizhistr = "";
  public void compalllength() {
    if (sulu)
      return;
    String str = QQZaiwenListener.wenbenstr;
    String fu = "@#$%^&*,./;'\\\"——-+=_·| ";
    weizhistr = "";
    char c[] = str.toCharArray();
    String bianmatemp;
    dingshowstr = "";
    showstr = new StringBuilder();
    for (int i = 0; i < str.length(); i++)
      //判断是否在已用索引中
      if (!userSign[i]) {
        danzi.add(i); //单字索引添加
        if (hashtable.containsKey(String.valueOf(c[i]))) { //查询单字链表中是否存在该单字
          bianmatemp = hashtable.get(String.valueOf(c[i])); //放入编码中
          int lengthtemp = bianmatemp.length();
          if (lengthtemp == 2) {
            edz++;
          } else if (lengthtemp == 3) {
            sdz++;
          } else if (lengthtemp == 4) {
            qdz++;
          } else {
            cqdz++;
          }
        } else { //不存在则原搬不动，码长算一
          if (fuhao.containsKey(String.valueOf(c[i]))) {
            bianmatemp = fuhao.get(String.valueOf(c[i]));
            fh++;
          } else if ((c[i] >= 'a' && c[i] <= 'z') || (c[i] >= 'A' && c[i] <= 'Z')
              || (c[i] >= '0' && c[i] <= '9') || fu.indexOf(String.valueOf(c[i])) != -1) {
            bianmatemp = String.valueOf(c[i]);
            szfh++;
          } else {
            bianmatemp = String.valueOf(c[i]);
            weizhistr += bianmatemp;
            weizhi++;
          }
        }
        code[i] = bianmatemp;
      }
    for (int i = 0; i < c.length; i++) {
      if (code[i] != null) {
        showstr.append(code[i]);
      }
    }
    dingshowstr = RegexText.biaoding(showstr.toString());
    dingalllength = dingshowstr.length();
    alllength = showstr.length();
    System.out.println("理论:" + alllength);
    System.out.println("标定:" + dingalllength);
    dengji = (50 * 4 * qmc + 55 * 4 * smc + 60 * 3 * emc + 65 * 5 * cqmc + 70 * 4 * csmc
                 + 75 * 3 * cemc + 80 * 2 * edz + 85 * 3 * sdz + 90 * 4 * qdz + 95 * 5 * cqdz
                 + 100 * weizhi + 45 * 2 * fh + szfh)
        / alllength;
  }
}
