package Tips;

import genda1.ComputeSpeed;
import genda1.Window;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Changetxt extends Thread{
	File more;
	String filename;
//	Changetxt(File file){
//		 more = file;
//	}
	Changetxt(String filename){
		this.filename = filename;
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
		File one = new File("编码文件/生成码表.txt");
		
//		more = new File(filename);
		Set<String> strlist = new TreeSet<String>();
		boolean sign = true;
		StringBuilder all = new StringBuilder();
		String str;
		int jin = 0;
		int length = 0;
		int xuan = 0;
		String temp = "";
		ComputeSpeed comp = new  ComputeSpeed ();

			FileInputStream fis = new FileInputStream(filename); 
	        InputStreamReader read = new InputStreamReader(fis, "UTF-8");
			BufferedReader  bufferRead = new BufferedReader(read);
			
			
			FileOutputStream fos = new FileOutputStream(one); 
	        OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter  bufferWriter = new BufferedWriter(writer);
			
//			LookChange look = new LookChange();
			comp.setTimeOne();
			while((str=bufferRead.readLine())!=null){
				if(str.indexOf("---")!=-1)continue;
				int i = str.indexOf('#');
				int n = str.indexOf('$');
				if(i!=-1)
					if(str.toCharArray()[i+1]=='序'||str.toCharArray()[i+1]=='用'||str.toCharArray()[i+1]=='固')
						str = str.substring(0, i);
					else continue;
				else if(n!=-1)continue;
				String[] splited = str.split("\\s+");
				length = splited[1].length();
				temp = splited[1];
				xuan = 2;
				if(strlist.contains(splited[1]))
					sign = false;
				while(strlist.contains(temp+String.valueOf(xuan))){
					xuan++;
					sign = false;
				}
				if(sign)
					strlist.add(splited[1]);
				else
					strlist.add(splited[1]+String.valueOf(xuan));
				if(length<4&&sign){
					str = splited[0]+"\t"+splited[1];
					str += "_"+"\r\n";
				}
				else if(length>=4&&sign){
					str = splited[0]+"\t"+splited[1];
					str += "\r\n";
				}
				else {
					str = splited[0]+"\t"+splited[1]+String.valueOf(xuan);
					str += "\r\n";
				}
				all.append(str);
				sign = true;
				LookChange.jindu.setText(String.valueOf(++jin));
				comp.setTimeTwo();
				System.out.println(splited[0]+"\t"+str);
			}
			bufferWriter.write(all.toString());
			bufferWriter.flush();
			double time = comp.getSecond();
			JOptionPane.showMessageDialog(new JTextArea(),"转换成功,重新设置全码表生效词提、编码提示与理论码长\n生成码表位于:编码文件\n该次转换历时"+String.format("%.2f", time)+"秒");
			bufferWriter.close();
			read.close();
			fis.close();
	}
}
