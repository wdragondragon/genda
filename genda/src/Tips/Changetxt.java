package Tips;

import genda1.Window;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Changetxt extends Thread{
	File more;
	Changetxt(File file){
		 more = file;
	}
	public void run(){
		try {
			changetxt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JTextArea(),"转换失败2");
		}
	}
	public  void changetxt()throws IOException{
		File one = new File("生成码表.txt");
//		File more = new File("小鹤词组提示.txt");
		List<String> strlist = new ArrayList<String>();
		String str;
		try {
			FileInputStream fis = new FileInputStream(more); 
	        InputStreamReader read = new InputStreamReader(fis, "UTF-8");
			BufferedReader  bufferRead = new BufferedReader(read);
			
			
			FileOutputStream fos = new FileOutputStream(one); 
	        OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter  bufferWriter = new BufferedWriter(writer);
			
			LookChange look = new LookChange();
			
			while((str=bufferRead.readLine())!=null){
				String[] splited = str.split("\\s+");
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(strlist.contains(splited[1])){
					str = splited[0]+"\t"+splited[1]+String.valueOf(2)+"\r\n";
				}
				else
					str = splited[0]+"\t"+splited[1]+"\r\n";
				strlist.add(splited[1]);
				if(look.look.getText().length()>5000)
					look.look.setText("");
				System.out.println(str);
				look.look.append(str);
				look.look.setCaretPosition(look.look.getText().length());
				bufferWriter.write(str);
				bufferWriter.flush();
			}
			JOptionPane.showMessageDialog(new JTextArea(),"转换成功,重启跟打器生效");
			look.dispose();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JTextArea(),"转换失败3");
		} 
	}
}
