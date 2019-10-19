package Tips;

import genda1.QQZaiwenListener;
import genda1.RegexText;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import java.util.Scanner;

import javax.swing.JLabel;


public class Tips{
	int i = 0;
	JLabel showText;
	public static double alllength = 0.0,dingalllength = 0.0;
	
	public static int qmc=0,cqmc=0,smc=0,csmc=0,emc=0,cemc=0,qdz=0,sdz=0,edz=0,cqdz=0,fh=0,weizhi=0,szfh=0;
	public static double dengji = 0.0;
	public static HashMap<String,Integer> alltable;
	public static HashMap<String,Integer> citable;
	public static HashMap<String,Integer> shoutable;
	
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
		alltable =  new HashMap<String,Integer>();
		citable =  new HashMap<String,Integer>();
		shoutable =  new HashMap<String,Integer>();
		try{
			for(i=0;i<10;i++){
				moretiphash = new HashMap<String,String>();
				hashlist.add(moretiphash);
			}
			FileInputStream fis = new FileInputStream(more); 
	        InputStreamReader read = new InputStreamReader(fis, "UTF-8");
			BufferedReader  bufferRead = new BufferedReader(read);
			while((str=bufferRead.readLine())!=null){
				boolean cixuanSign = false;
				String[] splited = str.split("\\s+");
				String ch = splited[0];
				String bm = splited[1];
				String temp;
			    int chlength = splited[0].length();
			    int length = splited[1].length();
			    temp = bm.substring(bm.length()-1);
			    if(temp.equals("_"))length -= 1;
			    else if(regex.indexOf(temp)!=-1){
			    	cixuanSign=true;
			    	length -=1;
			    }
			    i = -1;
			    if(chlength==1){
			    	if(hashtable.containsKey(splited[0])){
						if(hashtable.get(splited[0]).length()>length){
							hashtable.put(ch, bm);
							alltable.put(ch, length);
							i++;
						}
						else if(hashtable.get(splited[0]).length()==length){
							if(temp.equals("2")){
								hashtable.put(ch, bm);
								alltable.put(ch, length);
								i++;
							}
						}
					}
					else{
						hashtable.put(ch, bm);
						i++;
					}
			    }
			    else if(chlength>=2&&chlength<=11){
			    	i = chlength - 2;
			    }
			    if(i!=-1){
				    if(hashlist.get(i).containsKey(splited[0])){
			    		if(hashlist.get(i).get(splited[0]).length()>length){
			    			hashlist.get(i).put(ch,bm);
			    			alltable.put(ch, length);
			    			if(cixuanSign)
			    				citable.put(ch, length);
			    			else
			    				shoutable.put(ch, length);
			    			
			    		}
			    	}
			    	else{
			    		hashlist.get(i).put(ch,bm);
			    		alltable.put(ch, length);
			    		if(cixuanSign)
		    				citable.put(ch, length);
		    			else
		    				shoutable.put(ch, length);
			    	}
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
		

		String str1,str2;
		int cixuansign = 0;
		int length;
		String bianmatemp;
//		char a[] = str.toCharArray();
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
		emc=0;cemc=0;smc=0;csmc=0;qmc=0;cqmc=0;qdz=0;sdz=0;edz=0;cqdz=0;fh=0;weizhi=0;szfh=0;
		try{
			for(int i=9;i>=0;i--){
				if(str.length()<i+2)continue;
				for(int j=0;j<str.length()-(i+1);j++){
					cixuansign = 0;
					str1 = str.substring(j,j+i+2);//获取判断是否为词的str
					if(hashlist.get(i).containsKey(str1)){
						if(!quanmaci.contains(j)&&!quanmaci.contains(j+i+1)&&!ciquanmaci.contains(j)&&!ciquanmaci.contains(j+i+1)&&
								!ejianmaci.contains(j)&&!ejianmaci.contains(j+i+1)&&!ciejianmaci.contains(j)&&!ciejianmaci.contains(j+i+1)&&
								!sjianmaci.contains(j)&&!sjianmaci.contains(j+i+1)&&!cisjianmaci.contains(j)&&!cisjianmaci.contains(j+i+1))
						{
							bianmatemp =  hashlist.get(i).get(str1);	//临时放入编码，往后加 _
							length = bianmatemp.length();
							//解决两码词最简 上身体 uhufti_ uh_ut_的冲突
							if(i==0&&j<str.length()-2&&
									hashtable.containsKey(str.substring(j,j+1))&&
									hashtable.containsKey(str.substring(j+2,j+3))){
								//当为两字词时，判断下一个词编码是否比当前更短
								String strTemp = str.substring(j+1,j+3);
								int danziLength1 = hashtable.get(str.substring(j+2,j+3)).length();//截取二字词后单字
								int danziLength2 = hashtable.get(str.substring(j,j+1)).length();//截取二字词前单字
								if(hashlist.get(0).containsKey(strTemp)){
									String bianmatemp2 = hashlist.get(0).get(strTemp);
									int length2 = bianmatemp2.length();
									if(length2+danziLength2<length+danziLength1){
										bianmatemp = bianmatemp2;
										length = length2;
										str1 = strTemp;
										j += 1;
									}
								}
							}
							if(bianmatemp.substring(length-1,length).equals("_"))length -= 1;
							str2 = hashlist.get(i).get(str1).substring(length-1,length);	//获取编码最后一个字符
							if(regex.indexOf(str2)!=-1){		//判断最后一字符是否为多重
								length = length-1;
								cixuansign=1;
							}
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
			
			Collections.sort(ciquanmaci);
			Collections.sort(ciejianmaci);
			Collections.sort(cisjianmaci);
			
			Collections.sort(ciquanmacione);
			Collections.sort(ciejianmacione);
			Collections.sort(cisjianmacione);
			
			Collections.sort(ciquanmacitwo);
			Collections.sort(ciejianmacitwo);
			Collections.sort(cisjianmacitwo);
		}catch(Exception e){System.out.print("000");}
	}
	public static String weizhistr = "";
	public void compalllength(){
		String str = QQZaiwenListener.wenbenstr;
//		String regex = "234567890";
		String fu = "@#$%^&*,./;'\\\"——-+=_·| ";
		weizhistr = "";
		char c[] = str.toCharArray();
		String bianmatemp;
		System.out.println();
		dingshowstr = "";
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
					else if((c[i]>='a'&&c[i]<='z')||(c[i]>='A'&&c[i]<='Z')||(c[i]>='0'&&c[i]<='9')||fu.indexOf(String.valueOf(c[i]))!=-1){
						bianmatemp = String.valueOf(c[i]);
						szfh++;
					}
					else {
						bianmatemp = String.valueOf(c[i]);
						weizhistr += bianmatemp;
						weizhi++;
					}
				}
				bianma.put(i, bianmatemp);
			}
		for(int i=0;i<c.length;i++){
			if(Tips.bianma.containsKey(i)){
				showstr.append(Tips.bianma.get(i));
			}
		}
		dingshowstr = RegexText.biaoding(showstr.toString());
		dingalllength = dingshowstr.length();
		alllength = showstr.length();
		System.out.println("理论:"+alllength);
		System.out.println("标定:"+dingalllength);
		dengji = (50*4*qmc+
				55*4*smc
				+60*3*emc
				+65*5*cqmc
				+70*4*csmc
				+75*3*cemc
				+80*2*edz
				+85*3*sdz
				+90*4*qdz
				+95*5*cqdz
				+100*weizhi
				+45*2*fh
				+szfh)
				/alllength;
	}
}
