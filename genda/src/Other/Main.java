package Other;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void changetxt() throws IOException {
        File one = new File("生成码表.txt");
        Set<String> strlist = new TreeSet<String>();
        boolean sign = true;
        StringBuilder all = new StringBuilder();
        String str;
        int length = 0;
        int xuan = 0;
        String temp = "";

        FileInputStream fis = new FileInputStream("./原码表.txt");
        InputStreamReader read = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader bufferRead = new BufferedReader(read);


        FileOutputStream fos = new FileOutputStream(one);
        OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        BufferedWriter bufferWriter = new BufferedWriter(writer);

        while ((str = bufferRead.readLine()) != null) {
            // 忽略注释
            if (str.contains("---")) continue;

            int i = str.indexOf('#');
            int n = str.indexOf('$');
            if (i != -1)
                if (str.toCharArray()[i + 1] == '用' || str.toCharArray()[i + 1] == '固')
                    str = str.substring(0, i);
                else continue;
            else if (n != -1) continue;
            String[] splited = str.split("\\s+");
            length = splited[1].length();
            temp = splited[1];
            xuan = 2;
            if (strlist.contains(splited[1])) {
                sign = false;
            } else
                str = splited[0] + "\t" + splited[1];
            while (strlist.contains(temp + xuan)) {
                xuan++;
                sign = false;
            }
            if (sign)
                strlist.add(splited[1]);

            else
                strlist.add(splited[1] + xuan);
            if (length < 4 && sign) {
                str += "_" + "\r\n";
            } else {
                str = splited[0] + "\t" + splited[1] + xuan;
                str += "\r\n";
            }
            all.append(str);
            sign = true;
        }
        bufferWriter.write(all.toString());
        bufferWriter.flush();
        bufferWriter.close();
        read.close();
        fis.close();
    }

    public static void main(String[] args) throws IOException {
        Main.changetxt();
    }
}
