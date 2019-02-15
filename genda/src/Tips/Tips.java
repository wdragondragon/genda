package Tips;

import genda1.QQZaiwenListener;
import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Tips{
	int i = 0;
	JLabel showText;
	public static double alllength = 0.0;
	
	public static HashMap<String,String> hashtable;
	public static HashMap<String,String> moretiphash = null;

	
	public static ArrayList<HashMap<String,String>> hashlist;
	public static List<Integer> quanmaci = new ArrayList<Integer>();
	public static List<Integer> quanmacitwo = new ArrayList<Integer>();
	public static List<Integer> quanmacione = new ArrayList<Integer>();
	public static List<Integer> ciquanmaci = new ArrayList<Integer>();
	public static List<Integer> ciquanmacitwo = new ArrayList<Integer>();
	public static List<Integer> ciquanmacione = new ArrayList<Integer>();
	
	public static List<Integer> ejianmaci= new ArrayList<Integer>();
	public static List<Integer> ejianmacitwo= new ArrayList<Integer>();
	public static List<Integer> ejianmacione = new ArrayList<Integer>();
	public static List<Integer> ciejianmaci= new ArrayList<Integer>();
	public static List<Integer> ciejianmacitwo= new ArrayList<Integer>();
	public static List<Integer> ciejianmacione = new ArrayList<Integer>();
	
	public static List<Integer> sjianmaci= new ArrayList<Integer>();
	public static List<Integer> sjianmacitwo= new ArrayList<Integer>();
	public static List<Integer> sjianmacione = new ArrayList<Integer>();
	
	public static List<Integer> cisjianmaci= new ArrayList<Integer>();
	public static List<Integer> cisjianmacitwo= new ArrayList<Integer>();
	public static List<Integer> cisjianmacione = new ArrayList<Integer>();
	
	public static List<Integer> danzi = new ArrayList<Integer>();
	File more = new File(ChooseFile.cizufilename);
	Scanner sc = null;
	String str = null;
	public Tips(JLabel showText){
		this.showText = showText;
		hashlist = new ArrayList<HashMap<String,String>>();
		hashtable = new HashMap<String,String>();
		try{
			for(i=0;i<10;i++){
				moretiphash = new HashMap<String,String>();
				hashlist.add(moretiphash);
			}
			FileInputStream fis = new FileInputStream(more); 
	        InputStreamReader read = new InputStreamReader(fis, "UTF-8");
			BufferedReader  bufferRead = new BufferedReader(read);
			while((str=bufferRead.readLine())!=null){
				String[] splited = str.split("\\s+");
				String ch = splited[0];
				String bm = splited[1];
			    int chlength = splited[0].length();
			    i = -1;
			    if(chlength==1){
			    	if(hashtable.containsKey(splited[0])){
						if(hashtable.get(splited[0]).length()>splited[1].length()){
							hashtable.put(ch, bm);
							i++;
						}
					}
					else{
						hashtable.put(ch, bm);
						i++;
					}
//					System.out.println(splited[0]+":"+splited[1]);
			    }
			    if(chlength==2){
//			    	System.out.println(ch+":"+bm);
			    	i=0;
			    }
			    else if(chlength==3){
//			    	System.out.println(ch+":"+bm);
			    	i=1;
			    }
			    else if(chlength==4){
//			    	System.out.println(ch+":"+bm);
			    	i=2;
			    }
			    else if(chlength==5){
//			    	System.out.println(ch+":"+bm);
			    	i=3;
			    }
			    else if(chlength==6){
//			    	System.out.println(ch+":"+bm);
			    	i=4;
			    }
			    else if(chlength==7){
//			    	System.out.println(ch+":"+bm);
			    	i=5;
			    }
			    else if(chlength==8){
//			    	System.out.println(ch+":"+bm);
			    	i=6;
			    }
			    else if(chlength==9){
//			    	System.out.println(ch+":"+bm);
			    	i=7;
			    }
			    else if(chlength==10){
//			    	System.out.println(ch+":"+bm);
			    	i=8;
			    }
			    else if(chlength==11){
//			    	System.out.println(ch+":"+bm);
			    	i=9;
			    }
			    if(i!=-1)
				    if(hashlist.get(i).containsKey(splited[0])){
			    		if(hashlist.get(i).get(splited[0]).length()>splited[1].length())
			    			hashlist.get(i).put(ch,bm);
			    	}
			    	else{
			    		hashlist.get(i).put(ch,bm);
			    	}
			}
		}catch(Exception e){
			System.out.println("打开失败2");
//			e.printStackTrace();
		}
	}
	public void changeTips(String ch) {
		// TODO Auto-generated method stub
		if(hashtable.containsKey(ch)){
			String bm = hashtable.get(ch);
			showText.setText(ch+":"+bm);
		}
		else{
			showText.setText("没有该字");
		}
	}
	public void changecolortip(){
		String str = QQZaiwenListener.wenbenstr;
		String regex = "1234567890";
		alllength = 0;
//		System.out.println(str);
//		String str = Window.wenben.getText();
		String str1,str2;
		int cixuansign = 0;
		int length;
		char a[] = str.toCharArray();
		quanmaci.clear();
		quanmacione.clear();
		quanmacitwo.clear();
		ciquanmaci.clear();
		ciquanmacione.clear();
		ciquanmacitwo.clear();
		ejianmaci.clear();
		ejianmacione.clear();
		ejianmacitwo.clear();
		ciejianmaci.clear();
		ciejianmacione.clear();
		ciejianmacitwo.clear();
		sjianmaci.clear();
		sjianmacione.clear();
		sjianmacitwo.clear();
		cisjianmaci.clear();
		cisjianmacione.clear();
		cisjianmacitwo.clear();
		try{
			for(int i=9;i>=0;i--){
				if(str.length()<i+2)continue;
				for(int j=0;j<str.length()-(i+1);j++){
					cixuansign = 0;
					str1 = str.substring(j,j+i+2);
					if(hashlist.get(i).containsKey(str1)){
						if(!quanmaci.contains(j)&&!quanmaci.contains(j+i+1)&&!ciquanmaci.contains(j)&&!ciquanmaci.contains(j+i+1)&&
								!ejianmaci.contains(j)&&!ejianmaci.contains(j+i+1)&&!ciejianmaci.contains(j)&&!ciejianmaci.contains(j+i+1)&&
								!sjianmaci.contains(j)&&!sjianmaci.contains(j+i+1)&&!cisjianmaci.contains(j)&&!cisjianmaci.contains(j+i+1))
						{
							length = hashlist.get(i).get(str1).length();
							str2 = hashlist.get(i).get(str1).substring(length-1,length);
							
//							System.out.print(str1+str2);
							if(regex.indexOf(str2)!=-1){
								length = length-1;
								cixuansign=1;
							}
							System.out.print(length);
							for(int k=j;k<j+i+2;k++){
								if(length<3){
									if(cixuansign==0)
										ejianmaci.add(k);
									else
										ciejianmaci.add(k);
								}
								else if(length<4){
									if(cixuansign==0)
										sjianmaci.add(k);
									else
										cisjianmaci.add(k);
								}
								else {
									if(cixuansign==0)
										quanmaci.add(k);
									else
										ciquanmaci.add(k);
								}
							}
							if(length<3){
								if(cixuansign==0){
									ejianmacione.add(j);
									ejianmacitwo.add(j+i+1);}
								else{
									ciejianmacione.add(j);
									ciejianmacitwo.add(j+i+1);
								}
								alllength =alllength+length+1;
							}
							else if(length<4){
								if(cixuansign==0){
									sjianmacione.add(j);
									sjianmacitwo.add(j+i+1);}
								else{
									cisjianmacione.add(j);
									cisjianmacitwo.add(j+i+1);
								}
								alllength = alllength+length+1;
							}
							else{
								if(cixuansign==0){
									quanmacione.add(j);
									quanmacitwo.add(j+i+1);
									alllength =alllength+length;
								}
								else{
									ciquanmacione.add(j);
									ciquanmacitwo.add(j+i+1);
									alllength =alllength+length+1;
								}
							}
//							System.out.println(str1);
						}
					}
				}
			}
			Collections.sort(quanmaci);
			Collections.sort(ejianmaci);
			Collections.sort(sjianmaci);
			
			Collections.sort(quanmacione);
			Collections.sort(ejianmacione);
			Collections.sort(sjianmacione);
			
			Collections.sort(quanmacitwo);
			Collections.sort(ejianmacitwo);
			Collections.sort(sjianmacitwo);
//			for(int i :quanmaci){
//				System.out.print(i);
//			}
		}catch(Exception e){System.out.print("000");}
	}
	public double compalllength(){
		String str = QQZaiwenListener.wenbenstr;
		String regex = "234567890";
		char c[] = str.toCharArray();
		System.out.println();
		for(int i=0;i<str.length();i++){
			if(!quanmaci.contains(i)&&!ejianmaci.contains(i)&&!sjianmaci.contains(i)&&
					!ciquanmaci.contains(i)&&!ciejianmaci.contains(i)&&!cisjianmaci.contains(i)){
//				System.out.println("单字");
				danzi.add(i);
				if(hashtable.containsKey(String.valueOf(c[i]))){
					String str1 = hashtable.get(String.valueOf(c[i]));
					String temp = str1.substring(str1.length()-1);
					if(hashtable.get(String.valueOf(c[i])).length()<4)
						if(regex.indexOf(temp)!=-1){
							alllength += str1.length();
							System.out.print(c[i]);
						}
						else
							alllength += str1.length()+1;
					else {
						alllength += str1.length();
					}
				}
				else{
					alllength+=1;
				}
			}
		}
		System.out.println("\n理论:"+alllength);
		return alllength;
	}
}
