package Other;
import java.io.*;
public class mabiao {

	public static void main(String args[]) throws IOException{
		File filename = new File("编码文件/生成码表.txt");
		File one = new File("码表.txt");
		FileInputStream fis = new FileInputStream(filename); 
        InputStreamReader read = new InputStreamReader(fis, "UTF-8");
		BufferedReader  bufferRead = new BufferedReader(read);
		
		
		FileOutputStream fos = new FileOutputStream(one); 
        OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
		BufferedWriter  bufferWriter = new BufferedWriter(writer);
		String str ;
		String regex = "_23456789";
		StringBuilder all = new StringBuilder();
		while((str=bufferRead.readLine())!=null){
			String temp = str.substring(str.length()-1);
			int i = regex.indexOf(temp);
			if(i!=-1)
				str = str.substring(0,str.length()-1)+"\r\n";
			else 
				str += "\r\n";
			all.append(str);
		}
		bufferWriter.write(all.toString());
		bufferWriter.flush();
		bufferWriter.close();
		read.close();
		fis.close();
	}
}
