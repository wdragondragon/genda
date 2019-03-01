package Other;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadTxtFile {
    public static void main(String[] args) {
        String filePath = "编码文件\\符号文件\\符号文件.txt";
        ReadTxtFile rxf = new ReadTxtFile();
        rxf.readFile(filePath);
        
        PreComb pre = new PreComb();
        pre.run();
    }
    private void readFile(String filePath) {
        File file = new File(filePath);
        //鍒ゆ柇鏂囦欢鏄惁瀛樺湪
        if ((file.isFile() && file.exists())) {
            System.out.println("鏂囦欢瀛樺湪锛屽紑濮嬩笅涓�姝�");
            try {
                //鍒涘缓涓�涓� BufferedReader绫绘潵璇诲彇瀵硅薄
                BufferedReader br = new BufferedReader(new FileReader(file));
                String s;


                int rows = 0;
                // 姣忔璇诲彇涓�琛�
                while ((s = br.readLine()) != null) {
                    System.out.println(s + "\t" + s.substring(2, 4));
//                    if (s.substring(2,4).equals()){
//
//                    }
                    rows += 1;
                }
                System.out.println("鍏辫璇诲彇" + rows + "琛�");
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("鏂囦欢涓嶅瓨鍦�");
        }
    }

}
