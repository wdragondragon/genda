package Tips;

import genda1.QQZaiwenListener;
import genda1.RegexText;
import genda1.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JTextField;

import SetWin.Set;

public class Tips{
	int i = 0;
	JLabel showText;
	public static double alllength = 0.0,dingalllength = 0.0;
	
	public static int qmc=0,cqmc=0,smc=0,csmc=0,emc=0,cemc=0,qdz=0,sdz=0,edz=0,cqdz=0,fh=0,weizhi=0;
	public static double dengji = 0.0;
	public static HashMap<String,Integer> alltable = new HashMap<String,Integer>();
	
	public static HashMap<String,String> hashtable;
	public static HashMap<String,String> moretiphash = null;
	public static HashMap<String,String> fuhao = new HashMap<String,String>();
	
	public static ArrayList<HashMap<String,String>> hashlist;
	public static ArrayList<Integer> quanmaci = new ArrayList<Integer>();
	public static ArrayList<Integer> quanmacitwo = new ArrayList<Integer>();
	public static ArrayList<Integer> quanmacione = new ArrayList<Integer>();
	public static ArrayList<Integer> ciquanmaci = new ArrayList<Integer>();
	public static ArrayList<Integer> ciquanmacitwo = new ArrayList<Integer>();
	public static ArrayList<Integer> ciquanmacione = new ArrayList<Integer>();
	
	public static ArrayList<Integer> ejianmaci= new ArrayList<Integer>();
	public static ArrayList<Integer> ejianmacitwo= new ArrayList<Integer>();
	public static ArrayList<Integer> ejianmacione = new ArrayList<Integer>();
	public static ArrayList<Integer> ciejianmaci= new ArrayList<Integer>();
	public static ArrayList<Integer> ciejianmacitwo= new ArrayList<Integer>();
	public static ArrayList<Integer> ciejianmacione = new ArrayList<Integer>();
	
	public static ArrayList<Integer> sjianmaci= new ArrayList<Integer>();
	public static ArrayList<Integer> sjianmacitwo= new ArrayList<Integer>();
	public static ArrayList<Integer> sjianmacione = new ArrayList<Integer>();
	
	public static ArrayList<Integer> cisjianmaci= new ArrayList<Integer>();
	public static ArrayList<Integer> cisjianmacitwo= new ArrayList<Integer>();
	public static ArrayList<Integer> cisjianmacione = new ArrayList<Integer>();
	
	public static ArrayList<Integer> danzi = new ArrayList<Integer>();
	public static HashMap<Integer,String> bianma = new HashMap<Integer,String>();
	public static String dingshowstr;
	public static StringBuilder showstr = new StringBuilder(); 
	String regex = "234567890";
	File more = new File(ChooseFile.cizufilename);
	File FuhaoFile = new File("编码文件/符号文件/符号文件.txt");
	Scanner sc = null;
	String str = null;
	public void Fuhao() throws IOException{
		FileInputStream fis = new FileInputStream(FuhaoFile); 
        InputStreamReader read = new InputStreamReader(fis, "UTF-8");
		BufferedReader  bufferRead = new BufferedReader(read);
		while((str=bufferRead.readLine())!=null){
			String[] splited = str.split("\\s+");
			String ch = splited[0];
			String bm = splited[1];
			fuhao.put(ch, bm);
			System.out.println(ch+":"+bm);
		}
		bufferRead.close();
		read.close();
		fis.close();
	}
	public Tips(JLabel showText){
		try {
			Fuhao();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
				String temp;
			    int chlength = splited[0].length();
			    int length = splited[1].length();
			    temp = bm.substring(bm.length()-1);
			    if(temp.equals("_")||regex.indexOf(temp)!=-1)length -= 1;
			    i = -1;
			    if(chlength==1){
			    	if(hashtable.containsKey(splited[0])){
						if(hashtable.get(splited[0]).length()>length){
							hashtable.put(ch, bm);
							i++;
						}
						else if(hashtable.get(splited[0]).length()==length){
							
//							System.out.println(temp);
							if(temp.equals("2")){
								hashtable.put(ch, bm);
								i++;
							}
						}
					}
					else{
						hashtable.put(ch, bm);
						i++;
					}
//					System.out.println(splited[0]+":"+splited[1]);
			    }
			    else if(chlength==2){
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
			    if(i!=-1){
				    if(hashlist.get(i).containsKey(splited[0])){
			    		if(hashlist.get(i).get(splited[0]).length()>length)
			    			hashlist.get(i).put(ch,bm);
			    	}
			    	else{
			    		hashlist.get(i).put(ch,bm);
			    	}
				    alltable.put(ch, length);
			    }
			}
			bufferRead.close();
			read.close();
			fis.close();
		}catch(Exception e){
			System.out.println("打开失败2");
			e.printStackTrace();
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
		dingalllength = 0;
		
//		System.out.println(str);
//		String str = Window.wenben.getText();
		String str1,str2,temp;
		int cixuansign = 0;
		int length;
		String bianmatemp;
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
		bianma.clear();
		emc=0;cemc=0;smc=0;csmc=0;qmc=0;cqmc=0;qdz=0;sdz=0;edz=0;cqdz=0;fh=0;weizhi=0;
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
							bianmatemp =  hashlist.get(i).get(str1);	//临时放入编码，往后加 _
							length = bianmatemp.length();
							if(bianmatemp.substring(length-1,length).equals("_"))length -= 1;
							str2 = hashlist.get(i).get(str1).substring(length-1,length);	//获取编码最后一个字符
							if(regex.indexOf(str2)!=-1){		//判断最后一字符是否为多重
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
									ejianmacitwo.add(j+i+1);
									emc++;
								}
								else{
									ciejianmacione.add(j);
									ciejianmacitwo.add(j+i+1);
									cemc++;
								}
							}
							else if(length<4){
								if(cixuansign==0){
									sjianmacione.add(j);
									sjianmacitwo.add(j+i+1);
									smc++;
								}
								else{
									cisjianmacione.add(j);
									cisjianmacitwo.add(j+i+1);
									csmc++;
								}
							}
							else{
								if(cixuansign==0){
									quanmacione.add(j);
									quanmacitwo.add(j+i+1);
									qmc++;
								}
								else{
									ciquanmacione.add(j);
									ciquanmacitwo.add(j+i+1);
									cqmc++;
								}
							}
							bianma.put(j, bianmatemp);
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
		}catch(Exception e){System.out.print("000");}
	}
	public void compalllength(){
		String str = QQZaiwenListener.wenbenstr;
		String regex = "234567890";
		char c[] = str.toCharArray();
		String bianmatemp;
		System.out.println();
		dingshowstr = "";
//		showstr = "";
		showstr = new StringBuilder();
		for(int i=0;i<str.length();i++)
			if(!quanmaci.contains(i)&&!ejianmaci.contains(i)&&!sjianmaci.contains(i)&&
					!ciquanmaci.contains(i)&&!ciejianmaci.contains(i)&&!cisjianmaci.contains(i)){	//判断是否在已用索引中
				danzi.add(i);		//单字索引添加
				if(hashtable.containsKey(String.valueOf(c[i]))){ 	//查询单字链表中是否存在该单字
					bianmatemp = hashtable.get(String.valueOf(c[i]));		//放入编码中
					int lengthtemp = bianmatemp.length();
					if(lengthtemp==2){
						edz++;
					}
					else if(lengthtemp==3){
						sdz++;
					}
					else if(lengthtemp==4){
						qdz++;
					}
					else{
						cqdz++;
					}
				}
				else{									//不存在则原搬不动，码长算一
					if(fuhao.containsKey(String.valueOf(c[i]))){
						bianmatemp = fuhao.get(String.valueOf(c[i]));
						fh++;
					}
					else {
						bianmatemp = String.valueOf(c[i]);
						weizhi++;
					}
				}
				bianma.put(i, bianmatemp);
			}
		for(int i=0;i<c.length;i++){
			if(Tips.bianma.containsKey(i)){
				showstr.append(Tips.bianma.get(i));
//				showstr += Tips.bianma.get(i);
			}
		}
		dingshowstr = RegexText.biaoding(showstr.toString());
		dingalllength = dingshowstr.length();
		alllength = showstr.length();
		System.out.println("\n理论:"+alllength);
		System.out.println("\n标定:"+dingalllength);
		dengji = (1*4*qmc+
				5*4*smc
				+3*3*emc
				+7*5*cqmc
				+11*4*csmc
				+7*3*cemc
				+7*2*edz
				+8*3*sdz
				+13*4*qdz
				+17*5*cqdz
				+20*weizhi
				+1*2*fh)
				/alllength;
	}
}
