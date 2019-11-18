package Other;
import java.io.*;
public class Example10_2 {
	public static void main(String args[]) throws IOException{
		int n = 0;
		int strnum = 0;
		int strlength = 0;
		String strall = "";
		File dirFile = new File("C:\\Users\\Administrator\\Desktop\\src");
		FileAccept10_2 fileAccept = new FileAccept10_2();
		fileAccept.setExtendName(".java");
		String[] fileName = dirFile.list(fileAccept);
//		for (int i = 0; i < fileName.length; i++) {
//            if (fileName[i].isFile()) {
//                String fileName1 = fileName[i].getName();
//                System.out.println("文件：" + fileName1);                
//            }
//            
//            if (fileName[i].isDirectory()) {
//                String fileName1 = fileName[i].getName();
//                System.out.println("目录：" + fileName1);        
//            }
//        }
		for(String name:fileName){
			String str = "";
			System.out.println(name);
			File file = new File("C:\\Users\\Administrator\\Desktop\\src",name);
			FileInputStream fis = new FileInputStream(file); 
			InputStreamReader read = new InputStreamReader(fis, "UTF-8");
			BufferedReader  bufferRead = new BufferedReader(read);
			while((str=bufferRead.readLine())!=null){
				strall += str;
				strnum++;
			}
			n++;
		}
		strlength = strall.length();
		System.out.println("一共"+n+"个类");
		System.out.println("一共"+strnum+"行代码");
		System.out.println("一共"+strlength+"个字符");
	}
}