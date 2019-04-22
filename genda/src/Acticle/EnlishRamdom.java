package Acticle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;

import Tips.ChooseFile;

public class EnlishRamdom implements ActionListener {
	public static List wordlist = null;
	public static String []word = null ;
	public static String FileName = "";
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		RamdomWord();
	}
	public static String readtext(){
		File File = null;
		FileInputStream fis;
		String str = "";
		try {
			FileName = ChooseFile.getFileName();
			File = new File(FileName);
			fis = new FileInputStream(File);
			InputStreamReader read = new InputStreamReader(fis, "UTF-8");
			BufferedReader  bufferRead = new BufferedReader(read);
			while((str=bufferRead.readLine())!=null){
				word=str.split(" ");
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return RamdomWord();
	}
	public static String RamdomWord(){
		String str = "";
		Random rand = new Random();
		str="";
		wordlist = new ArrayList<>();
		for(int i = 0;i<Integer.parseInt(Acticle.number.getText());i++){
			wordlist.add(word[rand.nextInt(word.length)]);
			str+=word[rand.nextInt(word.length)];
			str+=" ";
		}
		return str.substring(0,str.length()-1);
	}
}
